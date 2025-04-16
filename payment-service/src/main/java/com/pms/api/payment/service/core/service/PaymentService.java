package com.pms.api.payment.service.core.service;

import com.pms.api.common.lib.exceptions.EntityNotFoundException;
import com.pms.api.payment.service.core.model.dto.PaymentRequest;
import com.pms.api.payment.service.core.model.dto.PaymentResponse;
import com.pms.api.payment.service.core.model.entity.Payment;
import com.pms.api.payment.service.core.repository.PaymentRepository;
import com.pms.api.payment.service.lib.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Business logic for payment data.
 */
@Service
public class PaymentService {
    /**
     * Connection to payment data.
     */
    private final PaymentRepository paymentRepository;

    /**
     * Model mapper for {@link Payment} and {@link PaymentResponse}.
     */
    private final PaymentMapper paymentMapper;

    /**
     * Dependency injection constructor.
     *
     * @param paymentRepository
     *         the data source for payment data
     * @param paymentMapper
     *         the mapper for converting between data models
     */
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    /**
     * Creates a new payment.
     *
     * @param paymentRequest
     *         the {@link PaymentRequest} from the {@code POST} mapping in the
     *         {@link com.pms.api.payment.service.core.controller.PaymentController}
     *
     * @return a {@link PaymentResponse} with the new payment details
     */
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        // Creating a new payment entity in the database
        Payment payment = paymentRepository.save(paymentMapper.toModel(paymentRequest));

        // Returning created payment
        return paymentMapper.toDTO(payment);
    }

    /**
     * Gets all payments.
     *
     * @return a list {@link PaymentResponse}'s with payment details
     */
    public List<PaymentResponse> getPayments() {
        // Retrieving all payments from the repository
        List<Payment> payments = paymentRepository.findAll();

        // Returning mapped payments as list of responses
        return payments.stream().map(paymentMapper::toDTO).toList();
    }

    /**
     * Gets a payment by ID.
     *
     * @param id
     *         the {@link UUID} associated with a {@link Payment}
     *
     * @return a {@link PaymentResponse} with a specific payment's details
     *
     * @throws EntityNotFoundException
     *         if the entity is not found in the repository
     */
    public PaymentResponse getPaymentById(UUID id) {
        // Verifying the ID exists and getting the payment
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Unable to find payment '" + id.toString() + "'"));

        // Returning the response
        return paymentMapper.toDTO(payment);
    }
}
