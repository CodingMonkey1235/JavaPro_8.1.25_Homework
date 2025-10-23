package org.example;

import org.example.Exceptions.SingleAnnotationException;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Dog bars = new Dog("Bars");
        Cat boris = new Cat("Boris");

        try {
            TestRunner.runTests(bars);
        } catch (RuntimeException | SingleAnnotationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            TestRunner.runTests(boris);
        } catch (RuntimeException | SingleAnnotationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}