import WeatherDataType from './weatherDataType';
import Observer from '../interfaces/Observer';
import DisplayElement from '../interfaces/DisplayElement';
import WeatherDataSubject from '../weatherData/subject';

class CurrentConditionsDisplay implements Observer<WeatherDataType>, DisplayElement {
  private data: WeatherDataType;
  private weatherDataSubject: WeatherDataSubject<WeatherDataType>;
  constructor(weatherDataSubject: WeatherDataSubject<WeatherDataType>) {
    this.weatherDataSubject = weatherDataSubject;
    this.weatherDataSubject.registerObserver(this);
  }
  update(data: WeatherDataType): void {
    this.data = data;
    this.display();
  }
  display(): void {
    console.log(
      `Current conditions are: Humidity: ${this.data.humidity} Temperature: ${this.data.temperature} Pressure: ${this.data.pressure}`,
    );
  }
}

export default CurrentConditionsDisplay;
