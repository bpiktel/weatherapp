package pap21l.z09.weatherappserver.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {

    public static ForecastData parse(String data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ForecastData forecastData = gson.fromJson(data, ForecastData.class);
        return forecastData;
    }

    public static AdditionalForecastData additionalParse(String data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        AdditionalForecastData additionalForecast = gson.fromJson(
            data, AdditionalForecastData.class);
        return additionalForecast;
    }
}
