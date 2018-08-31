package com.matheusicaro.course.fullstack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional(readOnly=true)
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String nome, List<Category> categorias,Pageable pageRequest);

}
