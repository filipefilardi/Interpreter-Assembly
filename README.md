# Interpreter-Assembly

Programar, em Java, um simulador de processador com 4 registradores de 32 bits (AX, BX, CX e DX) que seja capaz de interpretar os seguintes comandos em assembly:

MOV de registrador para registrador MOV de endereço de memória (fixo ou guardado em um registrador) para registrador e vice-verça MOV de endereço de endereço na memória para registrador e vice-versa (ciclo de indireção) ADD, SUB, MUL, DIV, INC, DEC CMP JMP, JZ, JNZ, JL, JG, JLE, JGE (usar as flags de zero e sinal, e mostrá-las) Ao interpretar cada instrução (armazenada em uma memória simulada com endereço de 32 bits), o simulador deve mostrar passo-a-passo:

- cada linha do micro-programa horizontal usado para o controle;
- cada sinal de controle enviado para fluxo de dados e controle da ULA e memória;
- os pulsos de clock e em qual fase (T1, T2, T3...) o processamento se encontra;
- as flags zero e de sinal;
- se o ciclo de instrução está no ciclo de busca, indireção ou execucção (não é preciso implementar nada relacionado a interrupções nesse EP).

Os sinais de controle devem ser indicados em um esquema do processador (o ideal seria que esse esquema aparecesse na interface do EP, mas não é necessário implementar uma interface gráfica - se optarem por uma interface de texto devem entregar esse esquema em um relatório junto com o EP). Definir a arquitetura, os opcodes, os códigos de operação para a ULA, os sinais de controle para a memória e o que cada bit das linhas do microprograma horizontal significa faz parte do EP e também deve ser indicado em relatório ou na interface do programa.
