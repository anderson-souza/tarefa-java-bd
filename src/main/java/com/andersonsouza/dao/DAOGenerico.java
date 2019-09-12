package com.andersonsouza.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe DAOGenerico serve para abstrair a implementação mais comuns utilizadas
 * nos DAOs, como operações de Inclusão e Seleção de dados.
 * 
 * @author ander
 *
 * @param <T>        Classe pra fazer a persistencia e busca dos dados
 * @param <UnitName> Nome da Unidade Mapeada nas configurações do Hibernate
 */
public abstract class DAOGenerico<T, UnitName> {

	Class<T> classePersistida;
	private EntityManager manager;
	private String UnitName;

	private DAOGenerico() {

	}

	protected DAOGenerico(Class<T> classePersistida, String UnitName) {
		this();
		this.classePersistida = classePersistida;
		this.UnitName = UnitName;
	}

	/**
	 * Salva uma Entidade no Banco de dados
	 * 
	 * @param entidade
	 * @return
	 */
	public T salvar(T entidade) {
		manager = getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(entidade);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return entidade;
	}

	/**
	 * Retorna uma lista com todos os registros encontrados no banco de dados
	 * 
	 * @return Lista com os registros
	 */
	public List<T> retornaListaTodosRegistrosBD() {
		manager = getEntityManager();
		List<T> lista = new ArrayList<>();
		try {
			manager.getTransaction().begin();
			// Necessário converter primeiro caracter da Unidade para maiúsculo, pois é
			// assim que está configurado no banco de dados
			String tempUnitName = UnitName.substring(0, 1).toUpperCase() + UnitName.substring(1);
			lista = manager.createQuery("from " + tempUnitName, classePersistida).getResultList();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return lista;
	}

	/**
	 * Imprime todos os registros do banco de dados no Terminal
	 */
	public void imprimirTodosRegistros() {
		retornaListaTodosRegistrosBD().stream().forEach(System.out::println);
	}

	/**
	 * Retorna o EntityManager para a entidade que está sendo utilizada neste DAO
	 * 
	 * @return EntityManager gerado
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.UnitName);
		return emf.createEntityManager();
	}
}
