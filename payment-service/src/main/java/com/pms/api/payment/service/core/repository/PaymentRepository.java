package com.pms.api.payment.service.core.repository;

import com.pms.api.payment.service.core.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for connecting to payment data.
 *
 * @see Payment
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
