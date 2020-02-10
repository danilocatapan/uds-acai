package br.com.uds.data.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "preparation_time", nullable = false)
	private Integer preparationTime;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acais_id")
	private Acai acai;
	
	public String getFormattedId() {
		return String.format("#%04d", id);
	}
	
}