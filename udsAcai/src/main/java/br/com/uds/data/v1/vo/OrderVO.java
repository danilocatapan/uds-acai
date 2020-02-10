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
@JsonPropertyOrder({ "id", "tempo de preparação", "preço final" })
public class OrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;

	@JsonProperty("tempo de preparação")
	private Integer preparationTime;

	@JsonProperty("preço final")
	private BigDecimal amount;

}