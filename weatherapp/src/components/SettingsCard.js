import React from 'react';
import PropTypes from 'prop-types';
import Dropdown from 'react-dropdown';

import 'react-dropdown/style.css';
import './SettingsCard.css';

class SettingsCard extends React.Component {
  constructor(props) {
    super(props);

    var initProfiles = [{name: "default", city: "Warszawa"}];

    this.state = {
      selectedProfile: props.profile,
      profiles: initProfiles,
      profileNameList: [initProfiles[0].name],
      currentEdit: 'overview',
      nameInput: '',
      cityInput: '',
      notification: false,
      notificationMessage: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleCreate = this.handleCreate.bind(this);
    this.handleDelete = this.handleDelete.bind(this);
    this.handleUpdate = this.handleUpdate.bind(this);
    this.handleConfirm = this.handleConfirm.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
  }

  render() {
    const options = this.state.profileNameList;
    const defaultOption = this.state.selectedProfile.name;

    return (
      <div>
        <div className="settingsCard">
          <div className="profileColumn">
            <h1>Select user profile</h1>
            <div className="profileList">
              <div className="settingsText">Profile name:</div>
              <Dropdown options={options} onChange={this.handleChange} value={defaultOption}/>
            </div>
            <div className="profileList">
              <div className="settingsText">City:</div>
              <input className="cityInput" value={this.state.selectedProfile.city} disabled={true}/>
            </div>
            <button className="profileActionButton" onClick={this.handleSubmit}>Apply profile</button>
          </div>
          {this.renderEditPanel()}
        </div>
        {this.renderNotification()}
      </div>
    )
  }

  renderEditPanel() {
    if (this.state.currentEdit === 'overview') {
      return (
        <div className="profileColumn">
          <h1>Edit user profiles</h1>
            <button className="profileActionButton" onClick={this.handleCreate}>Create new profile</button>
            <button className="profileActionButton" onClick={this.handleDelete}>Remove selected profile</button>
            <button className="profileActionButton" onClick={this.handleUpdate}>Update selected profile</button>
        </div>
      )
    }
    if (this.state.currentEdit === 'create') {
      return (
        <div className="profileColumn">
          <h1>Create new profile</h1>
          <div className="profileList">
            <div className="settingsText">Profile name:</div>
            <input className="editableInput" onChange={event => this.updateNameInput(event)}/>
          </div>
          <div className="profileList">
            <div className="settingsText">City:</div>
            <input className="editableInput" onChange={event => this.updateCityInput(event)}/>
          </div>
          <div>
            <button className="profileActionButton" onClick={this.handleConfirm}>Confirm</button>
            <button className="profileActionButton" onClick={this.handleCancel}>Cancel</button>
          </div>
        </div>
      )
    }
    if (this.state.currentEdit === 'remove') {
      return (
        <div className="profileColumn">
          <h2>Remove currently selected profile?</h2>
          <div className="profileList">
            <div className="settingsText">Profile name:</div>
            <input className="editableInput" value={this.state.selectedProfile.name} disabled={true}/>
          </div>
          <div className="profileList">
            <div className="settingsText">City:</div>
            <input className="editableInput" value={this.state.selectedProfile.city} disabled={true}/>
          </div>
          <div>
            <button className="profileActionButton" onClick={this.handleConfirm}
              disabled={this.state.selectedProfile.name === 'default'}>Confirm</button>
            <button className="profileActionButton" onClick={this.handleCancel}>Cancel</button>
          </div>
        </div>
      )
    }
    if (this.state.currentEdit === 'update') {
      return (
        <div className="profileColumn">
          <h2>Update currently selected profile?</h2>
          <div className="profileList">
            <div className="settingsText">Profile name:</div>
            <input className="editableInput" value={this.state.selectedProfile.name} disabled={true}/>
          </div>
          <div className="profileList">
            <div className="settingsText">City:</div>
            <input className="editableInput" onChange={event => this.updateCityInput(event)}/>
          </div>
          <div>
            <button className="profileActionButton" onClick={this.handleConfirm}
              disabled={this.state.selectedProfile.name === 'default'}>Confirm</button>
            <button className="profileActionButton" onClick={this.handleCancel}>Cancel</button>
          </div>
      </div>
      )
    }
  }

  renderNotification() {
    return (
      <div className="notification"
        style={this.state.notification ? {'opacity': 1} :
          {'opacity': 0, 'transition': '1s opacity ease', 'willChange': 'opacity'}}>
        <h2>{this.state.notificationMessage}</h2>
      </div>
    )
  }

  handleChange(event) {
    this.setState({selectedProfile: this.getProfileByName(event.value)});
  }

  handleSubmit(notif=true) {
    this.props.onProfileChange(this.state.selectedProfile);
    if (notif) {
      this.showNotification('Profile changed');
    }
  }

  handleCreate() {
    this.setState({currentEdit: 'create'});
  }

  handleDelete() {
    this.setState({currentEdit: 'remove'});
  }

  handleUpdate() {
    this.setState({currentEdit: 'update'});
  }

  handleConfirm() {
    if (this.state.currentEdit === 'create') {
      this.sendCreateProfile(this.state.nameInput, this.state.cityInput);
    }
    if (this.state.currentEdit === 'remove') {
      this.sendRemoveProfile(this.state.selectedProfile.name);
    }
    if (this.state.currentEdit === 'update') {
      this.sendUpdateProfile(this.state.selectedProfile.name, this.state.cityInput);
    }
  }

  handleCancel() {
    this.setState({
      currentEdit: 'overview',
      nameInput: '',
      cityInput: ''
    });
  }

  updateNameInput(event) {
    this.setState({nameInput: event.target.value});
  }

  updateCityInput(event) {
    this.setState({cityInput: event.target.value});
  }

  showNotification(message) {
    this.setState({
      notification: true,
      notificationMessage: message
    });
    this.timeout = setTimeout(() => {
      this.setState({
        notification: false,
      });
      this.timeout = null;
    }, 2000);
  }

  componentDidMount() {
    this.getProfiles();
  }
  
  componentWillUnmount() {
    clearTimeout(this.timeout);
  }

  getProfiles() {
    fetch('http://localhost:8080/getAllProfiles')
      .then((response) => response.json())
      .then((responseJson) => {
        var profileList = [{name: 'default', city: 'Warszawa'}];
        profileList = profileList.concat(responseJson)
        this.setState({
          profiles: profileList,
          profileNameList: this.getProfileNameList(profileList)
        });
      })
  }

  sendCreateProfile(name, city) {
    if (this.state.cityInput === '' || this.state.nameInput === '') {
      this.showNotification("Fields cannot be empty");
      return;
    }
    if (this.getProfileByName(name).name === "default") {
      fetch('http://localhost:8080/createProfile?name=' + name + '&city=' + city)
      .then(response => {
        if (response.ok) {
          this.showNotification("Success");
          this.getProfiles();
          this.handleCancel();
        } else {
          this.showNotification("Failed to create profile");
        }
        });
    }
    else {
      this.showNotification("Profile with this name already exists");
    }
  }

  sendRemoveProfile(name) {
    if (this.getProfileByName(name).name === "default") {
      this.showNotification("Can't delete profile");
    }
    else {
      fetch('http://localhost:8080/deleteProfile?name=' + name)
      .then(response => {
        if (response.ok) {
          this.showNotification("Success");
          this.setState({
            selectedProfile: this.getProfileByName("default")
          });
          this.handleSubmit(false);
          this.getProfiles();
          this.handleCancel();
        } else {
          this.showNotification("Failed to delete profile");
        }
        });
    }
  }

  sendUpdateProfile(name, city) {
    if (this.state.cityInput === '') {
      this.showNotification("Fields cannot be empty");
      return;
    }
    fetch('http://localhost:8080/updateProfile?name=' + name + '&city=' + city)
    .then(response => {
      if (response.ok) {
        this.showNotification("Success");
        var idx = this.getProfileIndexByName(name);
        var modProfiles = this.state.profiles;
        modProfiles[idx].city = city;
        this.setState({profiles: modProfiles}, 
          () => this.setState({
            selectedProfile: this.state.profiles[idx]
          }));
        this.handleSubmit(false);
        this.handleCancel();
      } else {
        this.showNotification("Failed to update profile");
      }
      });
  }

  getProfileNameList(profiles) {
    var pnames = [];
    profiles.forEach(profile => {
      pnames.push(profile.name);
    });
    return pnames;
  }

  getProfileByName(name) {
    var p = {name: "default", city: "Warszawa"}
    this.state.profiles.forEach(profile => {
      if (profile.name === name)
        p = profile;
    });
    return p;
  }

  getProfileIndexByName(name) {
    var idx = 0;
    this.state.profiles.forEach((profile, i) => {
      if (profile.name === name)
        idx = i;
    });
    return idx;
  }
}

SettingsCard.propTypes = {
  profile: PropTypes.object,
  onProfileChange: PropTypes.func
};

export default SettingsCard;