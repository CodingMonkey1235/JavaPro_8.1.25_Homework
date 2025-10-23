package org.example;

import org.example.Annotations.BeforeSuite;
import org.example.Annotations.Test;

public class Cat extends Animal{

    public Cat(String name) {
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

    @BeforeSuite
    public void fightWithDog() {
        System.out.println(this.getName() + " fighting with dog...\n");
    }

    @BeforeSuite
    public void fightWithPigeon() {
        System.out.println(this.getName() + " fighting with pigeon...\n");
    }

}
