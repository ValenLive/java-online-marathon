package com.softserve.edu.sprint4.task4;

class ArrayUtil{
    public static <T> T setAndReturn(T[] arr, T item, int index){
        arr[index] = item;
        return arr[index];
    }
}
