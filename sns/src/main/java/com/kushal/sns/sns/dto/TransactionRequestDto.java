package com.kushal.sns.sns.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequestDto {
    private String paymentType;

    private BigDecimal amount;

    private String customerId;
}
