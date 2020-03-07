package com.patterns.observer;

import java.util.Random;

class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);

        Random random = new Random();
        weatherData.setMeasurements(random.nextFloat() * 100, random.nextFloat() * 200, random.nextFloat() * 50);
        weatherData.setMeasurements(random.nextFloat() * 100, random.nextFloat() * 200, random.nextFloat() * 50);
        weatherData.setMeasurements(random.nextFloat() * 100, random.nextFloat() * 200, random.nextFloat() * 50);

        SwingObservableExample example = new SwingObservableExample();
        example.go();
    }
}
