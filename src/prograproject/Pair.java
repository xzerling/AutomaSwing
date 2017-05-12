/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Pair {
    
    private String node;
    private ArrayList<Character> transitions;
    
    public Pair(String n)
    {
        this.node = n;
        this.transitions = new ArrayList<Character>();
    }
    
    public String getNode()
    {
        return this.node;
    }
    
    public char getTransition(int i)
    {
        return transitions.get(i);
    }
    
    public void setNode(String n)
    {
        this.node = n;
    }
    
}
