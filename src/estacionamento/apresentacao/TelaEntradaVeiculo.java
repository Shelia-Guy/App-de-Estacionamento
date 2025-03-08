package estacionamento.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import estacionamento.controle.EstacionamentoController;
import estacionamento.controle.EstacionamentoException;
import estacionamento.controle.VeiculoException;

public class TelaEntradaVeiculo extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textCor;
	private JFormattedTextField txfPlaca;
	private JButton btnOk;
	private JButton btnCancel;
	
	
	//TODO Tempory
	public static void main(String[] args) {
		TelaEntradaVeiculo tela = new TelaEntradaVeiculo(null);
		tela.setVisible(true);
	}
	
	
	public TelaEntradaVeiculo(JFrame parent) {
		setResizable(false);
		setSize(400,300);
		setTitle("Entrada de Veículo");
		// TODO Auto-generated constructor stub
		this.parent = parent;
		getContentPane().setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(59, 31, 46, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(59, 72, 46, 14);
		getContentPane().add(lblMarca);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(59, 149, 46, 14);
		getContentPane().add(lblCor);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(59, 109, 46, 14);
		getContentPane().add(lblModelo);
		
		textMarca = new JTextField();
		textMarca.setBounds(149, 65, 104, 20);
		getContentPane().add(textMarca);
		textMarca.setColumns(10);
		
		textModelo = new JTextField();
		textModelo.setBounds(149, 102, 104, 20);
		getContentPane().add(textModelo);
		textModelo.setColumns(10);
		
		textCor = new JTextField();
		textCor.setBounds(149, 142, 104, 20);
		getContentPane().add(textCor);
		textCor.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(59, 214, 89, 23);
		btnOk.setActionCommand("Ok");
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(204, 214, 89, 23);
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(this);
		getContentPane().add(btnCancel);
		
		try {
			txfPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e) {
			assert false : "Padrão de placa errado!";
		}
		txfPlaca.setBounds(146, 28, 107, 20);
		getContentPane().add(txfPlaca);
		setLocationRelativeTo(null);
	}

	

	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getActionCommand().equals("Ok")) {
			if (txfPlaca.getText().isEmpty() || textMarca.getText().isEmpty() ||
		            textModelo.getText().isEmpty() || textCor.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", 
		                "Erro", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
			
			EstacionamentoController controle = new EstacionamentoController();
			try {
				controle.processarEntrada(txfPlaca.getText(),textMarca.getText(),
						textModelo.getText(), textCor.getText());
				JOptionPane.showMessageDialog(null,  "Veiculo registrado com sucesso",
						"Entrada de Veiculo ", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstacionamentoException | VeiculoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), 
						"Erro na Entrada", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (evento.getActionCommand().equals("cancel")) {
		if (this.parent != null) {
		    this.parent.setVisible(true);
		}
		this.dispose();
	}
}

}



