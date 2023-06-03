package com.kushal.aws.lambda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataTypes {

    /**
     * Creating a lambda function method with input param float and return type of int
     *
     * @param number
     * @return
     */
    public int getNumber(float number) {
        return (int) number;
    }

    public boolean isGreaterThan100(float number) {
        return number > 100;
    }

    public List<Integer> getScores(List<String> names) {
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("John", 90);
        studentScores.put("Bob", 80);
        studentScores.put("Kushal", 100);

        List<Integer> matchingScores = new LinkedList<>();
        names.forEach(name -> {
            matchingScores.add(studentScores.get(name));
        });
        return matchingScores;
    }
}
