package com.kushal.sns.sns.service;

import com.kushal.sns.sns.dto.TransactionDTO;
import com.kushal.sns.sns.dto.TransactionRequestDto;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * This class has a method that receives from the controller a request, transforms it into a DTO,
 * and sends this DTO to the topic using different methods available in the NotificationMessagingTemplate class.
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${aws.sns-topic.name}")
    private String topicName;

    public void processRequest(TransactionRequestDto request) {

        log.info("Received request {}.", request);
        TransactionDTO transactionDTO = TransactionDTO.valueOf(request);

        log.info("Sending purchase transaction");

        //1. Using send method
        Message<TransactionDTO> message = new GenericMessage<>(transactionDTO);
        transactionDTO.setSentFrom("Method send args <<generic message>>. Using default topic.");
        log.info("Method send args <<generic message>>. Using default topic.");
        notificationMessagingTemplate.send(message);

        transactionDTO.setSentFrom("Method send args <<topic name>> and <<generic message>>.");
        log.info("Method send args <<topic name>> and <<generic message>>.");
        notificationMessagingTemplate.send(topicName, message);

        log.info("Method send args <<topic name>> and <<generic message>>. Message with headers");
        HashMap<String, Object> messageHeaders = new HashMap<>();
        messageHeaders.put("Key_send_message", "Value_send_message");
        transactionDTO.setSentFrom("Method send args <<topic name>> and <<generic message>>. Message with headers");
        message = new GenericMessage<>(transactionDTO, messageHeaders);
        notificationMessagingTemplate.send(topicName, message);

        //2. Using convertAndSend method
        log.info("Method convertAndSend args <<object transactionDTO>>. Using default topic.");
        transactionDTO.setSentFrom("Method convertAndSend args <<object transactionDTO>>. Using default topic.");
        notificationMessagingTemplate.convertAndSend(new GenericMessage<>(transactionDTO));

        log.info("Method convertAndSend args  <<topic name>> and <<object transactionDTO>>. ");
        transactionDTO.setSentFrom("Method convertAndSend args <<topic name>> and <<object transactionDTO>>.");
        notificationMessagingTemplate.convertAndSend(topicName, transactionDTO);

        log.info("Method convertAndSend args <<topicName>> , <<object transactionDTO>>, <<messageHeaders>>.");
        transactionDTO.setSentFrom("Method convertAndSend args <<topicName>> , <<object transactionDTO>>, <<messageHeaders>>.");
        messageHeaders.put("Key_send_message", "Value_convertAndSend_message");
        notificationMessagingTemplate.convertAndSend(topicName, transactionDTO, messageHeaders);

        //3. Using sendNotification method
        log.info("Method sendNotification args <<object transactionDTO>>, <<subject>>. Using default topic.");
        transactionDTO.setSentFrom("Method sendNotification args <<object transactionDTO>>, <<subject>>. Using default topic.");
        notificationMessagingTemplate.sendNotification(transactionDTO, "transaction-subject");

        log.info("Method sendNotification args <<topicName>> , <<object transactionDTO>>, <<subject>>.");
        transactionDTO.setSentFrom("Method sendNotification args <<topicName>> , <<object transactionDTO>>, <<subject>>.");
        notificationMessagingTemplate.sendNotification(topicName, transactionDTO, "transaction-subject");
    }
}
