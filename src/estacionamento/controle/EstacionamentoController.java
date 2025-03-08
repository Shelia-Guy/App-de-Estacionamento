package estacionamento.controle;

import java.time.LocalDateTime;
import java.util.List;

import estacionamento.negocio.Movimentacao;
import estacionamento.negocio.Vaga;
import estacionamento.negocio.Veiculo;
import estacionamento.persistencia.DAOEstacionamento;
import estacionamento.utilitario.EstacionamentoUtil;

/**
 * A partir dos dados de veiculo informados pelo operador
 * o fluxo de entrada do veiculo no estacionamento registrandos a movimentação
 *
 *@param placa Placa do veiculo
 *@param marca Marca  do veiculo
 *@param modelo Modelo do veiculo
 *@param cor Cor do veiculo
 * @throws EstacionamentoException Quando o estacionamento estiver lotado
 * @throws VeiculoException, Quando o padrão da placa for invalido
 */

public class EstacionamentoController {
	
	public void processarEntrada(String placa, String marca, String modelo, String cor) 
			throws EstacionamentoException,VeiculoException{
		
		//Verificar se o estacionamento está lotado
		if(!Vaga.temVagaLivre()) {
			throw new EstacionamentoException("Estacionamento lotado!");
			
		}
		
		//Verificar o padrão de string da placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)) {
			throw new VeiculoException("Placa informada inválida!");
		}
		//criar uma instancia do veiculo
		Veiculo veiculo = new Veiculo(placa,marca,modelo,cor);
		
		//criar a movimentação vinculado o veículo e com data entrada corrente
		Movimentacao movimentacao = new Movimentacao(veiculo,LocalDateTime.now());
				
		//registrar na base de dados a informação
		DAOEstacionamento dao = new DAOEstacionamento();
		dao.criar(movimentacao);
		
		//atualizar o numero vagas ocupadas
		Vaga.entrou();
		
		//fim
		
	}
	
	/**
	 * Apartir de uma placa de veículo informada, realiza todo o
	 * fluxo de saída de veículo do estacionamento
	 * @param placa Placa do veículo que estiver saindo
	 * @return uma instancia de movimentação com dados atualizados de vaçlor
	 * @throws VeiculoException Quando a placa estiver incorreta
	 * @throws EstacionamentoException Quando o veículo com a placa informada não é localizado
	 * no estacionamento
	 */
	public Movimentacao processarSaida(String placa) throws VeiculoException, EstacionamentoException {
		//validar a placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)) {
			throw new VeiculoException("Placa inválida!");
		}
		
		//Buscar a movimentação aberta baseada na placa
		DAOEstacionamento dao = new DAOEstacionamento();
		Movimentacao movimentacao = dao.buscarMovimentacaoAberta(placa);
			
		if(movimentacao == null) {
			throw new EstacionamentoException("Veículo não encontrado!"); 
		}
		
		
		// Fazer calcular o calculo do valor a ser pago
		movimentacao.setDataHoraSaida(LocalDateTime.now());
		EstacionamentoUtil.calcularValorPago(movimentacao);
		
		//Atualizar os dados da movimentação
		dao.atualizar(movimentacao);
		
		
		
		//Atualizar o status da vaga
		Vaga.saiu();
		
		
		return movimentacao;
	}
	
	public List<Movimentacao> emitirRelatorio(LocalDateTime data){
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.consultarMovimentacoes(data);
	}

	
	
	public static  int inicializarOcupadas() {
	DAOEstacionamento dao = new DAOEstacionamento();
		return dao.getOcupadas();
	}

	
	
}
