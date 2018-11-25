package graphs;

import java.util.*;

public class primsAlgorithm  {
	
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		
		Edge(int from,int to,int cost){
		  this.from = from;
		  this.to = to;
		  this.cost = cost;
		}

		@Override
		public int compareTo(Edge object) {
			return cost - object.cost;
		}
		
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
		List<Edge> res = prims(graph,noOfNodes);
		
		for(int i = 0;i<res.size();i++) {
			System.out.println(res.get(i).from + "---->" +res.get(i).to);
		}
	}


	private static List<Edge> prims(Map<Integer, List<Edge>> graph, int noOfNodes) {
		List<Edge> results = new ArrayList<>();

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[noOfNodes];
		List<Edge> firstNode = graph.get(0);
		int visitedSoFar = 1;
		visited[0] = true;
		for(Edge e : firstNode) {
			pq.add(e);
		}
		
		while(visitedSoFar != noOfNodes || !pq.isEmpty()) {
			Edge currEdge = pq.poll();
			if(!visited[currEdge.to]) {
				List<Edge> lisEdges = graph.get(currEdge.to);
				for(Edge ed : lisEdges) {
					  if (!visited[ed.to])
				            pq.offer(ed);
				}
			 visited[currEdge.to] = true;
			 results.add(currEdge);
			 visitedSoFar++;
			}
			
		}
		
		return results;
	}
}
