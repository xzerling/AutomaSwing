
package prograproject;

/**
 *
 * @author Nicolas
 */
public class Transition {
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
    
    public String repTransition()
    {
        return ("f( " + this.start + " , " + this.symbol + " ) = " + this.end);
    }
}
