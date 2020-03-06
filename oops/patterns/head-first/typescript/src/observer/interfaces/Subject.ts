import Observer from './Observer';

interface Subject<T> {
  registerObserver(_: Observer<T>): void;
  removeObserver(_: Observer<T>): void;
  notifyObservers(): void;
}

export default Subject;
