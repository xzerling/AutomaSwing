/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    
    private JButton verWord;
    
    private Dialog dialog;
    
    JFrame frame;
    JFrame creditos;
    
    JFrame frameLv1;
    
    private JTextField sigma;
    private JTextField inputWord;
    
    private BufferedImage img;
    private ImageIcon  imgbutton1;
    private ImageIcon  imgbutton2;
    private ImageIcon  imgbutton3;
    private ImageIcon  imgbutton4;
    
    
    private static final int WIDTH = 600;
    private static final int HEIGHT =600;
    private static final int DEFAULT_SIZE = 50;
    
    private JDrawPanel panelLv1;
    public Dialog labelDiag;
    private JPanel rightPanel;
    private String trans;
    JTextArea ta = new JTextArea();

    private State labelCoected[] = new State[3];
    private String name;
    private ArrayList<Character> characters;
    
    public JMainPanel()
    {
        this.name = JOptionPane.showInputDialog(this, "ingrese caracteres SEPARADOS POR COMA!", "Caracteres", JOptionPane.INFORMATION_MESSAGE);
        while(this.name.equals(""))
        {
            this.name = JOptionPane.showInputDialog(this, "ingrese caracteres SEPARADOS POR COMA!", "Caracteres", JOptionPane.INFORMATION_MESSAGE);
        }
        this.characters = new ArrayList<>();
        
        String[] array = this.name.split(",");
        for (int i = 0; i < array.length; i++) {
            this.characters.add(array[i].charAt(0));
        }
        
        this.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(JMainPanel.WIDTH, JMainPanel.HEIGHT);
        this.setPreferredSize(dimension);
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        
        
        ImageIcon icon = new ImageIcon("src/prograproject/dc6.png");
        Image img = icon.getImage() ;  
        Image newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        this.imgbutton1 = new ImageIcon(newimg);
        
        ImageIcon icon2 = new ImageIcon("src/prograproject/dc4.png");
        Image img2 = icon2.getImage() ;  
        Image newimg2 = img2.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        this.imgbutton2 = new ImageIcon(newimg2);
        
        ImageIcon icon3 = new ImageIcon("src/prograproject/dc7.png");
        Image img3 = icon3.getImage() ;  
        Image newimg3 = img3.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        this.imgbutton3 = new ImageIcon(newimg3);
        
        ImageIcon icon4 = new ImageIcon("src/prograproject/arrow.png");
        Image img4 = icon4.getImage() ;  
        Image newimg4 = img4.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        this.imgbutton4 = new ImageIcon(newimg4);
        

        
        
        this.panelLv1 = this.panelMaker();
        frameLv1 = frameMaker(panelLv1);
        frameLv1.setVisible(false);
        this.frameLv1.setVisible(true);
        
        
        startNode.addActionListener(this);
        transNode.addActionListener(this);
        endNode.addActionListener(this);
        transition.addActionListener(this);
        verWord.addActionListener(this);
        
        transNode.setEnabled(false);
        endNode.setEnabled(false);
        transition.setEnabled(false);
        
    }

    public JFrame frameMaker(JDrawPanel panel)
     {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,1));
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Archivo");
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        this.rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setSize(JPanel.HEIGHT, 200);
        JPanel auxTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        auxTitle.add(new JLabel("Transiciones"));
        
        rightPanel.add(auxTitle,BorderLayout.NORTH);
