import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ro.mta.se.lab.FileParser;
import ro.mta.se.lab.Logger;
import ro.mta.se.lab.WeatherForecast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoggerTest {

    @Mock
    WeatherForecast weatherForecast = mock(WeatherForecast.class);

    @Test
    void log() {
        when(weatherForecast.getCityName()).thenReturn("Campina");
        when(weatherForecast.getTemperature(null)).thenReturn((float) 173.2);
        when(weatherForecast.getWindSpeed()).thenReturn((float) 45);

        Logger logger = new Logger("logFile");
        FileParser fileParser = new FileParser();
        String testVal;

        logger.log(weatherForecast, null);
        logger.close();

        testVal = fileParser.getFileContent();

        assertEquals(testVal.split(" ")[3], "Campina");
        assertEquals(testVal.split(" ")[5], "173.2");
        assertEquals(testVal.split(" ")[7], "45.0\n");
    }
}