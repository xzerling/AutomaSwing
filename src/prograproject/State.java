/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.Point;
import java.awt.Rectangle;


/**
 *
 * @author Juan
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class State extends Rectangle{
    private Point point;
    private String state;
    private static final int DEFAULT_SIZE = 75;

    public State(Point point, String state) {
        this.point = point;
        this.state = state;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean intersects(State r) {
        
        int tw = DEFAULT_SIZE;
        int th = DEFAULT_SIZE;
        int rw = DEFAULT_SIZE;
        int rh = DEFAULT_SIZE;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.getPoint().x;
        int ty = this.getPoint().y;
        int rx = r.getPoint().x;
        int ry = r.getPoint().y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
    
}
