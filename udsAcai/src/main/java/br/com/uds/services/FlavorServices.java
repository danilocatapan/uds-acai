package br.com.uds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.converter.DozerConverter;
import br.com.uds.data.model.Flavor;
import br.com.uds.data.v1.vo.FlavorVO;
import br.com.uds.exception.ResourceNotFoundException;
import br.com.uds.repository.FlavorRepository;

@Service
public class FlavorServices {

	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	FlavorRepository repository;

	public FlavorVO create(FlavorVO flavor) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(flavor, Flavor.class)),
				FlavorVO.class);
	}

	public List<FlavorVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), FlavorVO.class);
	}

	public FlavorVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		return DozerConverter.parseObject(entity, FlavorVO.class);
	}

	public FlavorVO update(FlavorVO flavor) {
		var entity = repository.findById(flavor.getKey())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		entity.setDescription(flavor.getDescription());
		entity.setAdditionalPreparationTime(flavor.getAdditionalPreparationTime());

		return DozerConverter.parseObject(repository.save(entity), FlavorVO.class);
	}

	public void delete(Long id) {
		Flavor entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		repository.delete(entity);
	}

}
