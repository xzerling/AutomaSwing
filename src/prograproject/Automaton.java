
package prograproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Nicolas
 */
public class Automaton 
{
    private String initialState;
    private ArrayList<String> states;
    private ArrayList<Transition> transitions;
    private ArrayList<String> finalStates;
    private ArrayList<Character> sigma;
    private ArrayList<String> sumStates;
    private HashMap<String,HashSet<String>> repeatFlag;
    private ArrayList<String> repTransitions;
    private String input;
    
    public Automaton(String ini,ArrayList<String> stat, ArrayList<Transition> trans, ArrayList<String> fs,ArrayList<String> sum, ArrayList<Character> alph, String in)
    {
        this.initialState = ini;
        this.states = stat;
        this.transitions = trans;
        this.finalStates = fs;
        this.sumStates = sum;
        this.sigma = alph;
        this.input = in;
        this.repeatFlag = new HashMap<>();
        this.repTransitions = new ArrayList<>();
        for( String s : stat)
        {
            repeatFlag.put(s, new HashSet<>());
        }
    }
    
    
    public boolean verify() 
    {
        //verificar 
        boolean consume = false;
        
        String current = this.initialState;
        String s = "";
        for (int i = 0; i < input.length(); i++) 
        {
            char c = this.input.charAt(i);
            for(Transition t : this.transitions) 
            {
                if(t.getStart().equals(current) && t.getSymbol()== c && !consume)
                {
                    s = s + t.repTransition();
                    this.repTransitions.add(s);
                    System.out.println("s: " + s);
                    //TimeUnit.SECONDS.sleep(1);
                    current = t.getEnd();
                    consume = true;
                }
            }
            consume = false;
        }
        
        if(this.finalStates.contains(current))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
 
    public ArrayList<String> getRepTransitions()
    {
        return this.repTransitions;
    }
    
    public String obtenerTransicion(int estadoI, int sigmaJ, ArrayList<String> states, ArrayList<Character> sigma, ArrayList<Transition> trans)
    {
        String estadoL = states.get(estadoI);
        String estadoInicio = estadoL;
        char sigmaL = sigma.get(sigmaJ);
        
        String transicion = "";
        
        for (int i = 0; i < trans.size(); i++)
        {
            if(trans.get(i).getStart().equals(estadoL) && trans.get(i).getSymbol()== '_')
            {
                estadoL = trans.get(i).getEnd();
            }
            if(trans.get(i).getStart().equals(estadoL) && trans.get(i).getSymbol()== sigmaL)
            {
                if(!transicion.contains(trans.get(i).getEnd()) )
                    {
                        String local = (trans.get(i).getEnd() + " ");
                        transicion = (transicion + local);
                    }
            }
            String estadoL2 = estadoInicio;
            
            //encontrar |
            if(trans.get(i).getStart().equals(estadoInicio))
            {
                for (int j = i; j < trans.size(); j++)
                {
                    if(trans.get(j).getStart().equals(estadoL2) && trans.get(j).getSymbol()== '_')
                    {
                        estadoL2 = trans.get(j).getEnd();
                    }
                    if(trans.get(j).getStart().equals(estadoL2) && trans.get(j).getSymbol()== sigmaL)
                    {
                        if(!transicion.contains(trans.get(j).getEnd()) )
                        {
                            String local = (trans.get(j).getEnd() + " ");
                            transicion = (transicion + local);
                        }
                    }
                }
            }
        }
        //termina y no encontro la estrella
        estadoL = estadoInicio;
        for (int i = 0; i < trans.size(); i++)
        {
            if(trans.get(i).getStart().equals(estadoL) && trans.get(i).getSymbol()== '_')
            {
                estadoL = trans.get(i).getEnd();
                for (int j = i; j >= 0; j--)
                {
                    if(trans.get(j).getStart().equals(estadoL) && trans.get(j).getSymbol()== sigmaL)
                    {
                        if(!transicion.contains(trans.get(j).getEnd()) )
                        {
                            String local = (trans.get(j).getEnd() + " ");
                            transicion = (transicion + local);
                        }
                    }
                }
            }
            estadoL = estadoInicio;
        }
        
        return transicion;
    }
    
    public String crearEstado(int contador)
    {
        return ("q" + contador);
    }
    
}
