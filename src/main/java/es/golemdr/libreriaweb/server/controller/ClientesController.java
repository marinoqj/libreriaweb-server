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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.libreriaweb.server.controller.constantes.UrlConstants;
import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.domain.Pedido;
import es.golemdr.libreriaweb.server.domain.Producto;
import es.golemdr.libreriaweb.server.service.ClientesService;



@RestController
public class ClientesController {
	
	private static final Logger log = LogManager.getLogger(ClientesController.class);
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Resource
	private ClientesService clientesService;
	
	@GetMapping(UrlConstants.CLIENTES)
	public @ResponseBody List<Cliente> listadoClientes() {
		
		List<Cliente> clientes = null;
		clientes = clientesService.getClientes();
		
		log.debug("Hemos encontrado " + clientes.size() + " clientes");
		
		return clientes;		
	}	
	
	
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
	public List<Cliente> listadoClientesPaginados(@RequestHeader("Paginacion-Inicio") int inicio, @RequestHeader("Paginacion-ElementosPagina") int elementosXpagina,
			HttpServletResponse response) throws JsonProcessingException {
		
		List<Cliente> resultado = null;
		resultado = clientesService.getClientes(inicio, elementosXpagina);

		int total = 0;
		total = clientesService.getTotalClientes();
		response.addHeader("Paginacion-Total", String.valueOf(total));
		
		return resultado;
	}
	
}
;