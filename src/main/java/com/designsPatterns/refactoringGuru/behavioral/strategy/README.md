

If you are using the principle of coding to abstractions rather than "concretions" then a lot of the patterns start looking like variations on a theme.


- The Factory ( method ) Pattern.
  - The factory pattern allows you to encapsulate object creation.
	- Create concrete instances only. Different arguments may result in different objects. It depends on the logic etc.
- The Strategy Pattern.
  - The strategy pattern allows you to polymorphically change behavior of a class.
  - Encapsulate the algorithm ( steps ) to perform an action. So you can change the strategy and use another algorithm.

-  One of the reasons of these patterns is to avoid conditionals in favor of polymorphism


### Factory (and FactoryMethod returned by Factory):

    Creational pattern
    Based on inheritance
    Factory returns a Factory Method (interface) which in turn returns Concrete Object
    You can substitute new Concrete Objects for interface and client (caller) should not be aware of all concrete implementations
    Client always access interface only and you can hide object creation details in Factory method

### Strategy pattern:

    It's a behavioural pattern
    It's based on delegation
    It changes guts of the object by modifying method behaviour
    It's used to switch between family of algorithms
    It changes the behaviour of the object at run time
