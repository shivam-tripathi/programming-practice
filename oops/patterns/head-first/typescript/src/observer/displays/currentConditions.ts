import Observer from '../interfaces/Observer';
import DisplayElement from '../interfaces/DisplayElement';
import { WeatherData } from '../weatherData/subject';
import Observable from '../interfaces/Observable';

class CurrentConditionsDisplay implements Observer, DisplayElement {
  private temperature: number;
  private humidity: number;
  private pressure: number;
  private weatherDataSubject: WeatherData;
  constructor(weatherDataSubject: WeatherData) {
    this.weatherDataSubject = weatherDataSubject;
    this.weatherDataSubject.registerObserver(this);
  }
  update(subject: Observable): void {
    if (subject instanceof WeatherData) {
      const weatherData = subject as WeatherData;
      this.temperature = weatherData.getTemperature();
      this.humidity = weatherData.getHumidity();
      this.pressure = weatherData.getPressure();
      this.display();
    }
  }
  display(): void {
    console.log(
      `Current conditions are: Humidity: ${this.humidity} Temperature: ${this.temperature} Pressure: ${this.pressure}`,
    );
  }
}

export default CurrentConditionsDisplay;
