package org.example;

import org.example.Annotations.AfterSuite;
import org.example.Annotations.BeforeSuite;
import org.example.Annotations.CsvSource;
import org.example.Annotations.Test;

public class Dog extends Animal {
    public String name;

    public Dog(String name) {
        super(name);
    }

    @Test(priority = 1)
    public void eat() {
        System.out.println(this.getName() + " eating...\n");
    }

    @Test
    public void sleep() {
        System.out.println(this.getName() + " sleeping...\n");
    }

    @Test(priority = 3)
    public void run() {
        System.out.println(this.getName() + " running...\n");
    }

    @AfterSuite
    public static void bark() {
        System.out.println("Some barking...\n");
    }

    @CsvSource(paramString = "77, brown")
    public void say(int speed, String color) {
        System.out.println(this.getName() + " saying: Hello! My speed: " + speed + " and my color: " + color + "\n");
    }
}
