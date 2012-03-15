
import entities.BaseStation;
import entities.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maszter
 */
public class Test {
    public static void main(String[] args){       
        User u1 = new User(0, 0);
        User u2 = new User(1, 0);
        User u3 = new User(0, 1);
        BaseStation s1 = new BaseStation(4, 0);
        BaseStation s2 = new BaseStation(0, 10);
        
        System.out.println(u1.computeDistance(s1));
        System.out.println(u1.computeDistance(s2));
        System.out.println(u2.computeDistance(s1));
        System.out.println(u2.computeDistance(s2));
        System.out.println(u3.computeDistance(s1));
        System.out.println(u3.computeDistance(s2));
    }
}
