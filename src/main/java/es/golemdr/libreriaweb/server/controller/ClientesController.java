package es.golemdr.libreriaweb.server.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.libreriaweb.server.controller.constantes.UrlConstants;
import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.ext.Constantes;
import es.golemdr.libreriaweb.server.service.ClientesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;;


@RestController
public class ClientesController {
	
	private static final Logger log = LogManager.getLogger(ClientesController.class);
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Resource
	private ClientesService clientesService;
	
	@GetMapping(UrlConstants.CLIENTES_BUSCAR_DNI)
	public ResponseEntity<?> buscarClienteDNI(@PathVariable("dni") String dni) {

		ResponseEntity<?> resultado = null;
		Cliente cliente = null;
		Cliente filtro = new Cliente();
		filtro.setDni(dni);
		
		try {
			
			Example<Cliente> example = Example.of(filtro);
			
			cliente = clientesService.findByExample(example);
					
			resultado = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			log.error("Se produjo la excepción:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontró ningún cliente con los datos de búsqueda ", HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	

	@PostMapping(UrlConstants.CLIENTES_BUSCAR)
	public List<Cliente> buscarClientes(@RequestBody Cliente filtro) throws JsonProcessingException {
		
		List<Cliente> resultado = null;
		
		resultado = clientesService.findClientes(filtro);
		
		
		return resultado;
	}

	@GetMapping(UrlConstants.CLIENTES_PAGINADO)
	public List<Cliente> listadoClientesPaginado(@RequestHeader(Constantes.PAGINACION_INICIO) int inicio, 
			@RequestHeader(Constantes.PAGINACION_ELEMENTOS_PAGINA) int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Cliente> resultado = null;
		resultado = clientesService.getClientes(inicio, elementosXpagina);

		int total = 0;
		total = clientesService.getTotalClientes();
		response.addHeader(Constantes.PAGINACION_TOTAL, String.valueOf(total));
		
		return resultado;
	}
	
}
;