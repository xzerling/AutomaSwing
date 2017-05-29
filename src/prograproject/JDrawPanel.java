/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Juan Ignacio
 */
public class JDrawPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
    
    private static final int WIDTH = 600;
    private static final int HEIGHT =600;
    private static final int DEFAULT_SIZE = 75;
    private static final int DEFAULT_LINE_SIZE = 38;

    
    private BufferedImage img;
    private BufferedImage img2;
    private BufferedImage img3;
    
    private ArrayList<State> startPoints;
    private ArrayList<State> standPoints;
    private ArrayList<State> endPoints;
    private ArrayList<State[]> transitionPoints;
    
    private boolean isStartNode = false;
    private boolean isEndNode = false;
    private boolean isStandNode = false;
    private boolean isTransition = false;
    
    //[0]in, [1] out, [2] label
    private final State selected[] = new State[3];
    private State onMainSelected[] = new State[3];
    private State select = new State(null, null);
    private ArrayList<Integer> states;
    private ArrayList<Transition> trans;

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    
    private boolean right2 = false;
    private boolean left2 = false;
    private boolean up2 = false;
    private boolean down2 = false;
    
    private String labelDiag;
    private JTextArea textArea = new JTextArea();
    public String transAsString;

    
    public JDrawPanel()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(JDrawPanel.WIDTH, JDrawPanel.HEIGHT);
        this.setPreferredSize(dimension);
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        
        this.startPoints = new ArrayList<>();
        this.standPoints = new ArrayList<>();
        this.endPoints = new ArrayList<>();
        this.transitionPoints = new ArrayList<State[]>();
        this.states = new ArrayList<>();
        this.trans = new ArrayList<>();
        
        
        
        
        try{
            img = ImageIO.read(new File("src/prograproject/dc6.png"));            
            img2 = ImageIO.read(new File("src/prograproject/dc4.png"));            
            img3 = ImageIO.read(new File("src/prograproject/dc7.png"));            
            }
            catch(IOException e)
            {
            }
        
    }
    public ArrayList<Transition> getTransitions()
    {
        return this.trans;
    }
    
    public ArrayList<String> getAutomatonStates()
    {
        ArrayList<String> states = new ArrayList<>();
        for (int i = 0; i < startPoints.size() ; i++) {
            states.add(startPoints.get(i).getState());
        }
        for (int i = 0; i < standPoints.size(); i++) {
            states.add(standPoints.get(i).getState());
        }
        for (int i = 0; i < endPoints.size(); i++) {
            states.add(endPoints.get(i).getState());
        }
        return states;
    }
    
    public ArrayList<String> getFinalStates()
    {
        ArrayList<String> finalStates = new ArrayList<>();
        for (int i = 0; i < endPoints.size(); i++) {
            finalStates.add(endPoints.get(i).getState());
        }
        return finalStates;
    }
    
    public String getStart()
    {
        String ini = startPoints.get(0).getState();
        return ini;
    }
    //pinta los nodos y las lineas de trans
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setStroke(new BasicStroke(5));   
        
        if (startPoints != null)
        {
            for (int i = 0; i < startPoints.size(); i++) {
                g2.drawImage(img, startPoints.get(i).getPoint().x, startPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(startPoints.get(i).getState(), startPoints.get(i).getPoint().x + DEFAULT_SIZE/2, startPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 5);
            }
        }
        if (standPoints != null)
        {
            for (int i = 0; i < standPoints.size(); i++) {
                g2.drawImage(img2, standPoints.get(i).getPoint().x, standPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(standPoints.get(i).getState(), standPoints.get(i).getPoint().x + DEFAULT_SIZE/2 - 6, standPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 2);
            }
        }
        if (endPoints != null)
        {
            for (int i = 0; i < endPoints.size(); i++) {
                g2.drawImage(img3, endPoints.get(i).getPoint().x, endPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(endPoints.get(i).getState(), endPoints.get(i).getPoint().x + DEFAULT_SIZE/2 - 7, endPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 3);
            }
        }
        
        if(this.transitionPoints != null)
        {
            for (int k = 0; k < this.transitionPoints.size(); k++)
            {
                if(this.transitionPoints.get(k)[1] != null)
                {
                    System.out.println("p1: "+this.transitionPoints.get(k)[0].getPoint() +"p2: " +this.transitionPoints.get(k)[1].getPoint());
                    this.drawTrans(this.transitionPoints.get(k)[0], this.transitionPoints.get(k)[1], this.transitionPoints.get(k)[2], g2);
                    this.repaintNodes(g);
                    System.out.println("dibujó");   
                }
            }
        }

    }




    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("click");
        if(isIsStartNode() && !overlap(e))
        {
            State state = new State(e.getPoint(), labelMaker(states));
            startPoints.add(state);
            setIsStartNode(false);
        }
        else if(isStandNode && !overlap(e))
        {
            standPoints.add(new State(e.getPoint(), labelMaker(states)));
            setIsStandNode(false);
        }
        else if(isEndNode && !overlap(e))
        {
            endPoints.add(new State(e.getPoint(), labelMaker(states)));
            setIsEndNode(false);
        }
         else if(this.isTransition)
        {
            System.out.println(this.getElementAt(e.getPoint().x, e.getPoint().y)); 
            System.out.println("antesIF");
            this.printTrans();
            if(this.selected[0] == null)
            {
//                System.out.println("entra PRIMERIF");
                this.selected[0] = this.getElementAt(e.getPoint().x, e.getPoint().y);
//                this.transitionPoints.add(this.selected[0]);
            }
            else
            {
//                System.out.println("ENTRA SEGUNDOIF");
                this.selected[1] = this.getElementAt(e.getPoint().x, e.getPoint().y);
                
                /* llamada a metodo que pide la etiqueta de la transicion
                    se guarda el string en una variable temporal
                */
                State p1 = selected[0];
                State p2 = selected[1];
                //etiqueta como parametro de state, point queda null
                //
                System.out.println("etiqueta a guardar: "+this.labelDiag);
                State p3 = new State(null, this.labelDiag);
                this.selected[2] = p3;
                
                System.out.println(selected[0].getPoint()+"");
                System.out.println(selected[1].getPoint()+"");
                System.out.println(selected[2].getState()+"");
                
                State localSelected[] = new State[3];
                localSelected[0] = p1;
                localSelected[1] = p2;
                localSelected[2] = p3;
                
                this.onMainSelected = localSelected;
                
                //condicion si choca la flecha con algun elemento
                /*if(this.transitionPoints.size() >= 1)
                overlapTransition(localSelected);*/
                
                    this.transitionPoints.add(localSelected);
                
                this.setIsTransition(false);
                this.printTrans();
                this.writeTrans();

                this.flushTrans();
//                System.out.println("FLUSH!");
                this.printTrans();

            }
            System.out.println("FUERA CLICK");
//                    this.printTrans();
        }
        repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        select = this.getElementAt(e.getX(), e.getY());
    }

    public boolean isIsStartNode() {
        return isStartNode;
    }

    public void setIsStartNode(boolean isStartNode) {
        this.isStartNode = isStartNode;
    }

    public boolean isIsEndNode() {
        return isEndNode;
    }

    public void setIsEndNode(boolean isEndNode) {
        this.isEndNode = isEndNode;
    }

    public boolean isIsStandNode() {
        return isStandNode;
    }

    public void setIsStandNode(boolean isStandNode) {
        this.isStandNode = isStandNode;
    }

    public boolean isIsTransition() {
        return isTransition;
    }

    public void setIsTransition(boolean isTransition) {
        this.isTransition = isTransition;
    }

       
    
    public State getElementAt(int x, int y)
    {
        State finalPoint;
        
        for (int k = 0; k < startPoints.size(); k++) 
        {
            finalPoint = contains(startPoints.get(k), x, y);
            if(finalPoint != null)
            return finalPoint;
        }
        
        for (int k = 0; k < standPoints.size(); k++) {
            finalPoint = contains(standPoints.get(k), x, y);
            if(finalPoint != null)
                return finalPoint;
        }
        
        for (int k = 0; k < endPoints.size(); k++) {
            finalPoint = contains(endPoints.get(k), x, y);
            if(finalPoint != null)
                return finalPoint;
        }
        
        return null;
    }
    
    public State contains(State p, int x, int y)
    {
        if( contains2(p.getPoint(), x, y) )
        {
            return p;
        }
        return null;
    }
    
    public boolean contains2(Point p, double x, double y) {
        double x0 = p.getX();
        double y0 = p.getY();
        return (x >= x0 &&
                y >= y0 &&
                x < x0 + DEFAULT_SIZE &&
                y < y0 + DEFAULT_SIZE);
    }
    
    
    public void drawTrans(State from, State to, State label, Graphics2D g)
    {
        
//        int lambda = 10;
//        label.setState(this.transDialogMaker());
        g.drawLine(from.getPoint().x+DEFAULT_LINE_SIZE, from.getPoint().y+DEFAULT_LINE_SIZE, to.getPoint().x+DEFAULT_LINE_SIZE, to.getPoint().y+DEFAULT_LINE_SIZE);
        g.drawString(label.getState(), (from.getPoint().x+to.getPoint().x+2*DEFAULT_LINE_SIZE-30)/2, (from.getPoint().y+to.getPoint().y+2*DEFAULT_LINE_SIZE)/2);
//        Polygon poly = new Polygon(new int[] {to.getPoint().x, to.getPoint().x-lambda, to.getPoint().x-lambda}, new int[] {to.getPoint().y+DEFAULT_LINE_SIZE, to.getPoint().y+lambda+10, to.getPoint().y+DEFAULT_SIZE-20}, 3);
//        g.drawPolygon(poly);
        

    }
    
    
    public void flushTrans()
    {
        this.selected[0] = null;
        this.selected[1] = null;
        this.selected[2] = null;
    }
    
    public void printTrans()
    {
        System.out.println("largo trans: "+this.transitionPoints.size());
        System.out.println("********printTrans*********");
        for (int k = 0; k < this.transitionPoints.size(); k++)
            {
                System.out.println("k: "+k+" p1: "+this.transitionPoints.get(k)[0].getState() +" p2: " +this.transitionPoints.get(k)[1].getState());
            }
//        System.out.println("largo selected: " + selected.length);
        for (int k = 0; k < selected.length; k++)
        {
            System.out.println("selected" + k +": " + selected[k]);
        }
    }

    public boolean overlap(MouseEvent e) 
    {
        if(this.select != null)
        {
            System.out.println("presionado!");
            return true;
        }
        if(getElementAt(e.getX() + DEFAULT_SIZE, e.getY() + DEFAULT_SIZE) != null)
        {
            System.out.println("choca");
            return true;
        }
        if(getElementAt(e.getX()+ DEFAULT_SIZE, e.getY()) != null)
        {
            System.out.println("choca");
            return true;
        }
        if(getElementAt(e.getX(), e.getY() + DEFAULT_SIZE) != null)
        {
            System.out.println("choca");
            return true;
        }
        if(isLine(e.getX(), e.getY()))
        {
            System.out.println("hay linea!");
            return true;
        }
        System.out.println("false!!!!!!!!!!!!!!!!!!!!");
        return false;
    }
    
    public void resetBools()
    {
        this.setIsStandNode(false);
        this.setIsEndNode(false);
        this.setIsTransition(false);
    }
    
    public void repaintNodes(Graphics g)
    {
        if (startPoints != null)
        {
            for (int i = 0; i < startPoints.size(); i++) {
                g.drawImage(img, startPoints.get(i).getPoint().x, startPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(startPoints.get(i).getState(), startPoints.get(i).getPoint().x + DEFAULT_SIZE/2, startPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 5);
            }
        }
        if (standPoints != null)
        {
            for (int i = 0; i < standPoints.size(); i++) {
                g.drawImage(img2, standPoints.get(i).getPoint().x, standPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(standPoints.get(i).getState(), standPoints.get(i).getPoint().x + DEFAULT_SIZE/2 - 6, standPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 2);
            }
        }
        if (endPoints != null)
        {
            for (int i = 0; i < endPoints.size(); i++) {
                g.drawImage(img3, endPoints.get(i).getPoint().x, endPoints.get(i).getPoint().y, DEFAULT_SIZE, DEFAULT_SIZE, this);
                g.drawString(endPoints.get(i).getState(), endPoints.get(i).getPoint().x + DEFAULT_SIZE/2 - 7, endPoints.get(i).getPoint().y + DEFAULT_SIZE/2 + 3);
            }
        }
        
        
//       this.buildTransPanel();
    }
    
    public String labelMaker(ArrayList<Integer> states)
    {
        if(states == null)
        {
            states.add(0);
            return ("q"+states.get(0));
        }
        else
        {
            Integer local = (states.size() - 1);
            local++;
            states.add(local);
            return ("q"+states.get(states.size() - 1));
        }
            
    }

    private boolean isLine(int x, int y)
    {
        double x1;
        double x2;
        double y1; 
        double y2;
        double m;
        double mY = 1;
        double posX;
        double posY;
        int t;
        
        for (int i = 0; i < transitionPoints.size(); i++)
        {
            x1 = transitionPoints.get(i)[0].getPoint().x + DEFAULT_LINE_SIZE;
            y1 = transitionPoints.get(i)[0].getPoint().y + DEFAULT_LINE_SIZE;
            x2 = transitionPoints.get(i)[1].getPoint().x + DEFAULT_LINE_SIZE;
            y2 = transitionPoints.get(i)[1].getPoint().y + DEFAULT_LINE_SIZE;
            
            System.out.println("x1: " + x1);
            System.out.println("y1: " + y1);
            System.out.println("x2: " + x2);
            System.out.println("y2: " + y2);
            
            m =  (x2-(x1))/(y2-(y1));
            System.out.println("!!!!!!!m: "+ m);
            //m<0 && y2 < y1
            if(m<0 && y2 < y1)
            {
                m = -m;
                mY = -1;
            }
            else
                mY = 1;
            if(x2 < x1 && y2 < y1)
            {
                m = -m;
                mY = -1;
            }
            
            if(m == 0 && y2 < y1)
            {
                mY = -1;
            }
            
            m = sloapRight(m, x1, x2, y1, y2);
            System.out.println("M: " + m);
            System.out.println("MY: " + mY);
            
            t = 1;
            posX = x1 + m*t;
            posY = y1 + mY*t;
            
            System.out.println("!!!!!!!!!!!X: "+x);
            System.out.println("!!!!!!!!!!!Y: "+y);
            
            System.out.println("RRRRRRRRRR: " + this.right);
            System.out.println("LLLLLLLLLL: " + this.left);
            System.out.println("UUUUUUU: " + this.up);
            System.out.println("DDDDDDD: " + this.down);
            System.out.println("EndLine: " + endLine(posX, x2, m, posY, y2));
            while(endLine(posX, x2, m, posY, y2))   //hasta que termine la linea respectivamente
            {
                posX = x1 + m*t;
                posY = y1 + mY*t;
                t++;
//                System.out.println("rectaX: " + posX);
//                System.out.println("rectaY: " + posY);
                if(containsLine(posX, x, posY, y))    //contains
                    return true;
            }
            resetBoolsLine();
        }
        return false;
    }
    
    public boolean containsLine(double x, double x0, double y, double y0)
    {
//        double x0 = x;
//        double y0 = y;
        return (x >= x0 &&
                y >= y0 &&
                x < x0 + DEFAULT_SIZE &&
                y < y0 + DEFAULT_SIZE);
    }

    private double sloapRight(double m, double x1, double x2, double y1, double y2) {
        if(m != 0)
        {
            if(x2 > x1)
            {
                this.right = true;
                return Math.abs(m);
            }
            this.left = true;
        }
        else
        {
            if(y2 > y1)
            {
                this.down = true;
                return m;
            }
            this.up = true;
        }
        return m;
    }
    private double sloapRight2(double m, double x1, double x2, double y1, double y2) {
        if(m != 0)
        {
            if(x2 > x1)
            {
                this.right2 = true;
                return Math.abs(m);
            }
            this.left2 = true;
        }
        else
        {
            if(y2 > y1)
            {
                this.down2 = true;
                return m;
            }
            this.up2 = true;
        }
        return m;
    }

    private boolean endLine(double posX, double x2, double m, double posY, double y2) {
        if(m != 0)
        {
            if(this.right)
            {
                return (posX <= x2);
            }
            else if(this.left)
            {
                return (posX >= x2);
            }
        }
        else
        {
            if(this.down)
            {
                return (posY <= y2);
            }
            else if(this.up)
            {
                return (posY >= y2);
            }
        }
        return false;
    }
    
    private boolean endLine2(double posX, double x2, double m, double posY, double y2) {
        if(m != 0)
        {
            if(this.right2)
            {
                return (posX <= x2);
            }
            else if(this.left2)
            {
                return (posX >= x2);
            }
        }
        else
        {
            if(this.down2)
            {
                return (posY <= y2);
            }
            else if(this.up2)
            {
                return (posY >= y2);
            }
        }
        return false;
    }

    private void resetBoolsLine() {
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
    }

    private void resetBoolsLine2() {
        this.right2 = false;
        this.left2 = false;
        this.up2 = false;
        this.down2 = false;
    }
    
    
    
    
    //*****************************Metodos sin usos***************************
    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }

    void setLabelDiag(String transLabel) 
    {
        this.labelDiag = transLabel;
    }
    
        public String writeTrans()
    {
        String transitions = new String();
        System.out.println("TRANS EN EL PANEL!!!!");

        for (int k = 0; k < this.transitionPoints.size(); k++)
            {
                this.transAsString = ("f( "+this.transitionPoints.get(k)[0].getState() +" ,  " + this.transitionPoints.get(k)[2].getState()+ " ) = "+this.transitionPoints.get(k)[1].getState()+"\n");
                Transition t = new Transition(this.transitionPoints.get(k)[0].getState(), this.transitionPoints.get(k)[1].getState(), this.transitionPoints.get(k)[2].getState().charAt(0));
                this.trans.add(t);
                System.out.println(transitions);

            }
            System.out.println("en clase transition");
            this.printTransOnArraylist();
            System.out.println("en clase transition");

        return transitions;
    }
    
        
    public JTextArea getTarnsitionTextArea()
    {
        return this.textArea;
    }
    
    public void buildTransPanel()
    {
        JPanel drawTransitionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        JLabel trans = new JLabel(this.writeTrans());
        JTextArea ta = new JTextArea();
        this.textArea.append(this.writeTrans());
        System.out.println("(en drawpanel)rellena con: "+this.textArea.getText());
        drawTransitionPanel.add(this.textArea);
    }
    
    public void printTransOnArraylist()
    {
        System.out.println("string de funciones: \n"+this.transAsString);
        for (int i = 0; i < this.trans.size(); i++)
        {
            System.out.println("en arraylist"+trans.get(i).repTransition());
            
        }
    }

    public void overlapTransition(State[] localSelected) {
        Point start = localSelected[0].getPoint();
        Point end  = localSelected[1].getPoint();
//        boolean line = false;
        
        
        double x1;
        double x2;
        double y1;
        double y2;
        double m;
        double mY = 1;
        double posX;
        double posY;
        int t;
        
        double xStart;
        double xEnd;
        double yStart;
        double yEnd;
        double m2;
        double m2Y = 1;
        double pos2X;
        double pos2Y;
        int t2;
        
            xStart= start.x + DEFAULT_LINE_SIZE;
            yStart = start.y + DEFAULT_LINE_SIZE;
            xEnd = end.x + DEFAULT_LINE_SIZE;
            yEnd = end.y + DEFAULT_LINE_SIZE;
            
            System.out.println("xStart: " + xStart);
            System.out.println("yStart: " + yStart);
            System.out.println("xEnd: " + xEnd);
            System.out.println("yEnd: " + yEnd);
            
            m2 =  (xEnd-(xStart))/(yEnd-(yStart));
            System.out.println("!!!!!!!m2: "+ m2);
            if(m2<0 && yEnd < yStart)
            {
                m2 = -m2;
                m2Y = -1;
            }
            else
                m2Y = 1;
            if(xEnd < xStart && yEnd < yStart)
            {
                m2 = -m2;
                m2Y = -1;
            }
            
            if(m2 == 0 && yEnd < yStart)
            {
                m2Y = -1;
            }
            
            m2 = sloapRight2(m2, xStart, xEnd, yStart, yEnd);
            System.out.println("M2: " + m2);
            System.out.println("M2Y: " + m2Y);
            
            t2 = 1;
            pos2X = xStart + m2*t2;
            pos2Y = yStart + mY*t2;
            
            System.out.println("R222222: " + this.right2);
            System.out.println("LL22222: " + this.left2);
            System.out.println("UUU2: " + this.up2);
            System.out.println("DDD2: " + this.down2);
            System.out.println("EndLine2: " + endLine2(pos2X, xEnd, yEnd, pos2Y, yEnd));
            while(endLine2(pos2X, xEnd, yEnd, pos2Y, yEnd))   //hasta que termine la linea respectivamente
            {
                pos2X = xStart + m2*t2;
                pos2Y = yStart + m2Y*t2;
                
                System.out.println("POS2X!!!!!!!!!!!!!!!!!!!!!!!!!!: " + pos2X);
                System.out.println("POS2Y!!!!!!!!!!!!!!!!!!!!!!!!!!: " + pos2Y);
                
//                System.out.println("rectaX: " + pos2X);
//                System.out.println("rectaY: " + pos2Y);

                for (int i = 0; i < transitionPoints.size(); i++)
                {
                    x1 = transitionPoints.get(i)[0].getPoint().x + DEFAULT_LINE_SIZE;
                    y1 = transitionPoints.get(i)[0].getPoint().y + DEFAULT_LINE_SIZE;
                    x2 = transitionPoints.get(i)[1].getPoint().x + DEFAULT_LINE_SIZE;
                    y2 = transitionPoints.get(i)[1].getPoint().y + DEFAULT_LINE_SIZE;

                    System.out.println("x1: " + x1);
                    System.out.println("y1: " + y1);
                    System.out.println("x2: " + x2);
                    System.out.println("y2: " + y2);

                    m =  (x2-(x1))/(y2-(y1));
                    System.out.println("!!!!!!!m: "+ m);
                    //m<0 && y2 < y1
                    if(m<0 && y2 < y1)
                    {
                        m = -m;
                        mY = -1;
                    }
                    else
                        mY = 1;
                    if(x2 < x1 && y2 < y1)
                    {
                        m = -m;
                        mY = -1;
                    }

                    if(m == 0 && y2 < y1)
                    {
                        mY = -1;
                    }

                    m = sloapRight(m, x1, x2, y1, y2);
                    System.out.println("M: " + m);
                    System.out.println("MY: " + mY);

                    t = 1;
                    posX = x1 + m*t;
                    posY = y1 + mY*t;

                    System.out.println("RRRRRRRRRR: " + this.right);
                    System.out.println("LLLLLLLLLL: " + this.left);
                    System.out.println("UUUUUUU: " + this.up);
                    System.out.println("DDDDDDD: " + this.down);
                    System.out.println("EndLine: " + endLine(posX, x2, m, posY, y2));
                    while(endLine(posX, x2, m, posY, y2))   //hasta que termine la linea respectivamente
                    {
                        posX = x1 + m*t;
                        posY = y1 + mY*t;
                        if(posX == pos2X && posY == pos2Y )
                            System.out.println("IGUALES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        t++;
                        System.out.println("rectaX: " + posX);
                        System.out.println("rectaY: " + posY);
                        System.out.println("recta2X: " + pos2X);
                        System.out.println("recta2Y: " + pos2Y);
                        System.out.println("");

                    }
                    resetBoolsLine();
                }
            t2++;
            }
        resetBoolsLine2();
        
//        this.transitionPoints.add(localSelected);
        
    }

}

