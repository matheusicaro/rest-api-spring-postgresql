package com.matheusicaro.course.fullstack.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusicaro.course.fullstack.domain.Category;
import com.matheusicaro.course.fullstack.dto.CategoryDTO;
import com.matheusicaro.course.fullstack.services.CategoryService;


@RestController()
@RequestMapping(path="/categories")
public class CategoryResources {

	@Autowired
	CategoryService service;
	
	@RequestMapping(value="{Id}", method=RequestMethod.GET)
	public ResponseEntity<Category> findById (@PathVariable Integer Id) {
		
		Category category = service.findById(Id);
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll () {
		
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<CategoryDTO> listDto = service.findPage(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertCategory (@RequestBody Category category){
		
		Category categorySaved = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(categorySaved.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{Id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCategory (@RequestBody Category category, @PathVariable Integer Id){
		category.setId(Id);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value= "/{Id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory (@PathVariable Integer Id){
		service.delete(Id);
		return ResponseEntity.noContent().build();
	}	
	
}
