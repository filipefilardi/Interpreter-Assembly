
public class CmdAssembly {
	
	public static void main(String[] args){

		//[Entrada]: não sei como sera o input
		String cmd = "ADD 10, 20";
		
		//Declaracao das strings
		String command ="";
		String ax = "";
		String bx = "";

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
		
		switch(command){
			case "ADD":
				ax+=bx;
				break;
			case "SUB":
				break;
			case "MUL":
				break;
			case "DIV":
				break;
			case "INC":
				break;
			case "DEC":
				break;
			case "CMP":
				break;
			case "JMP":
				break;
			case "JZ":
				break;
			case "JNZ":
				break;
			case "JL":
				break;
			case "JG":
				break;
			case "JLE":
				break;
			case "JGE":
				break;
			case "MOV":
				break;
			default:
				throw new IllegalArgumentException("Invalid command: " + command);
		}
	
	}	
}
