package br.com.uds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.uds.data.model.AcaiCustomization;
import br.com.uds.data.model.AcaiCustomizationId;

public interface AcaiCustomizationRepository extends JpaRepository<AcaiCustomization, AcaiCustomizationId>{

	@Query(value = "SELECT a.* FROM acai_customizations a WHERE a.acai_id = ?1", nativeQuery = true)
	public List<AcaiCustomization> findByAcaiId(Long id);
	
}
