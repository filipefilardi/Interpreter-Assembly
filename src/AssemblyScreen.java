package src;

/*
EP2-OCD
Filipe Filardi de Jesus, 8516761
Gabriel Salgado Sina, 8061448
Rodrigo Guerra, 8516497
*/


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.Color;

public class AssemblyScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel linhaSendoExecutada;

	public JTable microInstrucoes;
	public JLabel ax_label, cx_label, bx_label, dx_label, mbrLabel, ulaLabel, zero_label, sinal_label;
	boolean jump;
	int lineNeedUp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssemblyScreen frame = new AssemblyScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void atualizaLinha(int i){
		linhaSendoExecutada.setText(Integer.toString(i));
	}

	/**
	 * Create the frame.
	 */
	public AssemblyScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea lineNumber = new JTextArea();
		lineNumber.setText("0:\n1:\n2:\n3:\n4:\n5:\n6:\n7:\n8:\n9:\n10:\n11:\n12:\n13:\n14:\n15:\n16:\n17:\n18:\n19:\n20:\n21:\n22:\n23:\n24:\n25:\n26:\n27:\n28:\n29:\n30:\n31:\n32:\n33:\n34:\n35:\n36:\n37:\n38:\n39:\n40:");
		lineNumber.setWrapStyleWord(true);
		lineNumber.setRows(32);
		lineNumber.setBounds(6, 107, 20, 628);
		contentPane.add(lineNumber);
		
		JTextPane textAssembly = new JTextPane();
		textAssembly.setBounds(33, 107, 171, 557);
		contentPane.add(textAssembly);
		
		String[] columnNames = {"Memoria", "Dados"};
		
