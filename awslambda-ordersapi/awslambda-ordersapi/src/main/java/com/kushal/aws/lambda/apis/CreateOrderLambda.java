package com.kushal.aws.lambda.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushal.aws.lambda.apis.dto.Order;

public class CreateOrderLambda {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());


    public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) throws JsonProcessingException {
        Order order = objectMapper.readValue(request.getBody(), Order.class);

        //Dynamodb connection instance
        //get table
        Table ordersTable = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
        //creating a item for inserting into ordersTable
        Item item = new Item().withPrimaryKey("id", order.id)
                              .withString("itemName", order.itemName)
                              .withInt("quantity", order.quantity);
        ordersTable.putItem(item);

        return new APIGatewayProxyResponseEvent().withStatusCode(200)
                                                 .withBody("Order ID : " + order.id);
    }
}
