package com.matheusicaro.course.fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheusicaro.course.fullstack.domain.Payment;

@Repository
public interface PaymentRespository extends JpaRepository<Payment, Integer> {

}
