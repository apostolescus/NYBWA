package ro.mta.se.lab;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private File file;
    private FileWriter outFile;

    public Logger(String outDirName)  {
        Path path = Paths.get("./" + outDirName);

        try{
            try {
                Files.createDirectory(path);
            }
            catch (FileAlreadyExistsException e){
              System.out.println("File already exists");
            }
            //java.time.LocalDateTime.now().toString()
            file = new File("./" + outDirName + "/" + "logFile" +".txt");
            outFile = new FileWriter(file);
        }
        catch (IOException e) {
            System.out.println("Error creating the file");
            }
        }

    public void log(WeatherForecast weatherForecast, String error){
        String formattedString;

        if(weatherForecast == null){
            formattedString = "[ERROR]: " + java.time.LocalDateTime.now() + " " + error;
        }
        else {
            formattedString = "[INFO]: " + java.time.LocalDate.now().toString() + " oraș: " + weatherForecast.getCityName() + " temperatură: " +
                    weatherForecast.getTemperature(null) + " viteza vântului:" + weatherForecast.getWindSpeed();
        }

        try {
            outFile.write(formattedString);
            outFile.write("\n");
            outFile.flush();
        }
        catch (IOException e){
            System.out.println("Error creating the file");
        }
    }

    public void close(){
        try {
            outFile.close();
        }
        catch (IOException e){
            System.out.println("Could close the file proprerty");
        }

    }
}
