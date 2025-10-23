package org.example;

import org.example.Annotations.AfterSuite;
import org.example.Annotations.BeforeSuite;
import org.example.Annotations.CsvSource;
import org.example.Annotations.Test;
import org.example.Exceptions.SingleAnnotationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.Arrays;

public class TestRunner {

    public static void runTests(Object object) throws SingleAnnotationException, InvocationTargetException, IllegalAccessException {
        Class<?> representation = object.getClass();
        Method[] methods = representation.getDeclaredMethods();

        runBeforeAfterMethods(object, methods, BeforeSuite.class);
        runTestMethods(object, findMethodsWithAnnotations(methods, Test.class));
        runTestForCsvSource(object, findMethodsWithAnnotations(methods, CsvSource.class));
        runBeforeAfterMethods(object, methods, AfterSuite.class);
    }

    private static List<Method> findMethodsWithAnnotations(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    private static Optional<Method> checkBeforeAfterAnnotations(List<Method> methods, Class<? extends Annotation> annotationClass) throws SingleAnnotationException {
        // проверка на количество разрешенных методов (1)
        int count = methods.size();
        if (count == 1) {
            Method method = methods.getFirst();
            return Optional.of(method);
        } else if (count > 1) {
            throw new SingleAnnotationException("Error! " + annotationClass.getName() + " allowed to be defined only once!\n");
        } else  {
            return Optional.empty();
        }
    }

    private static void runBeforeAfterMethods(Object object, Method[] methods, Class<? extends Annotation> annotationClass) throws SingleAnnotationException, IllegalAccessException, InvocationTargetException {
        List<Method> annotatedMethods = findMethodsWithAnnotations(methods, annotationClass);
        checkBeforeAfterAnnotations(annotatedMethods, annotationClass).ifPresent(method -> {
            try {
                if (annotationClass.equals(BeforeSuite.class)) {
                    System.out.println("Running before suite...");
                } else {
                    System.out.println("Running after suite...");
                }
                method.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void runTestMethods(Object object, List<Method> methods) throws IllegalAccessException, InvocationTargetException {
        ArrayList<Map.Entry<Integer, Method>> mappedMethods = new ArrayList<>(methods.stream().map(method -> {
            Test testAnnotation = method.getAnnotation(Test.class);
            return Map.entry(testAnnotation.priority(), method);
        }).toList());
        mappedMethods.sort(Comparator.comparingInt(Map.Entry::getKey));

        for (Map.Entry<Integer, Method> item : mappedMethods) {
            try {
                Method method = item.getValue();
                int priority = item.getKey();
                System.out.println("Testing method with annotation " + Test.class.getSimpleName() + " : "+ method.getName() + " with priority: " + priority);
                method.invoke(object);
            } catch (InvocationTargetException e) {
                throw new InvocationTargetException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessException();
            }
        }
    }

    private static void runTestForCsvSource(Object object, List<Method> methods) throws InvocationTargetException, IllegalAccessException, IndexOutOfBoundsException {
        for (Method method : methods) {
            if (method.isAnnotationPresent(CsvSource.class)) {
                CsvSource testAnnotation = method.getAnnotation(CsvSource.class);
                String[] params = testAnnotation.paramString().split(",");
                Object[] newParams = new Object[params.length];

                for (int i = 0; i < params.length; i++) {
                    try {
                        int param = Integer.parseInt(params[i]);
                        newParams[i] = param;
                    } catch (NumberFormatException _) {
                        newParams[i] = params[i];
                    }
                }
                
                System.out.println("Testing method with annotation "
                        + CsvSource.class.getSimpleName() + ", "
                        + method.getName() + " with params: "
                        + Arrays.toString(params)
                        + " with new params: "
                        + Arrays.toString(newParams)
                );

                Object _ = method.invoke(object, newParams);
            }
        }
    }

}
