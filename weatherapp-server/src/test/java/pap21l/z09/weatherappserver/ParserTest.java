package pap21l.z09.weatherappserver;


import org.junit.jupiter.api.Test;

import pap21l.z09.weatherappserver.Utility.ForecastData;
import pap21l.z09.weatherappserver.Utility.Parser;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ParserTest {

    @Test
    public void isTestResponseFileExistsTest() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
			assertEquals(false, jsonString.isEmpty());
		} catch (IOException e) {
            Assertions.fail("No test file");
        }
    }

    @Test
    public void codParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.cod,"200");
        } catch (IOException e) {}
        
    }

    @Test
    public void msgParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.msg,0);
        } catch (IOException e) {}
    }

    @Test
    public void chunkNumberParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.list.length,40);
        } catch (IOException e) {}
    }

    @Test
    public void tempParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.list[0].main.temp,14);
            assertEquals(forecastData.list[1].main.temp,13.91);
            assertEquals(forecastData.list[5].main.temp,5.58);
            assertEquals(forecastData.list[10].main.temp,11.8);
            assertEquals(forecastData.list[39].main.temp,8.51);
        } catch (IOException e) {}
    }

    @Test
    public void windSpeedParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.list[0].wind.speed,4.18);
            assertEquals(forecastData.list[2].wind.speed,2.99);
            assertEquals(forecastData.list[8].wind.speed,2.15);
            assertEquals(forecastData.list[16].wind.speed,6.06);
            assertEquals(forecastData.list[30].wind.speed,2.38);
        } catch (IOException e) {}
    }

    @Test
    public void weatherNumberParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.list[0].weather.length,1);
            assertEquals(forecastData.list[4].weather.length,1);
            assertEquals(forecastData.list[7].weather.length,1);
            assertEquals(forecastData.list[26].weather.length,1);
            assertEquals(forecastData.list[34].weather.length,1);
        } catch (IOException e) {}
    }

    @Test
    public void weatherDescriptionParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.list[0].weather[0].description,"clear sky");
            assertEquals(forecastData.list[3].weather[0].description,"few clouds");
            assertEquals(forecastData.list[12].weather[0].description,"overcast clouds");
            assertEquals(forecastData.list[14].weather[0].description,"light rain");
            assertEquals(forecastData.list[35].weather[0].description,"moderate rain");
        } catch (IOException e) {}
    }

    @Test
    public void cityNameParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestResponse.json")), StandardCharsets.UTF_8);
            ForecastData forecastData = Parser.parse(jsonString);
            assertEquals(forecastData.city.name,"Warsaw");
        } catch (IOException e) {}
    }
}