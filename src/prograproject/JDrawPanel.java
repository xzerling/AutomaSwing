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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    
    private boolean cleared = false;
    
    private double cantidadImagenes = 6.0;
    private BufferedImage fondo;
    private BufferedImage img;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img6;
    private BufferedImage imgEH;
    private BufferedImage imgEH2;
    private BufferedImage imgEH3;
    private BufferedImage imgEH4;
    private BufferedImage imgEH5;
    private BufferedImage imgEH6;
    private BufferedImage imgEV;
    private BufferedImage imgEV2;
    private BufferedImage imgEV3;
    private BufferedImage imgEV4;
    private BufferedImage imgEV5;
    private BufferedImage imgEV6;
    private BufferedImage imgEB;
    private BufferedImage imgEB2;
    private BufferedImage imgEB3;
    private BufferedImage imgEB4;
    private BufferedImage imgEB5;
    private BufferedImage imgEB6;
    private BufferedImage imgCaca;
    private int filaInicial;
    private int columnaInicial;
    int contador = 0;
    int puntaje = 0;
    private int puntajeNivel = 0;
    private int movimientosNivel = -1;

    
    public JDrawPanel()
    {
        this.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(JDrawPanel.WIDTH, JDrawPanel.HEIGHT);
        this.setPreferredSize(dimension);
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        
        boton1 = new JButton("Rellenar");
        boton2 = new JButton("recorrerY");
        boton3 = new JButton("RecorrerX");
        boton4 = new JButton("Recorrer");
        boton5 = new JButton("Bajar");
        boton6 = new JButton("Secuencia");

        boton1.setBounds(0, 0, 100, 40);
        boton2.setBounds(120, 0, 100, 40);
        boton3.setBounds(240, 0, 100, 40);
        boton4.setBounds(360, 0, 100, 40);
        boton5.setBounds(480, 0, 100, 40);
        boton6.setBounds(540, 0, 100, 40);
        
        this.add(boton1);
        this.add(boton2);
        this.add(boton3);
        this.add(boton4);
        this.add(boton5);
        this.add(boton6);
        
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        boton4.addActionListener(this);
        boton5.addActionListener(this);
        boton6.addActionListener(this);
        
        
        

        try{
            fondo = ImageIO.read(new File("src/fondojuego.jpg"));            
            img = ImageIO.read(new File("src/dc1.jpg"));            
            img2 = ImageIO.read(new File("src/dc2.jpg"));
            img3 = ImageIO.read(new File("src/dc3.jpg"));            
            img4 = ImageIO.read(new File("src/dc4.jpg"));
            img5 = ImageIO.read(new File("src/dc5.jpg"));            
            img6 = ImageIO.read(new File("src/dc6.jpg"));
            imgCaca = ImageIO.read(new File("src/dc7.jpg"));
            imgEH = ImageIO.read(new File("src/dcHorizontal/dc1h.jpg"));            
            imgEH2 = ImageIO.read(new File("src/dcHorizontal/dc2h.jpg"));
            imgEH3 = ImageIO.read(new File("src/dcHorizontal/dc3h.jpg"));            
            imgEH4 = ImageIO.read(new File("src/dcHorizontal/dc4h.jpg"));
            imgEH5 = ImageIO.read(new File("src/dcHorizontal/dc5h.jpg"));            
            imgEH6 = ImageIO.read(new File("src/dcHorizontal/dc6h.jpg"));
            imgEV = ImageIO.read(new File("src/dcVertical/dc1v.jpg"));            
            imgEV2 = ImageIO.read(new File("src/dcVertical/dc2v.jpg"));
            imgEV3 = ImageIO.read(new File("src/dcVertical/dc3v.jpg"));            
            imgEV4 = ImageIO.read(new File("src/dcVertical/dc4v.jpg"));
            imgEV5 = ImageIO.read(new File("src/dcVertical/dc5v.jpg"));            
            imgEV6 = ImageIO.read(new File("src/dcVertical/dc6v.jpg"));
            imgEB = ImageIO.read(new File("src/dcBomba/dc1b.jpg"));
            imgEB2 = ImageIO.read(new File("src/dcBomba/dc2b.jpg"));
            imgEB3 = ImageIO.read(new File("src/dcBomba/dc3b.jpg"));
            imgEB4 = ImageIO.read(new File("src/dcBomba/dc4b.jpg"));
            imgEB5 = ImageIO.read(new File("src/dcBomba/dc5b.jpg"));
            imgEB6 = ImageIO.read(new File("src/dcBomba/dc6b.jpg"));
            
            }
            catch(IOException e)
            {
            }
        
        
//        secuencia();
        
    }
    
    @Override
    public void paint(Graphics g)
    {

        super.paint(g);
        if(fondo != null) {
          g.drawImage(fondo, 0, 0, null);
         }
        
        
        for(i = 1; i <= columnas+1; i++)
        {
            g.drawLine(i*JDrawPanel.DEFAULT_SIZE, JDrawPanel.DEFAULT_SIZE, 
                        i*JDrawPanel.DEFAULT_SIZE, JDrawPanel.DEFAULT_SIZE * (columnas +1));           
            for (int j = 1; j <= filas+1; j++)
            {

               g.drawLine(i*JDrawPanel.DEFAULT_SIZE, j*JDrawPanel.DEFAULT_SIZE, 
               JDrawPanel.DEFAULT_SIZE * filas, j*JDrawPanel.DEFAULT_SIZE);   
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
    


}

