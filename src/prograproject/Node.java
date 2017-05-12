package prograproject;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Node {
    
    private String name;
    private ArrayList<Pair> adjList;
    
    public Node(String n)
    {
        this.name = n;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public ArrayList<Pair> getList()
    {
        return this.adjList;
    }
}
