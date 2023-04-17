package org.getmygithub.webapp.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.getmygithub.webapp.config.Application;
import org.getmygithub.webapp.model.Product;

public class ProductRepository {
	private EntityManager em;

	public ProductRepository() {
		em = Application.createEntityManager();
	}

	public Product save(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		System.out.println(product.toString());
		return product;
	}

	public Optional<Product> findById(Long id) {
		em.getTransaction().begin();
		Product product = em.find(Product.class, id);
		em.getTransaction().commit();
		return product != null ? Optional.of(product) : Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		for (Product product : (List<Product>) em.createQuery("from Product").getResultList() ) {
			System.out.println(product.toString());
		}
		return em.createQuery("from Product").getResultList();
	}

	public Product update(Product product) {
		em.getTransaction().begin();
		product = em.merge(product);
		em.getTransaction().commit();
		return product;
	}

	public void deleteById(Long id) {
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
		em.getTransaction().commit();
	}

	public void close() {
		Application.closeFactory();
	}
}
