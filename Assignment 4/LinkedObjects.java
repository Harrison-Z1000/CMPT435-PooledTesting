import java.util.*;

public class LinkedObjects {
    ArrayList<Vertex> linkedObjList;

    //Constructor initializes an arraylist for storing Vertex objects
    public LinkedObjects() {
        this.linkedObjList = new ArrayList<>();
    }

    //Adds a vertex to the arraylist
    public void addVertex(int id) {
        Vertex vertex = new Vertex(id);
        //Note: Vertex 1 will be at index 0 of the list
        this.linkedObjList.add(vertex);
    }

    //Adds vertex ID of one vertex to the other's neighbor property
    //and vice versa to represent an edge between them
    public void addEdge(int vertex1, int vertex2) {
        int vertex1Index = findVertexIndex(vertex1);
        int vertex2Index = findVertexIndex(vertex2);
        this.linkedObjList.get(vertex1Index).neighbors.add(linkedObjList.get(vertex2Index));
        this.linkedObjList.get(vertex2Index).neighbors.add(linkedObjList.get(vertex1Index));
    }

    //Find the index of the vertex with the ID 
    //we're looking for in the linked objects list
    public int findVertexIndex(int wantedID) {
        int index = 0;
        for (index = 0; index < this.linkedObjList.size(); index++) {
            Vertex currentVertex = linkedObjList.get(index);
            if (currentVertex.id == wantedID) {
                break;
            }
        }
        return index;
    }

}