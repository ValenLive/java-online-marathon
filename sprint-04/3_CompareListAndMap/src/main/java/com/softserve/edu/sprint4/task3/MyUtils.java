package com.softserve.edu.sprint4.task3;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MyUtils {
    /**
     * Comparing list of Strings and Map
     */
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
       return new HashSet<>(list).containsAll(map.values());
    }
    
}