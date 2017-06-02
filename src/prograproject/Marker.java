/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

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
    static final double radius = 5;
    
    public Marker(Point2D.Double control) 
    {
      center = control; 
      circle = new Ellipse2D.Double(control.x - radius, control.y - radius, 2.0 * radius,
          2.0 * radius);
    }
    public void draw(Graphics2D g2D) 
    {
      g2D.draw(circle);
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
