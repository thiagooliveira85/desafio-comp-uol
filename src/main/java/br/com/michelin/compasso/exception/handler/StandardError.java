package br.com.michelin.compasso.exception.handler;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("status_code")
	private Integer status;
	
	@JsonProperty("message")
	private String msg;
	
}
