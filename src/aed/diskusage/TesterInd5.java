package aed.diskusage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

import net.datastructures.Tree;
import net.datastructures.TreePosition;


/**
 * @author Guillermo Roman
 *
 */

public class TesterInd5 {

	public static void main(String[] args) {

		try {
			String[] ids = ManagementFactory.getRuntimeMXBean().getName()
					.split("@");
			BufferedWriter bw = new BufferedWriter(new FileWriter("pid"));
			bw.write(ids[0]);
			bw.close();
		} catch (Exception e) {
			System.out.println("Avisa al profesor de fallo sacando el PID");
		}

		test1();
		test2();
		test3();
		test4();
		test5();

		System.out.println("\n TesterInd5: Test finalizado correctamente.");
	}

	public static void test1 () {
		System.out.println("******* Empezando Test1 con arbol:\n");

		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		TreePosition<FileNode> n2 = tree.addChild(tree.root(), new FileNode("dir1/",0));
		TreePosition<FileNode> n3 = tree.addChild(tree.root(), new FileNode("dir2/",0));

		tree.addChild(n2, new FileNode("f1",10));
		tree.addChild(n2, new FileNode("f2",20));
		tree.addChild(n2, new FileNode("f3",29));

		tree.addChild(n3, new FileNode("f4",10));
		TreePosition<FileNode> n8 = tree.addChild(n3, new FileNode("dir3/",0));

		tree.addChild(n8, new FileNode("f5",20));

		System.out.println(tree);

		Printer.init(getResultTest1());
		doCheck(tree);
		
		
		System.out.println(" - Test 1 finalizado correctamente\n\n\n");
	}
	
	public static void test2 () {
		System.out.println("******* Empezando Test2 con árbol:\n");

		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		tree.addChild(tree.root(), new FileNode("dir1/",0));
		tree.addChild(tree.root(), new FileNode("dir2/",0));
		tree.addChild(tree.root(), new FileNode("dir3/",0));
		System.out.println(tree);
		Printer.init(getResultTest2());
		doCheck(tree);
		System.out.println(" - Test 2 finalizado correctamente\n\n\n");
	}
	
	public static void test3 () {
		System.out.println("******* Empezando Test3 con árbol:\n ");
		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		tree.addChild(tree.root(), new FileNode("f1",10));
		tree.addChild(tree.root(), new FileNode("f2",20));
		tree.addChild(tree.root(), new FileNode("f3",29));

		TreePosition<FileNode> dir3 = tree.addChild(tree.root(), new FileNode("dir3/",0));

		tree.addChild(dir3, new FileNode("f5",20));

		System.out.println(tree);
		Printer.init(getResultTest3());
		doCheck(tree);
		System.out.println(" - Test 3 finalizado correctamente\n\n\n");
	}

	public static void test4 () {
		System.out.println("******* Empezando Test4 con árbol:\n ");
		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		TreePosition<FileNode> n2 = tree.addChild(tree.root(), new FileNode("dir1/",0));
		tree.addChild(n2, new FileNode("f1",100));
		TreePosition<FileNode> n3 = tree.addChild(n2, new FileNode("dir2/",0));
		tree.addChild(n3, new FileNode("f2",100));
		TreePosition<FileNode> n4 = tree.addChild(n3, new FileNode("dir3/",0));
		tree.addChild(n4, new FileNode("f3",100));
		TreePosition<FileNode> n5 = tree.addChild(n4, new FileNode("dir4/",0));
		tree.addChild(n5, new FileNode("f4",100));
		TreePosition<FileNode> n6 = tree.addChild(n5, new FileNode("dir5/",0));
		tree.addChild(n6, new FileNode("f5",100));
		
		System.out.println(tree);
		Printer.init(getResultTest4());
		doCheck(tree);
		System.out.println(" - Test 4 finalizado correctamente\n\n\n");
	}

