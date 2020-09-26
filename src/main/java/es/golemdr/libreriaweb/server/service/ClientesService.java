package es.golemdr.libreriaweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.domain.Producto;
import es.golemdr.libreriaweb.server.repository.ClientesRepository;
import es.golemdr.libreriaweb.server.repository.ProductosRepository;



@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	
	
	public List<Cliente> getClientes(){
		
		return clientesRepository.findAll();
		
	}
	
	public Cliente findByExample(Example<Cliente> example){
		
		return clientesRepository.findOne(example).get();
		
	}
	
}
