package com.retail.globomart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.globomart.model.Products;
import com.retail.globomart.repository.IProductJpaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/products")
@Api(value="ProductController API Description")
public class ProductController {

	@Autowired
	IProductJpaRepository productJpaRepository;
	
	@GetMapping("/all")
	@ApiOperation(httpMethod="GET", produces="application/json", value="Finds Products List", response=Products.class, responseContainer="List")
	public List<Products> findAllProducts(){
		return productJpaRepository.findAll();	
	}
	
	@GetMapping("/type/{type}")
	@ApiOperation(httpMethod="GET", produces="application/json", value="Finds Products List searched by Type", consumes="application/json", response=Products.class, responseContainer="List")
	public List<Products> findAllProductsByType(@ApiParam(value = "type needed to search product", required = true) @PathVariable final String type){
		return productJpaRepository.findByType(type);		
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation(httpMethod="GET", produces="application/json", value="Finds Product searched by Id", consumes="application/json", response=Products.class)
	public Optional<Products> findProductById(@ApiParam(value = "id needed to search product", required = true) @PathVariable final Long id){
		return productJpaRepository.findById(id);
	}
	
	@PostMapping()
	@ApiOperation(httpMethod="POST", produces="application/json", value="Adds Product", consumes="application/json", response=Products.class)
	public Optional<Products> addProduct(@ApiParam(value = "product to add product", required = true) @RequestBody final Products product) {
		productJpaRepository.save(product);
		return productJpaRepository.findById(product.getId());
	}
	
	@DeleteMapping("/id/{id}")
	@ApiOperation(httpMethod="DELETE", value="Deletes Product", consumes="application/json")
	public void deleteProduct( @ApiParam(value = "id needed to delete product", required = true) @PathVariable final Long id) {
		productJpaRepository.deleteById(id);
	}
}
