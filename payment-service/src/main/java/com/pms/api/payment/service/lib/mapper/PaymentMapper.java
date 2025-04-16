package com.pms.api.payment.service.lib.mapper;

import com.pms.api.common.lib.enums.PaymentMethod;
import com.pms.api.common.lib.mapper.ModelMapper;
import com.pms.api.payment.service.core.model.dto.PaymentRequest;
import com.pms.api.payment.service.core.model.dto.PaymentResponse;
import com.pms.api.payment.service.core.model.entity.Payment;
import com.pms.api.payment.service.lib.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Utility class for converting between entity models and Data Transfer Objects (DTOs) for payments.
 */
@Component
public class PaymentMapper implements ModelMapper<Payment, PaymentRequest, PaymentResponse> {
    /**
     * No args constructor.
     */
    PaymentMapper() {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adapts a {@link Payment} entity into a {@link PaymentResponse}
     *
     * @param payment
     *         the {@code Payment} linked to a repository
     *
     * @return a {@code PaymentResponse} DTO
     */
    @Override
    public PaymentResponse toDTO(Payment payment) {
        // Creating new response
        PaymentResponse paymentResponse = new PaymentResponse();

        // Setting response fields
        paymentResponse.setId(payment.getId().toString());
        paymentResponse.setCustomerId(payment.getCustomerId().toString());
        paymentResponse.setPaymentMethod(payment.getPaymentMethod().toString());
        paymentResponse.setAmount(payment.getAmount().toString());
        paymentResponse.setStatus(payment.getStatus().toString());
        paymentResponse.setCreatedAt(payment.getCreatedAt().toString());

        // Returning the response
        return paymentResponse;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adapts a {@link PaymentRequest} into a {@link Payment}
     *
     * @param request
     *         the {@code Request} from a controller
     *
     * @return a {@code Payment} entity model
     */
    @Override
    public Payment toModel(PaymentRequest request) {
        // Creating new model
        Payment payment = new Payment();

        // Setting model fields
        // Customer ID
        payment.setCustomerId(UUID.fromString(request.getCustomerId()));

        // Payment Method
        if (request.getPaymentMethod().equalsIgnoreCase(PaymentMethod.DEBIT.toString())) {
            payment.setPaymentMethod(PaymentMethod.DEBIT);
        } else if (request.getPaymentMethod().equalsIgnoreCase(PaymentMethod.CREDIT.toString())) {
            payment.setPaymentMethod(PaymentMethod.CREDIT);
        }

        // Amount
        payment.setAmount(new BigDecimal(request.getAmount()));

        // Payment Status
        if (request.getStatus().equalsIgnoreCase(PaymentStatus.COMPLETED.toString())) {
            payment.setStatus(PaymentStatus.COMPLETED);
        } else if (request.getStatus().equalsIgnoreCase(PaymentStatus.FAILED.toString())) {
            payment.setStatus(PaymentStatus.FAILED);
        }

        // Dates
        payment.setCreatedAt(LocalDateTime.parse(request.getCreatedAt()));

        // Returning the model
        return payment;
    }
}
