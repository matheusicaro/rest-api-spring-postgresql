package com.matheusicaro.course.fullstack.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheusicaro.course.fullstack.domain.Category;


@RestController()
@RequestMapping(path="/category")
public class CategoryResources {

	@RequestMapping(method=RequestMethod.GET)
	public List<Category> listCategory () {
		
		Category c = new Category(1, "teste");
		Category c2 = new Category(2, "teste-2");

		List<Category> categories = new ArrayList<>();
		
		categories.add(c);
		categories.add(c2);
		
		return categories;
		
	}
	
}
