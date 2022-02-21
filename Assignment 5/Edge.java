public class Edge {
    Vertex source;
    Vertex destination;
    int weight;

    //Initialize properties of new edges
    public Edge(Vertex src, Vertex dst, int wt) {
        this.source = src;
        this.destination = dst;
        this.weight = wt;
    }
}
