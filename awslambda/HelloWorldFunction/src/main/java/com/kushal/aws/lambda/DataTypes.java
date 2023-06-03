package com.kushal.aws.lambda;

import java.util.*;

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

    /**
     * For testing this method: change events.json with a List of String : ["John", "Kushal", "No name"]
     *
     * @param names
     * @return
     */
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

    /**
     * event.json input should be in json for Map Like:
     * {
     *   "John": 80,
     *   "Kushal": 95,
     *   "Messi": 100
     * }
     * @param scores
     * @return
     */
    public Map<String, Integer> getScoreGreaterThan90(Map<String, Integer> scores) {
        Map<String, Integer> filteredScores = new HashMap<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > 90) {
                filteredScores.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredScores;
    }
}
