package com.softserve.edu.sprint4.task5;

class Array<T> {
    private T[] array;

    public Array(T[] array) {
        this.array = array;
    }

    public T get(int index) {
        return array[index];
    }

    public int length() {
        return array.length;
    }

    public double sum() {
        double sum = 0;
        for (T item : array) {
            sum += (double) item;
        }
        return sum;
    }
}

public class ArrayUtil {
    public static <T extends Number> double averageValue(Array<T> array) {
        return array.sum() / array.length();
    }

}