//        JPanel mainTransitionPanel = this.panelLv1.buildTransPanel();
        rightPanel.add(ta, BorderLayout.CENTER);
        
        
        this.sigma = new JTextField();
        this.sigma.setEnabled(false);
        
        this.inputWord = new JTextField();
                
        menuBar.add(file);
        
        JToolBar toolbar = new JToolBar();
        
        toolbar.setFloatable(false);
        this.startNode = new JButton();
        this.transNode = new JButton();
        this.endNode = new JButton();
        this.transition = new JButton();
        
        this.startNode.setIcon(imgbutton1);
        this.transNode.setIcon(imgbutton2);
        this.endNode.setIcon(imgbutton3);
        this.transition.setIcon(imgbutton4);
        
        this.verWord = new JButton("Verificar palabra");
        this.verWord.setBounds(120,10 ,150 ,20);
        
        this.startNode.setPreferredSize(new Dimension(64,64));
        this.transNode.setPreferredSize(new Dimension(64,64));
        this.endNode.setPreferredSize(new Dimension(64,64));
        this.transition.setPreferredSize(new Dimension(64,64));
        
        startNode.setMaximumSize(new Dimension(64,64));
        transNode.setMaximumSize(new Dimension(64,64));
        endNode.setMaximumSize(new Dimension(64,64));
        transition.setMaximumSize(new Dimension(64,64));

        
        toolbar.add(this.startNode);
        toolbar.add(this.transNode);
        toolbar.add(this.endNode);
        toolbar.add(this.transition);
        
        northPanel.add(toolbar,1,0);
        southPanel.add(this.verWord );
        
        frame = new JFrame();
        frame.setJMenuBar(menuBar);
        frame.setTitle("Automata plz");        
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(rightPanel , BorderLayout.LINE_END);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(new Dimension(900, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
     }

     public  void actionPerformed(ActionEvent e)
    {
        this.trans = this.trans+this.panelLv1.transAsString;
        System.out.println("trans: "+trans);
        if(e.getSource()==startNode)
        {
            this.panelLv1.setIsStartNode(true);
            startNode.setEnabled(false);
            
            transNode.setEnabled(true);
            endNode.setEnabled(true);
            transition.setEnabled(true);
        }
        
        if(e.getSource()==transNode)
        {
            this.panelLv1.resetBools();
            this.panelLv1.setIsStandNode(true);
        }
        
        if(e.getSource()==endNode)
        {
            this.panelLv1.resetBools();
            this.panelLv1.setIsEndNode(true);
        }
        
        if(e.getSource() == this.transition)
        {
                                this.refreshMatrix();

            this.panelLv1.resetBools();
            String name = JOptionPane.showInputDialog(this, "Ingrese etiqueta para transicion", "Transicion", JOptionPane.INFORMATION_MESSAGE);
            while(name.equals(""))
            {
                name = JOptionPane.showInputDialog(this, "Ingrese etiqueta para transicion", "Transicion", JOptionPane.INFORMATION_MESSAGE);

            }
            this.panelLv1.setLabelDiag(name);

//            System.out.println("despues del dialog");
//            this.labelCoected[0] = this.panelLv1.getState0();
//            this.labelCoected[1] = this.panelLv1.getState1();
//            this.labelCoected[2] = this.panelLv1.getState2();
            
//            this.printMainLabel();
            this.panelLv1.setIsTransition(true);
        }
       
        
        if(e.getSource()==verWord)
        {
            String input = JOptionPane.showInputDialog(this, "Ingrese palabra a verificar", "Palabra", JOptionPane.INFORMATION_MESSAGE);
            String in = this.panelLv1.getStart();
            ArrayList<String> states = this.panelLv1.getAutomatonStates();
            ArrayList<Transition> trans = this.panelLv1.getTransitions();
            ArrayList<String> endStates = this.panelLv1.getFinalStates();
            
            Automaton NDFA = new Automaton(in, states, trans, states, characters, input);
            
            Automaton DFA = NDFA.convertToDFA(in, states, trans, states, characters, input);
            
            for (int i = 0; i < endStates.size(); i++) {
                System.out.println(endStates.get(i));
            }
            
            boolean verify = DFA.verify();
            
            if(verify)
            {
                JOptionPane.showMessageDialog(this, "¡Palabra Aceptada!");
            }
            else
            {
                JOptionPane.showMessageDialog(this ,"¡Palabra Rechazada! :-(",
                "Verificar Palabra", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
          
    public JDrawPanel panelMaker()
     {
        JDrawPanel panel = new JDrawPanel();
        panel.setLayout(null);
        return panel;
         
     }
    
    
    
    public void printMainLabel()
    {
        System.out.println(this.labelCoected[0].getState());
        System.out.println(this.labelCoected[1].getState());
        System.out.println(this.labelCoected[2].getState());
    }
    
      @Override
    public void paint(Graphics g)
    {
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

    private void setRightPanel(JTextArea ta)
    {
//        System.out.println("remueve");
//        this.rightPanel.removeAll();
        System.out.println("rellena con lo de abajo: ");
        this.trans = this.panelLv1.transAsString;
        System.out.println(trans);
        System.out.println(ta.getText());
        this.ta.append(trans);
//        this.rightPanel.add(ta);
    }
    
    private void refreshMatrix()
    {
        this.setRightPanel(this.panelLv1.getTarnsitionTextArea());
    }

}