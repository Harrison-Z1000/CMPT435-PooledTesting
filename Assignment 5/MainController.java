import java.io.*;
import java.util.*;

public class MainController {
    private static final String GRAPHS_FILE = "graphs2.txt";
    private static final String SPICE_FILE = "spice.txt";
    private static final int GRAPHS_FILE_LINE_COUNT = 90;
    private static final int SPICE_FILE_LINE_COUNT = 15;

    public static void main(String[] args) {
        //Part 1: Read the graphs file and build several 
        //directed graphs based on provided directions. 
        //Then run SSSP on each graph.
        try {
            //Create file scanner for graphs file 
            File fileObj = new File(GRAPHS_FILE);
            Scanner myReader = new Scanner(fileObj);
            String fileLine = myReader.nextLine();   

            //Initialize variables
            LinkedObjects linkedObj = null; 
            int graphCount = 0;
            String[] splitContents = null;

            for(int i = 0; i < GRAPHS_FILE_LINE_COUNT; i++) {
                //Use split to divide the string into segments 
                //that can be processed individually
                splitContents = fileLine.split("\\s+");
                
                //If the line starts with "--", 
                //it is a comment so go to the next line
                if (splitContents[0].equals("--")) {
                    //No action necessary if line is a comment or blank
                } 
                //If the line says "new graph", 
                //create a new linked object representation of the graph
                else if (splitContents[0].equals("new") && splitContents[1].equals("graph")) {
                    linkedObj = new LinkedObjects();
                    graphCount++;
                }
                //If the line starts with "add vertex", 
                //add a new vertex to the graph 
                else if (splitContents[0].equals("add") && splitContents[1].equals("vertex")) {
                    int vertexID = Integer.parseInt(splitContents[2]);
                    linkedObj.addVertex(vertexID);
                }
                //If the line starts with "add edge", 
                //add a new edge to the graph
                else if (splitContents[0].equals("add") && splitContents[1].equals("edge")) {
                    int srcVertex = Integer.parseInt(splitContents[2]);
                    int dstVertex = Integer.parseInt(splitContents[4]);
                    int wt = Integer.parseInt(splitContents[5]);
                    linkedObj.addEdge(srcVertex, dstVertex, wt);
                }
                //If the line is empty, then the graph is finished so we can 
                //print the single source shortest path for each vertex
                else if (fileLine.trim().equals("")) { 
                    System.out.println("\nGraph #" + graphCount);
                    SSSP s = new SSSP();
                    if (s.performSSSP(linkedObj) == true) {
                        s.printSSSP(linkedObj.verticesList);
                    } else {
                        //There is a negative weight cycle and performSSSP prints message
                    }
                }

                if (myReader.hasNextLine()) {
                    fileLine = myReader.nextLine();
                }
                else {
                    break;
                }
            }

            myReader.close();
            
        }
        catch (Exception ex) {
            System.out.println("See error below.");
            ex.printStackTrace();
        }
    }
    
    // try {
    //    
    // }
    // catch (Exception ex) {
    //     System.out.println("See error below.");
    //     ex.printStackTrace();
    // }
}