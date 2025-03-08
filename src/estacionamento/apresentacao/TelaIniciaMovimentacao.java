package estacionamento.apresentacao;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaIniciaMovimentacao extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
		private JButton btnEntrar;
		private JButton btnSair;
		
	//TODO temporary
	public static void main(String[] args) {
		TelaIniciaMovimentacao tela = new TelaIniciaMovimentacao();
		tela.setVisible(true);
	}
	public TelaIniciaMovimentacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(450,300));
		setResizable(false);
		setTitle("Sistema de Estacionamento");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(TelaIniciaMovimentacao.class.getResource("/recursos/getin.png")));
		getContentPane().add(btnEntrar);
		btnEntrar.addActionListener(this);
		btnEntrar.setActionCommand("entrada");
		
		btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(TelaIniciaMovimentacao.class.getResource("/recursos/getout.png")));
		btnSair.addActionListener(this);
		btnSair.setActionCommand("saida");
		getContentPane().add(btnSair);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		JFrame tela = null;
		
		if(cmd.equals("entrada")) {
			tela= new TelaEntradaVeiculo(this);
		}else{
			tela = new TelaSaidaVeiculo(this); 
		}
		tela.setVisible(true);
		this.setVisible(false);
	}
	

}
