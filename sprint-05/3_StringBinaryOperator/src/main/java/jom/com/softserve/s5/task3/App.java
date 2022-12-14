package jom.com.softserve.s5.task3;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class App {

    public static BinaryOperator<String> greetingOperator = (a, b) -> String.format("Hello %s %s!!!", a, b);

    public static List<String> createGreetings(List<Person> people, BinaryOperator<String> greetingOperator) {
        return people.stream()
                .map(person -> greetingOperator.apply(person.name, person.surname))
                .collect(Collectors.toList());
    }
}
