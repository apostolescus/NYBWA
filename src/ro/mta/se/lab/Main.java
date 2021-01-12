package ro.mta.se.lab;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(this.getClass().getResource("/screen.fxml"));
        loader.setController(new Controller());
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("NYBWA");
        stage.show();

    }

}
