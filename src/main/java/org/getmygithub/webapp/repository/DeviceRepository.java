package org.getmygithub.webapp.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.getmygithub.webapp.config.Application;
import org.getmygithub.webapp.model.Device;
import org.getmygithub.webapp.utils.CustomQueryParam;

public class DeviceRepository {
	private EntityManager em;

	public DeviceRepository() {
		em = Application.createEntityManager();
	}

	public Device save(Device device) {
		em.getTransaction().begin();
		em.persist(device);
		em.getTransaction().commit();
		System.out.println(device.toString());
		return device;
	}

	public Optional<Device> findById(Long id) {
		em.getTransaction().begin();
		Device device = em.find(Device.class, id);
		em.getTransaction().commit();
		return device != null ? Optional.of(device) : Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public List<Device> findAll() {
		for (Device device : (List<Device>) em.createQuery("from Device").getResultList() ) {
			System.out.println(device.toString());
		}
		return em.createQuery("from Device").getResultList();
	}

	public List<Device> findAllByCustomCriteria(TypedQuery<Device> typedQuery) {
		return typedQuery.getResultList();
	}
	
	
	public Device update(Device device) {
		em.getTransaction().begin();
		device = em.merge(device);
		em.getTransaction().commit();
		return device;
	}

	public void deleteById(Long id) {
		em.getTransaction().begin();
		em.remove(em.find(Device.class, id));
		em.getTransaction().commit();
	}
	
	public TypedQuery<Device> createTypedQuery(String query, List<CustomQueryParam> customQueryParams){

		TypedQuery<Device> typedQuery = em.createQuery(" SELECT e FROM Device e WHERE " + query, Device.class);
		
		for (Iterator<CustomQueryParam> iterator = customQueryParams.iterator(); iterator.hasNext();) {
			CustomQueryParam customQueryParam = (CustomQueryParam) iterator.next();
			typedQuery.setParameter(customQueryParam.getIdentity(), customQueryParam.getValue());
		}
		
		return typedQuery;	
		
	}

	public void close() {
		Application.closeFactory();
	}
}
