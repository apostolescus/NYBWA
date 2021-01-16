
# **Not Your Basic Weather App**   <img src="https://github.com/apostolescus/NYBWA/blob/main/src/resources/images/logo.png" width="48" height="48">

## About
NYBWA is a meteo app developed in JavaFX. 
This application offers the user the possibility to search weather forecast for a desired city.
It has a nice and user-friendly interface, which makes it easy to use.
It is based on OpenWeatherMap API.

![Interface](/images/second_GUI.png)
***
## Features
- search weather by city name 
 (temperature, pressure, wind speed, wind direction)
- suggestive icons
- error notifications
- MVC arhitecture
## How to use 
    
First of all, in order to get a weather forecast, each user should insert their API key in the WeatherForecast class.

        private static final String APIKEY = "YOUR API KEY";
If you don't have a key, get one from: https://openweathermap.org/api.

At the runtime, you will be greeted with a simple interface, and you should insert your desired city name in the 
text field.
**City name must be only in English !**
After that, you shoul see the desired city forecast in the right side of the screen, next to a suggestive icon.

## Error handling

In most cases error will occur in one of the following situations: no internet connection or mispelled city name.
The program will handle these cases and many other and will display a message with the error explanation. 

*Error example*

![Error](/images/error.png)

## Logging
The program logs every search and every error occurs. 
The log file is hardcoded and it can be find in /logFIle/logFile.txt. 
There is a possibility to generate a log file each time the program starts with 
the date and the current time, but that is confusing for the average user.

*logFile content example:*

`2021-01-16T09:36:49.9133 [INFO]: oraș: Bucharest temperatură: -3.0 viteza vântului:3.09`

` 2021-01-16T09:36:56.4337 [ERROR]: Response code was 404 `

## Tests
Given the fact that the project structure is fairly simple, it has only two possible 
tested classes: WeatherForecast and Logger. The rest of the classes are either data classes 
(WeatherData), either view (Main) or controller (Controller). 

## Future improvements
- save user city
- get weather for user location
- allow user to insert it's own API key
- allow user to insert logFile path
***
## UML Charts

### Sequence diagram

       ┌─┐                                                                                                        
       ║"│                                                                                                        
       └┬┘                                                                                                        
       ┌┼┐                                                                                                        
        │                ┌─────────┐             ┌──────────┐          ┌───────────────┐                  ┌──────┐
       ┌┴┐               │interface│             │Controller│          │WeatherForecast│                  │Logger│
      User               └────┬────┘             └────┬─────┘          └───────┬───────┘                  └──┬───┘
       │      insert city     │                       │                        │                             │    
       │ ─────────────────────>                       │                        │                             │    
       │                      │                       │                        │                             │    
       │                      │   EventHandler(City)  │                        │                             │    
       │                      │ ──────────────────────>                        │                             │    
       │                      │                       │                        │                             │    
       │                      │                       │    searchCty(city)     │                             │    
       │                      │                       │ ──────────────────────>│                             │    
       │                      │                       │                        │                             │    
       │                      │                       │                        ────┐                         │    
       │                      │                       │                            │ makeApiRequest()        │    
       │                      │                       │                        <───┘                         │    
       │                      │                       │                        │                             │    
       │                      │                       │                        ────┐                              
       │                      │                       │                            │ processJSON(jsonResponse)    
       │                      │                       │                        <───┘                              
       │                      │                       │                        │                             │    
       │                      │                       │                        │     log(processedJSON)      │    
       │                      │                       │                        │─────────────────────────────>    
       │                      │                       │                        │                             │    
       │                      │                       │    getWeatherInfo()    │                             │    
       │                      │                       │ ──────────────────────>│                             │    
       │                      │                       │                        │                             │    
       │                      │                       │  returnWeatherInfo()   │                             │    
       │                      │                       │ <──────────────────────│                             │    
       │                      │                       │                        │                             │    
       │                      │ SetText(returnedInfos)│                        │                             │    
       │                      │ <──────────────────────                        │                             │    
       │                      │                       │                        │                             │    
       │ display weather infos│                       │                        │                             │    
       │ <─────────────────────                       │                        │                             │    
      User               ┌────┴────┐             ┌────┴─────┐          ┌───────┴───────┐                  ┌──┴───┐
       ┌─┐               │interface│             │Controller│          │WeatherForecast│                  │Logger│
       ║"│               └─────────┘             └──────────┘          └───────────────┘                  └──────┘
       └┬┘                                                                                                        
       ┌┼┐                                                                                                        
        │                                                                                                         
       ┌┴┐                                                                                                        


### Class diagram

![Classes Diagram](/images/classesDiagram.png)

## Resources

- icons : https://icons8.com/icon/set/weather/doodle
- weather infos:  https://openweathermap.org/api