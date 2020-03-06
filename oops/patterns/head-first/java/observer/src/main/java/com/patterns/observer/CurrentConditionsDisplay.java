package com.patterns.observer;

import java.util.Observer;
import java.util.Observable;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;

    CurrentConditionsDisplay(Observable o) {
        this.observable = o;
        this.observable.addObserver(this);
    }

    public void update(Observable observable, Object args) {
        if (observable instanceof WeatherData) {
            WeatherData data = (WeatherData)observable;
            this.temperature = data.getTemperature();
            this.humidity = data.getHumidity();
            this.display();
        } else {
            System.out.printf("[CurrentConditionsDisplay] Args not of type WeatherData\n");
        }
    }

    public void display() {
       System.out.printf("[CurrentConditionsDisplay] Temperature is %f and humidity is %f.\n", this.temperature, this.humidity);
    }
}
