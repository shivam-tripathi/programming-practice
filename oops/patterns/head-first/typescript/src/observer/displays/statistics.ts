import DisplayElement from '../interfaces/DisplayElement';
import Observer from '../interfaces/Observer';
import WeatherDataType from './weatherDataType';
import WeatherDataSubject from '../weatherData/subject';

class StatisticsDisplay implements DisplayElement, Observer<WeatherDataType> {
  min: WeatherDataType;
  max: WeatherDataType;
  avg: WeatherDataType;
  count: number;
  weatherDataSubject: WeatherDataSubject<WeatherDataType>;
  constructor(weatherDataSubject: WeatherDataSubject<WeatherDataType>) {
    this.weatherDataSubject = weatherDataSubject;
    this.weatherDataSubject.registerObserver(this);
    this.count = 0;
  }
  update(weatherData: WeatherDataType): void {
    if (this.count === 0) {
      this.min = Object.assign({}, weatherData);
      this.max = Object.assign({}, weatherData);
      this.avg = Object.assign({}, weatherData);
    } else {
      this.min.temperature = Math.min(this.min.temperature, weatherData.temperature);
      this.min.humidity = Math.min(this.min.humidity, weatherData.humidity);
      this.min.pressure = Math.min(this.min.pressure, weatherData.pressure);

      this.max.temperature = Math.max(this.max.temperature, weatherData.temperature);
      this.max.humidity = Math.max(this.max.humidity, weatherData.humidity);
      this.max.pressure = Math.max(this.max.pressure, weatherData.pressure);

      this.avg.temperature = parseFloat(
        ((this.count * this.avg.temperature + weatherData.temperature) / (this.count + 1)).toFixed(2),
      );
      this.avg.humidity = parseFloat(
        ((this.count * this.avg.humidity + weatherData.humidity) / (this.count + 1)).toFixed(2),
      );
      this.avg.pressure = parseFloat(
        ((this.count * this.avg.pressure + weatherData.pressure) / (this.count + 1)).toFixed(2),
      );
    }
    this.count++;
    this.display();
  }
  display(): void {
    console.log(`MIN | MAX | AVG humidity    ${this.min.humidity} | ${this.max.humidity} | ${this.avg.humidity}`);
    console.log(
      `MIN | MAX | AVG temperature ${this.min.temperature} | ${this.max.temperature} | ${this.avg.temperature}`,
    );
    console.log(`MIN | MAX | AVG pressure    ${this.min.pressure} | ${this.max.pressure} | ${this.avg.pressure}`);
  }
}

export default StatisticsDisplay;
