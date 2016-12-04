package aed.diskusage;

/**
 * Implements a generic pair of objects.
 */
public class FileNode {

	private String name;
	private Integer size;

	public FileNode(String name, Integer size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}


	public Integer getSize() {
		return size;
	}


	public String toString() {
		return "FileNode("+name+","+size+")";
	}
}
