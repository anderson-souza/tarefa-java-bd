package com.andersonsouza.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO {

	public abstract void salvar();

	public abstract void listar();

	protected EntityManager getEntityManager(String unit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(unit);
		return emf.createEntityManager();
	}
}
