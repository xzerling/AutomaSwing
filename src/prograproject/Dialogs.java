/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class Dialogs extends JDialog implements ActionListener
{
    
    private JButton ok;
    private JButton cancel;
    
    private JTextField sigma;
    private JTextField word;
    
    public Dialogs()
    {
        super.setSize(new Dimension(300,150));
          
        createDialog();
        
        super.setVisible(true);
    }
    
    private void createDialog()
    {
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        this.ok = new JButton("Aceptar");
        this.cancel = new JButton("Cancelar");
        
        
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        
        this.ok.addActionListener(this);
        this.cancel.addActionListener(this);
        
        GridBagConstraints gbc = new GridBagConstraints();
 
        gbc.insets = new Insets(4, 4, 4, 4);
    
	gbc.gridx = 0;
	gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(new JLabel("Alfabeto : "), gbc);
        
        sigma = new JTextField();
        
        gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.weightx = 1;
        topPanel.add(sigma, gbc);
        
        gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = 0;
	topPanel.add(new JLabel("Palabra : "), gbc);
        
        word = new JTextField();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.weightx = 1;
	topPanel.add(word, gbc);
        
        super.add(topPanel);
        
        super.add(buttonPanel, BorderLayout.SOUTH);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
