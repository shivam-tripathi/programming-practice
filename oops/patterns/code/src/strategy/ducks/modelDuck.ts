import Duck from './duck';
import FlyNoWay from '../flyBehaviors/flyNoWay';
import Squeak from '../quackBehaviors/squeak';

class ModelDuck extends Duck {
  constructor() {
    super(new FlyNoWay(), new Squeak());
  }
  display(): void {
    console.log('This is a model duck!');
  }
}

export default ModelDuck;
