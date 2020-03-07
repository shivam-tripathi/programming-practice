package com.patterns.observer;

import java.util.Observable;

/*
Problems with Observable
    - It is a class, so everytime we want to use it it must be subclassed
    - As it's not an interface we can't create our own implemetation
    - setChanged method is protected, so we can't call it unless we subclass it (no composition)
*/

public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measurementsChanged();
    }

    public float getTemperature() {
        return this.temperature;
    }

    public float getHumidity() {
        return this.humidity;
    }

    public float getPressure() {
        return this.pressure;
    }
}
