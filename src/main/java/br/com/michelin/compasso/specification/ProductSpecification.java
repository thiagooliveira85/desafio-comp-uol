package br.com.michelin.compasso.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.michelin.compasso.dto.FilterDto;
import br.com.michelin.compasso.entity.ProductEntity;
import br.com.michelin.compasso.util.StringUtil;

public class ProductSpecification implements Specification<ProductEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FilterDto filter;

	public ProductSpecification(FilterDto filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		query.distinct(true);
		
		if (this.filter != null) {
			
			if (!StringUtil.checkIfIsBlankOrNull(this.filter.getQ())) {
				Predicate equalName = cb.equal(cb.upper(root.get("name")), this.filter.getQ().toUpperCase());
				Predicate equalDesc = cb.equal(cb.upper(root.get("description")), this.filter.getQ().toUpperCase());
				Predicate or = cb.or(equalName, equalDesc);
				predicates.add(or);
			}

			if (this.filter.getMinPrice().isPresent())
				predicates.add(cb.greaterThanOrEqualTo(root.get("price"), this.filter.getMinPrice().get()));
			
			if (this.filter.getMaxPrice().isPresent())
				predicates.add(cb.lessThanOrEqualTo(root.get("price"), this.filter.getMaxPrice().get()));
				
		}
		
		query.orderBy(cb.asc(root.get("name")));		

		return andTogether(predicates, cb);
	}
	
	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb){
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
