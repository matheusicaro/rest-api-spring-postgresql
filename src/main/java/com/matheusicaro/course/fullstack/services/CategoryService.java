package com.matheusicaro.course.fullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRespository repository;

	public Category findById(Integer id) {

		Optional<Category> category = repository.findById(id);
		return category.orElse(null);
	}
}
