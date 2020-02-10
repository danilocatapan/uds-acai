package br.com.uds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uds.data.v1.vo.SizeVO;
import br.com.uds.services.SizeServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint de Tamanhos")
@RestController
@RequestMapping("/api/v1/size")
public class SizeController {

	@Autowired
	private SizeServices services;

	@ApiOperation(value = "Exibir todos os tamanhos")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<SizeVO> findAll() {
		return services.findAll();
	}

	@ApiOperation(value = "Exibir um tamanho pelo seu ID")
	@GetMapping(value = "find/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public SizeVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}

	@ApiOperation(value = "Criar um novo tamanho")
	@PostMapping(value = "create/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public SizeVO create(@RequestBody SizeVO size) {
		return services.create(size);
	}

	@ApiOperation(value = "Atualizar um tamanho")
	@PutMapping(value = "update/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public SizeVO update(@RequestBody SizeVO size) {
		return services.update(size);
	}

	@ApiOperation(value = "Deletar um tamanho pelo seu ID")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<SizeVO> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}