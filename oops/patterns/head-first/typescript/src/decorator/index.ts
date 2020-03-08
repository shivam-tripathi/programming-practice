import beverages from './beverages';
import condimentDecorators from './condimentDecorators';
import Beverage from './Beverage';
import { SIZES } from './constants';

function main(): void {
  let beverage: Beverage = new beverages.DarkRoast();
  beverage = new condimentDecorators.Mocha(beverage);
  beverage = new condimentDecorators.Mocha(beverage);
  beverage = new condimentDecorators.Whip(beverage);
  beverage.setSize(SIZES.LARGE);
  console.log(`${beverage.getDescription()}: ${beverage.cost()}`);

  const beverage2: Beverage = new beverages.Expresso();
  console.log(`${beverage2.getDescription()}: ${beverage2.cost()}`);

  let beverage3: Beverage = new beverages.HouseBlend();
  beverage3 = new condimentDecorators.Soy(beverage3);
  beverage3 = new condimentDecorators.Mocha(beverage3);
  beverage3 = new condimentDecorators.Whip(beverage3);
  beverage3.setSize(SIZES.LARGE);
  console.log(`${beverage3.getDescription()}: ${beverage3.cost()}`);
}

main();
