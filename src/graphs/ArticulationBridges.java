package graphs;

import java.util.ArrayList;
import java.util.List;

/*
 * ArticulationBridges in undirectional graph 
 *  
 */
public class ArticulationBridges {
	
	public static class Edge{
		int from;
		int to;
		int cost;
		Edge(int from,int to,int cost){
			this.from = from;
			this.to= to;
			this.cost = cost;
		}
	}
	
	
	public static List<List<Edge>> createGraph(int noOfElements) {
		List<List<Edge>> graph = new ArrayList<>();
		for(int i = 0;i<noOfElements;i++) {
			graph.add(new ArrayList<>());
		}
		return graph;
	}
	
	public static void addEdge(List<List<Edge>> graph, int from,int to) {
		addToUndirectedGraph(graph,from,to);
	}
	
	public static void addToUndirectedGraph(List<List<Edge>> graph,int from,int to) {
		addDirectedEdge(graph,from,to,1);
		addDirectedEdge(graph,from,to,1);
	}
	
	 public static void addDirectedEdge(List<List<Edge>> graph, int u, int v, int cost) {
		    graph.get(u).add(new Edge(u, v, cost));
		  }
	
	public static void main(String[] args) {
		int noOfElements = 9;
		List<List<Edge>> graph = createGraph(noOfElements);
		
			addEdge(graph, 0, 1);
		    addEdge(graph, 0, 2);
		    addEdge(graph, 1, 2);
		    addEdge(graph, 2, 3);
		    addEdge(graph, 3, 4);
		    addEdge(graph, 2, 5);
		    addEdge(graph, 5, 6);
		    addEdge(graph, 6, 7);
		    addEdge(graph, 7, 8);
		    addEdge(graph, 8, 5);
	}
}
