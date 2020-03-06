import Subject from '../interfaces/Subject';
import Observer from '../interfaces/Observer';

export type WeatherDataType = {
  temperature: number;
  pressure: number;
  humidity: number;
};

export class WeatherData implements Subject {
  private observers: Observer[] = [];
  private data: WeatherDataType;
  private lastUpdated: Date;

  registerObserver(observer: Observer): void {
    this.observers.push(observer);
  }

  removeObserver(observer: Observer): void {
    const index: number = this.observers.indexOf(observer);
    if (index !== -1) {
      this.observers.splice(index, 1);
    }
  }

  notifyObservers(): void {
    this.setChanged();
    this.observers.forEach(_observer => _observer.update(this));
  }

  setData(data: WeatherDataType): void {
    this.data = data;
    this.notifyObservers();
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

  setChanged(): void {
    this.lastUpdated = new Date();
  }
}
