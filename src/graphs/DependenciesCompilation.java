package graphs;
import java.util.ArrayList;
import java.util.List;

public class DependenciesCompilation {
	public static class Node{
		String data;
		List<Node> children = new ArrayList<>();
		Node(String data,List<Node> children){
			this.data = data;
			this.children = children;
		}
		
		Node(String data){
			this.data = data;
		}
	}
	
	public static void main(String args[]) {
		Node root = new Node("p");
		Node nodeA = new Node("a");
		Node nodeB = new Node("b");
		Node nodeC = new Node("c");
		Node nodeD = new Node("d");
		Node nodeE = new Node("e");
		
		List<Node> childrenP = new ArrayList<>();
		childrenP.add(nodeA);
		childrenP.add(nodeB);
		childrenP.add(nodeC);
		root.children = childrenP;
		
		List<Node> childrenA = new ArrayList<>();
		childrenA.add(nodeD);
		nodeA.children = childrenA;
		
		List<Node> childrenB = new ArrayList<>();
		childrenB.add(nodeE);
		nodeB.children = childrenB;
		

		List<Node> childrenD = new ArrayList<>();
		childrenD.add(nodeE);
		nodeD.children = childrenD;
		
		List<Node> visited = new ArrayList<>();
		dfsUtil(root,visited);
		for(int i = 0;i<visited.size();i++) {
			System.out.println(visited.get(i).data);
		}	
	}
	
	public static void dfsUtil(Node root,List<Node> visited) {
		if(visited.contains(root)) {
			return;
		}
		
		
		List<Node> children = root.children;
		
		for(int i = 0;i<children.size();i++) {
			Node currNode = children.get(i);
			dfsUtil(currNode,visited);
		}
		visited.add(root);
	}
}
