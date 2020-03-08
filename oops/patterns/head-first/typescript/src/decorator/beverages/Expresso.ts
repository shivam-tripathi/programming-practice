import Beverage from '../Beverage';
import { SIZES, PRICING } from '../constants';

export default class Expresso implements Beverage {
  description: string;
  size: SIZES;
  constructor() {
    this.description = 'Expresso';
    this.size = SIZES.TALL;
  }
  getDescription(): string {
    return `${this.description} ${this.getSize()}`;
  }
  cost(): number {
    return PRICING.BEVERAGES.EXPRESSO[this.getSize()];
  }
  getSize(): SIZES {
    return this.size;
  }
  setSize(size: SIZES): void {
    this.size = size;
  }
}
