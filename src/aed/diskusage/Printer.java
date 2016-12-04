package aed.diskusage;

import java.util.StringTokenizer;

public class Printer {
	private static int nTest = 0; 
	private static String expected; 
	private static StringBuffer buffer; 
	private static StringTokenizer tokenizer; 
	
	public static void init (String expected) {
		nTest++; 
		Printer.expected = expected; 
		buffer = new StringBuffer (); 
		tokenizer = new StringTokenizer(expected, "\n"); 
	}

	public static void print (String s) {
		System.out.println(s);
		if (!tokenizer.hasMoreTokens()) {
			throw new Error ("Estas imprimiendo de mas... te sobra '" + s + "'"); 
		}
			
		String next = tokenizer.nextToken(); 
		if (!next.equals(s)) {
			System.out.println();
			System.out.println("* ERROR en el test " + nTest + ": La linea impresa no coincide con la esperada");
			System.out.println("* Impresa:  '" + s + "'");
			System.out.println("* Esperada: '" + next + "'\n");
			System.out.println("* El texto impreso hasta el momento es ");
			System.out.println(buffer);
			System.out.println("\n* El resultado esperado es: ");
			System.out.println(expected);
			throw new Error ("La linea impresa no coincide con la esperada"); 
		}
		buffer.append(s + "\n"); 
	}
	
	public static void checkResult () {
		if (!expected.equals(buffer.toString())) {
			System.out.println("ERROR: El resultado final no es el esperado");
			System.out.println("** Resultado obtenido (imprimido usando Printer.print): **");
			System.out.println(buffer);
			System.out.println("** Resultado esperado: **");
			System.out.println(expected);
			throw new Error ("El resultado no es el esperado"); 
		}
		System.out.println();
	}
	
}
