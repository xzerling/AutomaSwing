/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prograproject;

import java.awt.Point;

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
public class State {
    private Point point;
    private String state;

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
    
    
}
