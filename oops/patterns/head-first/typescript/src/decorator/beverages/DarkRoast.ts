import Beverage from '../Beverage';
import { SIZES, PRICING } from '../constants';

export default class DarkRoast implements Beverage {
  description: string;
  size: SIZES;
  constructor() {
    this.description = 'Dark Roast';
    this.size = SIZES.TALL;
  }
  getDescription(): string {
    return `${this.description} ${this.getSize()}`;
  }
  cost(): number {
    return PRICING.BEVERAGES.DARK_ROAST[this.getSize()];
  }
  getSize(): SIZES {
    return this.size;
  }
  setSize(size: SIZES): void {
    this.size = size;
  }
}
