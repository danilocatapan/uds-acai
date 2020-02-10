package br.com.uds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uds.data.model.Order;
import br.com.uds.data.v1.vo.OrderVO;
import br.com.uds.services.OrderServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endpoint de Pedidos")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	
	@Autowired
	private OrderServices services;
	
	@ApiOperation(value = "Exibir o pedido resumido por Id")
	@GetMapping(value = "/resume/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<Object[]> findOrderResumeById(@PathVariable("id") Long id){
		return services.findOrderResumeById(id);
	}

	@ApiOperation(value = "Exibir as customizações do pedido por Id")
	@GetMapping(value = "/customization/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<Object[]> findOrderCustomizationsById(@PathVariable("id") Long id){
		return services.findOrderCustomizationsById(id);
	}
	
	@ApiOperation(value = "Exibir os totalizadores do pedido por Id")
	@GetMapping(value = "/amount/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public OrderVO findById(@PathVariable("id") Long id) {
		return services.findById(id);
	}
	
	@ApiOperation(value = "Criar um novo pedido")
	@PostMapping(value = "create/{acaiId}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public Order create(@PathVariable("acaiId") String acaiId) {
		return services.create(acaiId);
	}

	@ApiOperation(value = "Deletar um pedido pelo seu Id")
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		services.delete(id);
	}
}
