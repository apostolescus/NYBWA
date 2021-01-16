package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

//    @Override
//    public void stop(){
//
//    }
}
