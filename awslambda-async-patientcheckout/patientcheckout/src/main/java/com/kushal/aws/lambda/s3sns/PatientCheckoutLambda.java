package com.kushal.aws.lambda.s3sns;


import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Lambda function: Handles event trigger from S3
 */
public class PatientCheckoutLambda {

    AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    ObjectMapper objectMapper = new ObjectMapper();
    AmazonSNS sns = AmazonSNSClientBuilder.defaultClient();

    public void handler(S3Event event) {

        event.getRecords()
             .forEach(record -> {

                 // Getting object's content
                 S3ObjectInputStream s3inputStream = s3.getObject(record.getS3()
                                                                        .getBucket()
                                                                        .getName(), record.getS3()
                                                                                          .getObject()
                                                                                          .getKey())
                                                       .getObjectContent();
                 try {
                     List<PatientCheckoutEvent> patientCheckoutEvents = Arrays.asList(objectMapper.readValue(s3inputStream, PatientCheckoutEvent[].class));
                     System.out.println("-------- Patient Checkout Events ---------");
                     System.out.println(patientCheckoutEvents);
                     patientCheckoutEvents.forEach(checkoutEvent -> {
                         try {
                             sns.publish(System.getenv("PATIENT_CHECKOUT_TOPIC"), objectMapper.writeValueAsString(checkoutEvent));
                         } catch (JsonProcessingException e) {
                             throw new RuntimeException(e);
                         }
                     });
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }

             });

    }
}
