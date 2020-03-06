import Observable from './Observable';

interface Observer {
  update(_1: Observable, _2?: object): void;
}

export default Observer;
