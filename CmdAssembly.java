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
import java.util.LinkedList;


public class CmdAssembly {
	
	private String [] comandos;
	
	public int getRowByValue(JTable model, String value) {
		//http://stackoverflow.com/questions/14196974/getting-a-row-index-in-a-jtable-using-only-a-value-included-in-the-row
	    for (int i = model.getRowCount() - 1; i >= 0; --i) {
	        for (int j = model.getColumnCount() - 1; j >= 0; --j) {
	            if (model.getValueAt(i, j).equals(value)) {
	                return i;
	            }
	        }
	    }
	    return -1;
	}	
 
	
	public void updateMemoria(JTable table, String valor, String endereco){
		//Procura qual é a linha que está o endereço -> getRowByValue(table, endereco)
		//Atualizar a coluna 1 = valor
		table.setValueAt(valor, getRowByValue(table, endereco), 1);
	}
	
	public void updateBus(JLabel openBars, Integer[] portas){
		String abertas = "";
		for(int i=0;i<=portas.length-1;i++){
			abertas += portas[i].toString();
			if (i<portas.length-1) abertas += ", ";
		}
		abertas += ".";
		
		openBars.setText(abertas);
		
	}
	
	public void executeLine(Integer i){
		
		//Verifica qual o comando e roda o código do comando.
		
		//Declaracao das strings
		String command ="";
		String ax = "";
		String bx = "";
		
		//Declaracao das flags [sinal e zero]
		Integer flagSinal = null; // 0 = positivo   &    1 = negativo
		Integer flagZero = null; //  0 != 0        &    1 = 0

		//Separacao  do cmd em cmmand, ax e bx
		int counter = 0;
		for(char c : comandos[i].toCharArray()){
			if (c == ' ') counter++;
			if (counter == 0){
				command += c;
			}
			if(counter == 1 && c!=' ' && c!= ','){
				ax += c;
			}
			if(counter == 2 && c!=' '){
				bx += c;
			}
		}
		
		//TODO apagar os prints
		System.out.println("[DEBUG] Cmd:" + command);
		System.out.println("[DEBUG] AX:" + ax);
		System.out.println("[DEBUG] BX:" + bx);
		
		if(command.equalsIgnoreCase("") || ax.equalsIgnoreCase("") || bx.equalsIgnoreCase("")) throw new IllegalArgumentException("Parametros nao podem ser nulos");
		
		//Transformando em decimal
		Integer AXDec = toDec(ax);
		Integer BXDec = toDec(bx);
		
		switch(command){
			case "ADD":
				AXDec += BXDec;
				System.out.println(toHex(AXDec));
				break;
			case "SUB":
				AXDec -= BXDec;
				System.out.println(toHex(AXDec));
				break;
			case "MUL":
				AXDec *= BXDec;
				System.out.println(toHex(AXDec));
				break;
			case "DIV":
				if (BXDec == 0) throw new IllegalArgumentException("Nao pode divir por zero");
				else AXDec /= BXDec;
				System.out.println(toHex(AXDec));
				break;
			case "INC":
				AXDec++;
				System.out.println(toHex(AXDec));
				break;
			case "DEC":
				AXDec--;
				System.out.println(toHex(AXDec));
				break;
			case "CMP":
				int result = AXDec - BXDec;
				
				//Determinacao das flags
				if(result == 0) flagZero = 1;
				else flagZero = 0;
				
				if(result < 0) flagSinal = 1;
				if(result > 0) flagSinal = 0; // TODO Se for igual a zero, flagSinal = ?
				break;
			case "JMP":
				jump(AXDec, BXDec);
				break;
			case "JZ":
				if(flagZero == null) throw new IllegalArgumentException("CMP NAO FOI EXECUTADO"); //TODO Eh necessario? se sim, adicionar nos outros jumps
				if(flagZero == 1) jump(AXDec, BXDec);
				break;
			case "JNZ":
				if(flagZero == 0) jump(AXDec, BXDec);
				break;
			case "JL":
				//TODO if(flagSinal == 1) jump(AX, BX); CONFIRMAR
				break;
			case "JG":
				//TODO if(flagSinal == 0) jump(AX, BX); CONFIRMAR
				break;
			case "JLE":
				//TODO if((flagSinal == 1) || (flagSinal == 1 && flagZero == 1)) jump(AX, BX); CONFIRMAR 
				break;
			case "JGE":
				//TODO if((flagSinal == 0) || (flagSinal == 0 && flagZero == 1)) jump(AX, BX); CONFIRMAR
				break;
			case "MOV":
				AXDec = BXDec;
				break;
			default:
				throw new IllegalArgumentException("Comando Invalido: " + command + " [>] tente CMD ax, bx");
		}
		
		//Retorna as portas que ficarão abertas e As memórias que mudarão
	
	}
	
	public String execute(JTable table, JTextPane txtAssembly, JLabel openBars){
		
		
		//Limpar comandos
		
		//Cria a lista ligada ou matriz com o código.
		String codigo = txtAssembly.getText();
		comandos = codigo.split("\\r?\\n");
		
		//updateBus(openBars, abre );
		
		
		//Rodar comando da primeira linha.
		executeLine(0);
			//Verifica qual o comando e roda o código do comando.
			//Retorna as portas que ficarão abertas e 
			//Muda as memórias que mudarão
	
		
		return null;
		
	
	}
	
	//String to Integer Decimal
	public static Integer toDec(String hex){
		return Integer.parseInt(hex, 16);
	}
	
	//Integer to String Hexadecimal
	public static String toHex(Integer dec){
		return Integer.toHexString(dec);
	}
	
	public static void jump (int AX, int BX){
		//TODO faz o jump
	}
}
