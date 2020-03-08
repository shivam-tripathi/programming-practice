import Beverage from '../Beverage';
import { SIZES, PRICING } from '../constants';

export default class HouseBlend implements Beverage {
  description: string;
  size: SIZES;
  constructor() {
    this.description = 'House Blend';
    this.size = SIZES.TALL;
  }
  getDescription(): string {
    return `${this.description} ${this.getSize()}`;
  }
  cost(): number {
    return PRICING.BEVERAGES.HOUSE_BLEND[this.getSize()];
  }
  getSize(): SIZES {
    return this.size;
  }
  setSize(size: SIZES): void {
    this.size = size;
  }
}
