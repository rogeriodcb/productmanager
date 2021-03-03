package com.gs.productmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gs.productmanager.dto.MessageResponseDTO;
import com.gs.productmanager.model.Product;
import com.gs.productmanager.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>**************************************************************************************
* <p>                                 ProductController
* <p>**************************************************************************************
* <p>
* This Rest API controller provides CRUD for product.
* <p> To comply with the requirement the following implementations were made:
* <li> Creation,
* <li> Reading, 
* <li> Updating
* <li> Deletion operations.
*/
@RestController
@RequestMapping(value="/product-api/v1")
@Api(value="API REST for product CRUD operations")
@CrossOrigin(origins="*")
public class ProductController {
	
	private ProductService productService;

	// constructor
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// Rest mappings
	// CRUD operations
	
	// ********** Create ********** //
	// create product
	@PostMapping("/product")
	@ApiOperation(value="This method creates new product.")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDTO create(@RequestBody @Valid Product product) {
		return productService.save(product);		
	}

	// ********** Read ********** //
	// returns all products or a product with specific name registered
	@GetMapping("/products")
	@ApiOperation(value="This method returns all products or a product with specific name registered.")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<Product>> getProduct(@RequestParam(required = false) String name) {
		return productService.getProduct(name);
	}
	
	// return product by id
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
	    return productService.getProductById(id);
	}
	
	// ********** Update ********** //

	// update product by id
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
	    return productService.update(id,product);
	}
	
	// ********** Delete ********** //

	// delete product by pass id
	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
		return productService.deleteProduct(id);
	}
	  
	// delete all products
	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		return productService.deleteAllProducts();

	}
}
