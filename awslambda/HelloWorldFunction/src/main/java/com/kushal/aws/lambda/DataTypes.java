package com.kushal.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class DataTypes {

    private static final Double staticVariable = Math.random();

    static {
        System.out.println("Static Block Executed");
    }

    private final Double instanceVariable = Math.random();

    public DataTypes() {
        System.out.println("Inside Constructor");
    }

    public void coldstartBasics() {
        Double localVariable = Math.random();
        System.out.println("Static Variable : " + staticVariable);
        System.out.println("Instance Variable : " + instanceVariable);
        System.out.println("Local Variable : " + localVariable);
    }

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
     * "John": 80,
     * "Kushal": 95,
     * "Messi": 100
     * }
     *
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

    /**
     * For Map of Lists
     * INPUT: Empty
     * OUTPUT: {"Bob":[80,70,90],"John":[80,90,100],"Kushal":[80,90,20]}%
     *
     * @return
     */
    public Map<String, List<Integer>> getStudentScores() {
        Map<String, List<Integer>> studentScores = new HashMap<String, List<Integer>>();
        studentScores.put("John", Arrays.asList(80, 90, 100));
        studentScores.put("Bob", Arrays.asList(80, 70, 90));
        studentScores.put("Kushal", Arrays.asList(80, 90, 20));
        return studentScores;
    }

    /**
     * For POJOs
     *
     * @param patient
     * @return
     */
    public ClinicalData getClinicals(Patient patient) {
        System.out.println("Name : " + patient.getName());
        System.out.println("SSN : " + patient.getSsn());
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setBp("80/120");
        clinicalData.setHeartRate("80");
        return clinicalData;
    }

    /**
     * For Input and Output Streams
     * INPUT SAMPLE: ALL THE POWER IS WITH IN YOU. YOU can do anything, just BELIEVE it.
     *
     * @param input
     * @param output
     * @throws IOException
     */
    public void getOutputInLowerCase(InputStream input, OutputStream output) throws IOException {
        int data;
        while ((data = input.read()) != -1) {
            output.write(Character.toLowerCase(data));
        }
    }

    /**
     * Playing with AWS Lambda Context Object
     *
     * @param input
     * @param output
     * @throws IOException
     */
    public void playWithAWSLambdaContextObject(InputStream input, OutputStream output, Context context) throws IOException {
        System.out.println("Request ID : " + context.getAwsRequestId());
        System.out.println("Function Name : " + context.getFunctionName());
        System.out.println("Remaining Time In Millis : " + context.getRemainingTimeInMillis());
        System.out.println("Get Memory Limit In MB : " + context.getMemoryLimitInMB());
        System.out.println("Get Group Name " + context.getLogGroupName());
        int data;
        while ((data = input.read()) != -1) {
            output.write(Character.toLowerCase(data));
        }
    }

    /**
     * Set Timeout
     * Default Timeout: 20 seconds i.e 20000ms
     * To change the timout period, modify template.yaml file with Timeout: X <- in seconds
     *
     * @param input
     * @param output
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    public void setTimeOut(InputStream input, OutputStream output, Context context) throws IOException, InterruptedException {
        Thread.sleep(21000);
        System.out.println("Request ID : " + context.getAwsRequestId());
        System.out.println("Function Name : " + context.getFunctionName());
        System.out.println("Remaining Time In Millis : " + context.getRemainingTimeInMillis());
        System.out.println("Get Memory Limit In MB : " + context.getMemoryLimitInMB());
        System.out.println("Get Group Name " + context.getLogGroupName());
        int data;
        while ((data = input.read()) != -1) {
            output.write(Character.toLowerCase(data));
        }
    }


    /**
     * Passing environment variables dynamically in our lambda function
     *
     * @param input
     * @param output
     */
    public void getEnvironmentVariables(InputStream input, OutputStream output) {
        System.out.println(System.getenv("restapiurl"));
    }
}
