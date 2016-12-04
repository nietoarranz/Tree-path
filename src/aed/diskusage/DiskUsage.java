package aed.diskusage;


import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.Tree;

public class DiskUsage {

	public static void printDiskUsage(Tree<FileNode> tree) {
		Position<FileNode> raiz = tree.root();
		auxRecur(tree,raiz);
	}
	//Metodo recursivo principal encargado de recorrer el arbol
	private static void auxRecur (Tree<FileNode> tree, Position<FileNode> nodo){
		if(tree.isExternal(nodo)){
			Printer.print(nodo.element().getSize() + " " + path(tree,nodo));
		}else{
			for (Position<FileNode> w : tree.children(nodo)){
				auxRecur(tree,w);
			}
			Printer.print(tamanioDir(tree,nodo) + " " + path(tree,nodo));
		}
	}
	
	//Metodo auxiliar para obtener la ruta del archivoo carpeta
	private static String path (Tree<FileNode> tree, Position<FileNode> nodo){
		if(tree.isRoot(nodo)){
			return nodo.element().getName();
		}else{
			String aux = nodo.element().getName();
			nodo = tree.parent(nodo);
			return path(tree,nodo) + aux;
		}
	}
	//Metodo auxiliar para calcular el tamanio de una carpeta a partir de los archivos hijos del arbol
	private static int tamanioDir (Tree<FileNode> tree, Position<FileNode> nodo){
		if(tree.isExternal(nodo)){
			return nodo.element().getSize();
		}else{
			int aux = 0;
			tree.children(nodo).iterator();
			for (Position<FileNode> w : tree.children(nodo)){
				aux = aux + tamanioDir(tree,w);
			}
			return aux;
		}
	}
	
}
