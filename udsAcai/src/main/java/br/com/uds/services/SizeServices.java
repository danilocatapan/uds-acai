package br.com.uds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uds.converter.DozerConverter;
import br.com.uds.data.model.Size;
import br.com.uds.data.v1.vo.SizeVO;
import br.com.uds.exception.ResourceNotFoundException;
import br.com.uds.repository.SizeRepository;

@Service
public class SizeServices {

	private static final String MSG_RECORD_NOT_FOUND = "Não encontrou registro com este ID";

	@Autowired
	SizeRepository repository;

	public SizeVO create(SizeVO size) {
		return DozerConverter.parseObject(repository.save(DozerConverter.parseObject(size, Size.class)), SizeVO.class);
	}

	public List<SizeVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), SizeVO.class);
	}

	public SizeVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		return DozerConverter.parseObject(entity, SizeVO.class);
	}

	public SizeVO update(SizeVO size) {
		var entity = repository.findById(size.getKey())
				.orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));

		entity.setDescription(size.getDescription());
		entity.setPrice(size.getPrice());
		entity.setPreparationTime(size.getPreparationTime());

		return DozerConverter.parseObject(repository.save(entity), SizeVO.class);
	}

	public void delete(Long id) {
		Size entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_RECORD_NOT_FOUND));
		repository.delete(entity);
	}

}
