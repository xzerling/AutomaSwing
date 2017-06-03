/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Zerling
 */
public class Marker 
{
    Ellipse2D.Double circle; 
    Point2D.Double center; 
    String label;
    static final double radius = 12;
    
    public Marker(Point2D.Double control, String label) 
    {
      center = control; 
      circle = new Ellipse2D.Double(control.x - radius, control.y - radius, 2.0 * radius,
          2.0 * radius);
      this.label = label;
    }
    public void draw(Graphics2D g2D) 
    {
      g2D.fill(circle);
      g2D.setColor(Color.WHITE);
      g2D.drawString(label, (float)(center.x-2), (float)(center.y+4));
    }

    Point2D.Double getCenter() 
    {
      return center;
    }
    public boolean contains(double x, double y) 
    {
      return circle.contains(x, y);
    }
    public void setLocation(double x, double y)
    {
      center.x = x; 
      center.y = y; 
      circle.x = x - radius; 
      circle.y = y - radius; 
    }
    
}
