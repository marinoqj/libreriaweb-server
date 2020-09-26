package es.golemdr.libreriaweb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.libreriaweb.server.domain.Pedido;


@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long>{

}