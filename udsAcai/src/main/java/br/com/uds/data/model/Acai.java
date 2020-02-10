package br.com.uds.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "acais")
public class Acai {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sizes_id")
	private Size size;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flavors_id")
	private Flavor flavor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acai_id")
	@JoinColumn(name = "customization_id")
	private AcaiCustomization acaiCustomization;


}
