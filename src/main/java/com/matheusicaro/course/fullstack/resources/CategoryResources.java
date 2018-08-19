package com.matheusicaro.course.fullstack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.services.CategoryService;


@RestController()
@RequestMapping(path="/category")
public class CategoryResources {

	@Autowired
	CategoryService service;
	
	@RequestMapping(value="{Id}", method=RequestMethod.GET)
	public ResponseEntity<?> findById (@PathVariable Integer Id) {
		
		Category category = service.findById(Id);
		System.out.println();
		return ResponseEntity.ok().body(category);
	}
}
