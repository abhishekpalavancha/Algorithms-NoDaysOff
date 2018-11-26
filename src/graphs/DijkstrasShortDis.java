package graphs;

import java.util.*;

import graphs.primsAlgorithm.Edge;
import heap.BinaryHeap;
import heap.BinaryHeap.Node;

public class DijkstrasShortDis {

	public static class Edge{
		int from;
		int to;
		int weight;
		Edge(int from,int to,int weight){
			this.from = from;
			this.to = to;
		    this.weight = weight;
		}
	}
	int noOfNodes = 10;
	public static List<Edge> shortestPath(int start,int end,Map<Integer,List<Edge>> graph){
		BinaryHeap heap = new BinaryHeap();
		heap.add(0, start);
		boolean[] visited = new boolean[10];
		int[] dist = new int[10];
		for(int i = 0;i<dist.length;i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[0] = 0;
		while(!heap.isEmpty()) {
			
			Node currNode = heap.extractMin();
			int id = (int) currNode.getKey();
			visited[id] = true;
			if(currNode.getWeight() > dist[id]) continue;
			for(Edge edge : graph.get(id)) {
				if(visited[edge.to]) continue;
				int newDist = dist[id] + edge.weight;
				if(newDist < dist[edge.to]) {
					dist[edge.to] = newDist;
					if(heap.containsKey(edge.to)) {
						heap.decrese(edge.to, newDist);
					}else {
						heap.add(newDist, edge.to);
					}
				}
			}
		}
		return null;
	}
	
	
	public static void main(String args[]) {
		
		int noOfNodes = 10;
		int[][] edges = {
			      
			      {0, 1, 1},
			      {0, 3, 4},
			      {0, 4, 5},

			      {1, 3, 2},
			      {1, 2, 1},

			      {2, 3, 5},
			      {2, 5, 7},

			      {3, 4, 2},
			      {3, 6, 2},
			      {3, 5, 11},

			      {4, 7, 4},

			      {5, 6, 1},
			      {5, 8, 4},

			      {6, 7, 4},
			      {6, 8, 6},

			      {7, 8, 1},
			      {7, 9, 2},

			      {8, 9, 0}

			    };

		final int NUM_NODES = 10;

	    // Setup graph as adjacency list
	    Map <Integer, List<Edge>> graph = new HashMap<>();
	    for (int i = 0; i < NUM_NODES; i++) graph.put(i, new ArrayList<>());
	    for ( int[] tuple : edges ) {
	      int from = tuple[0];
	      int to = tuple[1];
	      int cost = tuple[2];
	      Edge edge = new Edge(from, to, cost);
	      Edge revEdge = new Edge(to, from, cost);
	      graph.get(from).add(edge);
	      graph.get(to).add(revEdge);
	    }
		List<Edge> res = shortestPath(0,3,graph);
		
		for(int i = 0;i<res.size();i++) {
			System.out.println(res.get(i).from + "---->" +res.get(i).to);
		}
	}
}
