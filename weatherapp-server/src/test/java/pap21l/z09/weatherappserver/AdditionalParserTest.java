package pap21l.z09.weatherappserver;


import org.junit.jupiter.api.Test;

import pap21l.z09.weatherappserver.Utility.AdditionalForecastData;
import pap21l.z09.weatherappserver.Utility.Parser;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class AdditionalParserTest {

	@Test
    public void isTestAdditionalResponseFileExistsTest() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
			assertEquals(false, jsonString.isEmpty());
		} catch (IOException e) {
            Assertions.fail("No test file");
        }
    }

	@Test
	public void latAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
            assertEquals(forecastData.lat,52.2298);
        } catch (IOException e) {}
        
    }

	@Test
	public void lonAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
            assertEquals(forecastData.lon,21.0118);
        } catch (IOException e) {}
	}

	@Test
    public void hourlyAdditionalNumberParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
            assertEquals(forecastData.hourly.length,17);
        } catch (IOException e) {}
	}

	@Test
	public void tempAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
			assertEquals(forecastData.hourly[0].temp,15.7);
            assertEquals(forecastData.hourly[1].temp,15.36);
            assertEquals(forecastData.hourly[2].temp,14.37);
            assertEquals(forecastData.hourly[4].temp,14.62);
            assertEquals(forecastData.hourly[10].temp,25.03);
        } catch (IOException e) {}
	}

	@Test
	public void wind_speedAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
			assertEquals(forecastData.hourly[0].wind_speed,5.14);
            assertEquals(forecastData.hourly[1].wind_speed,5.66);
            assertEquals(forecastData.hourly[2].wind_speed,4.63);
            assertEquals(forecastData.hourly[4].wind_speed,5.14);
            assertEquals(forecastData.hourly[10].wind_speed,10.29);
        } catch (IOException e) {}
	}

	@Test
	public void weatherNumberAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
			assertEquals(forecastData.hourly[0].weather.length,1);
            assertEquals(forecastData.hourly[2].weather.length,1);
            assertEquals(forecastData.hourly[5].weather.length,1);
            assertEquals(forecastData.hourly[7].weather.length,1);
            assertEquals(forecastData.hourly[12].weather.length,1);
        } catch (IOException e) {}
	}

	@Test
	public void weatherDescriptionAdditionalParseTest() {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("src/test/java/pap21l/z09/weatherappserver/TestAdditionalResponse.json")), StandardCharsets.UTF_8);
            AdditionalForecastData forecastData = Parser.additionalParse(jsonString);
			assertEquals(forecastData.hourly[0].weather[0].description,"clear sky");
            assertEquals(forecastData.hourly[9].weather[0].description,"clear sky");
            assertEquals(forecastData.hourly[14].weather[0].description,"clear sky");
            assertEquals(forecastData.hourly[15].weather[0].description,"clear sky");
            assertEquals(forecastData.hourly[16].weather[0].description,"clear sky");
        } catch (IOException e) {}
	}
}