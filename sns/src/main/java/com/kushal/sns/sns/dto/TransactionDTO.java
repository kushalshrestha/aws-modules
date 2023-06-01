package com.kushal.sns.sns.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class TransactionDTO {
    private String id;

    private String paymentType;

    private BigDecimal amount;

    private String customerId;

    private LocalDate createdAt;

    private String sentFrom;

    public static TransactionDTO valueOf(TransactionRequestDto request) {
        return builder()
                .id(UUID.randomUUID().toString())
                .paymentType(request.getPaymentType())
                .amount(request.getAmount())
                .customerId(request.getCustomerId())
                .createdAt(LocalDate.now())
                .build();
    }
}
