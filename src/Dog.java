import Annotations.AfterSuite;
import Annotations.BeforeSuite;
import Annotations.CsvSource;
import Annotations.Test;

public class Dog {
    public String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test(priority = 1)
    public void eat() {
        System.out.println(this.name + " eating...\n");
    }

    @Test
    public void sleep() {
        System.out.println(this.name + " sleeping...\n");
    }

    @BeforeSuite
    public static void run() {
        System.out.println("Some running...\n");
    }

    @AfterSuite
    public static void bark() {
        System.out.println("Some barking...\n");
    }

    @CsvSource(paramString = "77, brown")
    public void say(int speed, String color) {
        System.out.println(this.name + " saying: Hello! My speed: " + speed + " and my color: " + color + "\n");
    }
}
