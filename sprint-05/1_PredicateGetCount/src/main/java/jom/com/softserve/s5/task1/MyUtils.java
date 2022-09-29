package jom.com.softserve.s5.task1;

import java.util.Arrays;
import java.util.function.Predicate;


public class MyUtils {
    public static int getCount(int[] arr, Predicate<Integer> predicate) {
        return (int) Arrays.stream(arr)
                .filter(predicate::test)
                .count();
    }
}