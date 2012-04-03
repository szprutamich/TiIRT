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
    private int d; //zasięg lub moc nadawania
    private int N = 5; //zakłócenia (stała wartość)
    private int resources;

    public BaseStation(){
        
    }
    
    public BaseStation(int x, int y, int d, int resources){
        this.x = x;
        this.y = y;
        this.d = d;
        this.resources = resources;
    }

    public int getResources() {
        return resources;
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

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
    
    public int getN() {
        return N;
    }
}
