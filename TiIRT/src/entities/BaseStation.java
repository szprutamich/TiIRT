/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author maszter
 */
public class BaseStation extends Object{
    private int d;
    private int M;

    public BaseStation(){
        
    }
    
    public BaseStation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public void setX(int x){
        this.x = x;
    }
    
    @Override
    public void setY(int y){
        this.y = y;
    }
}
