package br.com.michelin.compasso.business;

import java.util.Optional;

import br.com.michelin.compasso.entity.ProductEntity;
import br.com.michelin.compasso.util.StringUtil;

public class ProductValidator {
	
	public static void execute(Optional<ProductEntity> entity) {
		
		if (!entity.isPresent())
			throw new IllegalArgumentException("object not found!");
		
		if (StringUtil.checkIfIsBlankOrNull(entity.get().getName()))
			throw new IllegalArgumentException("The field name is not found!");
		
		if (StringUtil.checkIfIsBlankOrNull(entity.get().getDescription()))
			throw new IllegalArgumentException("The field description is not found!");
		
		if (entity.get().getPrice() == null)
			throw new IllegalArgumentException("The field price is not found!");
	}

}
