package br.com.uds.data.v1.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "id acai", "id personalização" })
public class AcaiCustomizationVO implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;

	@JsonProperty("id acai")
	private Long idAcai;

	@JsonProperty("id personalização")
	private Long idCustomization;
	
}