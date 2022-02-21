public class Vertex {
    int id;
    //Holds the distance from the source vertex to this one
    double distance;
    //Holds onto the source vertex for ease (memoization)
    Vertex predecessor;

 
    //Initialize properties of new Vertex instances
    public Vertex(int id) {
        this.id = id;
        this.distance = 0;
        this.predecessor = null;
    }

    //Print the id of a vertex
    public void printVertexID(Vertex vertex) {
        System.out.print(vertex.id);
    }

}
