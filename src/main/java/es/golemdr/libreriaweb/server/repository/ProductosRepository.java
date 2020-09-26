package es.golemdr.libreriaweb.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.libreriaweb.server.domain.Producto;




@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long>{

}