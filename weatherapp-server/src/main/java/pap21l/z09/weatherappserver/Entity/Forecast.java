package pap21l.z09.weatherappserver.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pap21l.z09.weatherappserver.Utility.Parser;
import pap21l.z09.weatherappserver.Utility.ForecastData;
import pap21l.z09.weatherappserver.Utility.AdditionalForecastData;
import pap21l.z09.weatherappserver.Utility.Chunk;

import pap21l.z09.weatherappserver.Controller.ForecastController;

@Entity(name = "FORECAST")
public final class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private final String city;
    private String lastUpdate;
    @ElementCollection
    private List<Double> temp = new ArrayList<Double>();
    @ElementCollection
    private List<Double> windSpeed = new ArrayList<Double>();
    @ElementCollection
    private List<String> weather = new ArrayList<String>();
    @ElementCollection
    private List<String> icon = new ArrayList<String>();

    public Forecast() {
        this.city = "";
    }

    public Forecast(String city, String data) {
        this.city = city;
        this.setData(data);
    }

    public String getCity() {
        return city;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public List<Double> getTemp() {
        return temp;
    }

    public List<Double> getWindSpeed() {
        return windSpeed;
    }

    public List<String> getWeather() {
        return weather;
    }

    public List<String> getIcon() {
        return icon;
    }

    private void setData(String data) {
        ForecastData forecastData = Parser.parse(data);
        this.lastUpdate = forecastData.list[0].dt_txt.substring(0, 10);
        int updateHour = Integer.parseInt(
            forecastData.list[0].dt_txt.substring(11, 13));
        AdditionalForecastData af;
        if (updateHour != 0) {
            af = ForecastController.getAdditionalData(forecastData, updateHour);
            for (int i = 0; i < updateHour; i = i + 3) {
                this.temp.add(af.hourly[i].temp);
                this.windSpeed.add(af.hourly[i].wind_speed);
                this.weather.add(af.hourly[i].weather[0].description);
                this.icon.add(af.hourly[i].weather[0].icon);
            }
        }

        for (Chunk chunk: forecastData.list) {
            if (this.temp.size() < 40) {
                this.temp.add(chunk.main.temp);
                this.windSpeed.add(chunk.wind.speed);
                this.weather.add(chunk.weather[0].description);
                this.icon.add(chunk.weather[0].icon);
            }
        }
    }

    public void updateData(String data) {
        this.temp.clear();
        this.windSpeed.clear();
        this.weather.clear();
        this.icon.clear();
        this.setData(data);
    }

}
