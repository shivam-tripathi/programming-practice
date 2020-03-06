import DisplayElement from '../interfaces/DisplayElement';
import Observer from '../interfaces/Observer';
import { WeatherData } from '../weatherData/subject';
import Observable from '../interfaces/Observable';

type StatisticsDisplayDataType = {
  temperature: number;
  humidity: number;
  pressure: number;
};

class StatisticsDisplay implements DisplayElement, Observer {
  min: StatisticsDisplayDataType;
  max: StatisticsDisplayDataType;
  avg: StatisticsDisplayDataType;
  count: number;
  weatherData: WeatherData;
  constructor(weatherData: WeatherData) {
    this.weatherData = weatherData;
    this.weatherData.registerObserver(this);
    this.count = 0;
  }
  update(subject: Observable): void {
    if (subject instanceof WeatherData) {
      const weatherData = subject as WeatherData;
      if (this.count === 0) {
        const temperature = weatherData.getTemperature();
        const pressure = weatherData.getPressure();
        const humidity = weatherData.getHumidity();
        const statisticsDisplayData: StatisticsDisplayDataType = { temperature, humidity, pressure };
        this.min = Object.assign({}, statisticsDisplayData);
        this.max = Object.assign({}, statisticsDisplayData);
        this.avg = Object.assign({}, statisticsDisplayData);
      } else {
        this.min.temperature = Math.min(this.min.temperature, weatherData.getTemperature());
        this.min.humidity = Math.min(this.min.humidity, weatherData.getHumidity());
        this.min.pressure = Math.min(this.min.pressure, weatherData.getHumidity());

        this.max.temperature = Math.max(this.max.temperature, weatherData.getTemperature());
        this.max.humidity = Math.max(this.max.humidity, weatherData.getHumidity());
        this.max.pressure = Math.max(this.max.pressure, weatherData.getHumidity());

        this.avg.temperature = parseFloat(
          ((this.count * this.avg.temperature + weatherData.getTemperature()) / (this.count + 1)).toFixed(2),
        );
        this.avg.humidity = parseFloat(
          ((this.count * this.avg.humidity + weatherData.getHumidity()) / (this.count + 1)).toFixed(2),
        );
        this.avg.pressure = parseFloat(
          ((this.count * this.avg.pressure + weatherData.getHumidity()) / (this.count + 1)).toFixed(2),
        );
      }
      this.count++;
      this.display();
    }
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
