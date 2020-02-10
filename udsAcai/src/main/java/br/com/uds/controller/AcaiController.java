package br.com.uds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uds.data.model.Acai;
import br.com.uds.services.AcaiServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint de Acaí")
@RestController
@RequestMapping("/api/v1/acai")
public class AcaiController {

	@Autowired
	AcaiServices services;
	
	@ApiOperation(value = "Exibir todos os açais")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<Acai> findAll() {
		return services.findAll();
	}

	@ApiOperation(value = "Exibir um açai pelo seu Id")
	@GetMapping(value = "find/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<Acai> findById(@PathVariable("id") Long id) {
		return services.myfindById(id);
	}

	@ApiOperation(value = "Criar um novo açai")
	@PostMapping(value = "create/{sizeId}/{flavorId}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public Acai create(@PathVariable("sizeId") String sizeId, @PathVariable("flavorId") String flavorId) {
		return services.create(sizeId, flavorId);
	}

	@ApiOperation(value = "Deletar um açai pelo seu Id")
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		services.delete(id);
	}

}
