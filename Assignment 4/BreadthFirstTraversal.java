import java.util.*;

public class BreadthFirstTraversal {
    ArrayList<Vertex> linkedObjList;

    //Constructor initializes an arraylist to hold the linked objects passed in
    public BreadthFirstTraversal(ArrayList<Vertex> LOL) {
        this.linkedObjList = LOL;
    }

    //Use loop to pass all unproccessed vertices to the 
    //traversal function in case the graph has disconnected parts
    public void findUnprocessed() {
        for (int i = 0; i < this.linkedObjList.size(); i++) {
            Vertex v = this.linkedObjList.get(i);
            if (v.isProcessedBFT == false) {
                traverse(v);
            }
        }
    }

    //Perform breadth-first traversal
    public void traverse(Vertex v) {
        //The linked list data structure is used to 
        //create a queue that stores vertices
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
 
        //Enqueue the vertex passed in and mark it as processed
        queue.add(v);
        v.isProcessedBFT = true;
 
        while (queue.isEmpty() == false) {
            //Dequeue the vertex at the front of 
            //the queue and print the id
            Vertex dequeuedVertex = queue.poll();
            System.out.print(dequeuedVertex.id + " ");
 
            //Enqueue all adjacent vertices that are 
            //unprocessed and mark them as processed
            for (int i = 0; i < dequeuedVertex.neighbors.size(); i++) {
                Vertex n = dequeuedVertex.neighbors.get(i);
                if (n.isProcessedBFT == false) {
                    queue.add(n);
                    n.isProcessedBFT = true;
                }
            }
        }
    }
}
