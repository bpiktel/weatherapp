package pap21l.z09.weatherappserver.Utility;

public class AdditionalForecastData {

    public double lat;
    public double lon;
    public String timezone;
    public int timezone_offset;
    public DetailedWeather current;
    public HourlyWeather[] hourly;

    AdditionalForecastData() { }
}
