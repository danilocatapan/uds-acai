package br.com.uds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uds.data.model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{

}