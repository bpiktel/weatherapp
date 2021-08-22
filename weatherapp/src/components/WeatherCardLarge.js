import React from 'react';
import PropTypes from 'prop-types';
import Chart from 'react-apexcharts';

import './WeatherCardLarge.css';

const WeatherCardLarge = (props) => {
  const {weather, icons, day} = props;
 
  var temp = [];
  var wind = [];

  for (var idx = 0; idx < props.temp.length; idx++) {
    temp.push(parseFloat(props.temp[idx].toFixed(2)));
    wind.push(parseFloat(props.wind[idx].toFixed(2)));
  }

  const series = [
    {
      name: "temperature [\u00B0C]",
      data: temp
    },
    {
      name: "wind [m/s]",
      data: wind
    }
  ];

  var hours = [];
  for (var i = 0; i < 8; i++) {
    hours.push(i*3+':00')
  }

  const options = {
    chart: {
      height: 150,
      type: 'line',
      stacked: false,
      zoom: {
        enabled: false
      },
      dropShadow: {
        enabled: true,
        color: '#000',
        top: 18,
        left: 7,
        blur: 10,
        opacity: 0.2
      },
      toolbar: {
        show: false
      },
    },
    grid: {
      padding: {
        left: 20,
        right: 20,
      },
    },
    colors: ['#FF1654', '#247BA0'],
    dataLabels: {
      enabled: true,
    },
    stroke: {
      width: 4,
      curve: 'straight'
    },
    fill: {
      type: 'solid',
    },
    xaxis: {
      categories: hours
    },
    yaxis: [
      {
        axisTicks: {
          show: false
        },
      },
      {
        opposite: true,
        axisTicks: {
          show: false
        },
      }
    ],
  };

  return (
    <div className="largeCard">
      <div className="cardHeader">
      <img className="backIcon" src="./back.png" alt="back button" onClick={props.handleBack}/>
      </div>
      <h1 className="largeDay">
        {day}
      </h1>
      <div className="weatherTable">
        <table className="largeTable" cellSpacing="0">
          <thead>
            <tr className="hourRow">
            {hours.map((hour, idx) =>
              {return <td key={"lHour"+idx}>{hour}</td>})}
            </tr>
          </thead>
          <tbody>
            <tr>
              {icons.map((icon, idx) =>
              {return <td  key={"lIcon"+idx}><img className="weatherIcon" src={"http://openweathermap.org/img/wn/" + icon + "@2x.png"}/></td>})}
            </tr>
            <tr>
              {weather.map((w, idx) => 
                {return <td key={"lWeather"+idx}>{w}</td>})}
            </tr>
          </tbody>
        </table>
      </div>
      <div className="temp">
        <Chart className="chart" options={options} series={series} type='line' height={250}/>
      </div>
    </div>
  );
}

WeatherCardLarge.propTypes = {
  temp: PropTypes.array,
  weather: PropTypes.array,
  wind: PropTypes.array,
  icons: PropTypes.array,
  day: PropTypes.string,
  handleBack: PropTypes.func
};

export default WeatherCardLarge;