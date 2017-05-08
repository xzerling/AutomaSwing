/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.BorderLayout;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Zerling
 */
public class JMainPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
    
     private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    
    JDialog dialog;
    JFrame frame;
    JFrame creditos;
    
    JFrame frameLv1;
    JFrame frameLv2;
    JFrame frameLv3;
    JFrame frameLv4;
    JFrame frameLv5;
    JFrame frameLv6;
    JFrame frameLv7;
    JFrame frameLv8;
    JFrame frameLv9;
    JFrame frameLv10;
    
    private BufferedImage img;
    private ImageIcon  imgCredito;
    
    private static final int WIDTH = 600;
    private static final int HEIGHT =600;
    private static final int DEFAULT_SIZE = 50;
    private int filas = 9;
    private int columnas = 9;
    private int i;
    private int j;
//    private JButton boton1;
//    private JButton boton2;
//    private JButton boton3;
//    private JButton boton4;
//    private JButton boton5;
//    private JButton boton6;
    
    private boolean cleared = false;
    
//    JFrame frame;
    private BufferedImage fondo;
    private int filaInicial;
    private int columnaInicial;
    int contador = 0;
    int puntaje = 0;
    private int puntajeNivel = 0;
    private int movimientosNivel = -1;

    
    public JMainPanel()
    {
        
        this.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(JMainPanel.WIDTH, JMainPanel.HEIGHT);
        this.setPreferredSize(dimension);
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        
        
        JDrawPanel panelLv1 = this.panelMaker();
            frameLv1 = frameMaker(panelLv1);
            frameLv1.setVisible(false);
            System.out.println("cleared: "+panelLv1.getCleared());
            this.frameLv1.setVisible(true);
        
        //Dimension dimension = new Dimension(JMainPanel.WIDTH, JMainPanel.HEIGHT);
//        Dimension dimension = new Dimension(600, 600);
//        setLayout(null);
//        this.setPreferredSize(dimension);
//        this.setOpaque(false);
//        boton1 = new JButton("Jugar");
//        boton1.setBounds(270,250,130,30);
//        boton2 = new JButton("Como Jugar");
//        boton2.setBounds(270,285,130,30);
//        boton3 = new JButton("Altos puntajes");
//        boton3.setBounds(270,320,130,30);
//        boton4 = new JButton("Acerca de...");
//        boton4.setBounds(270,355,130,30);
        
        
//        boton1.addActionListener(this);
        
//        this.add(boton1);
        
//        boton1.addActionListener(this);

        

        try{
            fondo = ImageIO.read(new File("src/fondojuego.jpg"));            
            
            }
            catch(IOException e)
            {
            }
        
        
    }

    public JFrame frameMaker(JDrawPanel panel)
     {
        frame = new JFrame();
        frame.setTitle("Devil Crush");
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        JLabel button;
        frame.add(button = new JLabel("Puntaje Esperado: "), BorderLayout.EAST);
        frame.add(button = new JLabel("Puntaje Jugador: "), BorderLayout.WEST);
        frame.add(button = new JLabel("NORTE"), BorderLayout.NORTH);
        frame.add(button = new JLabel("Movimientos Restantes: "), BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        return frame;
         
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
        System.out.println("juan");
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("Jolape!");
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
     public  void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== boton1)
        {
            JDrawPanel panelLv1 = this.panelMaker();
            frameLv1 = frameMaker(panelLv1);
            frameLv1.setVisible(false);
            System.out.println("cleared: "+panelLv1.getCleared());
            this.frameLv1.setVisible(true);
        }
    }
     
    public JDrawPanel panelMaker()
     {
        JDrawPanel panel = new JDrawPanel();
        panel.setLayout(null);
        return panel;
         
     }
    
      @Override
    public void paint(Graphics g)
    {
    }

}