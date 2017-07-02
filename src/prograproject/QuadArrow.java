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
    private String s1;
    private String s2;
    double phi = Math.toRadians(40);
    double barb = 20;

    public QuadArrow(double x1, double y1, double x2, double y2 , String label) 
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
        this.control = new Marker(new Point2D.Double((x2+y2)/2, y2/2), label);
    }
    
    public QuadArrow(State s1, State s2, String label) 
    {
        this.x1 = s1.getPoint().getX()+ 38;
        this.y1 = s1.getPoint().getY() + 38;
        this.x2 = s2.getPoint().getX() + 38;
        this.y2 = s2.getPoint().getY() + 38;
        this.label = label;
        this.s1 = s1.getState();
        this.s2 = s2.getState();
        this.control = new Marker(new Point2D.Double((x2+y2)/2, y2/2), label);
    }
    
    public void make()
    {
        this.control = new Marker(new Point2D.Double((x2+y2)/2, y2/2), label);
        this.arrow = new QuadCurve2D.Double(this.x1, this.y1, control.getCenter().getX(), control.getCenter().getY(), this.x2, this.y2);                 
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

    public double getX1() {
        return this.x1;
    }

    public double getY1() {
        return this.y1;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY2() {
        return this.y2;
    }

    public String getLabel() {
        return label;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }
    
    public void setX2(double x) 
    {
        this.x2 = x;
    }

    public void setY2(double y) 
    {
        this.y2 = y;
    }
    public void setX1(double x) 
    {
        this.x1 = x;
    }

    public void setY1(double y) 
    {
        this.y1 = y;
    }
    
    private void drawArrowHead(Graphics2D g2, Color color)
    {
        g2.setPaint(color);
        double dy = this.y2 - this.control.center.y;
        double dx = this.x2 - this.control.center.x;
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
