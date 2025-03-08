package estacionamento.apresentacao;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import estacionamento.controle.EstacionamentoController;
import estacionamento.negocio.Movimentacao;

public class TelaIniciaRelatorio extends JFrame implements ActionListener {
	
	private JComboBox<String> cboAno;
	private JComboBox<String> cboMes;
	
	public TelaIniciaRelatorio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600,145));
		setResizable(false);
		setTitle("Filtro do Relatorio");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 15, 40));
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblAno);
		
		cboAno = new JComboBox<String>();
		cboAno.setModel(new DefaultComboBoxModel<>(new String[] {"", "2025", "2024", "2023", "2022", "2021", "2020"}));
		cboAno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(cboAno);
		
		JLabel lblMês = new JLabel("Mês:");
		lblMês.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblMês);
		
		cboMes = new JComboBox<String>();
		cboMes.setModel(new DefaultComboBoxModel<>(new String[] {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		cboMes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(cboMes);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(this);
		btnGerar.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(btnGerar);
	
		setLocationRelativeTo(null);
	}
	private static final long serialVersionUID = 1L;
	@Override
	public void actionPerformed(ActionEvent e) {
		// recupera do combo o ano e mes escolhido
		int ano = Integer.parseInt( (String) cboAno.getSelectedItem());
		int mes = (Integer) cboMes.getSelectedIndex()+1;
		
		
		//buscar as movimentações do mês e ano informados
		EstacionamentoController controle = new EstacionamentoController();
		LocalDateTime data = LocalDateTime.of(ano, mes,1,0,0);
		List<Movimentacao> movimentacoes = controle.emitirRelatorio(data);
		
		//exibe a tela de conteúdo e faturamento
		TelaResultadoRelatorio relatorio = new TelaResultadoRelatorio(this,movimentacoes,data);
		relatorio.setVisible(true);
		dispose();
		
	}
	
}
