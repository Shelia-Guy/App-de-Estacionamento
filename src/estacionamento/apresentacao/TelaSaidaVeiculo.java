package estacionamento.apresentacao;

import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import estacionamento.controle.EstacionamentoController;
import estacionamento.controle.EstacionamentoException;
import estacionamento.controle.VeiculoException;
import estacionamento.negocio.Movimentacao;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.Color;
import java.awt.FlowLayout;

public class TelaSaidaVeiculo extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private JFormattedTextField txtPlaca;
	
	public TelaSaidaVeiculo(JFrame parent) {
		setForeground(Color.BLACK);
		setResizable(false);
		setTitle("Saída de Veiculo");
		 setSize(new Dimension(445, 179));
		 
		 this.parent = parent;
		 
		 JPanel panel = new JPanel();
		 getContentPane().add(panel, BorderLayout.SOUTH);
		 
		 JButton btnOk = new JButton("Ok");
		 btnOk.addActionListener(this);
		 btnOk.setActionCommand("Ok");
		 panel.add(btnOk);
		 
		 JButton btnCancel = new JButton("Cancelar");
		 btnCancel.addActionListener(this);
		 btnCancel.setActionCommand("Cancelar");
		 panel.add(btnCancel);
		 
		 JPanel panel_1 = new JPanel();
		 FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		 flowLayout.setVgap(40);
		 getContentPane().add(panel_1, BorderLayout.CENTER);
		 
		 JLabel lblPlaca = new JLabel("Placa:");
		 lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 15));
		 panel_1.add(lblPlaca);
		 
		 txtPlaca = null;
		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e) {
			assert false : "Formato do padrão inválido!";
		}
		 
		 
		 txtPlaca.setForeground(Color.BLUE);
		 txtPlaca.setFont(new Font("Tahoma", Font.BOLD, 15));
		 txtPlaca.setColumns(10);
		 panel_1.add(txtPlaca);
		 
		 setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String cmd = evento.getActionCommand();
		
		if(cmd.equals("Ok")) {
			EstacionamentoController controle = new EstacionamentoController();
			Movimentacao movimentacao = null;
			try {
				movimentacao = controle.processarSaida(txtPlaca.getText ());
			} catch (VeiculoException | EstacionamentoException e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(),
					"Falha na saída", JOptionPane.ERROR_MESSAGE);
		}
			TelaResumoPagamento telaResumo = 
					new TelaResumoPagamento(movimentacao,parent);
			telaResumo.setVisible(true);
		}else {
			parent.setVisible(true);
		}
		dispose();
	}	
}

