package jom.com.softserve.s5.task5;

import java.util.Set;
import java.util.function.Predicate;

class MyUtils {
    public static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> set){
        Predicate<Integer> result = i -> true;
        for (Predicate<Integer> predicate: set) {
            result = result.and(predicate);
        }
        return result;
    }
}
