package br.com.uds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.converter.DozerConverter;
import br.com.uds.data.model.Customization;
import br.com.uds.data.v1.vo.CustomizationVO;
import br.com.uds.exception.ResourceNotFoundException;
import br.com.uds.repository.CustomizationRepository;

@Service
public class CustomizationServices {

	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	CustomizationRepository repository;

	public CustomizationVO create(CustomizationVO customization) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(customization, Customization.class)),
				CustomizationVO.class);
	}

	public List<CustomizationVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), CustomizationVO.class);
	}

	public CustomizationVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		return DozerConverter.parseObject(entity, CustomizationVO.class);
	}

	public CustomizationVO update(CustomizationVO customization) {
		var entity = repository.findById(customization.getKey())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		entity.setDescription(customization.getDescription());
		entity.setAdditionalPrice(customization.getAdditionalPrice());
		entity.setAdditionalPreparationTime(customization.getAdditionalPreparationTime());

		return DozerConverter.parseObject(repository.save(entity), CustomizationVO.class);
	}

	public void delete(Long id) {
		Customization entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		repository.delete(entity);
	}

}
