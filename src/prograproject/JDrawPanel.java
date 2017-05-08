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
    private static final int DEFAULT_SIZE = 50;
    private int filas = 9;
    private int columnas = 9;
    private int i;
    private int j;
    
    private boolean cleared = false;
    
    private double cantidadImagenes = 6.0;
    private BufferedImage img;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    
    private int filaInicial;
    private int columnaInicial;
    int contador = 0;
    int puntaje = 0;
    private int puntajeNivel = 0;
    private int movimientosNivel = -1;
    
    private ArrayList<Point> startPoints;
    private ArrayList<Point> standPoints;
    private ArrayList<Point> endPoints;
    private ArrayList<Point> transitionPoints;
    
    private boolean isStartNode = false;
    private boolean isEndNode = false;
    private boolean isStandNode = false;
    private boolean isTransition = false;
    
    public JDrawPanel()
    {
        this.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(JDrawPanel.WIDTH, JDrawPanel.HEIGHT);
        this.setPreferredSize(dimension);
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        
        this.startPoints = new ArrayList<>();
        this.standPoints = new ArrayList<>();
        this.endPoints = new ArrayList<>();
        this.transitionPoints = new ArrayList<>();
        
        try{
            img = ImageIO.read(new File("src/prograproject/dc6.jpg"));            
            img2 = ImageIO.read(new File("src/prograproject/dc4.jpg"));            
            img3 = ImageIO.read(new File("src/prograproject/dc7.jpg"));            
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
                g.drawImage(img, startPoints.get(i).x, startPoints.get(i).y, 50, 50, this);

            }
        }
        if (standPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < standPoints.size(); i++) {
                g.drawImage(img2, standPoints.get(i).x, standPoints.get(i).y, 50, 50, this);

            }
        }
        if (endPoints != null)
        {
//            g.drawImage(img, drawPoint.x, drawPoint.y, this);
            for (int i = 0; i < endPoints.size(); i++) {
                g.drawImage(img3, endPoints.get(i).x, endPoints.get(i).y, 50, 50, this);

            }
        }
    }
    
public BufferedImage imagenAleatoria(BufferedImage img, BufferedImage img2, BufferedImage img3, 
BufferedImage img4,BufferedImage img5, BufferedImage img6)
{
    float aleatorio = (float)Math.random();
//    System.out.println("aleatorio: " + aleatorio);
    
   
    if(aleatorio <= 1.0/cantidadImagenes)
    {
        return img;      
    }
    else if(aleatorio >=  1.0/cantidadImagenes && aleatorio <= 2.0/cantidadImagenes)
    {
        return img2;
    }
    
    else if(aleatorio >= 2.0/cantidadImagenes && aleatorio <= 3.0/cantidadImagenes )
    {
        return img3;
    }
    
    else if(aleatorio >= 3.0/cantidadImagenes && aleatorio <= 4.0/cantidadImagenes)
    {
        return img4;
    }
    
    else if(aleatorio >= 4.0/cantidadImagenes && aleatorio <= 5.0/cantidadImagenes)
    {
        return img5;
    }
    else if(aleatorio >= 5.0/cantidadImagenes)
    {
        return img6;
    }
    return null;
}

public BufferedImage imagenEspecial(Color color, BufferedImage img, BufferedImage img2, BufferedImage img3, 
BufferedImage img4,BufferedImage img5, BufferedImage img6)
{
    if(color.equals(Color.ORANGE))
    {
        return img;
    }
    
    if(color.equals(Color.RED))
    {
        return img2;
    }
    
    if(color.equals(Color.MAGENTA))
    {
        return img3;
    }
    if(color.equals(Color.YELLOW))
    {
        return img4;
    }
    if(color.equals(Color.BLUE))
    {
        return img5;
    }
    if(color.equals(Color.GREEN))
    {
        return img6;
    }
    return null;
}



    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("click");
        if(isIsStartNode())
        {
            startPoints.add(new Point(e.getPoint()));
            setIsStartNode(false);
            
        }
        else if(isStandNode)
        {
            standPoints.add(new Point(e.getPoint()));
            setIsStandNode(false);
        }
        else if(isEndNode)
        {
            endPoints.add(new Point(e.getPoint()));
            setIsEndNode(false);
        }
//        System.out.println("x: " + drawPoint.getX() + " y: " + drawPoint.getY());
        repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("press");
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("Dragged");
    }

    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        contador = 0;
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


    

    public int getPuntajeNivel()
    {
        return puntajeNivel;
    }

    public void setPuntajeNivel(int puntajeNivel)
    {
        this.puntajeNivel = puntajeNivel;
    }

    public int getMovimientosNivel()
    {
        return movimientosNivel;
    }

    public void setMovimientosNivel(int movimientosNivel)
    {
        this.movimientosNivel = movimientosNivel;
    }

    public int getPuntaje()
    {
        return puntaje;
    }
    
    public void levelClear()
    {
        if (this.puntaje >= puntajeNivel)
        {
            this.cleared = true;
        }
    }
    
    public boolean levelFail(int mov)
    {
        return mov == 0;
    }

    boolean getCleared()
    {
        return this.cleared;
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
    


}

