/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 *
 * @author Zerling
 */
public class ArrowHead 
{
    double dy;
    double dx;
    double theta;
    private Line2D.Double head;
    double phi = Math.toRadians(40);
    double barb = 20;
//        System.out.println("theta = " + Math.toDegrees(theta));
    double x, y;
    double rho;

    public ArrowHead(Point tip, Point tail)
    {
        this.dy = tip.y - tail.y;
        this.dx = tip.x - tail.x;
        this.theta = Math.atan2(dy, dx);
        this.rho = theta + phi;

        for(int j = 0; j < 2; j++)
        {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);

            this.head = new Line2D.Double(tip.x, tip.y, x, y);

            if(theta < 0)
            {
            this.head = new Line2D.Double(tip.x+30, tip.y+30, x+30, y+30);
                        rho = theta - phi;

            }
            rho = theta - phi;
        }
    }
        
    public void Paint(Graphics2D g2, Color color)
    {
        g2.setPaint(color);
        g2.draw(this.head);
    }
}
