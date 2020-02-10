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

import br.com.uds.data.v1.vo.FlavorVO;
import br.com.uds.services.FlavorServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint de Sabores")
@RestController
@RequestMapping("/api/v1/flavor")
public class FlavorController {

	@Autowired
	private FlavorServices services;

	@ApiOperation(value = "Exibir todos os sabores")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<FlavorVO> findAll() {
		return services.findAll();
	}

	@ApiOperation(value = "Exibir um sabor pelo seu ID")
	@GetMapping(value = "find/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public FlavorVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}

	@ApiOperation(value = "Criar um novo sabor")
	@PostMapping(value = "create/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public FlavorVO create(@RequestBody FlavorVO flavor) {
		return services.create(flavor);
	}

	@ApiOperation(value = "Atualizar um sabor")
	@PutMapping(value = "update/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public FlavorVO update(@RequestBody FlavorVO flavor) {
		return services.update(flavor);
	}

	@ApiOperation(value = "Deletar um sabor pelo seu ID")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<FlavorVO> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}