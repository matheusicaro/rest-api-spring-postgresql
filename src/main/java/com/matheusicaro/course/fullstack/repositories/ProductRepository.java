package com.matheusicaro.course.fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusicaro.course.fullstack.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
