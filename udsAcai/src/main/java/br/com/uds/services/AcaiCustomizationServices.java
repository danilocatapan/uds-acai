package br.com.uds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.data.model.Acai;
import br.com.uds.data.model.AcaiCustomization;
import br.com.uds.data.model.AcaiCustomizationId;
import br.com.uds.data.model.Customization;
import br.com.uds.repository.AcaiCustomizationRepository;
import br.com.uds.repository.AcaiRepository;
import br.com.uds.repository.CustomizationRepository;

@Service
public class AcaiCustomizationServices {

	@Autowired
	AcaiCustomizationRepository acaiCustomizationRepository;
	
	@Autowired
	AcaiRepository acaiRepository;
	
	@Autowired
	CustomizationRepository customizationRepository;
	
	public AcaiCustomization create(String acaiId, String customizationId) {
		Acai acai = acaiRepository.findById(Long.parseLong(acaiId.trim())).orElseThrow();
		Customization customization = customizationRepository.findById(Long.parseLong(customizationId.trim())).orElseThrow();
		
		AcaiCustomization ac = new AcaiCustomization();
		ac.setId(new AcaiCustomizationId(acai.getId(), customization.getId()));
		ac.setAcai(acai);
		ac.setCustomization(customization);
		
		return acaiCustomizationRepository.save(ac);
	}

	public void delete(AcaiCustomizationId id) {
		acaiCustomizationRepository.deleteById(id);
	}

	public List<AcaiCustomization> findByAcaiId(long id) {
		return acaiCustomizationRepository.findByAcaiId(id);
	}

}
