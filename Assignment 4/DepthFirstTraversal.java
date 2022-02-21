import java.util.*;

public class DepthFirstTraversal {
    ArrayList<Vertex> linkedObjList;  
    
    //Constructor initializes an arraylist to hold the linked objects passed in
    public DepthFirstTraversal(ArrayList<Vertex> LOL) {
        this.linkedObjList = LOL;
    }

    //Use loop to pass all unproccessed vertices to the 
    //traversal function in case the graph has disconnected parts
    public void findUnprocessed() {
        for (int i = 0; i < this.linkedObjList.size(); i++) {
            Vertex v = this.linkedObjList.get(i);
            if (v.isProcessedDFT == false) {
                traverse(v);
            }
        }
    }
    
    //Set the vertex as processed and recursively 
    //call the method with unprocessed adjacent vertices
    public void traverse(Vertex v) {
        System.out.print(v.id + " ");
        v.isProcessedDFT = true;
        for (int i = 0; i < v.neighbors.size(); i++) {
            Vertex vNext = v.neighbors.get(i);
            if (vNext.isProcessedDFT == false) {
                traverse(vNext);
            }
        }        
    }
}
