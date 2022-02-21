import java.util.*;

public class LinkedObjects {
    ArrayList<Vertex> verticesList;
    ArrayList<Edge> edgesList;

    //Constructor initializes arraylists for storing the vertices and edges of a graph
    public LinkedObjects() {
        this.verticesList = new ArrayList<>();
        this.edgesList = new ArrayList<>();
    }

    //Adds a vertex to the graph
    public void addVertex(int id) {
        Vertex vertex = new Vertex(id);
        //Note: Vertex 1 will be at index 0 of the list
        this.verticesList.add(vertex);
    }

    //Adds a directed edge to graph
    public void addEdge(int srcVertex, int dstVertex, int wt) {
        int srcVertexIndex = findVertexIndex(srcVertex);
        int dstVertexIndex = findVertexIndex(dstVertex);
        Edge edge = new Edge(verticesList.get(srcVertexIndex), verticesList.get(dstVertexIndex), wt);
        edgesList.add(edge);
    }

    //Finds the index of the vertex with the ID 
    //we're looking for in the vertices arraylist
    public int findVertexIndex(int wantedID) {
        int index = 0;
        for (index = 0; index < this.verticesList.size(); index++) {
            Vertex currentVertex = verticesList.get(index);
            if (currentVertex.id == wantedID) {
                break;
            }
        }
        return index;
    }

}