import java.util.Scanner;

public class Tester {


    public static void main(String[] args) {
        Graph graph = JSONHelper.createGraphFromJSON();
        Visualizer vis = new Visualizer(graph);

//        System.out.println("Testing Code Here...");

        // Display Graph to the screen

        System.out.println("Nodes:");
        graph.printNodes();
//		System.out.println("Edges (NOT Rush Hour):");
//		graph.printEdges(false);
//
//		System.out.println("Edges (Rush Hour):");
//		graph.printEdges(true);


        System.out.println("Please open dijkstra.html in your web browser");
        System.out.println("After every calculation, you may refresh your page to see the source graph updated.");

        Scanner key = new Scanner(System.in);

        boolean contin = true;
        String inpt;
        String origin;
        String destination;
        boolean isRushHour = false;

        boolean inputCheck = false;
        boolean originCheck = false;
        boolean destinationCheck = false;
        Node source = null;
        Node target = null;
        while (contin) {
            inputCheck = false;
            while (!inputCheck) {
                System.out.println("Please enter the Node Source letter: ");
                origin = key.next().trim().toUpperCase();
                System.out.println("Please enter the Node Destination letter: ");
                destination = key.next().trim().toUpperCase();

                for (Edge edge : graph.edges) {
                    if (origin.equals(edge.source.name)) {
                        originCheck = true;
                        source = edge.source;
                    }

                    if (destination.equals(edge.target.name)) {
                        destinationCheck = true;
                        target = edge.target;
                    }
                    if (originCheck && destinationCheck) inputCheck = true;

                }
                if (!inputCheck) System.out.println("Could not find Source/Target Node, Please try again...");


            }

            System.out.print("Is it rush hour? (y/N): ");
            inpt = key.next();
            if (inpt.toLowerCase().startsWith("y")) isRushHour = true;
            else isRushHour = false;

            System.out.println("Calculating...");

            graph.printDashes(20);
            graph.printDirections(source, target, isRushHour);
            graph.printDashes(20);

            vis.save("output.js");

            System.out.println("Would you like to continue (Y/n): ");
            inpt = key.next();
            if (inpt.toLowerCase().startsWith("n")) contin = false;

            originCheck = false;
            destinationCheck = false;

        }
    }

}
