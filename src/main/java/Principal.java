import javax.swing.JOptionPane;

import com.andersonsouza.dao.PessoaDAO;
import com.andersonsouza.model.Pessoa;

/**
 * Classe principal da aplicação.
 * 
 * @author ander
 *
 */
public class Principal {

	public static void main(String[] args) {

		Pessoa p = criarPessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(p);
		System.out.println("***IMPRIMINDO OS RESULTADOS NO TERMINAL***");
		pessoaDAO.imprimirTodosRegistros();
	}

	/**
	 * Método para Criar uma pessoa com base nas informações do usuário. Será
	 * apresentado 3 Caixas de diálogo solicitando nome, idade e endereço da pessoa.
	 * 
	 * @return Pessoa criada.
	 */
	private static Pessoa criarPessoa() {
		String nome = JOptionPane.showInputDialog("Digite o Nome da Pessoa");
		int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a Idade da Pessoa"));
		String endereco = JOptionPane.showInputDialog("Digite o Endereço da Pessoa");

		return new Pessoa(nome, idade, endereco);
	}
}
