import React from 'react';

import './App.css';
import WeatherCardSmall from './components/WeatherCardSmall';
import WeatherCardLarge from './components/WeatherCardLarge';
import SettingsCard from './components/SettingsCard';


class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      profile: {name: 'default', city: 'Warszawa'},
      temp: new Array(40).fill(0.0),
      wind: new Array(40).fill(0.0),
      weather: new Array(40).fill(""),
      icons: new Array(40).fill('01d'),
      display: 'small',
      error: false,
      loading: false
    };

    this.handleSettings = this.handleSettings.bind(this);
    this.handleBack = this.handleBack.bind(this);
    this.handleProfileChange = this.handleProfileChange.bind(this);
    this.handleLargeCardClick = this.handleLargeCardClick.bind(this);
    this.handleLargeCardBackClick = this.handleLargeCardBackClick.bind(this);
  }

  render() {
    return (
      <div className="app">
        <div className="header">
          {this.renderButton()}
        </div>
        {this.renderMainView()}
      </div>
    );
  }

  renderMainView() {
    if (this.state.display === 'small' && !this.state.error && !this.state.loading) {
      return (
        <div>
          <div className="title">
            <div>{this.state.profile.city}</div>
          </div>
  
          {this.renderCards()}
        </div>
    )}
    if (this.state.display === 'small' && this.state.error) {
      return (
        <div className="errorPanel">
          <h2>
            Couldn&apos;t get weather data, check if requested city is correct.
          </h2>
        </div>
      )
    }
    if (this.state.display === 'small' && !this.state.error && this.state.loading) {
      return (
        <div className="loader"></div>
      )
    }
    if (this.state.display === 'settings') {
      return (
        <SettingsCard profile={this.state.profile} onProfileChange={this.handleProfileChange}/>
      )
    }
    if (this.state.display === 'large') {
      var i = this.state.largeIndex;
      const weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      const dayIdx = new Date().getDay()
      return (
        <div>
          <div className="title">
            <div>{this.state.profile.city}</div>
          </div>

          <WeatherCardLarge 
            temp={this.state.temp.slice(i*8, (i+1)*8)}
            weather={this.state.weather.slice(i*8, (i+1)*8)}
            wind={this.state.wind.slice(i*8, (i+1)*8)}
            icons={this.state.icons.slice(i*8, (i+1)*8)}
            day={weekday[(dayIdx+i)%7]}
            handleBack={this.handleLargeCardBackClick}
          />
        </div>
      )
    }
  }

  renderButton() {
    if (this.state.display === 'small' || this.state.display === 'large') {
      return (
        <img className="settingsIcon" src="./settings.png" alt="setting button" onClick={this.handleSettings}/>
      )}

    if (this.state.display === 'settings') {
      return (
        <img className="settingsIcon" src="./back.png" alt="back button" onClick={this.handleBack}/>
      )}
  }

  handleSettings() {
    this.setState({display: 'settings'});
  }

  handleBack() {
    this.setState({display: 'small'});
  }

  handleProfileChange(newProfile) {
    this.setState({profile: newProfile},
      () => {this.getForecast();});
  }

  handleLargeCardClick(index) {
    this.setState({display: 'large', largeIndex: index});
  }

  handleLargeCardBackClick() {
    this.setState({display: 'small'});
  }

  renderCards() {
    const weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const dayIdx = new Date().getDay();

    var cards = [];

    for (var i = 0; i < 5; i++)
    {
      cards.push(<WeatherCardSmall 
        temp={this.state.temp.slice(i*8, (i+1)*8)}
        weather={this.state.weather.slice(i*8, (i+1)*8)}
        icons={this.state.icons.slice(i*8, (i+1)*8)}
        day={weekday[(dayIdx+i)%7]}
        index={i}
        handle={this.handleLargeCardClick}
        key={"sCard"+i}
        />);
    }
    return (
      <div className="weatherList">
        {cards}
      </div>
    )
  }

  componentDidMount() {
    this.getForecast();
  }

  getForecast() {
    this.setState({loading: true});
    fetch('http://localhost:8080/forecast?city=' + this.state.profile.city)
      .then((response) => response.json())
      .then((responseJson) => {
        console.log(responseJson);
        var fixedIcons = [];
        responseJson.icon.forEach((ico, idx) => {
          fixedIcons.push(ico.slice(0, -1) + ((idx % 8 < 2 || idx % 8 > 6) ? 'n' : 'd'));
        })
        this.setState({
          temp: responseJson.temp,
          wind: responseJson.windSpeed,
          weather: responseJson.weather,
          icons: fixedIcons,
          error: false,
          loading: false
        });
        })
      .catch((error) => {
        console.error(error);
        this.setState({
          error: true,
          loading: false
        });
      });
  }
  
}

export default App;
