package controllers;


import entities.BaseStation;
import entities.User;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maszter
 */
public class FakeController {
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
    
    public void test(){
        users.add(new User(20, 50));
        users.add(new User(40, 80));
        users.add(new User(70, 30));
        users.add(new User(20, 20));
        users.add(new User(10, 60));
        users.add(new User(30, 80));
        users.add(new User(70, 40));
        users.add(new User(90, 10));
        users.add(new User(70, 70));
        stations.add(new BaseStation(30, 25, 30));
        stations.add(new BaseStation(20, 80, 30));
        stations.add(new BaseStation(84, 37, 30));
        stations.add(new BaseStation(80, 75, 30));
    }

    public ArrayList<BaseStation> getStations() {
        return stations;
    }

    public void setStations(ArrayList<BaseStation> stations) {
        this.stations = stations;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public int computeSINR(User u, BaseStation b){
        double PL = u.computeDistance(b);
        double P = b.getD()/PL;
        P = P >= 1 ? P*50 : 0;
        int I = 0;
        for(BaseStation s : getStations()){
            if(s.equals(b)){
                continue;
            }
            I += s.getD()/u.computeDistance(s);
        }
        return (int) (P / (I + b.getN()));
    }
    
    public void testSINR(){
        for(User u : getUsers()){
            for(BaseStation s : getStations()){
                System.out.print(computeSINR(u, s) + " ");
            }
            System.out.println("");
        }
    }
}
