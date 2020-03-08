import CondimentDecorator from '../CondimentDecorator';
import Beverage from '../Beverage';
import { SIZES, PRICING } from '../constants';

export default class Mocha implements CondimentDecorator {
  beverage: Beverage;
  description: string;
  size: SIZES;
  constructor(beverage: Beverage) {
    this.beverage = beverage;
    this.description = 'Mocha';
    this.size = this.beverage.getSize();
  }
  getDescription(): string {
    return `${this.beverage.getDescription()}, ${this.description}`;
  }
  cost(): number {
    return parseFloat((this.beverage.cost() + PRICING.CONDIMENTS.MOCHA[this.getSize()]).toFixed(2));
  }
  getSize(): SIZES {
    return this.size;
  }
  setSize(size: SIZES): void {
    this.beverage.setSize(size);
    this.size = size;
  }
}
