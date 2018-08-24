package com.matheusicaro.course.fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheusicaro.course.fullstack.domain.OrderItem;

@Repository
public interface OrderItemRespository extends JpaRepository<OrderItem, Integer> {

}
