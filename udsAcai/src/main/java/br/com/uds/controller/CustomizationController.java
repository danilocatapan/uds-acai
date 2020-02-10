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

import br.com.uds.data.v1.vo.CustomizationVO;
import br.com.uds.services.CustomizationServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint de Personalização")
@RestController
@RequestMapping("/api/v1/customization")
public class CustomizationController {

	@Autowired
	CustomizationServices services;

	@ApiOperation(value = "Exibir todos as personalizações")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<CustomizationVO> findAll() {
		return services.findAll();
	}

	@ApiOperation(value = "Exibir uma personalização pelo seu ID")
	@GetMapping(value = "find/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public CustomizationVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}

	@ApiOperation(value = "Criar uma nova personalização")
	@PostMapping(value = "create/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public CustomizationVO create(@RequestBody CustomizationVO customization) {
		return services.create(customization);
	}

	@ApiOperation(value = "Atualizar uma personalização")
	@PutMapping(value = "update/", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public CustomizationVO update(@RequestBody CustomizationVO customization) {
		return services.update(customization);
	}

	@ApiOperation(value = "Deletar uma personalização pelo seu ID")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<CustomizationVO> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}
