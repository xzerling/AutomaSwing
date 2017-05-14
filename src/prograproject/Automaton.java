
package prograproject;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Automaton 
{
    private String initialState;
    private ArrayList<Transition> transitions;
    private ArrayList<String> finalStates;
    private String input;
    
    public Automaton(String ini, ArrayList<Transition> trans, String in, ArrayList<String> fs)
    {
        this.initialState = ini;
        this.transitions = new ArrayList<>();
        this.finalStates = fs;
        this.input = in;
        verify();
    }
    
    private void verify()
    {
        String initial = initialState;
        char[] array = input.toCharArray();
        boolean finState = finalStates.contains(initial);

        for (int i = 0; i < array.length; i++) 
        {
            char sym = array[i];
            
            for (int j = 0; j < transitions.size(); j++) 
            {
                if(transitions.get(j).getStart().equals(initial) && transitions.get(j).getSymbol() == sym)
                {
                    initial = transitions.get(j).getEnd();
                }
            }
        }
    }
}

class Transition
{
    private String start, end;
    private char symbol; 
    
    public Transition(String s, String e, char sym)
    {
        this.start = s;
        this.end = e;
        this.symbol = sym;
    }
    
    public String getStart()
    {
        return start;
    }
    
    public String getEnd()
    {
        return end;
    }
    
    public char getSymbol()
    {
        return symbol;
    }
}