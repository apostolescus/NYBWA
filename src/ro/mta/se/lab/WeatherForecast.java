package ro.mta.se.lab;

import com.google.gson.*;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeatherForecast {

    private static final String APIKEY = "1287bb7b7580eb650b1f63cbdc3ddf4b";
    private static final String getRequest = "https://api.openweathermap.org/data/2.5/weather?q=";
    private WeatherData weatherData;

    public void searchCity(String cityName) throws IOException {
        String finalRequest, inLine;
        StringBuffer response;
        int responseCode;
        BufferedReader inBuffer;
        URL url;
        HttpURLConnection connection;

        finalRequest = getRequest + cityName + "&appid=" + APIKEY;
        url = new URL(finalRequest);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();

        inBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = new StringBuffer();

        while ((inLine = inBuffer.readLine()) != null) {
            response.append(inLine);
        }

        inBuffer.close();
        processJson(response.toString());

    }

    private void processJson(String response){
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

        weatherData = new WeatherData(windDirection, pressure, temp, windSpeed, mainDescription, shortDescription, iconId);
    }

    public String getWindDirection(){
        int angle = weatherData.getWindDirection();

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
    public float getTemperature(){
        return (weatherData.getTemperature()-273);
    }
    public String getShortDescription(){
        return weatherData.getBaseType();
    }
    public String getLongDescription(){
        return weatherData.getDescription();
    }
    public Float getWindSpeed(){
        return weatherData.getWindSpeed();
    }
    public int getAirPressure(){
        return weatherData.getAirPressure();
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
