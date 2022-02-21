import java.io.*;
import java.util.*;

public class MainProgram {
   
    private static final String GRAPHS_FILE = "graphs1.txt";
    private static final String MAGIC_ITEMS_FILE = "magicitems.txt";
    private static final String ITEMS_TO_FIND_FILE = "magicitems-find-in-bst.txt";
    private static final int GRAPHS_FILE_LINE_COUNT = 377;
    private static final int ITEM_COUNT = 666;
    private static final int ITEMS_TO_FIND_COUNT = 42;

    public static void main(String[] args) {
        //Part 1: Read the graphs file and build several 
        //undirected graphs based on provided directions
        try {
            //Create file scanner for graphs file 
            File fileObj = new File(GRAPHS_FILE);
            Scanner myReader = new Scanner(fileObj);
            String fileLine = myReader.nextLine();   

            //Initialize different representations
            Matrix matrix = null;
            AdjacencyList adjList = null;
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
                    //No action necessary if line is a comment
                } 
                //If the line says "new graph", 
                //create three new objects for representations of the graph
                else if (splitContents[0].equals("new") && splitContents[1].equals("graph")) {
                    matrix = new Matrix(0);
                    adjList = new AdjacencyList(0);
                    linkedObj = new LinkedObjects();
                    graphCount++;
                }
                //If the line starts with "add vertex", 
                //add a new vertex in each representation 
                else if (splitContents[0].equals("add") && splitContents[1].equals("vertex")) {
                    int vertexID = Integer.parseInt(splitContents[2]);
                    matrix = new Matrix(vertexID);
                    adjList = new AdjacencyList(vertexID);
                    linkedObj.addVertex(vertexID);
                }
                //If the line starts with "add edge", 
                //take the 2 vertices and connect them in each representation
                else if (splitContents[0].equals("add") && splitContents[1].equals("edge")) {
                    int vertex1 = Integer.parseInt(splitContents[2]);
                    int vertex2 = Integer.parseInt(splitContents[4]);
                    matrix.addEdge(vertex1, vertex2);
                    adjList.addEdge(vertex1, vertex2);
                    linkedObj.addEdge(vertex1, vertex2);
                }
                //If the line is empty, then the graph is finished so we can 
                //print the matrix and adjacency list representations as well as 
                //perform depth-first and breadth-first traversal on the graph.
                else if (fileLine.trim().equals("")) {
                    System.out.println("\nGraph #" + graphCount);
                    System.out.println("\nMatrix");
                    matrix.printMatrix();
                    System.out.println("\nAdjacency List");
                    adjList.printAdjacencyList();
                    System.out.println("\nDFT");
                    DepthFirstTraversal dft = new DepthFirstTraversal(linkedObj.linkedObjList);
                    dft.findUnprocessed();
                    System.out.println("");
                    System.out.println("\nBFT");
                    BreadthFirstTraversal bft = new BreadthFirstTraversal(linkedObj.linkedObjList);
                    bft.findUnprocessed();
                    System.out.println("");
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
        
        //Part 2: Read the magic items file and 
        //build a BST and then look up items
        try {
            //Create file scanner for magic items file 
            File fileObj = new File(MAGIC_ITEMS_FILE);
            Scanner myReader = new Scanner(fileObj);
            String fileLine = null; 
            
            //Create instance of the binary search tree class
            System.out.println("\nMagic Items BST");
            BinarySearchTree bst = new BinarySearchTree();

            //Insert each magic item into the BST and print 
            //the path from the root node to the new node
            for (int i = 0; i < ITEM_COUNT; i++) {
                fileLine = myReader.nextLine();
                System.out.println("Item: " + fileLine);
                System.out.print("Path: ");
                bst.insert(fileLine);
                System.out.println("\n");
            }

            //Performs an in-order traversal of the BST and 
            //prints out the items in alphabetical order
            System.out.println("\nAll Nodes in the BST:");
            bst.inOrderTraversal(bst.root);

            myReader.close();

            //Change file scanner to read lists of magic items we want to look up 
            fileObj = new File(ITEMS_TO_FIND_FILE);
            myReader = new Scanner(fileObj);
            fileLine = null;
            int compsForItem = 0;
            int totalComps = 0;

            System.out.println("\nItems Looked Up in the BST:");
            
            //Look up the pre-selected magic items in our BST. 
            //Print out the path from the root node to the node 
            //we were searching for and the number of comparisons used.
            for (int i = 0; i < ITEMS_TO_FIND_COUNT; i++) {
                bst.comparisons = 0;
                fileLine = myReader.nextLine();
                System.out.println("Item: " + fileLine);
                System.out.print("Path: ");
                compsForItem = bst.lookupItem(bst.root, fileLine);
                System.out.println("\nComparisons: " + compsForItem);
                totalComps += compsForItem;
                System.out.println("\n");
            }
            
            //Compute and print the average number of comparisons to 2 decimal places
            double avgComparisons = (double) totalComps / ITEMS_TO_FIND_COUNT;
            System.out.println("Average number of comparisons used to look up each item: " + String.format("%.2f", avgComparisons));

            myReader.close();
        } 
        catch (Exception ex) {
            System.out.println("See error below.");
            ex.printStackTrace();
        }
    }

}
