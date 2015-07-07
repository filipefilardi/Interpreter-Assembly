import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.Panel;
import java.awt.Color;

import javax.swing.JTable;

import java.awt.Canvas;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AssemblyScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		textAssembly.setBounds(33, 6, 115, 658);
		contentPane.add(textAssembly);
		
		String[] columnNames = {"Memória", "Dados"};
		
		Object[][] data = {{"0x12341234", ""},{"0x87654321", ""},{"0x76543210", ""}};

	//	Object[][] data = new Object[40][40];
		
		table = new JTable(data, columnNames);
		table.setBounds(728, 128, 347, 109);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/guerra/Dropbox/EACH/OCD/EP2/Interpreter-Assembly/CPU.png"));
		lblNewLabel.setBounds(174, 128, 520, 520);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblPortasAbertas = new JLabel("Portas Abertas:");
		lblPortasAbertas.setBounds(174, 72, 177, 16);
		contentPane.add(lblPortasAbertas);
		
		JLabel openBars = new JLabel("1, 2, 3");
		openBars.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		openBars.setBounds(174, 88, 520, 29);
		contentPane.add(openBars);
		
		
		JButton btnExecutar = new JButton("Executar");
		JButton btnNextLine = new JButton("Próxima Linha");
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
		
		CmdAssembly cmdAssembly = new CmdAssembly();
		
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String portasAbertas = cmdAssembly.execute(table, textAssembly, openBars);
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
		//Todo: Quanto não tem mais linhas?
		
		
		
	}
}
