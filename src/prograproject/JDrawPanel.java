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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    
    private ArrayList<Point> startPoints;
    private ArrayList<Point> standPoints;
    private ArrayList<Point> endPoints;
    private ArrayList<Point[]> transitionPoints;
    
    private boolean isStartNode = false;
    private boolean isEndNode = false;
    private boolean isStandNode = false;
    private boolean isTransition = false;
    
    private final Point selected[] = new Point[2];
    private Point select = new Point();

    
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
        this.transitionPoints = new ArrayList<Point[]>();
        
        try{
            img = ImageIO.read(new File("src/prograproject/dc6.png"));            
            img2 = ImageIO.read(new File("src/prograproject/dc4.png"));            
            img3 = ImageIO.read(new File("src/prograproject/dc7.png"));            
            }
            catch(IOException e)
            {
            }
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if (startPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < startPoints.size(); i++) {
                g.drawImage(img, startPoints.get(i).x, startPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
        if (standPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < standPoints.size(); i++) {
                g.drawImage(img2, standPoints.get(i).x, standPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
        if (endPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < endPoints.size(); i++) {
                g.drawImage(img3, endPoints.get(i).x, endPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
        
        if(this.transitionPoints != null)
        {
            for (int k = 0; k < this.transitionPoints.size(); k++)
            {
                if(this.transitionPoints.get(k)[1] != null)
                {
                    System.out.println("p1: "+this.transitionPoints.get(k)[0] +"p2: " +this.transitionPoints.get(k)[1]);
                    this.drawLine(this.transitionPoints.get(k)[0], this.transitionPoints.get(k)[1], g);
//                    this.repaintNodes(g);

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
            startPoints.add(new Point(e.getPoint()));
            setIsStartNode(false);
            
        }
        else if(isStandNode && !overlap(e))
        {
            standPoints.add(new Point(e.getPoint()));
            setIsStandNode(false);
        }
        else if(isEndNode && !overlap(e))
        {
            endPoints.add(new Point(e.getPoint()));
            setIsEndNode(false);
        }
        else if(this.isTransition)
        {
            System.out.println(this.getElementAt(e.getPoint().x, e.getPoint().y)); 
            System.out.println("antesIF");
//            this.printTrans();
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
                
                Point p1 = selected[0];
                Point p2 = selected[1];
                System.out.println(selected[0]+"");
                System.out.println(selected[1]+"");
                Point localSelected[] = new Point[2];
                localSelected[0] = p1;
                localSelected[1] = p2;
                
                
                
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

    public Point getSelect() {
        return select;
    }

    public void setSelect(Point select) {
        this.select = select;
    }
       
    
    public Point getElementAt(int x, int y)
    {
        Point finalPoint;
        
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
    
    public Point contains(Point p, int x, int y)
    {
        if( contains2(p, x, y) )
        {
            return p.getLocation();
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
    
    
    public void drawLine(Point from, Point to, Graphics g)
    {
        g.drawLine(from.x+DEFAULT_LINE_SIZE, from.y+DEFAULT_LINE_SIZE, to.x+DEFAULT_LINE_SIZE, to.y+DEFAULT_LINE_SIZE);
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
                System.out.println("k: "+k+" p1: "+this.transitionPoints.get(k)[0] +" p2: " +this.transitionPoints.get(k)[1]);
            }
//        System.out.println("largo selected: " + selected.length);
        for (int k = 0; k < selected.length; k++)
        {
            System.out.println("selected" + k +": " + selected[k]);
            
        }
    }

    private boolean overlap(MouseEvent e) 
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
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < startPoints.size(); i++) {
                g.drawImage(img, startPoints.get(i).x, startPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
        if (standPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < standPoints.size(); i++) {
                g.drawImage(img2, standPoints.get(i).x, standPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
        if (endPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < endPoints.size(); i++) {
                g.drawImage(img3, endPoints.get(i).x, endPoints.get(i).y, DEFAULT_SIZE, DEFAULT_SIZE, this);

            }
        }
    }
    
}

