package Homework_02;

import java.util.*;
import java.util.stream.Collectors;

public class Homework_02 {
    public static void main(String[] args) {
        // 1
        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        List<Integer> listInt01 = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        int thirdMax = listInt01.stream().sorted(Collections.reverseOrder()).skip(2).findFirst().get();
        System.out.println("\n=== 1");
        System.out.println(thirdMax);

        // 2
        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        int thirdUniqueMax = listInt01.stream()
                .sorted(Collections.reverseOrder())
                .distinct()
                .skip(2)
                .findFirst().get();
        System.out.println("\n=== 2");
        System.out.println(thirdUniqueMax);

        // 3
        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо
        // получить список имен 3 самых старших сотрудников с должностью «Инженер»,
        // в порядке убывания возраста

        List<Employee> employees = Arrays.asList(
                new Employee("Alex", 20, "Engineer"),
                new Employee("Kate", 25, "Manager"),
                new Employee("Bob", 30, "Guard"),
                new Employee("Alice", 35, "HR"),
                new Employee("John", 40, "Accountant"),
                new Employee("Barak", 45, "Engineer"),
                new Employee("Tom", 50, "Engineer")
        );

        String[] engineersNamesList = employees.stream()
                .filter(employee -> employee.getJob().equals("Engineer"))
                .sorted(new AgeComparator().reversed())
                .limit(3)
                .map(Employee::getName)
                .toArray(String[]::new);
        System.out.println("\n=== 4");
        System.out.println(Arrays.toString(engineersNamesList));

        // 4
        // Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // посчитайте средний возраст сотрудников с должностью «Инженер»
        int[] engineersAge = employees.stream()
                .filter(employee -> employee.getJob().equals("Engineer"))
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .toArray();
        int middleAge = Arrays.stream(engineersAge).sum() / engineersAge.length;
        System.out.println("\n=== 5");
        System.out.println(middleAge);

        // 5
        // Найдите в списке слов самое длинное
        List<String> wordsList = Arrays.asList("найдите", "в", "списке", "слов", "слов", "алой", "самое", "длинное");
        System.out.println("\n=== 6");
        wordsList.stream()
                .max(Comparator.comparing(String::length))
                .ifPresent(System.out::println);

        // 6
        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        // Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается
        // во входной строке
        Map<String, Long> freq = wordsList.stream()
                .collect(Collectors.groupingBy(String::new, Collectors.counting()));
        System.out.println("\n=== 7");
        System.out.println(freq);

        // 7
        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют
        // одинаковую длины, то должен быть сохранен алфавитный порядок
        System.out.println("\n=== 8");
        wordsList.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(Comparator.comparing(String::new)))
                .forEach(System.out::println);

        // 8
        // Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом,
        // найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        String[] wordsArray =  { "red green blue black orange", "fly bark sleep programming driving" };
        System.out.println("\n=== 9");
        Arrays.stream(wordsArray)
                .flatMap(word -> Arrays.stream(word.split(" ")))
                .sorted(Comparator.comparing(String::length, Comparator.reverseOrder()))
                .findFirst()
                .ifPresent(System.out::println);
    }
}
