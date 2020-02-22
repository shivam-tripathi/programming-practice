import Duck from './ducks/duck';
import MallardDuck from './ducks/mallardDuck';
import ModelDuck from './ducks/modelDuck';
import RocketPowered from './flyBehaviors/rocketPowered';

function main(): void {
  let duck: Duck = new MallardDuck();
  duck.display();
  duck.performFly();
  duck.performQuack();

  duck = new ModelDuck();
  duck.display();
  duck.performFly();
  duck.performQuack();
  duck.setFly(new RocketPowered());
  duck.performFly();
}

main();
