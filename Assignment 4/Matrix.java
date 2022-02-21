public class Matrix {
    private int[][] matrix;

    //Constructor initializes matrix attribute with a 2D array
    public Matrix(int vertexID) {
        int matrixSize = vertexID + 1;
        this.matrix = new int[matrixSize][matrixSize];
    }

    //Add 1s in the matrix for the 2 vertices 
    //to represent an edge between them
    public void addEdge(int vertex1, int vertex2) {
        this.matrix[vertex1][vertex2] = 1;
        this.matrix[vertex2][vertex1] = 1;
    }

    //Method to print contents of the matrix 
    //after the graph is complete
    public void printMatrix() {
        System.out.println("Note: The first row and first column display edges connecting vertex 0 to other vertices.");
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
