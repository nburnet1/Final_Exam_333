import java.util.Scanner;

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


		Scanner key = new Scanner(System.in);

		boolean contin = true;
		String inpt;
		String origin;
		String destination;
		boolean isRushHour;

		while(contin){
			System.out.println("Please enter the Node Source letter: ");
			origin = key.nextLine();
			System.out.println("Please enter the Node Destination letter: ");
			destination = key.nextLine();
			System.out.print("Is it rush hour? (y/N): ");
			inpt = key.nextLine();
			if(inpt.toLowerCase().startsWith("y"))
				isRushHour = true;
			else
				isRushHour = false;

			System.out.println("Calculating...");



			System.out.println("Would you like to continue (Y/n): ");
			inpt = key.nextLine();
			if(inpt.toLowerCase().startsWith("n"))
				contin = false;




		}
		
	}

}
