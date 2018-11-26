package heap;
import java.util.*;

public class BinaryHeap<T> {
	
	/*
	 * extracMin - O(logn)
	 * addToHeap - O(logn)
	 * containsKey - O(1)
	 * decreaseKey - O(logn)
	 * getKeyWeight - O(1)
	 */
	
	public static class Node<T>{
		int weight;
		T key;
		public T getKey(){
			return key;
		}
		
		public int getWeight(){
			return weight;
		}
	}

	Map<T,Integer> nodePosition  = new HashMap<>();
	List<Node> allNodes  = new ArrayList<>();
	
	
	public void swap(Node parent,Node currNode) {
		T tempKey = (T) parent.key;
		Integer tempWeight = parent.weight;
		parent.weight = currNode.weight;
		parent.key = currNode.key;
		currNode.key = tempKey;
		currNode.weight = tempWeight;
	}
	
	public boolean isEmpty() {
		return allNodes.size() < 1;
	}
	
	
	public void add(int weight, T key) {
		Node node = new Node();
		node.key = key;
		node.weight = weight;
		allNodes.add(node);
	    Integer curr = allNodes.size() -1 ;
	    nodePosition.put(key , curr);
	    int parent = (curr - 1) / 2;
	    while(parent >= 0) {
	    	Node parentNode = allNodes.get(parent);
	    	Node currNode = allNodes.get(curr);
	    	if(currNode.weight < parentNode.weight) {
	    		swap(parentNode,currNode);
	    		updateMapPosition(currNode,parentNode,nodePosition.get(parentNode.key),nodePosition.get(currNode.key));
	    		curr = parent;
	    		parent = (parent - 1) / 2;
	    	}else {
	    		break;
	    	}
	    }
	}
	
	private void updateMapPosition(Node currNode, Node parentNode, Integer parent, Integer curr) {
		nodePosition.put((T) currNode.key,parent);
		nodePosition.put((T) parentNode.key,curr);
	}
	
	public void decrese(T key,Integer weight) {
		Integer nodeIndex = nodePosition.get(key);
		Node node = allNodes.get(nodeIndex);
		node.weight = weight;
		int parentIndex = (nodeIndex -1) /2 ;
	    while(parentIndex >= 0) {
	    	Node parentNode = allNodes.get(parentIndex);
	    	Node currNode = allNodes.get(nodeIndex);
	    	if(parentNode.weight > currNode.weight) {
	    		swap(parentNode,currNode);
	    		updateMapPosition(currNode,parentNode,nodePosition.get(parentNode.key),nodePosition.get(currNode.key));
	    		nodeIndex = parentIndex;
	    		parentIndex = (parentIndex - 1) / 2;
	    	}else {
	    		break;
	    	}
	    }
	}
	
	public Node extractMin() {
      int size = allNodes.size() - 1;
      Node minNode = new Node();
      minNode.key = allNodes.get(0).key;
      minNode.weight = allNodes.get(0).weight;
      Node lastNode = allNodes.get(size);
      allNodes.get(0).key = lastNode.key;
      allNodes.get(0).weight = lastNode.weight;
      nodePosition.put((T) allNodes.get(0).key, 0);
      allNodes.remove(size);
      size--;
      int currentIndex = 0;
      while(true) {
    	  int left = 2*currentIndex + 1;
          int right = 2*currentIndex + 2;
          int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
          if(allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight){
              swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
              updateMapPosition(allNodes.get(currentIndex),allNodes.get(smallerIndex),currentIndex,smallerIndex);
              currentIndex = smallerIndex;
          }else{
              break;
          }
      }
      return minNode;
	}
	
	public boolean containsKey(T key) {
		return nodePosition.containsKey(key);
	}

	public static void main(String args[]) {
		BinaryHeap heap = new BinaryHeap();
	    heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrese("Pramila", 1);
        heap.extractMin();
	}
}
