import FlyBehavior from './flyBehavior';

class RocketPowered implements FlyBehavior {
  fly(): void {
    console.log("I'm flying with a rocket!");
  }
}

export default RocketPowered;
