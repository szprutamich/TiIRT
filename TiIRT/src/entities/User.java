/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author maszter
 */
public class User {
    private int x;
    private int y;
    
    public User(){
        
    }
    
    public User(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public double computeDistance(BaseStation station){
        return Math.sqrt(Math.pow(this.x - station.getX(), 2) + Math.pow(this.y-station.getY(), 2));
    }
}
