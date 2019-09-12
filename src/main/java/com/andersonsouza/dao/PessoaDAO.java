package com.andersonsouza.dao;

import com.andersonsouza.model.Pessoa;

/**
 * Classe DAO do Model Pessoa. Extende a classe DAOGenerico que se encarregará
 * de realizar as operações mais comuns de um CRUD.
 * 
 * @author ander
 *
 */
public final class PessoaDAO extends DAOGenerico<Pessoa, String> {

	private final static String UNIT_NAME = "pessoa";// Nome da Unidade mapeada nas configurações

	/**
	 * No construtor da classe é invocado a superclasse DAOGenerico passando a
	 * classe Pessoa e o nome da Unidade que foi mapeada nas configurações do
	 * Hibernate (persistence.xml).
	 */
	public PessoaDAO() {
		super(Pessoa.class, UNIT_NAME);
	}
}
