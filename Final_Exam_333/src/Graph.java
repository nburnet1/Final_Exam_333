import java.util.List;

public class Graph {
	public List<Node> nodes;
	public List<Edge> edges;
	
	public Graph(List<Node> nodes, List<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	// TODO: Implement
	public void doDijkstra(Node source, boolean isRushHour) {
		
	}
	private void initializeSingleSource (Graph g, Node s){
		for(Node node: g.nodes){
			node.d = Integer.MAX_VALUE;
			node.p = null;
		}
		s.d = 0;

	}
	private void relax(Node u, Node v, float weight){
		if(u.d == Integer.MAX_VALUE)
			return;
		if(v.d > u.d + weight){
			v.d = u.d + weight;
			v.p = u;
		}

	}
	
	
	// TODO: Implement
	public void printDirections(Node source, Node destination, boolean isRushHour) {
		
	}
	
	private void printDashes(int numDashes) {
		for(int i = 0; i < numDashes; i++) {
			System.out.print("-");
		}
		System.out.println();
		
	}
	// Implemented for you:
	public void printNodes() {
		System.out.println();
		printDashes(25);
		System.out.println("id\tname\tparent");
		printDashes(25);
		for (Node node: this.nodes) {
			System.out.println(node);
		}
		printDashes(25);
		System.out.println();
	}
	
	// Implemented for you:
	public void printEdges(boolean isRushHour) {
		System.out.println();
		printDashes(80);
		System.out.println("id\tname\tsource\ttarget\tweight\tlanes\tspeed\tcongestion\tlength");
		printDashes(80);
		for (Edge edge: this.edges) {
			edge.setIsRushHour(isRushHour);
			System.out.println(edge);
		}
		printDashes(80);
		System.out.println();
	}
}
