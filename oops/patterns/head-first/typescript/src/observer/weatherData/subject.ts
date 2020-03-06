import Subject from '../interfaces/Subject';
import Observer from '../interfaces/Observer';

class WeatherDataSubject<T> implements Subject<T> {
  private observers: Observer<T>[] = [];
  private data: T;
  registerObserver(observer: Observer<T>): void {
    this.observers.push(observer);
  }
  removeObserver(observer: Observer<T>): void {
    const index: number = this.observers.indexOf(observer);
    if (index !== -1) {
      this.observers.splice(index, 1);
    }
  }
  notifyObservers(): void {
    this.observers.forEach(_observer => _observer.update(this.data));
  }
  setData(data: T): void {
    this.data = data;
    this.notifyObservers();
  }
}

export default WeatherDataSubject;
