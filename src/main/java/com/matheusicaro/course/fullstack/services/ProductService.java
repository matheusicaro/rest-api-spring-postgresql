package com.matheusicaro.course.fullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.domain.Product;
import com.matheusicaro.course.fullstack.repositories.CategoryRespository;
import com.matheusicaro.course.fullstack.repositories.ProductRepository;
import com.matheusicaro.course.fullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRespository categoryRepository;

	public Product find(Integer id) {
	
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);	
	}
}