import Subject from './Subject';

interface Observer {
  update(_1: Subject, _2?: object): void;
}

export default Observer;
