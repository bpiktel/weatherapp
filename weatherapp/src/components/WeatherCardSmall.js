import React from 'react';
import PropTypes from 'prop-types';

import './WeatherCardSmall.css';

const WeatherCardSmall = (props) => {
  const {temp, weather, icons, day} = props;
 
  var hours = [];
  for (var i = 0; i < 8; i++) {
    hours.push(i*3+':00')
  }

  var summaryIdx = 4;

  return (
    <div onClick={() => props.handle(props.index)} className="smallCard">
      <h2 className="day">{day}</h2>
      <div className="summary">
        <img className="icon" src={"http://openweathermap.org/img/wn/" + icons[summaryIdx] + "@2x.png"} alt="Weather icon"/>
        <h1 className="temp">{temp[summaryIdx].toFixed(1)+'\u00B0C'}</h1>
        <h3 className="condition">{weather[summaryIdx]}</h3>
      </div>
      <div className="tempTable">
        <table>
          <thead>
            <tr>
            {hours.map((hour, idx) =>
              {
                if (idx % 2 === 1)
                  return <td key={"sHour"+idx}>{hour}</td>
              })}
            </tr>
          </thead>
          <tbody>
            <tr>
              {icons.map((icon, idx) =>
              {
                if (idx % 2 === 1)
                  return <td key={"sImage"+idx}><img className="smallIcon" src={"http://openweathermap.org/img/wn/" + icon + "@2x.png"} alt="Weather icon"/></td>
              })}
            </tr>
            <tr>
              {temp.map((temp, idx) => 
                {
                  if (idx % 2 === 1)
                    return <td key={"sTemp"+idx}>{temp.toFixed(1)+'\u00B0C'}</td>
                })}
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

WeatherCardSmall.propTypes = {
  temp: PropTypes.array,
  weather: PropTypes.array,
  icons: PropTypes.array,
  day: PropTypes.string,
  index: PropTypes.number,
  handle: PropTypes.func
};

export default WeatherCardSmall;