import Beverage from './Beverage';

export default interface CondimentDecorator extends Beverage {
  beverage: Beverage;
}
