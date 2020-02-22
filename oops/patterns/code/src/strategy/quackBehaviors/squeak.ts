import QuackBehavior from './quackBehavior';

class Squeak implements QuackBehavior {
  quack(): void {
    console.log('Queak!');
  }
}

export default Squeak;
