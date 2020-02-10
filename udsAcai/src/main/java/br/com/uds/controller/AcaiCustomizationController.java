package br.com.uds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uds.data.model.AcaiCustomization;
import br.com.uds.data.model.AcaiCustomizationId;
import br.com.uds.services.AcaiCustomizationServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint do Personalizações de Acaí")
@RestController
@RequestMapping("/api/v1/acaicustomization")
public class AcaiCustomizationController {

	@Autowired
	AcaiCustomizationServices services;
	
	@ApiOperation(value = "Exibir personalização pelo ID do acaí")
	@GetMapping(value = "find/{acaiId}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<AcaiCustomization> findByAcaiId(@PathVariable("acaiId") String acaiId) {
		return services.findByAcaiId(Long.parseLong(acaiId.trim()));
	}

	@ApiOperation(value = "Criar uma nova personalização")
	@PostMapping(value = "create/{acaiId}/{customizationId}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public AcaiCustomization create(@PathVariable("acaiId") String acaiId, @PathVariable("customizationId") String customizationId) {
		return services.create(acaiId, customizationId);
	}
	
	@ApiOperation(value = "Deletar um açai pelo seu ID")
	@DeleteMapping("delete/{acaiId}/{customizationId}")
	public void delete(@PathVariable("acaiId") String acaiId, @PathVariable("customizationId") String customizationId) {
		services.delete(new AcaiCustomizationId(Long.parseLong(acaiId.trim()), Long.parseLong(customizationId)));
	}

}
