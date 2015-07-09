/*
EP2-OCD
Filipe Filardi de Jesus, 8516761
Gabriel Salgado Sina
Rodrigo Guerra, 8516497
*/


import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JLabel;
 
 
public class CmdAssembly {
	
	private String [] comandos;
	private JTable tabela;
	private JLabel AX_label, BX_label, CX_label, DX_label,Instructions_label;
	String AX, BX, CX, DX; // inicializacao dos contadores
	
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
		//Procura qual Ã© a linha que estÃ¡ o endereÃ§o -> getRowByValue(table, endereco)
		//Atualizar a coluna 1 = valor
		table.setValueAt(valor, getRowByValue(table, endereco), 1);
	}
	
	public int getMemoria(JTable table, String endereco){
	    return Integer.parseInt(table.getValueAt(getRowByValue(table, endereco), 1).toString() );
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
	
	public String addFunction(int v1, int v2){
		int aux = v1 + v2;
		return toHex(aux);
	}
	
	public String subFunction(int v1, int v2){
		int aux = v1 - v2;
		return toHex(aux);
	}
	
	
	public void executeLine(Integer i){
		
		//Verifica qual o comando e roda o cÃ³digo do comando.
		boolean registrador1 = false;
		boolean registrador2 = false;
		
		//Declaracao das strings
		String command ="";
		String x1 = "";
		String x2 = "";
		Integer  v1  = null;
		Integer  v2 = null;
		
		//Declaracao das flags [sinal e zero]
		Integer flagSinal = null; // 0 = positivo   &    1 = negativo
		Integer flagZero = null; //  0 != 0        &    1 = 0
 
		//Verifica se foi passado memoria como parametro no comando
		Boolean memoryX1 = false;
		Boolean memoryX2 = false;
 
		//Separacao do cmd em cmmand, x1 e x2
		int counter = 0;
		String comando = comandos[i].toUpperCase(); //Uppercase para funcionar tanto Add quanto ADD		
		for(char c : comando.toCharArray()){
			if (c == ' ') counter++;
			if (counter == 0){
				command += c;
			}
			if(counter == 1 && c!=' ' && c!= ','){
				if(c == '[' || c ==']') memoryX1 = true;
				else x1 += c;
			}
			if(counter == 2 && c!=' '){
				if(c == '[' || c ==']') memoryX2 = true;
				else{ 
					x2 += c;
				}
			}
		}
		
		if (x2.equals("")) x2= "0";
		
		//Transformando em decimal
		
		Integer V1Dec = null;
		Integer V2Dec = null;
		
		
		//Esse código serve apenas para descobrir se é registrador ou não. 
		//Nos sobrescreveremos os valores de V1Dec e V2Dec mais abaixo.
		try{
			V1Dec = toDec(x1);
		} catch (Exception e){
			registrador1 = true;
		}
		
		try{
			V2Dec = toDec(x2);
		} catch (Exception e){
			registrador2 = true;
		}
		
		
	
		//Calcula variavél v1, v2
//		-> Valores de x1 e x2 -> Se estiverem na memória ou não.
		
		if(registrador1){
			if (x1.equals("AX")){
				v1 = Integer.parseInt(AX);
			}
			if (x1.equals("BX")){
				v1 = Integer.parseInt(BX);
			}
			if (x1.equals("CX")){
				v1 = Integer.parseInt(CX);
			}
			if (x1.equals("DX")){
				v1 = Integer.parseInt(DX);
			}
			
			
		}else if (memoryX1){
			v1 = getMemoria(tabela,x1);
			v1 = toDec(Integer.toString(v1));
		}else{
			v1 =  toDec(x1);
		}
		
		if(registrador2){
			if (x2.equals("AX")){
				v2 = Integer.parseInt(AX);
			}
			if (x2.equals("BX")){
				v2 = Integer.parseInt(BX);
			}
			if (x2.equals("CX")){
				v2 = Integer.parseInt(CX);
			}
			if (x2.equals("DX")){
				v2 = Integer.parseInt(DX);
			}
			
		}else if (memoryX2){
			v2 = getMemoria(tabela, x2);
			v2 = toDec(Integer.toString(v2));
		}else{
			v2 =  toDec(x2);
		}
		
		
		
		//TODO apagar os prints
		System.out.println("[DEBUG] Cmd:" + command);
		System.out.println("[DEBUG] X1:" + x1);
		System.out.println("[DEBUG] X2:" + x2);
		
		System.out.println("[DEBUG] V1:" + v1);
		System.out.println("[DEBUG] v2:" + v2);
		
		System.out.println("[DEBUG] AX:" + AX);
		System.out.println("[DEBUG] BX:" + BX);
		
		if(command.equalsIgnoreCase("") || x1.equalsIgnoreCase("") || x2.equalsIgnoreCase("")) throw new IllegalArgumentException("Parametros nao podem ser nulos");
 
	
		String resultado;
		
		switch(command){
			case "ADD":
				
				resultado = addFunction(v1,v2);
				
				if(memoryX1){
					updateMemoria(tabela, resultado, x1);
				}else if(registrador1){
					if (x1.equals("AX")){
						AX = resultado;
					}
					if (x1.equals("BX")){
						BX = resultado;
					}
					if (x1.equals("CX")){
						CX = resultado;
					}
					if (x1.equals("DX")){
						DX = resultado;
					}
				}
				break;
			case "SUB":
				resultado = subFunction(v1,v2);
				
				if(memoryX1){
					updateMemoria(tabela, resultado, x1);
				}else if(registrador1){
					if (x1.equals("AX")){
						AX = resultado;
					}
					if (x1.equals("BX")){
						BX = resultado;
					}
					if (x1.equals("CX")){
						CX = resultado;
					}
					if (x1.equals("DX")){
						DX = resultado;
					}
				}
				break;
			case "MUL":
				
				int auxMul = toDec(AX);
				AX = toHex(auxMul *= v1);	
				break;
			case "DIV":
				if (v1 == 0) throw new IllegalArgumentException("Nao pode divir por zero");
				else {
					AX = Integer.toString(toDec(DX));
					int auxDiv = Integer.parseInt(AX);
					AX = toHex(auxDiv/=v1);
					DX = toHex(auxDiv % v1);
				}
				System.out.println(toHex(v1));
				break;
			case "INC":
				v1++;
				resultado = Integer.toString(v1);
						
				if(memoryX1){
					updateMemoria(tabela, resultado, x1);
				}else if(registrador1){
					if (x1.equals("AX")){
						AX = resultado;
					}
					if (x1.equals("BX")){
						BX = resultado;
					}
					if (x1.equals("CX")){
						CX = resultado;
					}
					if (x1.equals("DX")){
						DX = resultado;
					}
				}
				
				break;
			case "DEC":
				v1--;
				resultado = Integer.toString(v1);
						
				if(memoryX1){
					updateMemoria(tabela, resultado, x1);
				}else if(registrador1){
					if (x1.equals("AX")){
						AX = resultado;
					}
					if (x1.equals("BX")){
						BX = resultado;
					}
					if (x1.equals("CX")){
						CX = resultado;
					}
					if (x1.equals("DX")){
						DX = resultado;
					}
				}
				break;
			case "CMP":
				int result = v1 - v2;
				
				//Determinacao das flags
				if(result == 0) flagZero = 1;
				else flagZero = 0;
				
				if(result < 0) flagSinal = 1;
				if(result > 0) flagSinal = 0; // TODO Se for igual a zero, flagSinal = ?
				break;
			case "JMP":
				executeLine(v1);
				//Subustituir no Label linhaSendoExecutada
				AssemblyScreen as = new AssemblyScreen ();
				as.atualizaLinha(v1);
				break;
			case "JZ":
				if(flagZero == null) throw new IllegalArgumentException("CMP NAO FOI EXECUTADO"); //TODO Eh necessario? se sim, adicionar nos outros jumps
				if(flagZero == 1) executeLine(v1);
				break;
			case "JNZ":
				if(flagZero == 0) executeLine(v1);
				break;
			case "JL":
				if(flagSinal == 1) executeLine(v1); 
				break;
			case "JG":
				if(flagSinal == 0) executeLine(v1); 
				break;
			case "JLE":
				if((flagSinal == 1) || (flagSinal == 1 && flagZero == 1)) executeLine(v1);
				break;
			case "JGE":
				if((flagSinal == 0) || (flagSinal == 0 && flagZero == 1)) executeLine(v1);
				break;
			case "MOV":
			V1Dec = V2Dec;
				break;
			default:
				throw new IllegalArgumentException("Comando Invalido: " + command + " [>] tente CMD ax, bx");
		}
		
		 updateRegistradores();
		//Retorna as portas que ficarao abertas e As memorias que mudarao
//		updateRegistradores();
		
	}
	
	public String execute(JTable table, JTextPane txtAssembly, JLabel ax_label, JLabel bx_label,JLabel cx_label, JLabel dx_label, JLabel instructions_label){
		AX = "0"; BX = "0"; CX = "0"; DX = "0";
		
		//Limpar comandos
		
		//Cria a lista ligada ou matriz com o cÃ³digo.
		String codigo = txtAssembly.getText();
		comandos = codigo.split("\\r?\\n");
		tabela = table;
		//updateBus(openBars, abre );
		
		//Verifica qual o comando e roda o cÃ³digo do comando.
		//Retorna as portas que ficarÃ£o abertas e 
		//Muda as memÃ³rias que mudarÃ£o
	
		AX_label = ax_label; 
		BX_label = bx_label;
		CX_label = cx_label;
		DX_label = dx_label;
		Instructions_label = instructions_label;
		
		 updateRegistradores();
		
		 
		//Rodar comando da primeira linha.
			executeLine(0);
		return null;
	}
	
	public void updateRegistradores(){
		AX_label.setText(AX);
		BX_label.setText(BX);
		CX_label.setText(CX);
		DX_label.setText(DX);
	}
	
	public void updateInstructions(String text){
		Instructions_label.setText(text);
	}
	
	
	//String to Integer Decimal
	public static Integer toDec(String hex){
		return Integer.parseInt(hex, 16);
	}
	
	//Integer to String Hexadecimal
	public static String toHex(Integer dec){
		return Integer.toHexString(dec);
	}
	
}