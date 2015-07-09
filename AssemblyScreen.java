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
		
		JLabel instructions_label = new JLabel("<html>\nBusca\n\n<br/>\nT1: MAR <-  PC &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Portas: 2,3 <br/>\nT2: MBR <-  [MAR] + 1 Portas: 2, 3,3<br/>&nbsp;&nbsp;&nbsp;&nbsp; \nPC++<br/>\nT3: IR <- MBR\n</html>");
		instructions_label.setVerticalAlignment(SwingConstants.TOP);
		instructions_label.setBounds(547, 14, 385, 651);
		contentPane.add(instructions_label);
		
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
		
		Object[][] data = {{"000", "33"},{"004", "11"},{"FF8", "3"}};

		/*
		//TODO ARRUMAR OQ CAGAMOS
		Object[][] data = new Object[4088][2];
		data[0][0] = 0;
		for(int i = 1; i<= data.length; i++){
			data[i][0] = Integer.parseInt(CmdAssembly.toHex(i));
		}
		*/
		
		table = new JTable(data, columnNames);
		table.setBounds(942, 139, 289, 531);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/CPU.png"));
		lblNewLabel.setBounds(216, 226, 507, 427);
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
		lblAx.setBounds(974, 29, 34, 16);
		contentPane.add(lblAx);
		
		JLabel ax_label = new JLabel("");
		ax_label.setBounds(1002, 29, 61, 16);
		contentPane.add(ax_label);
		
		JLabel lblCx = new JLabel("CX:");
		lblCx.setBounds(1062, 30, 34, 16);
		contentPane.add(lblCx);
		
		JLabel cx_label = new JLabel("");
		cx_label.setBounds(1089, 30, 61, 16);
		contentPane.add(cx_label);
		
		JLabel lblBx = new JLabel("BX:");
		lblBx.setBounds(974, 50, 34, 16);
		contentPane.add(lblBx);
		
		JLabel bx_label = new JLabel("");
		bx_label.setBounds(1001, 50, 61, 16);
		contentPane.add(bx_label);
		
		JLabel lblDx = new JLabel("DX:");
		lblDx.setBounds(1059, 49, 34, 16);
		contentPane.add(lblDx);
		
		JLabel dx_label = new JLabel("");
		dx_label.setBounds(1086, 49, 61, 16);
		contentPane.add(dx_label);
		
		JLabel lblNewLabel_1 = new JLabel("Registradores:");
		lblNewLabel_1.setBounds(941, 7, 117, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Memoria (hex):");
		lblNewLabel_2.setBounds(942, 111, 185, 16);
		contentPane.add(lblNewLabel_2);
		
		CmdAssembly cmdAssembly = new CmdAssembly();
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String portasAbertas = cmdAssembly.execute(table, textAssembly, ax_label, bx_label, cx_label, dx_label, instructions_label);
				btnExecutar.setEnabled(false);
				btnNextLine.setEnabled(true);
				btnParar.setEnabled(true);
				textAssembly.setEnabled(false);
				linhaSendoExecutada.setText("0");
				
			}
		});
		
		
		btnNextLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String linhaS = linhaSendoExecutada.getText();
				int linha = Integer.parseInt(linhaS);
				linha++;
				linhaS = String.valueOf(linha);
				linhaSendoExecutada.setText(linhaS);
				cmdAssembly.executeLine(linha);
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
		//Todo: Quanto nao tem mais linhas?
		
		
		
	}
}
