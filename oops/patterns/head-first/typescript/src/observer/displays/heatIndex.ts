import DisplayElement from '../interfaces/DisplayElement';
import Observer from '../interfaces/Observer';
import WeatherDataType from './weatherDataType';
import WeatherDataSubject from '../weatherData/subject';

class HeatIndexDisplay implements DisplayElement, Observer<WeatherDataType> {
  temperature: number;
  humidity: number;
  weatherDataSubject: WeatherDataSubject<WeatherDataType>;
  constructor(weatherDataSubject: WeatherDataSubject<WeatherDataType>) {
    this.weatherDataSubject = weatherDataSubject;
    this.weatherDataSubject.registerObserver(this);
  }
  update(data: WeatherDataType): void {
    const { temperature, humidity } = data;
    this.temperature = temperature;
    this.humidity = humidity;
    this.display();
  }
  display(): void {
    const T = this.temperature,
      RH = this.humidity;
    const heatindex =
      16.923 +
      1.85212 * 10 -
      1 * T +
      5.37941 * RH -
      1.00254 * 10 -
      1 * T * RH +
      9.41695 * 10 -
      3 * Math.pow(T, 2) +
      7.28898 * 10 -
      3 * Math.pow(RH, 2) +
      3.45372 * 10 -
      4 * Math.pow(T, 2) * RH -
      8.14971 * 10 -
      4 * T * Math.pow(RH, 2) +
      1.02102 * 10 -
      5 * Math.pow(T, 2) * Math.pow(RH, 2) -
      3.8646 * 10 -
      5 * Math.pow(T, 3) +
      2.91583 * 10 -
      5 * Math.pow(RH, 3) +
      1.42721 * 10 -
      6 * Math.pow(T, 3) * RH +
      1.97483 * 10 -
      7 * T * Math.pow(RH, 3) -
      2.18429 * 10 -
      8 * Math.pow(T, 3) * Math.pow(RH, 2) +
      8.43296 * 10 -
      10 * Math.pow(T, 2) * Math.pow(RH, 3) -
      4.81975 * 10 -
      11 * Math.pow(T, 3) * Math.pow(RH, 3);
    console.log(`Heat index is ${heatindex}`);
  }
}

export default HeatIndexDisplay;
