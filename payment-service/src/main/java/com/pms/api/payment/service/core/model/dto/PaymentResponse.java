package com.pms.api.payment.service.core.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pms.api.common.core.model.dto.ApiResponse;

/**
 * A Data Transfer Object (DTO) that models a response to the {@code /payments} endpoint.
 */
@JsonPropertyOrder({"id", "customerId", "paymentMethod", "amount", "status", "createdAt"})
public class PaymentResponse implements ApiResponse {
    private String id;
    private String customerId;
    private String paymentMethod;
    private String amount;
    private String status;
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter(value = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    @JsonSetter(value = "customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonGetter(value = "payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @JsonSetter(value = "payment_method")
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonGetter(value = "created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonSetter(value = "created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
