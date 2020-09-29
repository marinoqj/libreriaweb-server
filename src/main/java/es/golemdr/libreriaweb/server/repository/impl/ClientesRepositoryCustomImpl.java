package es.golemdr.libreriaweb.server.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.repository.ClientesRepositoryCustom;


@Repository
public class ClientesRepositoryCustomImpl implements ClientesRepositoryCustom{
	
    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Cliente> findClientes(Cliente filtro) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
		Root<Cliente> cliente = query.from(Cliente.class);		

		List<Predicate> predicates = new ArrayList<Predicate>();
		if(filtro.getNombre() != null){
		    Predicate condition = cb.like(cliente.<String>get("nombre"),"%"+filtro.getNombre()+"%");
		    predicates.add(condition);
		}
		if(filtro.getApellidos() != null){
		    Predicate condition = cb.like(cliente.<String>get("apellidos"),"%"+filtro.getApellidos()+"%");
		    predicates.add(condition);
		}		
		if(filtro.getDireccion() != null){
		    Predicate condition = cb.like(cliente.<String>get("direccion"),"%"+filtro.getDireccion()+"%");
		    predicates.add(condition);
		}		
		
		
		// Devuelve solo los resultados que concidan (parcialmente - por los comodines) con TODOS los elementos del filtro
		query.select(cliente).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		/**
		 * Devuelve solo los resultados que concidan (parcialmente - por los comodines) con ALGUNO los elementos del filtro
		 */
		//query.select(cliente).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		
		
		return em.createQuery(query).getResultList();
	}

	
}



//public List<User> findUserByEmails(Set<String> emails) {
//CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//CriteriaQuery<User> query = cb.createQuery(User.class);
//Root<User> user = query.from(User.class);
//
//Path<String> emailPath = user.get("email");
//
//List<Predicate> predicates = new ArrayList<>();
//for (String email : emails) {
//  predicates.add(cb.like(emailPath, email));
//}
//query.select(user)
//  .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
//
//return entityManager.createQuery(query)
//  .getResultList();
//}



//public List<MyObject> listAllForIds(List<Long> ids) {
//
//    CriteriaBuilder builder = getSessionFactory().getCurrentSession().getCriteriaBuilder();
//    CriteriaQuery<MyObject> criteria = builder.createQuery(MyObject.class);
//    Root<MyObject> myObjectRoot = criteria.from(MyObject.class);
//    Join<MyObject, JoinObject> joinObject = myObjectRoot.join("joinObject");
//
//    Predicate likeRestriction = builder.and(
//            builder.notLike( myObjectRoot.get("name"), "%string1"),
//            builder.notLike( myObjectRoot.get("name"), "%string2")
//    );
//
//    criteria.select(myObjectRoot).where(joinObject.get("id").in(ids), likeRestriction);
//
//    TypedQuery<MyObject> query = getSessionFactory().getCurrentSession().createQuery(criteria);
//
//    return query.getResultList();
//}