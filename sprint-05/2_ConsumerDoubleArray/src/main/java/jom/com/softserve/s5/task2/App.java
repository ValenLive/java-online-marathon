package jom.com.softserve.s5.task2;

import java.util.Arrays;
import java.util.function.Consumer;

public class App {
    public static final Consumer<double[]> cons = (double[] arr) -> {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 2) {
                arr[i] *= 0.8;
            } else {
                arr[i] *= 0.9;
            }
        }
    };

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
        double[] newArr = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(newArr);
        return newArr;
    }
}

