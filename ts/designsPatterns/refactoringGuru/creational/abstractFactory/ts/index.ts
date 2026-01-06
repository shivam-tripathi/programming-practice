interface AbstractProductA {
  usefulFunctionA(): string;
}

interface AbstractProductB {
  usefulFunctionB(): string;
  anotherFunctionB(collaborator: AbstractProductA): string;
}

interface AbstractFactory {
  createProductA(): AbstractProductA;
  createProductB(): AbstractProductB;
}

class ConcreteProductA1 implements AbstractProductA {
  public usefulFunctionA() {
    return "ConcreteProductA1";
  }
}

class ConcreteProductA2 implements AbstractProductA {
  public usefulFunctionA() {
    return "ConcreteProductA2";
  }
}

class ConcreteProductB1 implements AbstractProductB {
  public usefulFunctionB() {
    return "ConcreteProductB1";
  }

  public anotherFunctionB(collaborator: AbstractProductA): string {
    return (
      this.usefulFunctionB() + " collabs " + collaborator.usefulFunctionA()
    );
  }
}

class ConcreteProductB2 implements AbstractProductB {
  public usefulFunctionB() {
    return "ConcreteProductB2";
  }

  public anotherFunctionB(collaborator: AbstractProductA): string {
    return (
      this.usefulFunctionB() + " collabs " + collaborator.usefulFunctionA()
    );
  }
}

class ConcreteFactory1 implements AbstractFactory {
  public createProductA(): AbstractProductA {
    return new ConcreteProductA1();
  }

  public createProductB(): AbstractProductB {
    return new ConcreteProductB1();
  }
}

class ConcreteFactory2 implements AbstractFactory {
  public createProductA(): AbstractProductA {
    return new ConcreteProductA2();
  }

  public createProductB(): AbstractProductB {
    return new ConcreteProductB2();
  }
}

function clientCode(factory: AbstractFactory) {
  const productA = factory.createProductA();
  const productB = factory.createProductB();
  console.log(productB.anotherFunctionB(productA));
}

function main() {
  clientCode(new ConcreteFactory1());
  clientCode(new ConcreteFactory2());
}

main();
