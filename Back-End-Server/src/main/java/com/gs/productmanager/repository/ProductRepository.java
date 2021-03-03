package com.gs.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gs.productmanager.model.Product;
/**
* <p>**************************************************************************************
* <p>                                 ProductRepository
* <p>**************************************************************************************
* <p>
* Provide JPA access management to the databank "produto" table.
*/
@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
	List<Product> findByValor(Long value);
	List<Product> findByNome(String name);
}
