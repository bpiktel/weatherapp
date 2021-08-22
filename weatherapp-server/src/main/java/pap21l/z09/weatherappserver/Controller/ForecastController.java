package pap21l.z09.weatherappserver.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pap21l.z09.weatherappserver.Entity.Forecast;
import pap21l.z09.weatherappserver.Repository.ForecastRepository;
import pap21l.z09.weatherappserver.Utility.Parser;
import pap21l.z09.weatherappserver.Utility.AdditionalForecastData;
import pap21l.z09.weatherappserver.Utility.ForecastData;

@RestController
public class ForecastController {

    @Autowired
    ForecastRepository forecastRepository;

    @CrossOrigin(origins = "http://localhost:3000")

    @Transactional
    @GetMapping("/forecast")
    public Forecast forecast(
        @RequestParam(value = "city", defaultValue = "Warszawa") String city) {

        Forecast f;
        if (forecastRepository.existsByCity(city)) {
            f = forecastRepository.findByCity(city);
            LocalDate dbLastUpdate = LocalDate.parse(f.getLastUpdate());
            Period period = dbLastUpdate.until(LocalDate.now());
            if (period.getDays() > 0 || period.getMonths() != 0
                || period.getYears() != 0) {

                String apiResponse = getForecastData(city);
                f.updateData(apiResponse);
                forecastRepository.save(f);
            }
        } else {
            String apiResponse = getForecastData(city);
            f = new Forecast(city, apiResponse);
            forecastRepository.save(f);
        }
        return f;
    }

    String getForecastData(String city) {
        String apiKey = "b036274fe27bce6e6abaff68eab3201c";
        String r = "";

        try {
            String service =
                "http://api.openweathermap.org/data/2.5/forecast?q=";
            String params = "&units=metric&appid=" + apiKey;
            String urlStr = service + URLEncoder.encode(city, "UTF-8") + params;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();

            r = content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public String getAdditionalData(double lat, double lon, int time) {
        String apiKey = "9cdf8ecc9aa971007e4e0d40db26467d";
        String r = "";

        try {
            String service =
                "https://api.openweathermap.org"
                + "/data/2.5/onecall/timemachine?lat=";
            String params = lat + "&lon=" + lon
                + "&dt=" + time + "&units=metric&appid=" + apiKey;
            String urlStr = service + params;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();

            r = content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static AdditionalForecastData getAdditionalData(
        ForecastData fd, int updateHour) {
        ForecastController fs = new ForecastController();
        int midnightDt = fd.list[0].dt - 3600 * updateHour;
        String data = fs.getAdditionalData(
            fd.city.coord.lat, fd.city.coord.lon, midnightDt);
        AdditionalForecastData af = Parser.additionalParse(data);
        return af;
    }
}
