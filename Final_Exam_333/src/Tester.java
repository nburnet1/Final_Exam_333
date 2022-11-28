public class Tester {
	
	
	
	public static void main(String[] args) {
		Graph graph = JSONHelper.createGraphFromJSON();
		Visualizer vis = new Visualizer(graph);
		
		System.out.println("Testing Code Here...");
		
		// Display Graph to the screen 

		System.out.println("Nodes:");
		graph.printNodes();
		System.out.println("Edges (NOT Rush Hour):");
		graph.printEdges(false);
		System.out.println("Edges (Rush Hour):");
		graph.printEdges(true);
		
		
		System.out.println("Outputting Graph to JavaScript file (open dijkstra.html in your web browser");
		vis.save("output.js");
		
	}

}
