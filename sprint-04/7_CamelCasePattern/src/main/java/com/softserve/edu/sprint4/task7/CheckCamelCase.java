package com.softserve.edu.sprint4.task7;

import java.lang.reflect.Method;

class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}

class Class1 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void InCorrect() {
    }

    @CamelCase
    public void JustMethod() {
    }
}

class Class2 {
    @CamelCase
    public void correct() {
    }

    @CamelCase
    public void oneMoreCorrect() {
    }
}

public class CheckCamelCase {
    public final static String CAMELCASE_PATTERN = "[a-z]\\W";
    public final static String ERROR_MESSAGE_TEMPLATE = "method %s.%s doesn't satisfy camelCase naming convention\n";

    public static boolean checkAndPrint(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CamelCase.class) && !method.getName().matches(CAMELCASE_PATTERN)) {
                System.out.printf(ERROR_MESSAGE_TEMPLATE, clazz.getName(), method.getName());
                return false;
            }
        }
        return true;
    }

}
