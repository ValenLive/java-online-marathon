package com.softserve.edu.sprint4.task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyUtils {
    // Code
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if (phones.containsValue(null)) return new HashMap<>();
        return phones
                .keySet()
                .stream()
                .collect(Collectors.groupingBy(phones::get));
    }
}