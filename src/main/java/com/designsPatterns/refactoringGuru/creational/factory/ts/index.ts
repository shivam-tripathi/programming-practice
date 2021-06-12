interface Product {
  operation(): string;
}

class ConcreteProduct1 implements Product {
  operation() {
    return 'product1';
  }
}

class ConcreteProduct2 implements Product {
  operation() {
    return 'product2';
  }
}

abstract class Creator {
  public abstract factoryMethod(): Product;
  public someOperation(): string {
    const product = this.factoryMethod();
    return `Creator: The same creator's code has just worked with ${product.operation()}`
  }
}

class ConcreteCreator1 extends Creator {
  public factoryMethod(): Product {
    return new ConcreteProduct1();
  }
}

class ConcreteCreator2 extends Creator {
  public factoryMethod(): Product {
    return new ConcreteProduct2();
  }
}

function clientCode(creator: Creator) {
  console.log('I\'m unaware of creator\'s class. But it still works');
  console.log(creator.someOperation());
}

function main() {
  console.log('Concrete creator 1');
  const creator1 = new ConcreteCreator1();
  clientCode(creator1);

  console.log('Concrete creator 2');
  const creator2 = new ConcreteCreator2();
  clientCode(creator2);
}

main();
