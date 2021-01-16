import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ro.mta.se.lab.WeatherData;
import ro.mta.se.lab.WeatherForecast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WeatherForecastTest {

    List<String> goodCityList = new ArrayList<>();
    List<String> badCityList = new ArrayList<>();
    private WeatherForecast weatherForecast;

    @BeforeEach
    void init(){
        weatherForecast = new WeatherForecast();

        goodCityList.add("Bucharest");
        goodCityList.add("Moscow");
        goodCityList.add("Pitesti");

        badCityList.add("Bucarest");
        badCityList.add("Moscoow");
        badCityList.add("Pitesc");
    }

    @Test
    void searchCity()  {
        byte[] data = new byte[0];

        for(String goodCity: goodCityList) {
            weatherForecast.searchCity(goodCity);
        }
        for(String badCity: badCityList){
            weatherForecast.searchCity(badCity);
        }

        //this string should be modified because the weather always changes :D
        String correctAnswer= "[INFO]: 2021-01-12 oraș: Bucharest temperatură: 1.0 viteza vântului:5.14\n" +
                "[INFO]: 2021-01-12 oraș: Moscow temperatură: -10.0 viteza vântului:5.0\n" +
                "[INFO]: 2021-01-12 oraș: Pitesti temperatură: 2.0 viteza vântului:2.92\n" +
                "[INFO]: 2021-01-12 oraș: Bucarest temperatură: 1.0 viteza vântului:5.14\n" +
                "[ERROR]: 2021-01-12 Response code was 404\n" +
                "[ERROR]: 2021-01-12 Response code was 404\n";

        File file = new File("./logFile/logFile.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
        }
        catch (IOException e){
            assert false;
        }

        String str = null;
        str = new String(data, StandardCharsets.UTF_8);

        assertEquals(correctAnswer, str);

    }

    @Mock
    WeatherData weatherData = mock(WeatherData.class);

    @Test
    void getWindDirection() {
        when(weatherData.getWindDirection()).thenReturn(12);
        Assertions.assertEquals(weatherForecast.getWindDirection(weatherData), "N-E");
    }

    @Test
    void getTemperature() {
        when(weatherData.getTemperature()).thenReturn((float) 276.5);
        Assertions.assertEquals(weatherForecast.getTemperature(weatherData), 3.5);
    }

    @Test
    void getDescription(){
        when(weatherData.getDescription()).thenReturn("Rainy day");
        Assertions.assertEquals(weatherForecast.getLongDescription(weatherData), "Rainy day");
    }
}