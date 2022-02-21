import java.util.*;

public class AdjacencyList {
    private ArrayList<ArrayList<Integer>> adjList;

    //Constructor initializes adjacency list attribute 
    //with an arraylist of arraylists
    public AdjacencyList(int vertexID) {
        int arraylistSize = vertexID + 1;
        this.adjList = new ArrayList<ArrayList<Integer>>(arraylistSize);
        //Initialize outer arraylist with inner arraylists
        for (int i = 0; i < arraylistSize; i++) {
            this.adjList.add(new ArrayList<Integer>());
        }
    }

    //Adds vertex IDs of the 2 vertices to arraylists
    //to represent an edge between them
    public void addEdge(int vertex1, int vertex2) {
        this.adjList.get(vertex1).add(vertex2);
        this.adjList.get(vertex2).add(vertex1);
    }

    //Method to print contents of the adjacency list 
    //after the graph is complete
    public void printAdjacencyList() {
        for (int i = 0; i < this.adjList.size(); i++) {
            if (i == 0 && this.adjList.get(i).isEmpty() == true) {
                //Do not print anything in this case
            } else {
                System.out.print("Vertex " + (i) + ":");
                for (int j = 0; j < this.adjList.get(i).size(); j++) {
                    int index = this.adjList.get(i).get(j);
                    System.out.print(" " + (index));
                }
                System.out.println();
            }
        }
    }
}
