package ro.mta.se.lab;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Controller {

    @FXML
    private Button buttonId;

    @FXML
    private Label errorId;
    @FXML
    private javafx.scene.control.TextField searchBarId;

    @FXML
    private void handleButtonClick(ActionEvent actionEvent){

    }

    public void initialize(){
        buttonId.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                String searchBarVal;

                searchBarVal = searchBarId.getText();

                if(searchBarVal.equals("")){
                    errorId.setText("Please Insert a city");
                }
                else{
                    errorId.setText("Searching City ...");
                }

            }
        });
    }



}
