package estacionamento.controle;

import java.io.Serializable;

public class EstacionamentoException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public EstacionamentoException(String msg) {
		super(msg);
	}
}
