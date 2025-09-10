import Annotations.AfterSuite;
import Annotations.BeforeSuite;
import Annotations.CsvSource;
import Annotations.Test;
import Exceptions.SingleAnnotationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class TestRunner {

    public static void runTests(Object object) throws SingleAnnotationException, InvocationTargetException, IllegalAccessException {
        Class<?> representation = object.getClass();
        Method[] methods = representation.getDeclaredMethods();

        Optional<Method> beforeSuite = findBeforeSuite(methods);
        beforeSuite.ifPresent(beforeSuiteMethod -> {
            try {
                System.out.println("Running before suite...");
                beforeSuiteMethod.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });

        runTestMethods(object, methods);
        runTestForCsvSource(object, methods);

        Optional<Method> afterSuite = findAfterSuite(methods);
        afterSuite.ifPresent(afterSuiteMethod -> {
            try {
                System.out.println("Running after suite...");
                afterSuiteMethod.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Optional<Method> findBeforeSuite(Method[] methods) throws SingleAnnotationException {
        List<Method> foundMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                foundMethods.add(method);
            }
        }
        int count = foundMethods.size();
        if (count == 1) {
            Method method = foundMethods.getFirst();
            return Optional.of(method);
        } else if (count > 1) {
            throw new SingleAnnotationException("Error! BeforeSuite allowed to be defined only once!\n");
        } else  {
            return Optional.empty();
        }
    }

    private static Optional<Method> findAfterSuite(Method[] methods) throws SingleAnnotationException {
        List<Method> foundMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                foundMethods.add(method);
            }
        }
        int count = foundMethods.size();
        if (count == 1) {
            Method method = foundMethods.getFirst();
            return Optional.of(method);
        } else if (count > 1) {
            throw new SingleAnnotationException("Error! AfterSuite allowed to be defined only once!\n");
        } else  {
            return Optional.empty();
        }
    }

    private static void runTestMethods(Object object, Method[] methods) throws IllegalAccessException, InvocationTargetException {
        ArrayList<Map.Entry<Integer, Method>> foundMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                Map.Entry<Integer, Method> item = Map.entry(testAnnotation.priority(), method);
                foundMethods.add(item);
            }
        }
        foundMethods.sort(Comparator.comparingInt(Map.Entry::getKey));
        for (Map.Entry<Integer, Method> item : foundMethods) {
            try {
                Method method = item.getValue();
                int priority = item.getKey();
                System.out.println("Testing method: "+ method.getName() + " with priority: " + priority);
                method.invoke(object);
            } catch (InvocationTargetException e) {
                throw new InvocationTargetException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessException();
            }
        }
    }

    private static void runTestForCsvSource(Object object, Method[] methods) throws InvocationTargetException, IllegalAccessException, IndexOutOfBoundsException {
        for (Method method : methods) {
            if (method.isAnnotationPresent(CsvSource.class)) {
                CsvSource testAnnotation = method.getAnnotation(CsvSource.class);
                String[] params = testAnnotation.paramString().split(",");
//                TypeVariable<?>[] typeVariables = method.getParameters();

//                System.out.println("Testing method " + method.getName() + " with params: " + Arrays.toString(params) /*+ " with typeVariables: " + Arrays.toString(typeVariables)*/);

//                if (params.length != typeVariables.length) {
//                    throw new IndexOutOfBoundsException("Method " + method.getName() + " params count: " + typeVariables.length + " not equal to annotation count: " + params.length);
//                }

                Object[] newParams = new Object[params.length];

                for (int i = 0; i < params.length; i++) {
                    try {
                        int param = Integer.parseInt(params[i]);
                        newParams[i] = param;
                    } catch (NumberFormatException _) {
                        newParams[i] = params[i];
                    }
                }

                System.out.println("Testing method "
                        + method.getName() + " with params: "
                        + Arrays.toString(params)
                        /*+ " with typeVariables: " + Arrays.toString(typeVariables)*/
                        + " with new params: "
                        + Arrays.toString(newParams)
                );

                // не знаю как сделать не гуглится нормально
//                for (int i = 0; i < typeVariables.length; i++) {
//                    if (typeVariables[i].getGenericDeclaration() instanceof Integer) {
//                        int param = Integer.parseInt(params[i]);
//                        newParams[i] = param;
//                    }
//                }

                Object _ = method.invoke(object, newParams);
            }
        }
    }
}
