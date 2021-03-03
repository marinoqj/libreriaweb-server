package es.golemdr.libreriaweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.repository.ClientesRepository;



@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	
	
	public List<Cliente> getClientes(){
		
		return clientesRepository.findAll();
		
	}
	
	public List<Cliente> getClientes(int inicio, int elementosXpagina){
		
		Pageable paginacion = PageRequest.of(inicio,elementosXpagina);
		
		return clientesRepository.findAll(paginacion).getContent();
		
	}
	
	public int getTotalClientes(){
		
		return clientesRepository.findAll().size();
		
	}
	
	/**
	 * Método para búsquedas exactas. Se utiliza la implementación por defecto de JPARepository
	 * @param example
	 * @return
	 */
	public Cliente findByExample(Example<Cliente> example){
		
		return clientesRepository.findOne(example).get();
		
	}
	
	
	/**
	 * Método para búsquedas aproximadas. Se implementa un repository personalizado
	 * @param cliente
	 * @return
	 */
	public List<Cliente> findClientes(Cliente cliente){
		
		return clientesRepository.findClientes(cliente);
		
	}
	
}
