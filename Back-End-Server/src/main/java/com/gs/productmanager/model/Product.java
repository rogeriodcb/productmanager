package com.gs.productmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>**************************************************************************************
* <p>                                Produto
* <p>**************************************************************************************
* <p>
* This model is responsible to create a "Produto" table modeling for the data bank. 
* It includes attributes with validation.
* <p> Lombok is used to provide builder, getters and setters for this class.
* <p> To comply with the requirements:
* <li> Entity "Produto" shall be created
* <li> Attributes "nome", "descrição" and "valor" shall be created
*/
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="nome",nullable=false, unique=true)
	@NotBlank(message="O nome do produto é necessário.")
	@Size(max=200)
	private String nome;
	
	@Column(name="descricao",nullable=false)
	private String descricao;
	
	@Column(name="valor",nullable=false)
	@NotBlank(message="O valor do produto deve ser passado.")
	private String valor;
	
}
