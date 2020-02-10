package br.com.uds.data.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "acai_customizations")
public class AcaiCustomization {

	@EmbeddedId
	private AcaiCustomizationId id;

	@MapsId("acaiId")
	@ManyToOne
	@JoinColumn(name = "acai_id", insertable = false, updatable = false)
	private Acai acai;

	@MapsId("customizationId")
	@ManyToOne
	@JoinColumn(name = "customization_id", insertable = false, updatable = false)
	private Customization customization;
	
}
