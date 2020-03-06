import { WeatherData } from './weatherData/subject';
import CurrentConditionsDisplay from './displays/currentConditions';
import HeatIndexDisplay from './displays/heatIndex';
import StatisticsDisplay from './displays/statistics';

class WeatherStation {
  run(): void {
    const weatherDataSubject = new WeatherData();
    new CurrentConditionsDisplay(weatherDataSubject);
    new HeatIndexDisplay(weatherDataSubject);
    new StatisticsDisplay(weatherDataSubject);

    weatherDataSubject.setData({ temperature: 12, humidity: 15, pressure: 13.11 });
    console.log('\n');
    weatherDataSubject.setData({ temperature: 13, humidity: 16, pressure: 13.22 });
    console.log('\n');
    weatherDataSubject.setData({ temperature: 14, humidity: 17, pressure: 13.33 });
  }
}

function main(): void {
  const weatherStation = new WeatherStation();
  weatherStation.run();
}

main();
