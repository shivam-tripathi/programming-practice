/**
 * Interface: Specify what we are going to implement without specifying how.
 * No intance variables. Methods don't have a body (abstract methods).
 *
 * While implementing/overriding methods the method in the child/implementing
 * class must not have higher access restrictions than the one in the superclass
 *
 * Default access specifier for interface is public, unlike methods in classes.
 */

interface MyInterface {
    void show();
}

class MyClass implements MyInterface {
    // Note about @Overrride: It is not required, but if used it will generate a compile error if that
    //      method actually does not correctly override a method in a superclass.
    @Override
    public void show() { // Public
        System.out.println("MyClass implementing MyInterface");
    }
}

class Main {
    public static void main(String[] args) { // None of default, private, protected would work.
        MyClass obj = new MyClass();
        obj.show();
    }
}
