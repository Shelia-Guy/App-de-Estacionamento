package estacionamento.negocio;

import estacionamento.controle.EstacionamentoController;

/**
 * Representa as informações relativas à vagas do estacionamento
 * ou status de ocupaçaõ das vagas.
 */
public class Vaga {
	public static int TOTAL_VAGAS = 100;
	private static int vagasOcupadas = inicializarOcupadas();
	
	private Vaga() {}
	/**
	 * Verificar a existencia de alguma vaga livre no estacionamento
	 *
	 * @return true se houver alguma vaga e false se stiver lotado
	 */
	public static boolean temVagaLivre() {
		
		return (vagasOcupadas < TOTAL_VAGAS);
	}
	

	/**
	 * Buscar o status atual das vagas do estacionamento.
	 */
	@SuppressWarnings("unused")
	public static int inicializarOcupadas() {
		EstacionamentoController controle = new EstacionamentoController();
		return EstacionamentoController.inicializarOcupadas();
	}
		
	/**
		 * Retorna o numero de vagas ocupadas
		 * @return numero total de vagas ocupadas num determinado instante
		 */
	public static int ocupadas() {
		return Vaga.vagasOcupadas;
	}
	
	
	/**
	 * Retorna o numero de vagas livres
	 * @return numero total de vagas livres num determinado instante
	 */
	
	public static int livres() {
		return TOTAL_VAGAS - Vaga.vagasOcupadas;
	}
	
	/**
	 * Atualiza o numero de vagas occupadas na entradas do veiculo
	 */
	public static void entrou() {
		Vaga.vagasOcupadas ++;
	}
	
	public static void saiu() {
		Vaga.vagasOcupadas --;
	}
}
