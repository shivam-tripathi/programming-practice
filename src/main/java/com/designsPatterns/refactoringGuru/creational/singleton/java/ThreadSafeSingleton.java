package com.designsPatterns.refactoringGuru.creational.singleton.java;

// Examples: Runtime.getRuntime(), awt.Desktop.getDesktop(), System.getSecurityManager()

public final class ThreadSafeSingleton {
  // value is private, static and volatile
  // the field needs to be volatile to prevent cache incoherence issues.
  private static volatile ThreadSafeSingleton instance;
  public String value;

  private ThreadSafeSingleton(String value) {
    this.value = value;
  }

  public static ThreadSafeSingleton getInstance(String value) {
    if (instance == null) { // first check
      synchronized (ThreadSafeSingleton.class) {
        if (instance == null) { // second check
          instance = new ThreadSafeSingleton(value);
        }
      }
    }
    return instance;
  }

  public static void main(String[] args) {
    System.out.println("If you see the same value, then singleton was reused. Else there is a problem.");
    Thread threadFoo = new Thread(() -> {
      ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("foo");
      System.out.println("Singleton value: " + singleton.value);
    });
    Thread threadBar = new Thread(() -> {
      ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("bar");
      System.out.println("Singleton value: " + singleton.value);
    });
    threadFoo.start();
    threadBar.start();
  }
}

