package br.com.michelin.compasso.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.michelin.compasso.dto.FilterDto;
import br.com.michelin.compasso.entity.ProductEntity;
import br.com.michelin.compasso.repository.ProductRepository;
import br.com.michelin.compasso.service.ProductService;
import br.com.michelin.compasso.specification.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	public List<ProductEntity> findAll() {
		return this.repository.findAll();
	}
	
	@Override
	public Optional<ProductEntity> findById(String id) {
		return this.repository.findById(id);
	}
	
	@Override
	public List<ProductEntity> search(FilterDto filterDto) {
		return this.repository.findAll(new ProductSpecification(filterDto));
	}


	@Override
	public ProductEntity save(ProductEntity entity) {
		return this.repository.save(entity);
	}
	
	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

}