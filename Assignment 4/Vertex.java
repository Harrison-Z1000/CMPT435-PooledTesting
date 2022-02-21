import java.util.*;

public class Vertex {
    int id;
    boolean isProcessedDFT;
    boolean isProcessedBFT;
    ArrayList<Vertex> neighbors;
 
    //Initialize properties of new Vertex instances
    public Vertex(int id) {
        this.id = id;
        this.isProcessedDFT = false;
        this.isProcessedBFT = false;
        this.neighbors = new ArrayList<Vertex>();
    }

    //Print the id of a vertex in depth-first and breadth-first traversals
    public void printVertexID(Vertex vertex) {
        System.out.print(vertex.id);
    }

}
