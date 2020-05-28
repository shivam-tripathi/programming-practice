/**
 * We can cast implementing object to that of interface.
 * If the name of interface functions are same, each invokation will be valid
 */

interface MyInterface1 {
    void show();
}

interface MyInterface2 {
    void show();
}

class MyClass implements MyInterface1, MyInterface2 {
    @Override
    public void show() {
        System.out.println("MyClass implementing show() method");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.show();

        MyInterface1 inter1 = (MyInterface1)myClass;
        inter1.show();

        MyInterface2 inter2 = (MyInterface2)myClass;
        inter2.show();
    }
}