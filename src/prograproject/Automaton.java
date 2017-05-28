
package prograproject;

import java.util.ArrayList;

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
    private String input;
    
    public Automaton(String ini,ArrayList<String> stat, ArrayList<Transition> trans, ArrayList<String> fs, ArrayList<Character> alph, String in)
    {
        this.initialState = ini;
        this.states = stat;
        this.transitions = trans;
        this.finalStates = fs;
        this.sigma = alph;
        this.input = in;
    }
    
    public boolean verify()
    {
        String initial = initialState;
        char[] array = input.toCharArray();
        int length = array.length;
        
        for (int i = 0; i < array.length; i++) 
        {
            char sym = array[i];
            
            for (int j = 0; j < transitions.size(); j++) 
            {
                if(transitions.get(j).getStart().equals(initial) && transitions.get(j).getSymbol() == sym)
                {
                    initial = transitions.get(j).getEnd();
                    length--;
                    j = transitions.size()+1;
                }
            }
        }       
        if(length > 0){return false;}
        else{return true;}
    }
    
    public Automaton convertToDFA(String ini, ArrayList<String> stat, ArrayList<Transition> trans, ArrayList<String> fin, ArrayList<Character> alph, String in)
    {
        String init = stat.get(0);
        ArrayList<String> dfaStates = new ArrayList<>();
        ArrayList<Transition> dfaTransitions = new ArrayList<>();
        ArrayList<String> dfaFinals = new ArrayList<>();
        
        int cont = 0;
        String state = crearEstado(cont);
        cont++;
        dfaStates.add(state);
        
        String[][] matrix = new String[stat.size()][alph.size()+1];
        
        for (int i = 0; i < stat.size(); i++)
        {
            for (int j = 0; j < alph.size()+1; j++)
            {
                if(j == 0)
                    matrix[i][j] = stat.get(i);
                
                else if(j <= alph.size() )
                    matrix[i][j] = obtenerTransicion(i, j-1, stat, alph, trans);
                
            }            
        }
        
        String qa = "";
        String sa = "";
        String st = "";
        String s = ini;
        String last = init;
        String[] transSplit = null;
        boolean flag = false;
        boolean isKleene = false;

        int size = stat.size();	
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < alph.size()+1; j++)
            {  
            	//System.out.println("i: " + i);	
            	if(j == 0){
                    qa = matrix[i][j];
                }
                else if(j <= alph.size())
                {
                    sa = matrix[i][j];
                    transSplit = sa.split(" ");
                    for (int k = 0; k < transSplit.length; k++)
                    {
                        String estadoSplit = transSplit[k];
                        if(i-1 >= 0 && !matrix[i][j].equals(matrix[i-1][j]) && !flag)
                        {
                            s = last;
                            k = 0;
                            j = 1;
                            flag = true;
                            //System.out.println("entra cuando no son iguales" + " s: " + s);
                        }
                        
                        if(stat.contains(estadoSplit))
                        {
                            for (int l = 0; l < size ; l++) 
                            {
                                if(matrix[l][0].equals(estadoSplit))
                                {
                                    String[] auxSplit = matrix[l][j].split(" ");
                                    for(int p = 0; p < auxSplit.length ; p++)
                                    {
                                            if(matrix[l][0].equals(auxSplit[p]))
                                            {
                                                    isKleene = true;
                                                    i = l;
                                                    Transition kl = new Transition(last,last,alph.get(j-1));
                                                    dfaTransitions.add(kl);
                                            }
                                    }

                                }
                        }
                        if(!isKleene)
                        {
                            st = crearEstado(cont);
                            cont++;
                            dfaStates.add(st);
                            if(j <= alph.size() )
                            {
                                Transition t = new Transition(s, st, alph.get(j-1));
                                dfaTransitions.add(t);

                            }
                            //System.out.println("Crea un nuevo estado" + "st: "  + st);
                            stat.remove(estadoSplit);
                        }
                        }
                        else if(i-1 >= 0 && matrix[i][j].equals(matrix[i-1][j]) && !flag)
                        {
                            k = transSplit.length;
                            //System.out.println("entra cuando son iguales");
                        }
                        else if (matrix[i][j].equals(""))
                        {
                            continue; 
                        }

                    }
                    isKleene = false;
                }
                
                
                
            }
            flag = false;
            if(!st.equals(""))
            {
            	last = st; 
            }
                
        }
        
        //encontrar finales
        String localInicio = init;
        boolean cambiado = false;
        for (int i = 0; i < dfaTransitions.size(); i++)
        {
            for (int j = i; j < dfaTransitions.size(); j++)
            {
                if(dfaTransitions.get(j).getStart().equals(localInicio))
                {
                    localInicio = dfaTransitions.get(j).getEnd();
                    cambiado = true;
                }
            }
            if(cambiado == true)
            {
                dfaFinals.add(localInicio);
                cambiado = false;
            }
            localInicio = ini;
        }
        
        Automaton a = new Automaton(init,dfaStates, dfaTransitions, dfaFinals, alph, in);
        return a;
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
