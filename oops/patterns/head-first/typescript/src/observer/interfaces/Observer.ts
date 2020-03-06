interface Observer<T> {
  update(_: T): void;
}

export default Observer;
