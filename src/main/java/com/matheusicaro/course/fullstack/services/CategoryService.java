package com.matheusicaro.course.fullstack.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.dto.CategoryDTO;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;
import com.matheusicaro.course.fullstack.services.exceptions.DataIntegrityException;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;


@Service
public class CategoryService {

	@Autowired
	private CategoryRespository repository;

	public Category findById(Integer id) {

		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Not Found! " + "Id: " + id + ", Tipo: " + Category.class.getSimpleName()));
	}

	public Category insert(Category category) {

		category.setId(null);
		return repository.save(category);
	}

	public Category update(Category category) {
		
		findById(category.getId()); 
		return repository.save(category);
	}

	public void delete(Integer id) {
		
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a category that has products");
		}
	}
	
	public List<CategoryDTO> findAll () {
		
		List<Category> listCategories = repository.findAll();
		
		List<CategoryDTO> listDto = listCategories.stream()
				.map( category -> new CategoryDTO(category))
				.collect(Collectors.toList());
		
		return listDto;
	}
	
	
}
