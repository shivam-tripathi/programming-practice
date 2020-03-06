import QuackBehvior from './quackBehavior';

class Quack implements QuackBehvior {
  quack(): void {
    console.log('Quack!');
  }
}

export default Quack;
