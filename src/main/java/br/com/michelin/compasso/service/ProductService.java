package br.com.michelin.compasso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.michelin.compasso.dto.FilterDto;
import br.com.michelin.compasso.entity.ProductEntity;

@Service
public interface ProductService {

	ProductEntity save(ProductEntity entity);

	List<ProductEntity> findAll();
	
	void delete(String id);

	Optional<ProductEntity> findById(String id);

	List<ProductEntity> search(FilterDto filterDto);

}
