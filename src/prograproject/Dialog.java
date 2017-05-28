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
public class Dialog extends JDialog implements ActionListener
{
    private JButton ok;
    private JButton cancel;
    
    private JTextField input;
    private JLabel text;
    private JLabel instructions;
    
    public Dialog()
    {
        super.setSize(new Dimension(300,150));
        super.setLocation(300,300);
        
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        this.ok = new JButton("Aceptar");
        this.cancel = new JButton("Cancelar");
        
        buttonsPanel.add(ok);
        buttonsPanel.add(cancel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        
        this.instructions = new JLabel("Instrucciones aqui");
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(this.instructions , gbc);
        
        this.text = new JLabel("Texto: ");
        
        gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.fill = GridBagConstraints.NONE;
	gbc.weightx = 0;
        topPanel.add(this.text, gbc);
        
        this.input = new JTextField();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.weightx = 1;
	topPanel.add(this.input, gbc);
        
        super.add(topPanel);
        super.add(buttonsPanel, BorderLayout.SOUTH);
        
        super.setVisible(true);
        
        this.ok.addActionListener(this);
        this.cancel.addActionListener(this);
        
    }
    
    public void setTextLabel(String s)
    {
        this.text.setText(s);
    }
    
    public void setInstructions(String s)
    {
        this.instructions.setText(s);
    }

    public String getInputText()
    {
        return this.input.getText();
    }
    public JTextField getInput()
    {
        return input;
    }

    public void setInput(JTextField input) 
    {
        this.input = input;
    }
    
    public void returnInput(JMainPanel mp)
    {
        System.out.println("returninput");
        mp.labelDiag.setInput(input);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String comm = e.getActionCommand();
        if(comm.equals("Aceptar") )
        {
//            this.input.setText(comm);
            this.dispose();

            System.out.println("actionperformDialog");
            System.out.println(this.input.getText());
            this.setVisible(false);
            
//            saveDrawPanel()

        }
    }
}
