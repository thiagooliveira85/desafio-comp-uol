package br.com.michelin.compasso.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterDto {
	
	private Optional<BigDecimal> minPrice;
	private Optional<BigDecimal> maxPrice;
	private String q;

}
