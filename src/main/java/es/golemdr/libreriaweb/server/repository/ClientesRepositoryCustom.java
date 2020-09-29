package es.golemdr.libreriaweb.server.repository;

import java.util.List;

import es.golemdr.libreriaweb.server.domain.Cliente;

public interface ClientesRepositoryCustom {

	List<Cliente> findClientes(Cliente cliente);
	
}
