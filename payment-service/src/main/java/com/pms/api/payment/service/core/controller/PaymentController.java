package com.pms.api.payment.service.core.controller;

import com.pms.api.payment.service.core.model.dto.PaymentRequest;
import com.pms.api.payment.service.core.model.dto.PaymentResponse;
import com.pms.api.payment.service.core.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Handles requests for the {@code /payments} resource.
 */
@RestController
@RequestMapping(value = "/payments", produces = "application/json")
@Tag(name = "Payment", description = "Microservice for handling payment data")
public class PaymentController {
    /**
     * Connection to business logic for the {@code /payments} resource.
     */
    private final PaymentService paymentService;

    /**
     * Dependency injection constructor
     *
     * @param paymentService
     *         business logic for payment data
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Creates a new payment.
     *
     * @param paymentRequest
     *         the {@link PaymentRequest}
     *
     * @return a {@link PaymentResponse} with a 201 status code and the URI of the new payment
     */
    @PostMapping
    @Operation(summary = "Create a new payment", responses = {@ApiResponse(responseCode = "201", headers = {@Header(name = "Location", description = "Relative URI of the newly created payment")})})
    public ResponseEntity<PaymentResponse> createPayment(
            @Validated({Default.class}) @RequestBody PaymentRequest paymentRequest
    ) {
        // Setting date property for request
        paymentRequest.setCreatedAt(LocalDateTime.now().toString());

        // Retrieving created payment
        PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest);

        // Returning payment with successful response
        return ResponseEntity.created(URI.create("/payments/" + paymentResponse.getId())).body(paymentResponse);
    }

    /**
     * Gets all payments.
     *
     * @return a {@link List} of {@link PaymentResponse} with a 200 status code
     */
    @GetMapping
    @Operation(summary = "Get all payments")
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        // Retrieving payment data
        List<PaymentResponse> payments = paymentService.getPayments();

        // Returning successful response
        return ResponseEntity.ok().body(payments);
    }

    /**
     * Gets a specific payment.
     *
     * @param id
     *         the {@link UUID} of the payment
     *
     * @return a specific {@link PaymentResponse} with a 200 status code
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a payment")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable UUID id) {
        // Returning successful response
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }
}
