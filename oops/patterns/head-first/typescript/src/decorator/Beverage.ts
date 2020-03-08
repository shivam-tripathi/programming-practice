import { SIZES } from './constants';

export default interface Beverage {
  description: string;
  size: SIZES;
  getDescription(): string;
  cost(): number;
  getSize(): SIZES;
  setSize(_: SIZES): void;
}
