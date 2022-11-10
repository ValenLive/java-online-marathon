package jom.com.softserve.s6.task7;

import java.util.HashSet;
import java.util.stream.Stream;

public class MyUtils {
    public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        HashSet<Integer> hashSet = new HashSet<>();
        return stream.filter(num -> num != null && !hashSet.add(num))
                .distinct()
                .sorted();
    }
}

