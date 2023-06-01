# AWS SNS (Simple Notification Service)

This is a sample project to use AWS SNS using Spring Boot. SNS is used in asynchronous software architecture.

## Running the application
### Dependency:
* AWS Messaging Dependency
```
<dependency>
   <groupId>io.awspring.cloud</groupId>
   <artifactId>spring-cloud-aws-messaging</artifactId>
   <version>2.4.0</version>
</dependency>
```

### How it works?
1. POSTMAN : POST METHOD

END POINT : http://localhost:8080/transactions
```
{
    "paymentType": "VISA",
    "amount": 101.20,
    "customerId": "c2365f3a-7441-4fc3-9045-68596b632609"
}
```

2. Create a topic in SNS
3. Create a SQS and subscribe to the topic