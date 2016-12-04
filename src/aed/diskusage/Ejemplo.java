package aed.diskusage;

import net.datastructures.TreePosition;

public class Ejemplo {
	public static void main(String[] args) {
		AEDTree<FileNode> tree = new AEDTree<FileNode>() ;
		tree.addRoot(new FileNode("/home/",0));

		TreePosition<FileNode> n2 = tree.addChild(tree.root(), new FileNode("dir1/",0));
		TreePosition<FileNode> n3 = tree.addChild(tree.root(), new FileNode("dir2/",0));

		TreePosition<FileNode> n4 = tree.addChild(n2, new FileNode("f1",10));
		TreePosition<FileNode> n5 = tree.addChild(n2, new FileNode("f2",20));
		TreePosition<FileNode> n6 = tree.addChild(n2, new FileNode("f3",29));

		TreePosition<FileNode> n7 = tree.addChild(n3, new FileNode("f4",10));
		TreePosition<FileNode> n8 = tree.addChild(n3, new FileNode("dir3/",0));

		TreePosition<FileNode> n9 = tree.addChild(n8, new FileNode("f5",20));
		
		System.out.println(tree);
		
		DiskUsage.printDiskUsage(tree);
		
	}
}
