package ro.mta.se.lab;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast {

        private static final String APIKEY = "YOUR_API_KEY";
    private static final String getRequest = "https://api.openweathermap.org/data/2.5/weather?q=";
    private WeatherData weatherData;
    private final Logger logger;
    public String error;

    public WeatherForecast(){
        logger = new Logger("logFile");
        error = "";
    }

    public void searchCity(String cityName)  {
        String finalRequest, inLine;
        StringBuffer response = null;
        int responseCode;
        BufferedReader inBuffer;
        URL url;
        HttpURLConnection connection;

        finalRequest = getRequest + cityName + "&appid=" + APIKEY;

        try {
            url = new URL(finalRequest);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                inBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                response = new StringBuffer();

                while ((inLine = inBuffer.readLine()) != null) {
                    response.append(inLine);
                }

                inBuffer.close();
                this.error = "";
            }
            else{
                logger.log(null, "Response code was " + responseCode);
                this.error=" Nu s-a putut gasi orasul.";
                return;
            }
        }
        catch (IOException e){
            logger.log(null, "Exception: " + e.getMessage());
            this.error = "A aparut o eroare. Verifica fisierul de log";
            return;
        }

        processJson(response.toString(), cityName);
        logger.log(this, null);

    }

    private void processJson(String response, String cityName){
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String mainDescription, shortDescription, iconId;
        float temp, windSpeed;
        int windDirection, pressure;

        JsonElement jsonElement = jsonObject.getAsJsonArray("weather").get(0);
        JsonObject windObj = jsonObject.getAsJsonObject("wind");
        JsonObject mainObj = jsonObject.getAsJsonObject("main");
        JsonObject weatherObj = jsonElement.getAsJsonObject();

        mainDescription = weatherObj.get("description").getAsString();
        shortDescription = weatherObj.get("main").getAsString();
        temp = mainObj.get("temp").getAsInt();
        pressure = mainObj.get("pressure").getAsInt();
        windSpeed = windObj.get("speed").getAsFloat();
        windDirection = windObj.get("deg").getAsInt();
        iconId = weatherObj.get("icon").getAsString();

        weatherData = new WeatherData(windDirection, pressure, temp, windSpeed, mainDescription, shortDescription, iconId, cityName);
    }

    public String getWindDirection(WeatherData wd){
        int angle;
        if(wd == null) {
            angle = weatherData.getWindDirection();
        }
        else {
            angle = wd.getWindDirection();
        }
        if (angle <10){
            return "N";
        }
        if (angle <80){
            return "N-E";
        }
        if (angle <100){
            return "E";
        }
        if (angle <170){
            return "S-E";
        }
        if (angle <190){
            return "S";
        }
        if (angle <260){
            return "S-V";
        }
        if (angle <280){
            return "V";
        }
        if (angle <350){
            return "N-V";
        }
        if (angle <=360){
            return "N";
        }
        return "None";
    }

    //weather parameter addded specially for mocking
    public float getTemperature(WeatherData wd) {
        if (wd == null) {
            return (weatherData.getTemperature() - 273);
        }
        else{
            return wd.getTemperature() - 273;
        }
    }

    //weather parameter addded specially for mocking
    public String getLongDescription(WeatherData wd){
        if(wd == null){
            return weatherData.getDescription();
        }
        else{
            return wd.getDescription();
        }
    }

    public Float getWindSpeed() {
        return weatherData.getWindSpeed();
    }
    public int getAirPressure(){
        return weatherData.getAirPressure();
    }
    public String getCityName(){
        return weatherData.cityName;
    }
    public Image getIcon(){
        String iconId = weatherData.getIconId();
        Image img;
        StringBuilder sb = new StringBuilder(iconId);

        if(iconId.equals("02n") || iconId.equals("02d")){
            img =  new Image("/images/"+iconId+".png");
        }
        else{
            sb.deleteCharAt(2);
            sb.append(".png");
            img = new Image("/images/" +sb.toString());
        }
        return img;
    }



}
