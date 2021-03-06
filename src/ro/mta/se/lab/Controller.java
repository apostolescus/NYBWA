package ro.mta.se.lab;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class Controller {

    private WeatherForecast weatherForecast;

    @FXML
    private Button buttonId;

    @FXML
    private ImageView iconId,  thermometerId, windDirectionIconId, windSpeedIconId;

    @FXML
    private Label errorId, tempId, windSpeedId, windDirectionId, pressureId, descriptionId, cityId;

    @FXML
    private Label   celsiusId, descriptionPressureId, windMUId, pressureMUId;

    @FXML
    private javafx.scene.control.TextField searchBarId;


    public void initialize(){
        weatherForecast = new WeatherForecast();
        hideWeather();
        hideError();

        buttonId.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                String searchBarVal;
                hideError();
                searchBarVal = searchBarId.getText();

                if(searchBarVal.equals("")){
                    errorId.setText("Please Insert a city");
                    showError();
                }
                else{
                        weatherForecast.searchCity(searchBarVal);
                        if(!weatherForecast.error.equals("")){
                            errorId.setText(weatherForecast.error);
                            showError();
                        }
                        else {
                            cityId.setText(searchBarVal);
                            tempId.setText(String.valueOf(weatherForecast.getTemperature(null)));
                            pressureId.setText(String.valueOf(weatherForecast.getAirPressure()));
                            windDirectionId.setText(String.valueOf(weatherForecast.getWindDirection(null)));
                            windSpeedId.setText(String.valueOf(weatherForecast.getWindSpeed()));
                            descriptionId.setText(String.valueOf(weatherForecast.getLongDescription(null)));
                            iconId.setImage(weatherForecast.getIcon());
                            showWeather();
                        }
                }

            }
        });
    }

    private void showWeather(){
        tempId.setVisible(true);
        windSpeedId.setVisible(true);
        windDirectionIconId.setVisible(true);
        pressureId.setVisible(true);
        descriptionId.setVisible(true);
        cityId.setVisible(true);
        windSpeedIconId.setVisible(true);
        windDirectionId.setVisible(true);
        celsiusId.setVisible(true);
        descriptionPressureId.setVisible(true);
        windMUId.setVisible(true);
        pressureMUId.setVisible(true);
        thermometerId.setVisible(true);

    }

    private void hideWeather(){
        tempId.setVisible(false);
        windSpeedId.setVisible(false);
        windDirectionIconId.setVisible(false);
        pressureId.setVisible(false);
        descriptionId.setVisible(false);
        cityId.setVisible(false);
        windSpeedIconId.setVisible(false);
        windDirectionId.setVisible(false);
        celsiusId.setVisible(false);
        descriptionPressureId.setVisible(false);
        windMUId.setVisible(false);
        pressureMUId.setVisible(false);
        thermometerId.setVisible(false);
    }

    private void showError(){
        errorId.setVisible(true);
    }

    private void hideError(){
        errorId.setVisible(false);
    }

}
