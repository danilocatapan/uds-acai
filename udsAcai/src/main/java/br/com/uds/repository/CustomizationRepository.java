package br.com.uds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uds.data.model.Customization;

public interface CustomizationRepository extends JpaRepository<Customization, Long>{

}
