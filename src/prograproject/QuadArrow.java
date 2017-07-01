/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author Zerling
 */
public class QuadArrow
{
    private QuadCurve2D.Double arrow;
    private Marker control;
    
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    
    private String label;
    double phi = Math.toRadians(40);
    double barb = 20;

    public QuadArrow(double x1, double y1, double x2, double y2 , String label) 
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
    }
    
    public QuadArrow(State s1, State s2, String label) 
    {
        this.x1 = s1.getX();
        this.y1 = s1.getY();
        this.x2 = s2.getX();
        this.y2 = s2.getY();
        this.label = label;
    }
    
    public void make()
    {
        this.control = new Marker(new Point2D.Double((x2+y2)/2, y2/2), label);
        this.arrow = new QuadCurve2D.Double(x1, y1, x2, y2, x2, y2);
        
    }
    
    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLUE);
        g.draw(arrow);
        g.setColor(Color.RED);
        this.control.draw(g);
        this.drawArrowHead(g, Color.ORANGE);
        g.setColor(Color.BLACK);
    }
    
    public Marker getControl()
    {
        return this.control;
    }
    
    public QuadCurve2D.Double getLine()
    {
        return this.arrow;
    }

    void setX2(double x) 
    {
        this.x2 = x;
    }

    void setY2(double y) 
    {
        this.y2 = y;
    }
    void setX1(double x) 
    {
        this.x1 = x;
    }

    void setY1(double y) 
    {
        this.y1 = y;
    }
    
    private void drawArrowHead(Graphics2D g2, Color color)
    {
        g2.setPaint(color);
        double dy = y2 - y1;
        double dx = x2 - x1;
        double theta = Math.atan2(dy, dx);
//        System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for(int j = 0; j < 2; j++)
        {
            x = x2 - barb * Math.cos(rho);
            y = y2 - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(x2, y2, x, y));
            rho = theta - phi;
        }
    }
}
