package br.com.uds.test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.uds.data.model.Acai;
import br.com.uds.data.model.AcaiCustomization;
import br.com.uds.data.model.AcaiCustomizationId;
import br.com.uds.data.model.Customization;
import br.com.uds.data.model.Flavor;
import br.com.uds.data.model.Order;
import br.com.uds.data.model.Size;
import br.com.uds.repository.AcaiCustomizationRepository;
import br.com.uds.repository.AcaiRepository;
import br.com.uds.repository.CustomizationRepository;
import br.com.uds.repository.FlavorRepository;
import br.com.uds.repository.OrderRepository;
import br.com.uds.repository.SizeRepository;

@Component
public class InsertDataForTesting {
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private FlavorRepository flavorRepository;
	
	@Autowired
	private CustomizationRepository customizationRepository;
	
	@Autowired
	private AcaiRepository acaiRepository;
	
	@Autowired
	private AcaiCustomizationRepository acaiCustomizationRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Size[] sizes = sizes();
		
		Flavor[] flavors = flavors();
		
		Customization[] customizations = customizations();
		
		Acai[] acais = acais(sizes, flavors);
		
		AcaiCustomization[] acaiCustomizations = acaiCustomizations(acais, customizations);
		
		orders(acaiCustomizations);
	}
	
	private Size[] sizes() {
		List<Size> sizes = new ArrayList<>(); 
		
		Size size = new Size();
		size.setDescription("Pequeno (300ml)");
		size.setPrice(BigDecimal.valueOf(10.0));
		size.setPreparationTime(5);
		sizes.add(size);
		sizeRepository.save(size);
		
		size = new Size();
		size.setDescription("Médio (500ml)");
		size.setPrice(BigDecimal.valueOf(13.0));
		size.setPreparationTime(7);
		sizes.add(size);
		sizeRepository.save(size);
		
		size = new Size();
		size.setDescription("Grande (700ml)");
		size.setPrice(BigDecimal.valueOf(15.0));
		size.setPreparationTime(10);
		sizes.add(size);
		sizeRepository.save(size);
		
		Size[] array = new Size[sizes.size()]; 
		return sizes.toArray(array);
	}
	
	private Flavor[] flavors() {
		List<Flavor> flavors = new ArrayList<>(); 
		
		Flavor flavor = new Flavor();
		flavor.setDescription("Morango");
		flavor.setAdditionalPreparationTime(0);
		flavors.add(flavor);
		flavorRepository.save(flavor);
		
		flavor = new Flavor();
		flavor.setDescription("Banana");
		flavor.setAdditionalPreparationTime(0);
		flavors.add(flavor);
		flavorRepository.save(flavor);
		
		flavor = new Flavor();
		flavor.setDescription("Kiwi");
		flavor.setAdditionalPreparationTime(5);
		flavors.add(flavor);
		flavorRepository.save(flavor);
		
		Flavor[] array = new Flavor[flavors.size()]; 
		return flavors.toArray(array);
	}
	
	private Customization[] customizations() {
		List<Customization> customizations = new ArrayList<>(); 
		
		Customization custom = new Customization();
		custom.setDescription("Granola");
		custom.setAdditionalPrice(BigDecimal.valueOf(0.0));
		custom.setAdditionalPreparationTime(0);
		customizations.add(custom);
		customizationRepository.save(custom);
		
		custom = new Customization();
		custom.setDescription("Paçoca");
		custom.setAdditionalPrice(BigDecimal.valueOf(3.0));
		custom.setAdditionalPreparationTime(3);
		customizations.add(custom);
		customizationRepository.save(custom);
		
		custom = new Customization();
		custom.setDescription("Leite Ninho");
		custom.setAdditionalPrice(BigDecimal.valueOf(0.0));
		custom.setAdditionalPreparationTime(3);
		customizations.add(custom);
		customizationRepository.save(custom);
		
		Customization[] array = new Customization[customizations.size()]; 
		return customizations.toArray(array);
	}
	
	private Acai[] acais(Size[] sizes, Flavor[] flavors) {
		List<Acai> acais = new ArrayList<>(); 
		
		Acai acai = new Acai();
		acai.setSize(sizes[0]);
		acai.setFlavor(flavors[0]);
		acais.add(acai);
		acaiRepository.save(acai);
		
		acai = new Acai();
		acai.setSize(sizes[0]);
		acai.setFlavor(flavors[1]);
		acais.add(acai);
		acaiRepository.save(acai);
		
		acai = new Acai();
		acai.setSize(sizes[1]);
		acai.setFlavor(flavors[2]);
		acais.add(acai);
		acaiRepository.save(acai);
		
		Acai[] array = new Acai[acais.size()]; 
		return acais.toArray(array);
	}
	
	private AcaiCustomization[] acaiCustomizations(Acai[] acais, Customization[] customizations) {
		List<AcaiCustomization> acaiCustomizations = new ArrayList<>(); 
		
		AcaiCustomization ac = new AcaiCustomization();
		ac.setId(new AcaiCustomizationId(acais[0].getId(), customizations[0].getId()));
		ac.setAcai(acais[0]);
		ac.setCustomization(customizations[0]);
		acaiCustomizations.add(ac);
		acaiCustomizationRepository.save(ac);
		
		ac = new AcaiCustomization();
		ac.setId(new AcaiCustomizationId(acais[0].getId(), customizations[1].getId()));
		ac.setAcai(acais[0]);
		ac.setCustomization(customizations[1]);
		acaiCustomizations.add(ac);
		acaiCustomizationRepository.save(ac);
		
		ac = new AcaiCustomization();
		ac.setId(new AcaiCustomizationId(acais[2].getId(), customizations[1].getId()));
		ac.setAcai(acais[2]);
		ac.setCustomization(customizations[1]);
		acaiCustomizations.add(ac);
		acaiCustomizationRepository.save(ac);
		
		AcaiCustomization[] array = new AcaiCustomization[acaiCustomizations.size()]; 
		return acaiCustomizations.toArray(array);
	}
	
	private void orders(AcaiCustomization[] acaiCustomizations) {
		List<Order> orders = new ArrayList<>(); 
		
		BigDecimal additionCustomizations = new BigDecimal(0);
		Integer preparationTime = 0;
		
		for (AcaiCustomization acaiCustomization : acaiCustomizations) {
			if (acaiCustomization.getAcai().getId() == 1L) {
				Customization customization = customizationRepository.findById(acaiCustomization.getCustomization().getId()).orElseThrow();
				
				additionCustomizations = additionCustomizations.add(customization.getAdditionalPrice());
				preparationTime += customization.getAdditionalPreparationTime();
			}
		}
		
		Acai acai = acaiRepository.findById(acaiCustomizations[0].getAcai().getId()).orElseThrow();
		
		Size size = sizeRepository.findById(acai.getSize().getId()).orElseThrow();
		Flavor flavor = flavorRepository.findById(acai.getFlavor().getId()).orElseThrow();
		
		Order order = new Order();
		order.setAcai(acai);
		order.setAmount(size.getPrice().add(additionCustomizations));
		order.setPreparationTime(size.getPreparationTime() + flavor.getAdditionalPreparationTime() + preparationTime);
		orders.add(order);
		orderRepository.save(order);
		
		//
		
		additionCustomizations = new BigDecimal(0);
		preparationTime = 0;
		
		for (AcaiCustomization acaiCustomization : acaiCustomizations) {
			if (acaiCustomization.getAcai().getId() == 3L) {
				Customization customization = customizationRepository.findById(acaiCustomization.getCustomization().getId()).orElseThrow();
				
				additionCustomizations = additionCustomizations.add(customization.getAdditionalPrice());
				preparationTime += customization.getAdditionalPreparationTime();
			}
		}
		
		acai = acaiRepository.findById(acaiCustomizations[2].getAcai().getId()).orElseThrow();
		
		size = sizeRepository.findById(acai.getSize().getId()).orElseThrow();
		flavor = flavorRepository.findById(acai.getFlavor().getId()).orElseThrow();
		
		order = new Order();
		order.setAcai(acai);
		order.setAmount(size.getPrice().add(additionCustomizations));
		order.setPreparationTime(size.getPreparationTime() + flavor.getAdditionalPreparationTime() + preparationTime);
		orders.add(order);
		orderRepository.save(order);
	}
	
}
