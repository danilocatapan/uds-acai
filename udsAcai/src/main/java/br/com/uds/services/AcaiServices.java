package br.com.uds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.data.model.Acai;
import br.com.uds.exception.ResourceNotFoundException;
import br.com.uds.repository.AcaiRepository;
import br.com.uds.repository.FlavorRepository;
import br.com.uds.repository.SizeRepository;

@Service
public class AcaiServices {
	
	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	AcaiRepository acaiRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	FlavorRepository flavorRepository;
	
	public Acai create(String sizeId, String flavorId) {
		Acai acai = new Acai();
		acai.setSize(sizeRepository.findById(Long.parseLong(sizeId.trim())).orElseThrow());
		acai.setFlavor(flavorRepository.findById(Long.parseLong(flavorId.trim())).orElseThrow());
		return acaiRepository.save(acai);
	}

	public List<Acai> findAll() {
		return acaiRepository.findAll();
	}

	public List<Acai> myfindById(Long id) {
		return acaiRepository.myfindById(id);
	}

	public void delete(Long id) {
		Acai entity = acaiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		acaiRepository.delete(entity);
	}

}