//		Object[][] data = {{"000", "33"},{"004", "11"},{"FF8", "3"}};

		
		Object[][] data = new Object[4088][2];
		data[0][0] = 0;
		data[0][1] = 0;
		int aux = 4;
		for(int i = 1; i<= data.length-1; i++){
			data[i][0] = CmdAssembly.toHex(aux).toUpperCase();
			data[i][1] = 0;
			aux += 4;
		}
		
		
		table = new JTable(data, columnNames);
		table.setBounds(942, 113, 289, 557);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("CPU.png"));
		lblNewLabel.setBounds(237, 107, 260, 260);
		contentPane.add(lblNewLabel);
		
		
		JButton btnExecutar = new JButton("Executar");
		JButton btnNextLine = new JButton("Proxima Linha");
		JButton btnParar = new JButton("Parar");
		JLabel lblExecutandoLinha = new JLabel("Executando Linha:");
		JLabel linhaSendoExecutada = new JLabel("0");
		
		
		btnExecutar.setBounds(38, 669, 117, 29);
		contentPane.add(btnExecutar);
		
	
		btnNextLine.setEnabled(false);
		btnNextLine.setBounds(292, 669, 117, 29);
		contentPane.add(btnNextLine);
		
		
		lblExecutandoLinha.setBounds(421, 674, 124, 16);
		contentPane.add(lblExecutandoLinha);
		
		JLabel linhaSendoExecutada2 = new JLabel("");
		linhaSendoExecutada2.setBounds(709, 85, 61, 16);
		contentPane.add(linhaSendoExecutada2);
		
		
		linhaSendoExecutada.setBounds(539, 674, 90, 16);
		contentPane.add(linhaSendoExecutada);
		linhaSendoExecutada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				linhaSendoExecutada2.setText(linhaSendoExecutada.getText());
			}
		});
		
		btnParar.setEnabled(false);
		btnParar.setBounds(174, 669, 117, 29);
		contentPane.add(btnParar);
		
		JPanel panel = new JPanel();
		panel.setBounds(519, 661, -21, -13);
		contentPane.add(panel);
		
		JLabel lblAx = new JLabel("AX:");
		lblAx.setBounds(237, 608, 34, 16);
		contentPane.add(lblAx);
		
		JLabel ax_label = new JLabel("");
		ax_label.setBounds(265, 608, 61, 16);
		contentPane.add(ax_label);
		
		JLabel lblCx = new JLabel("CX:");
		lblCx.setBounds(437, 610, 31, 16);
		contentPane.add(lblCx);
		
		JLabel cx_label = new JLabel("");
		cx_label.setBounds(464, 608, 61, 16);
		contentPane.add(cx_label);
		
		JLabel lblBx = new JLabel("BX:");
		lblBx.setBounds(237, 629, 34, 16);
		contentPane.add(lblBx);
		
		JLabel bx_label = new JLabel("");
		bx_label.setBounds(264, 629, 61, 16);
		contentPane.add(bx_label);
		
		JLabel lblDx = new JLabel("DX:");
		lblDx.setBounds(434, 629, 34, 16);
		contentPane.add(lblDx);
		
		JLabel dx_label = new JLabel("");
		dx_label.setBounds(464, 629, 61, 16);
		contentPane.add(dx_label);
		
		JLabel lblNewLabel_1 = new JLabel("Micro Operações: (Linha     ):");
		lblNewLabel_1.setBounds(555, 85, 245, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Memoria (hex):");
		lblNewLabel_2.setBounds(942, 85, 185, 16);
		contentPane.add(lblNewLabel_2);
		
		String[] columnNames2 = {"T1:", "Dados:", "Portas Abertas:", "Representacao Portas "};		
		Object[][] dataInstrucoes = {{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};

		
		microInstrucoes = new JTable(dataInstrucoes, columnNames2);
		microInstrucoes.setBounds(555, 153, 375, 517);
		contentPane.add(microInstrucoes);
		
		JLabel lblNewLabel_3 = new JLabel("Flags:");
		lblNewLabel_3.setBounds(229, 515, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sinal:");
		lblNewLabel_4.setBounds(239, 543, 34, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblZero = new JLabel("Zero:");
		lblZero.setBounds(430, 543, 34, 16);
		contentPane.add(lblZero);
		
		JLabel lblRegistradores = new JLabel("Registradores:");
		lblRegistradores.setBounds(229, 580, 109, 16);
		contentPane.add(lblRegistradores);
		
		JLabel sinal_label = new JLabel("");
		sinal_label.setBounds(277, 543, 61, 16);
		contentPane.add(sinal_label);
		
		JLabel zero_label = new JLabel("");
		zero_label.setBounds(464, 543, 61, 16);
		contentPane.add(zero_label);
		
		JLabel lblEpOcd = new JLabel("EP2 | OCD Interpreter Assembly");
		lblEpOcd.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		lblEpOcd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpOcd.setBounds(6, 11, 1225, 62);
		contentPane.add(lblEpOcd);
		
		JLabel lblCdigoAssembly = new JLabel("Código Assembly:");
		lblCdigoAssembly.setBounds(6, 85, 149, 16);
		contentPane.add(lblCdigoAssembly);
		
		JLabel lblNewLabel_5 = new JLabel("Criado por Filipe Filardi de Jesus, Gabriel Salgado Sina & Rodrigo Guerra. ");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblNewLabel_5.setBounds(940, 683, 299, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblCpuBarramentosE = new JLabel("CPU, Barramentos e Registradores:");
		lblCpuBarramentosE.setBounds(218, 81, 245, 16);
		contentPane.add(lblCpuBarramentosE);
		
		JLabel lblNewLabel_6 = new JLabel("Tempo");
		lblNewLabel_6.setBounds(555, 125, 61, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblMicroOperao = new JLabel("Micro operação:");
		lblMicroOperao.setBounds(682, 125, 100, 16);
		contentPane.add(lblMicroOperao);
		
		JLabel lblPortasAbertas = new JLabel("Portas Abertas:");
		lblPortasAbertas.setBounds(810, 125, 109, 16);
		contentPane.add(lblPortasAbertas);
		
		JTextPane txtpnKkkk = new JTextPane();
		txtpnKkkk.setText("Observação: Em alguns poucos casos o registrador ou porta utilizado dentro de nossa arquitetura(vide imagem à cima), não contemplava, ou não deixava claro as portas ou registradores de algumas micro operações implementadas. Detalhes sobre \"Magia\",\"Spell\" e \"Resto Divisão\" podem ser encontradas no relatório.\n");
		txtpnKkkk.setBounds(216, 371, 329, 115);
		contentPane.add(txtpnKkkk);
		
		JLabel alert_label = new JLabel("");
		alert_label.setForeground(Color.RED);
		alert_label.setBounds(557, 674, 375, 16);
		contentPane.add(alert_label);
		
		
		
		CmdAssembly cmdAssembly = new CmdAssembly();
		cmdAssembly.assembly(this);
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAssembly.execute(table, textAssembly, ax_label, bx_label, cx_label, dx_label, zero_label, sinal_label, microInstrucoes, alert_label);
				btnExecutar.setEnabled(false);
				btnNextLine.setEnabled(true);
				btnParar.setEnabled(true);
				textAssembly.setEnabled(false);
				linhaSendoExecutada.setText("0");
			}
		});
		
		
			btnNextLine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jump == false){
						String linhaS = linhaSendoExecutada.getText();
						int linha = Integer.parseInt(linhaS);
						linha++;
						linhaS = String.valueOf(linha);
						linhaSendoExecutada.setText(linhaS);
						cmdAssembly.executeLine(linha);	
					} else {
						String linhaS = linhaSendoExecutada.getText();
						linhaS = String.valueOf(lineNeedUp);
						linhaSendoExecutada.setText(linhaS);
						cmdAssembly.executeLine(lineNeedUp);
						jump = false;
					}
				}
			});

		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExecutar.setEnabled(true);
				btnNextLine.setEnabled(false);
				btnParar.setEnabled(false);
				textAssembly.setEnabled(true);
			}
		});
		//TODO: Quanto nao tem mais linhas?
		
		
		
	}
	
	public void setJump(boolean jump){
		this.jump = jump;
	}
	
	public void setLineNeedUp(int i){
		this.lineNeedUp = i;
	}
}
