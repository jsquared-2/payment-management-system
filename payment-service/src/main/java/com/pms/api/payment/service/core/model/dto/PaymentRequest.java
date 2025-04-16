package com.pms.api.payment.service.core.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pms.api.common.core.model.dto.ApiRequest;
import com.pms.api.common.lib.validation.group.CreateResourceValidationGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * A Data Transfer Object (DTO) that models a request from the {@code /payments} endpoint.
 */
@JsonPropertyOrder({"customerId", "paymentMethod", "amount", "status", "createdAt"})
public class PaymentRequest implements ApiRequest {
    @NotBlank(message = "Customer ID is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "ID must be of type UUID")
    private String customerId;

    @Schema(allowableValues = {"DEBIT", "CREDIT"})
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotBlank(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "Amount must be less than or equal to 999999999.99")
    @Digits(integer = 9, fraction = 2, message = "Amount must have up to 9 digits before the decimal and up to 2 after")
    private String amount;

    @Schema(allowableValues = {"COMPLETED", "FAILED"})
    @NotBlank(message = "Status is required")
    private String status;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @NotBlank(message = "Created at date is required", groups = {CreateResourceValidationGroup.class})
    private String createdAt;

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
