package es.golemdr.libreriaweb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.libreriaweb.server.domain.Cliente;


@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long>, ClientesRepositoryCustom{

}