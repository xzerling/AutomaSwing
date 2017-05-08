package prograproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zerling
 */
public class JMainWindow extends JFrame implements ActionListener
{


//    private final JDevilCrushPanel gamePanel;
    private JMainPanel mainPanel;
    private JMenuBar mb;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem mi1;
    private JMenuItem mi2;
    
    public JMainWindow()
    {
        super("Super Devil Crush: La venganza");
        super.setSize(800, 600);
//        this.setLayout(new BorderLayout());
        
        this.mb = new JMenuBar();
//        this.gamePanel = new JDevilCrushPanel();
        this.mainPanel = new JMainPanel();
        
                this.getContentPane().add(mainPanel);
        
//        this.add(button = new JButton("OESTE"), BorderLayou.EAST);
//        this.add(button = new JButton("OESTE"), BorderLayout.WEST);
//        this.add(button = new JButton("NORTE"), BorderLayout.NORTH);
//        this.add(button = new JButton("SUR"), BorderLayout.SOUTH);
        
//        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}