from abc import ABC, abstractmethod

# Interface for product
class Product(ABC):
    @abstractmethod
    def operation(self) -> str:
        pass

# Concrete Product 1
class ConcreteProduct1(Product):
    def operation(self) -> str:
        return 'product1'

# Concrete Product 2
class ConcreteProduct2(Product):
    def operation(self) -> str:
        return 'product2'

# Abstract class Creator
class Creator(ABC):
    @abstractmethod
    def factory_method(self) -> Product:
        pass

    def some_operation(self) -> str:
        product = self.factory_method()
        return product.operation()

# Concrete Creator 1
class ConcreteCreator1(Creator):
    def factory_method(self) -> Product:
        return ConcreteProduct1()

# Concrete Creator 2
class ConcreteCreator2(Creator):
    def factory_method(self) -> Product:
        return ConcreteProduct2()

def client_code(creator: Creator) -> None:
    print(f"Client: I'm not aware of the creator's class, but it still works")
    print(f"{creator.some_operation()}")

if __name__ == "__main__":
    print("App: Launched with the ConcreteCreator1.")
    client_code(ConcreteCreator1())

    print("App: Launched with the ConcreteCreator2.")
    client_code(ConcreteCreator2())