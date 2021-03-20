package br.com.michelin.compasso.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CrudTemplateController<T> {
	
	@PostMapping()
	public ResponseEntity<T> create (@RequestBody Optional<T> entity);	
	
	@GetMapping
	public ResponseEntity<List<T>> getAll();
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getOne(@PathVariable String id);
	
	@GetMapping("/search")
	public ResponseEntity<List<T>> search(@RequestParam(value = "min_price", required = false) Optional<BigDecimal> minPrice,
			@RequestParam(value = "max_price", required = false) Optional<BigDecimal> maxPrice,
			@RequestParam(value = "q", required = false) String q);
	
	@PutMapping("/{id}")
	public ResponseEntity<T> udpate(@PathVariable String id, @RequestBody Optional<T> entity);
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id);

}
