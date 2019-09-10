package com.andersonsouza.dao;

import javax.persistence.EntityManager;

import com.andersonsouza.model.Pessoa;

public final class PessoaDAO extends DAO {

	private Pessoa pessoa;
	private EntityManager manager;
	private final String UNIT_NAME = "pessoa";

	public PessoaDAO(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Salva uma pessoa no banco de dados
	 */
	@Override
	public void salvar() {
		manager = getEntityManager(UNIT_NAME);
		try {
			manager.getTransaction().begin();
			manager.persist(this.pessoa);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	/**
	 * Lista no terminal todas as pessoas cadastradas no banco de dados
	 */
	@Override
	public void listar() {
		manager = getEntityManager(UNIT_NAME);
		try {
			manager.getTransaction().begin();
			manager.createQuery("from Pessoa", Pessoa.class).getResultList().stream().forEach(System.out::println);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}
}
