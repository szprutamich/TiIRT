/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author maszter
 */
public class User extends Object{
    
    int requirements;
    int assigned;
    
    public User(){
        
    }
    
    public User(int x, int y){
        this.x = x;
        this.y = y;
        this.requirements = 4;
        this.assigned = 0;
    }
    
    public User(int x, int y, int requirements){
        this.x = x;
        this.y = y;
        this.requirements = requirements;
        this.assigned = 0;
    }
    
    @Override
    public void setX(int x){
        this.x = x;
    }
    
    @Override
    public void setY(int y){
        this.y = y;
    }
    
    @Override
    public int getX(){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }
    
    public double computeDistance(BaseStation station){
        return Math.sqrt(Math.pow(this.x - station.getX(), 2) + Math.pow(this.y-station.getY(), 2));
    }

    @Override
    public int getResourcesOrRequirements() {
        return requirements;
    }
    
    public void setResources(int requirements){
        this.requirements = requirements;
    }
    
    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }
    
    public int getAssigned() {
        return assigned;
    }
}
