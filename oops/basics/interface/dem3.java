/**
 * Multiple inheritance
 */

interface MyInterface1 {
    void show1();
}

interface MyInterface2 {
    void show2();
}

interface MyInterface3 {
    void show3();
}

class MyClass implements MyInterface1, MyInterface2, MyInterface3 {
    @Override
    public void show1() {
        System.out.println("MyClass implementing MyInterface1");
    }
    @Override
    public void show2() {
        System.out.println("MyClass implementing MyInterface2");
    }
    @Override
    public void show3() {
        System.out.println("MyClass implementing MyInterface3");
    }
}

class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.show1();
        myClass.show2();
        myClass.show3();
    }
}
