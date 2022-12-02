import java.util.*;

public class Graph {
    public List<Node> nodes;
    public List<Edge> edges;
    public List<Edge> connectedEdges;
    private float pathWeight;



    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        connectedEdges = new LinkedList<>();
        pathWeight = 0;

    }

    private void initializeSingleSource(Node s) {
        for (Node node : nodes) {
            node.d = Float.POSITIVE_INFINITY;
            node.p = null;
        }
        s.d = 0;
    }

    private void relax(Node u, Node v) {
        if (u.d == Float.POSITIVE_INFINITY || u.equals(v)) {
            return;
        }
        float weight = u.getBackEdge(v).getWeight();

        if (v.d > (u.d + weight)) {
            v.d = u.d + weight;
            v.p = u;
            connectedEdges.add(u.getBackEdge(v));


        }
    }

    // TODO: Implement
    public void doDijkstra(Node source, boolean isRushHour) {
        //sets edge's rushHours bool
        for (Edge edge : edges) {
            edge.setIsRushHour(isRushHour);
        }

        List<Node> lst = new LinkedList<>(this.nodes);
        initializeSingleSource(source);
        Queue<Node> nodeQueue = new PriorityQueue<>();
        nodeQueue.addAll(nodes);
        while (!nodeQueue.isEmpty()) {
            Node u = nodeQueue.poll();
            for (Edge v : u.outboundEdges) {
                relax(u, v.target);
            }

            lst.remove(u);
            nodeQueue = new PriorityQueue<>(lst);
        }
    }

    // TODO: Implement
    public void printDirections(Node source, Node destination, boolean isRushHour) {
        doDijkstra(source, isRushHour);

        //iterates through edges that are viable based off of source
        for (Edge edge : connectedEdges) {
            //checks to see if sources match
            if (edge.source.equals(source)) {
                //creates aux data structure that keeps track of blocked edges making it non-destructive for recursion
                List<Edge> blockedEdges = new ArrayList<>();
                printDirectionsHelper(edge, destination, edge, connectedEdges, blockedEdges, 0, "");
                return;
            }
        }


    }

    private void printDirectionsHelper(Edge edge, Node destination, Edge origin, List<Edge> connectedEdgesTemp, List<Edge> blockedEdges, int trgIndex, String path) {

        //add weight to temp
        pathWeight += edge.getWeight();

        //checks to see if the path weight is under weight
        if (underWeight(pathWeight, destination)) {
            //finds the target index of the selected edge
            trgIndex = findTarget(edge.target, blockedEdges, connectedEdgesTemp);
            //checks to see if the temp weight equals the destination weight
            if (pathWeight == destination.d) {
                System.out.println("Path Found!");
                System.out.println("Calculated Weight: " + pathWeight + " (Actual Weight: " + destination.d+")");
                path += edge.source.name + " -> " + edge.target.name;
                System.out.println("PATH: " + path);
                pathWeight = 0;
                //checks to see if the target index is valid
            } else if (trgIndex != -1) {
                path += edge.source.name + " -> ";
                printDirectionsHelper(connectedEdgesTemp.get(trgIndex), destination, origin, connectedEdgesTemp, blockedEdges, trgIndex, path);


            } else {
                //adds edge to blockedEdges list
                blockedEdges.add(edge);
                pathWeight = 0;
                //checks to see if the recursion path has reached the end
                if (origin.equals(edge))
                    return;
                //runs the helper again removing path string
                printDirectionsHelper(origin, destination, origin, connectedEdgesTemp, blockedEdges, trgIndex, "");
            }
        } else {
            pathWeight = 0;
            //adds edge to blockedEdges list
            blockedEdges.add(edge);
            //checks to see if the recursion path has reached the end
            if (origin.equals(edge)) {
                return;
            }
            //runs the helper again removing path string
            printDirectionsHelper(origin, destination, origin, connectedEdgesTemp, blockedEdges, trgIndex, "");

        }


    }

    private boolean underWeight(float weight, Node destination) {
        return weight <= destination.d;
    }

    private int findTarget(Node target, List<Edge> blockEdges, List<Edge> connectedEdgesTemp) {
        for (int i = 0; i < connectedEdgesTemp.size(); i++) {
            if (connectedEdgesTemp.get(i).source.equals(target) && !blockEdges.contains(connectedEdgesTemp.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void printDashes(int numDashes) {
        for (int i = 0; i < numDashes; i++) {
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
        for (Node node : this.nodes) {
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
        for (Edge edge : this.edges) {
            edge.setIsRushHour(isRushHour);
            System.out.println(edge);
        }
        printDashes(80);
        System.out.println();
    }
}

