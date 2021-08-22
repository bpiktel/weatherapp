package pap21l.z09.weatherappserver;


import org.junit.jupiter.api.Test;

import pap21l.z09.weatherappserver.Entity.Forecast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ForecastTest {

    @Test
    public void forecastCityNameTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getCity(),"Warszawa");
        } catch (IOException e) {}
    }

    @Test
    public void forecastLastUpdateTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getLastUpdate(),"2021-04-28");
        } catch (IOException e) {}
    }

    @Test
    public void forecastTempNumberTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getTemp().size(),40);
        } catch (IOException e) {}
    }

    @Test
    public void forecastWindSpeedNumberTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getWindSpeed().size(),40);
        } catch (IOException e) {}
    }

    @Test
    public void forecastWeatherDescrioptionNumberTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getWeather().size(),40);
        } catch (IOException e) {}
    }

    @Test
    public void forecastIconNumberTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getIcon().size(),40);
        } catch (IOException e) {}
    }

    @Test
    public void forecastWindSpeedSetTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getWindSpeed().get(0),4.18);
            assertEquals(forecast.getWindSpeed().get(3),3.23);
            assertEquals(forecast.getWindSpeed().get(24),1.43);
            assertEquals(forecast.getWindSpeed().get(33),3.91);
            assertEquals(forecast.getWindSpeed().get(36),7.95);
        } catch (IOException e) {}
    }

    @Test
    public void forecastTempSetTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getTemp().get(0),14);
            assertEquals(forecast.getTemp().get(6),9.22);
            assertEquals(forecast.getTemp().get(9),13.95);
            assertEquals(forecast.getTemp().get(23),11.9);
            assertEquals(forecast.getTemp().get(26),13.01);
        } catch (IOException e) {}
    }

    @Test
    public void forecastWeatherDescrioptionSetTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getWeather().get(0),"clear sky");
            assertEquals(forecast.getWeather().get(7),"overcast clouds");
            assertEquals(forecast.getWeather().get(17),"light rain");
            assertEquals(forecast.getWeather().get(19),"overcast clouds");
            assertEquals(forecast.getWeather().get(29),"overcast clouds");
        } catch (IOException e) {}
    }

    @Test
    public void forecastIconSetTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            Forecast forecast = new Forecast("Warszawa",jsonString);
            assertEquals(forecast.getIcon().get(0),"01d");
            assertEquals(forecast.getIcon().get(7),"04d");
            assertEquals(forecast.getIcon().get(17),"10d");
            assertEquals(forecast.getIcon().get(19),"04n");
            assertEquals(forecast.getIcon().get(29),"04n");
        } catch (IOException e) {}
    }

}