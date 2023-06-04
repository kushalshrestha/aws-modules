package com.kushal.aws.lambda.apis;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushal.aws.lambda.apis.dto.Order;

public class ReadOrdersLambda {
    public APIGatewayProxyResponseEvent getOrders(APIGatewayProxyRequestEvent request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order(123, "Mac Book Pro", 100);
        String jsonOutput = objectMapper.writeValueAsString(order);
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);
    }
}
