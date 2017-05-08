/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Zerling
 */
public class JMainPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
    
    private JButton startNode;
    private JButton transNode;
    private JButton endNode;
    private JButton transition;
    
    JDialog dialog;
    JFrame frame;
    JFrame creditos;
    
    JFrame frameLv1;
    
    private BufferedImage img;
    private ImageIcon  imgCredito;
    
    private static final int WIDTH = 600;
    private static final int HEIGHT =600;
    private static final int DEFAULT_SIZE = 50;
    private int filas = 9;
    private int columnas = 9;
    private int i;
    private int j;
    
    private BufferedImage fondo;
    
    
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
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,1));
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Archivo");
        
        menuBar.add(file);
        
        JToolBar toolbar = new JToolBar();
        
        this.startNode = new JButton("Str Node");
        this.transNode = new JButton("Trs Node");
        this.endNode = new JButton("End Node");
        this.transition = new JButton(" Trans  ");
        
        this.startNode.setPreferredSize(new Dimension(60,47));
        this.transNode.setPreferredSize(new Dimension(60,47));
        this.endNode.setPreferredSize(new Dimension(60,47));
        this.transition.setPreferredSize(new Dimension(60,47));
        
        toolbar.add(this.startNode);
        toolbar.add(this.transNode);
        toolbar.add(this.endNode);
        toolbar.add(this.transition);
        
        
        northPanel.add(toolbar,1,0);
        
        frame = new JFrame();
        frame.setJMenuBar(menuBar);
        frame.setTitle("Automata plz");
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
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
     
        /**
        if(e.getSource()== boton1)
        {
            JDrawPanel panelLv1 = this.panelMaker();
            frameLv1 = frameMaker(panelLv1);
            frameLv1.setVisible(false);
            System.out.println("cleared: "+panelLv1.getCleared());
            this.frameLv1.setVisible(true);
        }
        * */
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