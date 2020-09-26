package es.golemdr.libreriaweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.domain.Pedido;
import es.golemdr.libreriaweb.server.repository.ClientesRepository;
import es.golemdr.libreriaweb.server.repository.PedidosRepository;




@Service
public class PedidosService {

		
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	
	public Pedido insertarActualizarPedido(Pedido pedido) {
		
		clientesRepository.save(pedido.getCliente());
		
		return pedidosRepository.save(pedido);
		
	}
	
	public List<Pedido> findByExample(Example<Pedido> example){
		
		return pedidosRepository.findAll(example);
		
	}
	
	public List<Pedido> getPedidos(){
		
		return pedidosRepository.findAll();
		
	}

}
