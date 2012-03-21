package controllers;


import entities.BaseStation;
import entities.User;
import java.util.ArrayList;
import java.util.Random;

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
        Random rand = new Random();
        for(int i = 0; i< 30; i++){
            users.add(new User(rand.nextInt(100), rand.nextInt(100)));
        }
        for(int i = 0; i< 10; i++){
            stations.add(new BaseStation(rand.nextInt(100), rand.nextInt(100), rand.nextInt(40)+20));
        }
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
