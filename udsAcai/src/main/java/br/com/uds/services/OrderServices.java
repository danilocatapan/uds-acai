package br.com.uds.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.converter.DozerConverter;
import br.com.uds.data.model.Acai;
import br.com.uds.data.model.AcaiCustomization;
import br.com.uds.data.model.Customization;
import br.com.uds.data.model.Flavor;
import br.com.uds.data.model.Order;
import br.com.uds.data.model.Size;
import br.com.uds.data.v1.vo.OrderVO;
import br.com.uds.exception.ResourceNotFoundException;
import br.com.uds.repository.AcaiCustomizationRepository;
import br.com.uds.repository.AcaiRepository;
import br.com.uds.repository.CustomizationRepository;
import br.com.uds.repository.FlavorRepository;
import br.com.uds.repository.OrderRepository;
import br.com.uds.repository.SizeRepository;

@Service
public class OrderServices {

	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AcaiRepository acaiRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	FlavorRepository flavorRepository;
	
	@Autowired
	private AcaiCustomizationRepository acaiCustomizationRepository;	
	
	@Autowired
	CustomizationRepository customizationRepository;

	public Order create(String acaiId) {
		BigDecimal additionCustomizations = new BigDecimal(0);
		Integer preparationTime = 0;
		
		Acai acai = acaiRepository.findById(Long.parseLong(acaiId.trim())).orElseThrow();
		Size size = sizeRepository.findById(acai.getSize().getId()).orElseThrow();
		Flavor flavor = flavorRepository.findById(acai.getFlavor().getId()).orElseThrow();
		
		List<AcaiCustomization> acaiCustomizations = acaiCustomizationRepository.findByAcaiId(Long.parseLong(acaiId.trim()));
		
		for (AcaiCustomization acaiCustomization : acaiCustomizations) {
			Customization customization = customizationRepository.findById(acaiCustomization.getCustomization().getId()).orElseThrow();
			
			additionCustomizations = additionCustomizations.add(customization.getAdditionalPrice());
			preparationTime += customization.getAdditionalPreparationTime();
		}
		
		Order order = new Order();
		order.setAcai(acai);
		order.setAmount(size.getPrice().add(additionCustomizations));
		order.setPreparationTime(size.getPreparationTime() + flavor.getAdditionalPreparationTime() + preparationTime);
		return orderRepository.save(order);
	}

	public List<Object[]> findOrderResumeById(Long id) {
		return orderRepository.findOrderResumeById(id);
	}
	
	public List<Object[]> findOrderCustomizationsById(Long id) {
		return orderRepository.findOrderCustomizationsById(id);
	}
	
	public OrderVO findById(Long id) {
		var entity = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		return DozerConverter.parseObject(entity, OrderVO.class);
	}

	public void delete(Long id) {
		Order entity = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		orderRepository.delete(entity);
	}

}
