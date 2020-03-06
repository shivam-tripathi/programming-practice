import Observer from './Observer';

class Observable {
  observers: Observer[] = [];
  changed = false;
  registerObserver(observer: Observer): void {
    this.observers.push(observer);
  }

  removeObserver(observer: Observer): void {
    const index: number = this.observers.indexOf(observer);
    if (index !== -1) {
      this.observers.splice(index, 1);
    }
  }

  notifyObservers(args?: object): void {
    if (this.changed === true) {
      this.observers.forEach(_observer => _observer.update(this, args));
      this.changed = false;
    }
  }

  setChanged(): void {
    this.changed = true;
  }
}

export default Observable;
