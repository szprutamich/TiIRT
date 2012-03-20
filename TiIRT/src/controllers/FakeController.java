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
        users.add(new User(0, 0));
        users.add(new User(20, 10));
        users.add(new User(50, 99));
        stations.add(new BaseStation(50, 20));
        stations.add(new BaseStation(50, 80));
        
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
}
