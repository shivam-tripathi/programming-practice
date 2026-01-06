### Static Factory
- Creates objects without exposing the instantiation logic to the client.
-  Client just need a class and does not care about which concrete implementation it is getting.

### Factory Method
- Define an interface for creating an object, but let the subclasses decide which class to instantiate. The Factory method lets a class defer instantiation to subclasses
- Client doesn't know what concrete classes it will be required to create at runtime, but just wants to get a class that will do the job.


### Abstract Factory
- Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
-  When your system has to create multiple families of products or you want to provide a library of products without exposing the implementation details.


**AbstractFactory** pattern uses _**composition**_ to _**delegate**_ responsibility of creating object to another class while Factory method design pattern uses _**inheritance**_ and relies on _**derived class or sub class**_ to create object.

https://stackoverflow.com/questions/13029261/design-patterns-factory-vs-factory-method-vs-abstract-factory/35851402#35851402
