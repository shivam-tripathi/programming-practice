import Observer from './Observer';

interface Subject {
  registerObserver(_: Observer): void;
  removeObserver(_: Observer): void;
  notifyObservers(): void;
}

export default Subject;
