package es.golemdr.libreriaweb.server.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.libreriaweb.server.domain.Producto;
import es.golemdr.libreriaweb.server.repository.ProductosRepository;




@Service
public class ProductosService {
	
	@Autowired
	private ProductosRepository productosRepository;
	
	
	
	public List<Producto> getProductos(){
		
		return productosRepository.findAll();
		
	}
	
	public Producto getProductoById(Long idProducto){
		
		return productosRepository.findById(idProducto).get();
		
	}
	
	public Producto insertarActualizarProducto(Producto producto) {
		
		return productosRepository.save(producto);
		
	}
	
	public void borrarProducto(Long idProducto) {
		
		productosRepository.deleteById(idProducto);
		
	}
	
	// TODO - Para convertir en productosByExample
	
//	public List<Producto> getProductosPorCategoria(Example<Producto> example){
//		
//		return productosRepository.findAll(example);
//		
//	}
	

}
