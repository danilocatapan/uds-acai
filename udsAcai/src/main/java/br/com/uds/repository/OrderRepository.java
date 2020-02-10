package br.com.uds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uds.data.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT s.description, f.description, o.amount FROM Order o INNER JOIN o.acai a INNER JOIN a.size s INNER JOIN a.flavor f WHERE o.id = ?1")
	public List<Object[]> findOrderResumeById(Long id);

	@Query( value =   "SELECT c.description, c.additional_price "
			 		+ "FROM acai_customizations ac "  
			 		+ "		INNER JOIN customizations c "  
			    	+ "			ON c.id = ac.customization_id "  
			  		+ "WHERE ac.acai_id = ?1 ", nativeQuery = true)
	public List<Object[]> findOrderCustomizationsById(Long id);
	
}