package br.com.michelin.compasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.michelin.compasso.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String>,
		PagingAndSortingRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
	
}
