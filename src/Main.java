import Exceptions.SingleAnnotationException;

import java.lang.reflect.InvocationTargetException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dog bars = new Dog("Bars");
        try {
            TestRunner.runTests(bars);
        } catch (RuntimeException | SingleAnnotationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}