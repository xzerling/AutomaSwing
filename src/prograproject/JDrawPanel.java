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
    private State select = new State(null, null);
    private ArrayList<Integer> states;

    
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
        
        try{
            img = ImageIO.read(new File("src/prograproject/dc6.png"));            
            img2 = ImageIO.read(new File("src/prograproject/dc4.png"));            
            img3 = ImageIO.read(new File("src/prograproject/dc7.png"));            
            }
            catch(IOException e)
            {
            }
        
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
                    this.drawLine(this.transitionPoints.get(k)[0], this.transitionPoints.get(k)[1], this.transitionPoints.get(k)[2], g2);
                    this.repaintNodes(g);
                    System.out.println("dibujó");   
                }
            }
        }

    }


    @Override
    public void mouseMoved(MouseEvent e)
    {
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
                State p3 = new State(null, "a");
                this.selected[2] = p3;
                
                System.out.println(selected[0].getPoint()+"");
                System.out.println(selected[1].getPoint()+"");
                System.out.println(selected[2].getState()+"");
                
                State localSelected[] = new State[3];
                localSelected[0] = p1;
                localSelected[1] = p2;
                localSelected[2] = p3;
                
                
                
                this.transitionPoints.add(localSelected);
                this.setIsTransition(false);
                this.printTrans();

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

    @Override
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("Dragged");
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
        
        for (int k = 0; k < startPoints.size(); k++) {
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
    
    
    public void drawLine(State from, State to, State label, Graphics2D g)
    {
        int lambda = 10;
        g.drawLine(from.getPoint().x+DEFAULT_LINE_SIZE, from.getPoint().y+DEFAULT_LINE_SIZE, to.getPoint().x+DEFAULT_LINE_SIZE, to.getPoint().y+DEFAULT_LINE_SIZE);
        g.drawString(label.getState(), (from.getPoint().x+to.getPoint().x+2*DEFAULT_LINE_SIZE-30)/2, (from.getPoint().y+to.getPoint().y+2*DEFAULT_LINE_SIZE)/2);
        Polygon poly = new Polygon(new int[] {to.getPoint().x, to.getPoint().x-lambda, to.getPoint().x-lambda}, new int[] {to.getPoint().y+DEFAULT_LINE_SIZE, to.getPoint().y+lambda+10, to.getPoint().y+DEFAULT_SIZE-20}, 3);
//        g.drawPolygon(poly);

    }
    
    
    public void flushTrans()
    {
        this.selected[0] = null;
        this.selected[1] = null;
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
        int x1;
        int x2;
        int y1;
        int y2;
        int m;
        int posX;
        int posY;
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
            
            m =  (x2-(x1))/(y1-(y2));
            System.out.println("M: " + m);
            
            t = 1;
            posX = x1 + m*t;
//          posY = y1 + m*t;
            
//            System.out.println("!!!!!!!!!!!X: "+x);
//            System.out.println("!!!!!!!!!!!Y: "+y);

            while(posX <= x2)
            {
                posX = x1 + m*t;
                posY = y1 + t;
                t++;
//                System.out.println("rectaX: " + posX);
//                System.out.println("rectaY: " + posY);
                if(containsLine(posX, x, posY, y))    //contains
                    return true;
                
            }
        }
        return false;
    }
    
    public boolean containsLine(int x, int x0, int y, int y0)
    {
//        double x0 = x;
//        double y0 = y;
        return (x >= x0 &&
                y >= y0 &&
                x < x0 + DEFAULT_SIZE &&
                y < y0 + DEFAULT_SIZE);
    }
}

