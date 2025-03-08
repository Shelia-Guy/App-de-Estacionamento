package estacionamento.apresentacao;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import estacionamento.negocio.Movimentacao;
import estacionamento.utilitario.EstacionamentoUtil;


public class TelaResumoPagamento extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	
	public TelaResumoPagamento(Movimentacao movimentacao,   JFrame parent) {
		
		this.parent =parent;
		setSize(new Dimension(375, 300));
		setResizable(false);
		setTitle("Resumo de Pagamento");
		getContentPane().setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlaca.setBounds(79, 34, 73, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblDataEntrada = new JLabel("Entrada");
		lblDataEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataEntrada.setBounds(79, 87, 73, 14);
		getContentPane().add(lblDataEntrada);
		
		JLabel lblDataSaida = new JLabel("Saída:");
		lblDataSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataSaida.setBounds(79, 140, 73, 14);
		getContentPane().add(lblDataSaida);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(79, 193, 73, 14);
		getContentPane().add(lblValor);
		
		
		String sPlaca = movimentacao.getVeiculo().getPlaca();
		JLabel lblValPlaca = new JLabel(sPlaca);
		lblValPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValPlaca.setBounds(188, 34, 118, 14);
		getContentPane().add(lblValPlaca);
		
		String sEntrada = EstacionamentoUtil.getDisplayData
				(movimentacao.getDataHoraEntrada());
				JLabel lblValDataEntrada = new JLabel(sEntrada);
		lblValDataEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValDataEntrada.setBounds(188, 87, 118, 14);
		getContentPane().add(lblValDataEntrada);
		
		String sSaida = EstacionamentoUtil.getDisplayData
				(movimentacao.getDataHoraSaida());
		JLabel lblValDataSaida = new JLabel(sSaida);
		lblValDataSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValDataSaida.setBounds(188, 140, 118, 14);
		getContentPane().add(lblValDataSaida);
		
		String sValor = "R$" + movimentacao.getValor();
		JLabel lblValValor = new JLabel(sValor);
		lblValValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValValor.setBounds(188, 193, 118, 14);
		getContentPane().add(lblValValor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(123, 227, 89, 23);
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.setVisible(true);
		dispose();
	} 
}
