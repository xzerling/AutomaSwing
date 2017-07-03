/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Nicolas
 */
public class VerificationDialog extends JDialog
{
    
    private JTextArea textArea;
    
    public VerificationDialog()
    {
        super.setSize(new Dimension(300,300));
        super.setTitle("Transiciones: Paso a paso");
        super.setLayout(new BorderLayout());
        
        this.textArea = new JTextArea();        
        super.add(this.textArea , BorderLayout.CENTER);
        
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //super.setVisible(true);
    }
    
    public void refreshTextArea(String s) 
    {
        //TimeUnit.SECONDS.sleep(1);
        this.textArea.setText(s);
    }
}
