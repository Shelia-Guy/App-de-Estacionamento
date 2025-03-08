package estacionamento.utilitario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import estacionamento.negocio.Movimentacao;
import estacionamento.negocio.Tarifario;

/**
 * Representa uma classe de apoio às demais do sistema
 */
public class EstacionamentoUtil {
	
	/**
	 * Valida a placa com o padrao LLL - NNN
	 * L = LETRA
	 * N = NUMERO
	 * @param placa Placa do veiculo
	 * @return true se atender o padrão senão false
	 */
	public static boolean validarPadraoPlaca(String placa) {
		String padrao = "[A-Z]{3}-\\d{4}";
		Pattern p = Pattern.compile(padrao);
		Matcher m = p.matcher(placa);
		
		return m.matches();
	
	}
	/**
	 * O calculo do valoor do estado do veiculo baseado no
	 * e na entrada e saida do veiculo
	 * Altera a propria instancia
	 * @param movimentacao Instancia do parametro
	 */
	public void calcularPagamento(Movimentacao movimentacao) {
		//TODO implementar
	}
	
	
	/**
	 * Recupera uma propriedade no arquivo de configuração
	 * da aplicação : configuration.txt
	 * @param propriedade
	 * @return valor associado a propriedade
	 */
	public static String get(String propriedade) {
		Properties prop = new Properties();
		String valor = null;
		
		try {
			prop.load(EstacionamentoUtil.class.getResourceAsStream("/recursos/configuration.txt"));
			valor = prop.getProperty(propriedade);
		} catch (IOException e) {
			assert false : "Configuração não carregada";
		}
		return valor;
	}
	public static String getDataAsString(LocalDateTime dataHoraEntrada) {
		return dataHoraEntrada.toString();
	}
	
	
	
	public static void calcularValorPago(Movimentacao movimentacao) {
		LocalDateTime inicio = movimentacao.getDataHoraEntrada();
		LocalDateTime fim = movimentacao.getDataHoraSaida();
		double valor = 0;
		
		long diffHoras = inicio.until(fim, ChronoUnit.HOURS);
		
		if(diffHoras > 0) {
			valor += Tarifario.VALOR_HORA;
			fim = fim.minus(1,ChronoUnit.HOURS);
		}
		
		long diffMinutos = inicio.until(fim,  ChronoUnit.MINUTES);
		
		valor += (diffMinutos/Tarifario.INCREMENTO_MINUTOS) *
				Tarifario.VALOR_INCREMENTADO;
		movimentacao.setValor(valor);
				
		}
	
	public static LocalDateTime getData(String rdataEntrada) {
		return LocalDateTime.parse(rdataEntrada, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	public static String getDisplayData(LocalDateTime data) {

		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
	public static String gerarTextoFaturamento(LocalDateTime data, List<Movimentacao> movimentacoes) {
		double totalFaturado = 0;
		String texto = "";
		for(Movimentacao movimentacao : movimentacoes) {
			totalFaturado += movimentacao.getValor();
		}
					
		String sAno =""+data.getYear();
		String sMes = data.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
		texto = "Faturamento do mês de " + sMes;
		texto += " de " + sAno + " foi de R$ " + totalFaturado;
		
		return texto;
	}
	
}

