import java.util.*;

public class SSSP {
    public boolean performSSSP(LinkedObjects graph) {
        ArrayList<Vertex> verticesList = graph.verticesList;
        ArrayList<Edge> edgesList = graph.edgesList;
 
        //Initializes the distance from the first vertex (single source) to itself as 0 and 
        //from the first vertex to all other vertices as infinite
        for (int i = 0; i < verticesList.size(); i++) {
            if (i == 0) {
                verticesList.get(i).distance = 0;
            } else {
                verticesList.get(i).distance = Double.POSITIVE_INFINITY;
            }
        }

        //For each vertex, take each edge and determine
        //if it would lead to a shorter path from the source
        for (int i = 1; i < verticesList.size(); i++) {
            for (int j = 0; j < edgesList.size(); j++) {
                Vertex src = edgesList.get(j).source;
                Vertex dst = edgesList.get(j).destination;
                int wt = edgesList.get(j).weight;
                
                if (src.distance != Double.POSITIVE_INFINITY && dst.distance > (src.distance + wt)) {
                    dst.distance = src.distance + wt;
                    dst.predecessor = src;
                }
            }
        }
 
        //Check for negative weight cycles in the graph
        for (int i = 0; i < edgesList.size(); i++) {
            Vertex src = edgesList.get(i).source;
            Vertex dst = edgesList.get(i).destination;
            int wt = edgesList.get(i).weight;
            if (src.distance != Double.POSITIVE_INFINITY && dst.distance > (src.distance + wt)) {
                System.out.println("WARNING: Negative weight cycle detected");
                return false;
            }
        }

        return true;
    }
 
    //Prints the cost and SSSP to get from vertex 1 to every other vertex in the graph
    public void printSSSP(ArrayList<Vertex> verticesList) {
        Stack<Integer> path = new Stack<>();
        for (int i = 1; i < verticesList.size(); i++) {
            Vertex currentVertex = verticesList.get(i);
            System.out.print("1 --> " + currentVertex.id + " cost is " + currentVertex.distance + "; path is ");
            
            //Push each predecessor onto a stack and then pop them off 
            //to print the path in the correct order
            while (currentVertex.predecessor != null) {
                currentVertex = currentVertex.predecessor;
                path.push(currentVertex.id);
            }
            while (!path.isEmpty()) {
                int pathVertex = path.pop();
                System.out.print(pathVertex + " --> ");
            }

            System.out.print(verticesList.get(i).id + "\n");
        }
    }

}
