
public class CmdAssembly {
	
	public static void main(String[] args){

		//[Entrada]: não sei como sera o input
		String cmd = "ADD 1F, 20";
		
		//Declaracao das strings
		String command ="";
		String ax = "";
		String bx = "";
		
		//Declaracao das flags [sinal e zero]
		Integer flagSinal = null; // 0 = positivo   &    1 = negativo
		Integer flagZero = null; //  0 != 0        &    1 = 0

		//Separacao  do cmd em cmmand, ax e bx
		int counter = 0;
		for(char c : cmd.toCharArray()){
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
		
		//Transformando as Strings em inteiros
		//int AX = Integer.parseInt(ax); 
		//int BX = Integer.parseInt(bx);
		
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
