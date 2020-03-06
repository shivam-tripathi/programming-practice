import Observable from '../interfaces/Observable';

export type WeatherDataType = {
  temperature: number;
  pressure: number;
  humidity: number;
};

export class WeatherData extends Observable {
  private data: WeatherDataType;

  dataChanged(): void {
    this.setChanged();
    this.notifyObservers();
  }

  setData(data: WeatherDataType): void {
    this.data = data;
    this.dataChanged();
  }

  getTemperature(): number {
    return this.data.temperature;
  }

  getHumidity(): number {
    return this.data.humidity;
  }

  getPressure(): number {
    return this.data.pressure;
  }
}
