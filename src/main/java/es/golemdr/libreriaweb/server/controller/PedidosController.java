package es.golemdr.libreriaweb.server.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.libreriaweb.server.controller.constantes.UrlConstants;
import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.domain.Pedido;
import es.golemdr.libreriaweb.server.ext.Constantes;
import es.golemdr.libreriaweb.server.service.PedidosService;



@RestController
public class PedidosController {
	
	private static final Logger log = LogManager.getLogger(PedidosController.class);
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Resource
	private PedidosService pedidosService;
	
	@PostMapping(UrlConstants.PEDIDOS)
	public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
		
		// Actualizo la fecha del pedido
		pedido.setFecha(new Date(System.currentTimeMillis()));

		pedido = pedidosService.insertarActualizarPedido(pedido);

		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);
	}
	
	@PostMapping(UrlConstants.PEDIDOS_BUSCAR)
	public List<Pedido> buscarPedidos(@RequestBody Pedido filtro) throws JsonProcessingException {
		
		log.debug("El JSON es: " + objectMapper.writeValueAsString(filtro));
		
		List<Pedido> pedidos = null;
		
		Example<Pedido> example = Example.of(filtro);
		
		pedidos = pedidosService.findByExample(example);

		return pedidos;
	}

	// TODO - Quitar si no se usa
	@GetMapping(UrlConstants.PEDIDOS)
	public @ResponseBody List<Pedido> listadoPedidos() {
		
		List<Pedido> pedidos = null;
		pedidos = pedidosService.getPedidos();
		
		log.debug("Hemos encontrado " + pedidos.size() + " pedidos");
		
		return pedidos;		
	}
	
	@GetMapping(UrlConstants.PEDIDOS_PAGINADO)
	public List<Pedido> listadoPedidosPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Pedido> resultado = null;
		resultado = pedidosService.getPedidos(inicio, elementosXpagina);

		int total = 0;
		total = pedidosService.getTotalPedidos();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));
		
		return resultado;
	}
}