	public static void test5 () {
		System.out.println("******* Empezando Test5 con árbol:\n");
		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		TreePosition<FileNode> n2 = tree.addChild(tree.root(), new FileNode("dir1/",0));
		tree.addChild(n2, new FileNode("f1",100));
		
		TreePosition<FileNode> n3 = tree.addChild(n2, new FileNode("dir2/",0));
		tree.addChild(n3, new FileNode("f2",100));
		
		tree.addChild(tree.root(), new FileNode("f3",100));
		
		System.out.println(tree);
		Printer.init(getResultTest5());
		doCheck(tree);
		System.out.println(" - Test 5 finalizado correctamente\n\n\n");
	}

	

	/**
	 * Método que prueba el resultado obtenido con el resultado esperado
	 * @param tree Árbol de entrada
	 * @param expected Resultado esperado
	 * @param exception ¿debe lanzar una excepción?
	 */
	static void doCheck(Tree<FileNode> tree) {

		try {
			int size = tree.size(); 
			DiskUsage.printDiskUsage(tree);

			if (tree.size() != size) {
				throw new Error("Se ha cambiado el tamaño del árbol de entrada!!!");		
			}

			Printer.checkResult();
		} catch (Exception exc) {
			System.out.println("ERROR: Con el árbol");
			System.out.println(tree);
			System.out.println("\n*** TesterInd5 Error: devuelve la siguiente excepcion \n");
			
			exc.printStackTrace();
			throw new Error("Error en test: resultado incorrecto");		
		}
	}

	public static String getResultTest1() {
		StringBuffer buffer = new StringBuffer ();
		buffer.append("10 /home/dir1/f1\n");
		buffer.append("20 /home/dir1/f2\n");
		buffer.append("29 /home/dir1/f3\n");
		buffer.append("59 /home/dir1/\n"); 
		buffer.append("10 /home/dir2/f4\n");
		buffer.append("20 /home/dir2/dir3/f5\n");
		buffer.append("20 /home/dir2/dir3/\n");
		buffer.append("30 /home/dir2/\n");
		buffer.append("89 /home/\n");
		return buffer.toString(); 
	}

	public static String getResultTest2() {
		StringBuffer buffer = new StringBuffer ();
		buffer.append("0 /home/dir1/\n");
		buffer.append("0 /home/dir2/\n");
		buffer.append("0 /home/dir3/\n");
		buffer.append("0 /home/\n");
		return buffer.toString(); 
	}
	
	public static String getResultTest3() {
		StringBuffer buffer = new StringBuffer ();
		buffer.append("10 /home/f1\n");
		buffer.append("20 /home/f2\n");
		buffer.append("29 /home/f3\n");
		buffer.append("20 /home/dir3/f5\n");
		buffer.append("20 /home/dir3/\n");
		buffer.append("79 /home/\n");
		return buffer.toString(); 
	}

	public static String getResultTest4() {
		StringBuffer buffer = new StringBuffer ();
		buffer.append("100 /home/dir1/f1\n");
		buffer.append("100 /home/dir1/dir2/f2\n");
		buffer.append("100 /home/dir1/dir2/dir3/f3\n");
		buffer.append("100 /home/dir1/dir2/dir3/dir4/f4\n");
		buffer.append("100 /home/dir1/dir2/dir3/dir4/dir5/f5\n");
		buffer.append("100 /home/dir1/dir2/dir3/dir4/dir5/\n");
		buffer.append("200 /home/dir1/dir2/dir3/dir4/\n");
		buffer.append("300 /home/dir1/dir2/dir3/\n");
		buffer.append("400 /home/dir1/dir2/\n");
		buffer.append("500 /home/dir1/\n");
		buffer.append("500 /home/\n");
		return buffer.toString(); 
	}
	
	public static String getResultTest5() {
		StringBuffer buffer = new StringBuffer ();
		buffer.append("100 /home/dir1/f1\n");
		buffer.append("100 /home/dir1/dir2/f2\n");
		buffer.append("100 /home/dir1/dir2/\n");
		buffer.append("200 /home/dir1/\n");
		buffer.append("100 /home/f3\n");
		buffer.append("300 /home/\n");
		return buffer.toString(); 
	}
	
	 
	

}
