package com.kushal.DynamoDb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamoDbApplication implements CommandLineRunner {

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
    public static void main(String[] args) {
        SpringApplication.run(DynamoDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String tableName = "Music";
		String key = "Artist";
		System.out.println("Creating an Amazon DynamoDB table : '" + tableName + "' with a simple primary key : '" + key + "'");
//		String result = createTable(amazonDynamoDB, tableName, key);
    }

//	private String createTable(AmazonDynamoDB amazonDynamoDB, String tableName, String key) {
//
//	}
}
