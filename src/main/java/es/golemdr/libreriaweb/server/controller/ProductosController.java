package es.golemdr.libreriaweb.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.libreriaweb.server.controller.constantes.UrlConstants;
import es.golemdr.libreriaweb.server.domain.Producto;
import es.golemdr.libreriaweb.server.service.ProductosService;



//@CrossOrigin -- Creo que es para que se pueda llamar de otro contenedor
@RestController
public class ProductosController {
	
	private static final Logger log = LogManager.getLogger(ProductosController.class);
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Resource
	private ProductosService productosService;
	
	
	@GetMapping(UrlConstants.PRODUCTOS)
	public @ResponseBody List<Producto> listadoProductos() {
		
		List<Producto> productos = null;
		productos = productosService.getProductos();
		
		log.debug("Hemos encontrado " + productos.size() + " productos");
		
		return productos;		
	}
	
	
	@GetMapping(UrlConstants.PRODUCTOS_ID)
	public ResponseEntity<?> recuperarProducto(@PathVariable("id") Long idProducto) {
		
		ResponseEntity<?> resultado = null;
		Producto producto = null;
		
		try {
			
			producto = productosService.getProductoById(idProducto);
			log.debug(objectMapper.writeValueAsString(producto));			
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			log.error("Se produjo la excepción:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@PostMapping(UrlConstants.PRODUCTOS)
	public ResponseEntity<?> crearProducto(@RequestBody Producto producto) throws JsonProcessingException {
		
		log.debug(objectMapper.writeValueAsString(producto));

		producto = productosService.insertarActualizarProducto(producto);

		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
		
	@PutMapping(UrlConstants.PRODUCTOS)
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			log.debug(objectMapper.writeValueAsString(producto));						
			producto = productosService.insertarActualizarProducto(producto);
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			log.error("Se produjo la excepción:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + producto.getIdProducto(), HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@DeleteMapping(UrlConstants.PRODUCTOS_ID)
	public ResponseEntity<?> borrarProducto(@PathVariable("id") Long idProducto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			productosService.borrarProducto(idProducto);			
			resultado = new ResponseEntity<String>("El producto se borró correctamente", HttpStatus.OK);
			
		}catch (Exception e) {
			
			log.error("Se produjo la excepción:" + e.getMessage());
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);
		}		
		
		return resultado;
	}
	
}
