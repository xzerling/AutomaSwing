/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Nicolas
 */
public class Graph 
{
    private ArrayList<Node> graph;
    private String input;
    
    public Graph(ArrayList<Node> g, String in)
    {
        this.graph = g;
        this.input = in;
    }
    
    public void verify(Graph g, String in)
    {
        Node s = this.graph.get(0);
        char[] array = in.toCharArray();
        
        Stack<String> stack = new Stack<>();
        ArrayList<Pair> adj = s.getList();
       
        
    }
}
