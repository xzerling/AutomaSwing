/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Zerling
 */
public class MouseHandler extends MouseInputAdapter
{    
     Marker ctrlQuad;
//     Marker ctrlCubic1;
//     Marker ctrlCubic2;
     JDrawPanel pane;
     Marker selected;

    public MouseHandler(JDrawPanel pane, Marker ctrlQuad) 
    {
        this.pane = pane;
        this.ctrlQuad = ctrlQuad;
    }
          
    public void setMouseHandler(JDrawPanel pane, Marker ctrlQuad)
    {
        this.pane = pane;
        this.ctrlQuad = ctrlQuad;
//        System.out.println("marker parametro: "+ctrlQuad);
//        System.out.println("marker clase: "+this.ctrlQuad);
//        this.ctrlCubic1 = ctrCubic1;
//        this.ctrlCubic2 = ctrCubic2;
        
    }
    
    public void mousePressed(MouseEvent e) 
    {
        System.out.println("selected on moushandler");
        System.out.println(this.selected);
        System.out.println("Marker on mousehandler: "+this.ctrlQuad);
        System.out.println("Marker center: "+this.ctrlQuad.getCenter());
      if (this.ctrlQuad.contains(e.getX(), e.getY()))
      {
        this.selected = this.ctrlQuad;
          System.out.println("selected: "+this.selected);
      }
    }
    public void mouseReleased(MouseEvent e) 
    {
      this.selected = null;
    }
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("selected on dragg: "+this.selected);
      if (this.selected != null) 
      {
        this.selected.setLocation(e.getX(), e.getY());
        pane.repaint(); 
          System.out.println("relocalizado y repaint");
      }
    }
}
