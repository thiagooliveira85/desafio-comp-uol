package br.com.michelin.compasso.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(){
		super("Product not found!");
	}
	
	public ProductNotFoundException(String message){
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
