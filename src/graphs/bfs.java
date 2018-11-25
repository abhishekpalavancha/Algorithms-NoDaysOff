package graphs;

import java.util.*;

public class bfs {
	
	public static class Edge{
		int from,to,cost;
		public Edge(int from,int to,int cost) 
		{
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	  private int n;
	  private Integer[] prev;
	  private List<List<Edge>> graph;

	  public bfs(List<List<Edge>> graph) {
	    if (graph == null) throw new IllegalArgumentException("Graph can not be null");
	    n = graph.size();
	    this.graph = graph;
	  }
	
	public List<Integer> reConstruct(int start,int end){
	    bfs(start);
	    List<Integer> path = new ArrayList<>();
	    for(Integer i = end;i != null; i= prev[i]) {
	    	path.add(i);
	    }
	    Collections.reverse(path);
	    if(path.get(0)== start) {
	    	path.clear();
	    }
		return path;
	}
	
	private void bfs(int node) {
		prev = new Integer[n];
        boolean[] visited = new boolean[n];
	    Deque<Integer> queue = new ArrayDeque<>(n);
        queue.push(node);
        visited[node] = true;
        while(!queue.isEmpty()) {
        	int curr = queue.pop();
        	List<Edge> lis = graph.get(curr);
        	
        	for(Edge e : lis) {
        	if(!visited[e.to]) {
        		visited[e.to] = true;
        		prev[e.to] = curr;
        		queue.offer(e.to);
        	}
        	}
        }
	}



	public static void addUnweightedUndirectedEdge(List<List<Edge>> graph,int from,int to) {
		graph.get(from).add(new Edge(from, to, 1));
	}
	
	public static List<List<Edge>> createGraph(int noOfEdges){
	   	List<List<Edge>> graph = new ArrayList<>();
	   	for(int i = 0;i<noOfEdges;i++) {
	   		graph.add(new ArrayList<Edge>());
	   	}
		return graph;
	}
 	
	public static void main(String args[]) {
		int noOfEdges = 13;
		//empty graph
		List<List<Edge>> graph = createGraph(noOfEdges);
		
		//create connections
		addUnweightedUndirectedEdge(graph, 0,  7);
	    addUnweightedUndirectedEdge(graph, 0,  9);
	    addUnweightedUndirectedEdge(graph, 0,  11);
	    addUnweightedUndirectedEdge(graph, 7,  11);
	    addUnweightedUndirectedEdge(graph, 7,  6);
	    addUnweightedUndirectedEdge(graph, 7,  3);
	    addUnweightedUndirectedEdge(graph, 6,  5);
	    addUnweightedUndirectedEdge(graph, 3,  4);
	    addUnweightedUndirectedEdge(graph, 2,  3);
	    addUnweightedUndirectedEdge(graph, 2,  12);
	    addUnweightedUndirectedEdge(graph, 12, 8);
	    addUnweightedUndirectedEdge(graph, 8,  1);
	    addUnweightedUndirectedEdge(graph, 1,  10);
	    addUnweightedUndirectedEdge(graph, 10, 9);
	    addUnweightedUndirectedEdge(graph, 9,  8);
	    
	    //find fastest path between two points 
	    int start = 10;
	    int end = 5;
	    bfs b = new bfs(graph);
	    List<Integer> path = b.reConstruct(start,end);
	    for(int i = 0;i<path.size();i++) {
	    	System.out.println(path.get(i)+ " ->");
	    }
		
	}

}
