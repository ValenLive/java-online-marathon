package jom.com.softserve.s6.task6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils {
    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        Map<Object, List<String>> map = list.stream()
                .flatMap(item -> item)
                .map(str -> str.replaceAll("\\D+", ""))
                .collect(Collectors.groupingBy(i -> i.substring(0, 3)));

        map.forEach((o1, o2) -> System.out.println("Starts with: " + o1 + ", ends with: "+o2));
        return null;
    }
}
