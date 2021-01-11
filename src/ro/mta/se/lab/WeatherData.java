package ro.mta.se.lab;

import javafx.beans.property.*;

public class WeatherData {

    StringProperty description;
    StringProperty baseType;
    FloatProperty temperature;
    FloatProperty windSpeed;
    IntegerProperty windDirection, airPressure;
    StringProperty iconId;

    public WeatherData(int windDir, int pressure, float temp, float windSpd, String descr, String type, String icon){
        temperature = new SimpleFloatProperty(temp);
        windSpeed = new SimpleFloatProperty(windSpd);
        windDirection = new SimpleIntegerProperty(windDir);
        airPressure = new SimpleIntegerProperty(pressure);
        baseType = new SimpleStringProperty(type);
        description = new SimpleStringProperty(descr);
        iconId = new SimpleStringProperty(icon);
    }

    public int getWindDirection(){
        return  windDirection.get();
    }

    public float getTemperature() {
        return temperature.get();
    }

    public float getWindSpeed() {
        return windSpeed.get();
    }

    public String getDescription() {
        return description.get();
    }
    public String getBaseType() {
        return baseType.get();
    }
    public int getAirPressure() {
        return airPressure.get();
    }
    public String getIconId() {
        return iconId.get();
    }
}
