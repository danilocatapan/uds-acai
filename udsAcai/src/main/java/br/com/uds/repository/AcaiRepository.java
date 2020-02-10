package br.com.uds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.uds.data.model.Acai;

public interface AcaiRepository extends JpaRepository<Acai, Long>{
	
	@Query("SELECT a.acai, a.customization FROM AcaiCustomization a GROUP BY a.acai, a.customization ")
	public List<Acai> findAll();
	
	@Query("SELECT a FROM AcaiCustomization a INNER JOIN a.acai b WHERE b.id = ?1")
	public List<Acai> myfindById(Long id);

}
