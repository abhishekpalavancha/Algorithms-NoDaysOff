package heap;

import java.util.*;

public class BinaryMinHeap<T> {

/*
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 */
	
	class Node{
		int weight;
	    T key;
	}
	
	List<Node> allNodes  = new ArrayList<>();
	Map<T,Integer> position = new HashMap<>();
	
	public void swap(Node parent,Node node) {
		int temp = parent.weight;
		parent.weight = node.weight;
		node.weight = temp;
	}
	
	public void updateMap(T parent,T curr,Integer parentVal,Integer currVal) {
		position.put(parent, parentVal);
		position.put(curr,currVal);
	}
	public void add(Integer weight,T Key) {
		Node node = new Node();
		node.weight = weight;
		node.key = Key;
		allNodes.add(node);
		int curr = allNodes.size() - 1;
		position.put(Key, curr);
		int parent = (curr-1) / 2;
		
		while(parent >=0) {
			Node parentNode = allNodes.get(parent);
			Node currNode = allNodes.get(curr);
			if(parentNode.weight > currNode.weight) {
				swap(parentNode,currNode);
				updateMap(parentNode.key,currNode.key,currNode.weight,parentNode.weight);
				curr = parent;
                parent = (parent-1)/2;
			}else {
				break;
			}
		}
		
	}
	
	
	public void decrease(T key,Integer newPos) {
		Integer currPos = position.get(newPos);
		Integer parent = (currPos - 1)/ 2;
		
		while(parent >= 0) {
			Node curr = allNodes.get(currPos);
			Node parentNode = allNodes.get(parent);
			if(curr.weight > parentNode.weight) {
				swap(parentNode,curr);
				updateMap(parentNode.key, curr.key, curr.weight, parentNode.weight);
			    currPos = parent;
                parent = (parent-1)/2;
			}
			else {
				break;
			}
		}
	}
   
	
    
    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.weight + " " + n.key);
        }
    }
    
	public static void main(String[] args) {
		BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Abhishek");
        heap.add(4, "Ani");
        heap.add(8, "winner");
        heap.add(10, "gucci");
        heap.add(5, "gang");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("kurr", 1);
        heap.printHeap();
	}
	
	

}
