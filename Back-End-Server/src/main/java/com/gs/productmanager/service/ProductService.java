package com.gs.productmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gs.productmanager.dto.MessageResponseDTO;
import com.gs.productmanager.model.Product;
import com.gs.productmanager.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	// constructor
	@Autowired
	public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	

	// CRUD operations
	
	// ********** Create ********** //
	// call repository to save new product
    public MessageResponseDTO save(Product product) {
    	Product savedProduct = productRepository.save(product);
		return MessageResponseDTO.builder()
				.message("Product created with ID " + savedProduct.getId())
				.build();
    }
    
    
    // ********** Read ********** //
    
    // get products or one product by passing its name
    public ResponseEntity<List<Product>> getProduct(String name){
    	try {
			List<Product> products = new ArrayList<Product>();

		      if (name == null || name.isEmpty())
		      	productRepository.findAll().forEach(products::add);
		      else
		        productRepository.findByNome(name).forEach(products::add);

		      if (products.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(products, HttpStatus.OK);
			
		}catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    // get product by id    
	public ResponseEntity<Product> getProductById(long id) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
    
	// ********** Update ********** //
	public ResponseEntity<Product> update(Long id, Product product){
		Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	      Product _product = productData.get();
	      _product.setNome(product.getNome());
	      _product.setDescricao(product.getDescricao());
	      _product.setValor(product.getValor());
	      return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	// ********** Delete ********** //
	// delete product by pass id
	public ResponseEntity<HttpStatus> deleteProduct(long id) {
	    try {
	      productRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// delete all products
	public ResponseEntity<HttpStatus> deleteAllProducts() {
	    try {
	      productRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
}
