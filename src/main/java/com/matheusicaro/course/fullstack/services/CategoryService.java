package com.matheusicaro.course.fullstack.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
		
		List<Category> list = repository.findAll();
		
		List<CategoryDTO> listDto = list.stream()
				.map( category -> new CategoryDTO(category))
				.collect(Collectors.toList());
		
		return listDto;
	}
	
	public Page<CategoryDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Category> list = repository.findAll(pageRequest);
		
		Page<CategoryDTO> listDto = list.map( category -> new CategoryDTO(category));
		
		return listDto;
		
	}
	
	
	
}
