package aed.diskusage;

import java.util.Iterator;

import net.datastructures.InvalidPositionException;
import net.datastructures.LinkedTree;
import net.datastructures.NodePositionList;
import net.datastructures.NonEmptyTreeException;
import net.datastructures.Position;
import net.datastructures.Tree;
import net.datastructures.TreePosition;

public class AEDTree<E> extends LinkedTree<E> {
	
	public TreePosition<E> addChild (Position<E> node, E e) {
		TreePosition<E> pos = checkPosition(node);
		TreePosition<E> newNode = createNode(e,pos,new NodePositionList<Position<E>>()); 
		pos.getChildren().addLast(newNode);
		this.size++; 
		return newNode; 
	}

	public Position<E> addRoot(E e) throws NonEmptyTreeException {
		if(!isEmpty()) {
			throw new NonEmptyTreeException("Tree already has a root");
		}
		size = 1;
		root = createNode(e,null, new NodePositionList<Position<E>>());
		return root;
	}
	
	public Iterable<Position<E>> children(Position<E> v) 
			throws InvalidPositionException { 
		TreePosition<E> vv = checkPosition(v);
		return vv.getChildren();
	}
	

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer (); 
		
		toString(this.root(),"",false,buffer);
		return buffer.toString();
	}
	public void toString(Position<E> c, 
								  String prefix, 
								  boolean isTail,
								  StringBuffer buffer) {
	    //		buffer.append(prefix + (isTail ? "└── " : "├── ") + c.element() + "\n");
		buffer.append(prefix + (isTail ? "|-- " : "|-- ") + c.element() + "\n");
		Iterator<Position<E>> it = this.children(c).iterator();
		Position<E> child = null; 
        while(it.hasNext()) {
        	child = it.next(); 
        	if (it.hasNext()) // Si no es el último hijo
		    //        		this.toString(child,prefix + (isTail ? "    " : "│   "), false, buffer);
        		this.toString(child,prefix + (isTail ? "    " : "|   "), false, buffer);
        	
        }
        if (!isExternal(c)) {
	    //            this.toString(child,prefix + (isTail ?"    " : "│   "), true, buffer);
            this.toString(child,prefix + (isTail ?"    " : "|   "), true, buffer);
        }
    
	}

}
