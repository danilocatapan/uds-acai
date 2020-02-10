package br.com.uds.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AcaiCustomizationId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_acai")
	private Long acaiId;

	@Column(name = "id_customization")
	private Long customizationId;

}
