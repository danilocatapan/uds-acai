package br.com.uds.data.v1.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "descrição", "preço do adicional", "tempo de prepraração adicional" })
public class CustomizationVO implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	@JsonProperty("descrição")
	private String description;
	
	@JsonProperty("preço do adicional")
	private BigDecimal additionalPrice;
	
	@JsonProperty("tempo de prepraração adicional")
	private Integer additionalPreparationTime;
	
}