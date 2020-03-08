import Beverage from '../Beverage';
import { SIZES, PRICING } from '../constants';

export default class Decaf implements Beverage {
  description: string;
  size: SIZES;
  constructor() {
    this.description = 'Decaf';
    this.size = SIZES.TALL;
  }
  getDescription(): string {
    return `${this.description} ${this.getSize()}`;
  }
  cost(): number {
    return PRICING.BEVERAGES.DECAF[this.getSize()];
  }
  getSize(): SIZES {
    return this.size;
  }
  setSize(size: SIZES): void {
    this.size = size;
  }
}
