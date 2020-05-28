/**
 * An interface can extend but not implement another interface.
 *
 * Class object can be cast to objects of interface above immediate interface being implemented.
 * Only the functions belonging to that parent interface can be executed using this object.
 *
 * Tagging Interface/ Marker interface
 * An interface which is empty is termed as a tag/tagging interface
 *      - We can create a common parent.
 *      - A class can claim membership in the set e.g. if our class implements the
 *          Serializable interface, it becomes serializable. So, our class actually becomes an
 *          interface type through polymorphism. Even a class that is implementing a tagging
 *          interface, need not define any new method because the interface itself does not have
 *          any such method.
 *      - We can implement thread safety through marker interfaces.
 *
 * Annotations
 *      More popular than marker interfaces
 */

interface BaseInterface1 {
    void show1();
}

interface BaseInterface2 {
    void show2();
}

interface MyInterface extends BaseInterface1, BaseInterface2 {
    void show3();
}

class MyClass implements MyInterface {
    @Override
    public void show1() {
        System.out.println("Implementing show1");
    }

    @Override
    public void show2() {
        System.out.println("Implementing show2");
    }

    @Override
    public void show3() {
        System.out.println("Implementing show3");
    }
}

// Tagging interface or Marker interface
interface TaggingInterface {}

interface AnnotationInterface {
    @Deprecated
    void oldMethod();

    void newMethod();
}

class AnnotatedInterfaceImplementClass implements AnnotationInterface {
    @Override
    public void oldMethod() {
        System.out.println("Old Method");
    }

    @Override
    public void newMethod() {
        System.out.println("New Method");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.show1();
        myClass.show2();
        myClass.show3();

        MyInterface iter = (MyInterface)myClass;
        iter.show3();

        BaseInterface2 iter2 = (BaseInterface2)myClass;
        iter2.show2();

        BaseInterface1 iter1 = (BaseInterface1)myClass;
        iter1.show1();

        AnnotatedInterfaceImplementClass ann = new AnnotatedInterfaceImplementClass();
        ann.newMethod();
        ann.oldMethod(); // Just a warning
    }
}