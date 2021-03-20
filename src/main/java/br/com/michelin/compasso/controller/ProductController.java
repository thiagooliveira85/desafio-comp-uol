package br.com.michelin.compasso.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.michelin.compasso.business.ProductBusiness;
import br.com.michelin.compasso.entity.ProductEntity;

@RestController
@RequestMapping("/products")
public class ProductController implements CrudTemplateController<ProductEntity> {

	@Autowired
	private ProductBusiness business;
	
	@Override
	public ResponseEntity<ProductEntity> create(Optional<ProductEntity> entity) {
		return this.business.applyRulesToSave(entity);
	}
	
	@Override
	public ResponseEntity<List<ProductEntity>> getAll() {
		return this.business.findAll();
	}
	
	@Override
	public ResponseEntity<ProductEntity> getOne(String id) {
		return this.business.getOne(id);
	}
	
	@Override
	public ResponseEntity<List<ProductEntity>> search(Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice, String q) {
		return this.business.search(minPrice, maxPrice, q);
	}

	@Override
	public ResponseEntity<ProductEntity> udpate(String id, Optional<ProductEntity> entity) {
		ProductEntity update = this.business.applyRulesToUpdate(id, entity);
		return ResponseEntity.ok(update);
	}

	@Override
	public void delete(String id) {
		this.business.delete(id);
	}

}
