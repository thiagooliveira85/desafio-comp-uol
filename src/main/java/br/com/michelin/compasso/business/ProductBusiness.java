package br.com.michelin.compasso.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.michelin.compasso.dto.FilterDto;
import br.com.michelin.compasso.entity.ProductEntity;
import br.com.michelin.compasso.exception.ProductNotFoundException;
import br.com.michelin.compasso.service.ProductService;

@Component
public class ProductBusiness {
	
	@Autowired
	private ProductService service;
	
	public List<ProductEntity> findAll() {
		return this.service.findAll();
	}
	
	public Optional<ProductEntity> getOne(String id) {
		return this.service.findById(id);
	}
	
	public List<ProductEntity> search(Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice,
			String q) {
		return this.service.search(new FilterDto(minPrice, maxPrice, q));
	}

	public ProductEntity applyRulesToSave(Optional<ProductEntity> entity) {
		ProductValidator.execute(entity);
		return this.service.save(entity.get());
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
