import QuackBehavior from './quackBehavior';

class MuteQuack implements QuackBehavior {
  quack(): void {
    console.log('<< SILENCE >>');
  }
}

export default MuteQuack;
