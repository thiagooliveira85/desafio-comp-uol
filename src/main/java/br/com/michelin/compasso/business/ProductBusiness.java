package br.com.michelin.compasso.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.michelin.compasso.dto.FilterDto;
import br.com.michelin.compasso.entity.ProductEntity;
import br.com.michelin.compasso.exception.ProductNotFoundException;
import br.com.michelin.compasso.service.ProductService;

@Component
public class ProductBusiness {
	
	@Autowired
	private ProductService service;
	
	public ResponseEntity<List<ProductEntity>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
	
	public ResponseEntity<ProductEntity> getOne(String id) {
		Optional<ProductEntity> optionalProduct = this.service.findById(id);
		if (optionalProduct.isPresent())
			return ResponseEntity.ok(optionalProduct.get()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	public ResponseEntity<List<ProductEntity>> search(Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice,
			String q) {
		return ResponseEntity.ok(this.service.search(new FilterDto(minPrice, maxPrice, q)));
	}

	public ResponseEntity<ProductEntity> applyRulesToSave(Optional<ProductEntity> entity) {
		ProductValidator.execute(entity);
		ProductEntity saved = this.service.save(entity.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	public ProductEntity applyRulesToUpdate(String id, Optional<ProductEntity> entity) {
		ProductValidator.execute(entity);
		ProductEntity product = this.service.findById(id)
				.orElseThrow(ProductNotFoundException::new);
		product.setName(entity.get().getName());
		product.setDescription(entity.get().getDescription());
		return this.service.save(product);
	}

	public void delete(String id) {
		ProductEntity product = this.service.findById(id)
				.orElseThrow(ProductNotFoundException::new);
		this.service.delete(product.getId());
	}

}
