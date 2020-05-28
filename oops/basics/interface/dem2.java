/**
 * - If the methods are incomplete then a class who is using the interface needs
 * to implement all the methods in the interfaces.
 * - If the class cannot implement all of them, it will announce its
 * incompleteness by marking itself abstract
 *
 * A class needs to implement all the methods defined in the interface.
 * Otherwise, it will be an abstract class.
 * = An abstract class must be declared with an abstract keyword.
 * = It can have abstract and non-abstract methods.
 * = It cannot be instantiated.
 * = It can have constructors and static methods also.
 * = It can have final methods which will force the subclass not to change the body of the method.
 *
 * If a class has an abstract method, it should be declared abstract as well.
 * All the methods of an interface are public abstract by default.
 */

interface MyInterface {
    void show1();
    void show2();
}

abstract class MyClass implements MyInterface {
    @Override
    public void show1() {
        System.out.println("MyClass implementing show1 from MyInterface");
    }
}

abstract class Vehicle {
    abstract void run();
}

class Bike extends Vehicle {
    @Override
    void run() { // No issues with default access specifier here
        System.out.println("Bike is a vehicle");
    }
}

class Main {
    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.run();
    }
}