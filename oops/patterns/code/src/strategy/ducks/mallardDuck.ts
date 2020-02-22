import Duck from './duck';
import FlyWithWings from '../flyBehaviors/flyWithWings';
import Quack from '../quackBehaviors/quack';

class MallardDuck extends Duck {
  constructor() {
    super(new FlyWithWings(), new Quack());
  }
  display(): void {
    console.log('This is a mallard duck!');
  }
}

export default MallardDuck;
