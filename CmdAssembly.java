
public class CmdAssembly {
	
	public void main(String[] args){
		String comando = "ADD";
		
		switch(comando){
			case "ADD":
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
				throw new IllegalArgumentException("Invalid day of the week: " + comando);
		}
	
	}	
}