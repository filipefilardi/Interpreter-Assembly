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

public class AssemblyScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel linhaSendoExecutada;
	private JTable microInstrucoes;
	public JLabel ax_label, cx_label, bx_label, dx_label, marLabel, mbrLabel, ulaLabel;
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
		setBounds(100, 100, 1563, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea lineNumber = new JTextArea();
		lineNumber.setText("0:\n1:\n2:\n3:\n4:\n5:\n6:\n7:\n8:\n9:\n10:\n11:\n12:\n13:\n14:\n15:\n16:\n17:\n18:\n19:\n20:\n21:\n22:\n23:\n24:\n25:\n26:\n27:\n28:\n29:\n30:\n31:\n32:\n33:\n34:\n35:\n36:\n37:\n38:\n39:\n40:");
		lineNumber.setWrapStyleWord(true);
		lineNumber.setRows(32);
		lineNumber.setBounds(6, 6, 20, 665);
		contentPane.add(lineNumber);
		
		JTextPane textAssembly = new JTextPane();
		textAssembly.setBounds(33, 6, 171, 658);
		contentPane.add(textAssembly);
		
		String[] columnNames = {"Memoria", "Dados"};
		
//		Object[][] data = {{"000", "33"},{"004", "11"},{"FF8", "3"}};

		
		//TODO ARRUMAR OQ CAGAMOS
		Object[][] data = new Object[4088][2];
		data[0][0] = 0;
		data[0][1] = 0;
		int aux = 4;
		for(int i = 1; i<= data.length-1; i++){
			data[i][0] = CmdAssembly.toHex(aux);
			data[i][1] = 0;
			aux += 4;
		}
		
		
		table = new JTable(data, columnNames);
		table.setBounds(942, 139, 289, 531);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/guerra/Dropbox/EACH/OCD/EP2/Interpreter-Assembly/CPU.png"));
		lblNewLabel.setBounds(218, 389, 260, 260);
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
		
		
		linhaSendoExecutada.setBounds(539, 674, 90, 16);
		contentPane.add(linhaSendoExecutada);
		
		
		btnParar.setEnabled(false);
		btnParar.setBounds(174, 669, 117, 29);
		contentPane.add(btnParar);
		
		JPanel panel = new JPanel();
		panel.setBounds(519, 661, -21, -13);
		contentPane.add(panel);
		
		JLabel lblAx = new JLabel("AX:");
		lblAx.setBounds(224, 222, 34, 16);
		contentPane.add(lblAx);
		
		JLabel ax_label = new JLabel("");
		ax_label.setBounds(252, 222, 61, 16);
		contentPane.add(ax_label);
		
		JLabel lblCx = new JLabel("CX:");
		lblCx.setBounds(424, 224, 34, 16);
		contentPane.add(lblCx);
		
		JLabel cx_label = new JLabel("");
		cx_label.setBounds(451, 224, 61, 16);
		contentPane.add(cx_label);
		
		JLabel lblBx = new JLabel("BX:");
		lblBx.setBounds(224, 243, 34, 16);
		contentPane.add(lblBx);
		
		JLabel bx_label = new JLabel("");
		bx_label.setBounds(251, 243, 61, 16);
		contentPane.add(bx_label);
		
		JLabel lblDx = new JLabel("DX:");
		lblDx.setBounds(421, 243, 34, 16);
		contentPane.add(lblDx);
		
		JLabel dx_label = new JLabel("");
		dx_label.setBounds(448, 243, 61, 16);
		contentPane.add(dx_label);
		
		JLabel lblNewLabel_1 = new JLabel("Micro Operações: (LInha x)");
		lblNewLabel_1.setBounds(555, 101, 245, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Memoria (hex):");
		lblNewLabel_2.setBounds(943, 111, 185, 16);
		contentPane.add(lblNewLabel_2);
		
		String[] columnNames2 = {"T1:", "Dados:", "Portas:"};		
		Object[][] dataInstrucoes = {{"T1", "Mar< PC", "???"},{"T2", "MBR< [MAR]", "???"},{"", "PC++", "???"}, {"T3", "IR < MBR", "???"}};

		
		microInstrucoes = new JTable(dataInstrucoes, columnNames2);
		microInstrucoes.setBounds(555, 139, 375, 466);
		contentPane.add(microInstrucoes);
		
		JLabel lblNewLabel_3 = new JLabel("Flags:");
		lblNewLabel_3.setBounds(216, 29, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sinal:");
		lblNewLabel_4.setBounds(226, 57, 61, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel sinalLabel = new JLabel("");
		sinalLabel.setBounds(602, 101, 61, 16);
		contentPane.add(sinalLabel);
		
		JLabel zeroLabel = new JLabel("");
		zeroLabel.setBounds(793, 101, 61, 16);
		contentPane.add(zeroLabel);
		
		JLabel lblZero = new JLabel("Zero:");
		lblZero.setBounds(417, 57, 61, 16);
		contentPane.add(lblZero);
		
		JLabel lblRegistradores = new JLabel("Registradores:");
		lblRegistradores.setBounds(216, 94, 109, 16);
		contentPane.add(lblRegistradores);
		
		JLabel lblMar = new JLabel("MAR:");
		lblMar.setBounds(226, 122, 34, 16);
		contentPane.add(lblMar);
		
		marLabel = new JLabel("");
		marLabel.setBounds(254, 122, 61, 16);
		contentPane.add(marLabel);
		
		JLabel lblMbr = new JLabel("MBR:");
		lblMbr.setBounds(417, 122, 34, 16);
		contentPane.add(lblMbr);
		
		JLabel mbrLabel = new JLabel("");
		mbrLabel.setBounds(445, 122, 61, 16);
		contentPane.add(mbrLabel);
		
		JLabel lblPc = new JLabel("PC:");
		lblPc.setBounds(226, 150, 34, 16);
		contentPane.add(lblPc);
		
		JLabel pcLabel = new JLabel("");
		pcLabel.setBounds(254, 150, 61, 16);
		contentPane.add(pcLabel);
		
		JLabel lblIr = new JLabel("IR:");
		lblIr.setBounds(417, 150, 34, 16);
		contentPane.add(lblIr);
		
		JLabel irLabel = new JLabel("");
		irLabel.setBounds(445, 150, 61, 16);
		contentPane.add(irLabel);
		
		JLabel ulaLabel = new JLabel("");
		ulaLabel.setBounds(254, 177, 61, 16);
		contentPane.add(ulaLabel);
		
		JLabel lblUc = new JLabel("UC:");
		lblUc.setBounds(226, 205, 34, 16);
		contentPane.add(lblUc);
		
		JLabel lblUla = new JLabel("ULA:");
		lblUla.setBounds(226, 177, 34, 16);
		contentPane.add(lblUla);
		
		JLabel ucLabel = new JLabel("");
		ucLabel.setBounds(254, 205, 61, 16);
		contentPane.add(ucLabel);
		
		JLabel lblAc = new JLabel("AC:");
		lblAc.setBounds(417, 177, 34, 16);
		contentPane.add(lblAc);
		
		JLabel acLabel = new JLabel("");
		acLabel.setBounds(445, 177, 61, 16);
		contentPane.add(acLabel);
		
		JLabel uxLabel = new JLabel("");
		uxLabel.setBounds(445, 205, 61, 16);
		contentPane.add(uxLabel);
		
		JLabel lblUx = new JLabel("UX:");
		lblUx.setBounds(417, 205, 34, 16);
		contentPane.add(lblUx);
		
		JButton btnNewButton = new JButton("Proximo Tempo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(771, 617, 158, 29);
		contentPane.add(btnNewButton);
		
		CmdAssembly cmdAssembly = new CmdAssembly();
		cmdAssembly.assembly(this);
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAssembly.execute(table, textAssembly, ax_label, bx_label, cx_label, dx_label, microInstrucoes);
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
