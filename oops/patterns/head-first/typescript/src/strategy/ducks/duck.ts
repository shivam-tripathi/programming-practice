import FlyBehavior from '../flyBehaviors/flyBehavior';
import QuackBehavior from '../quackBehaviors/quackBehavior';

abstract class Duck {
  flyBehavior: FlyBehavior;
  quackBehavior: QuackBehavior;
  constructor(flyBehavior: FlyBehavior, quackBehavior: QuackBehavior) {
    this.flyBehavior = flyBehavior;
    this.quackBehavior = quackBehavior;
  }
  abstract display(): void;
  performFly(): void {
    this.flyBehavior.fly();
  }
  performQuack(): void {
    this.quackBehavior.quack();
  }
  setFly(fb: FlyBehavior): void {
    this.flyBehavior = fb;
  }
  setQuack(qb: QuackBehavior): void {
    this.quackBehavior = qb;
  }
}

export default Duck;
