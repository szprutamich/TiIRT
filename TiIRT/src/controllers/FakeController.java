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
    int[][] matrix;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
   
    public void test(){
        users.add(new User(20, 50));
        users.add(new User(40, 40));
        users.add(new User(35, 25));
        users.add(new User(40, 30));
        users.add(new User(35, 30));
        users.add(new User(40, 35));
        users.add(new User(45, 25));
        //users.add(new User(30, 80));
//        users.add(new User(70, 40));
//        users.add(new User(90, 10));
//        users.add(new User(70, 70));
        stations.add(new BaseStation(30, 25, 30));
        stations.add(new BaseStation(25, 30, 30));
        stations.add(new BaseStation(20, 40, 30));
//        stations.add(new BaseStation(84, 37, 30));
//        stations.add(new BaseStation(80, 75, 30));
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
    
    public void drawTable(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                 System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println("");
        }
        System.out.println("koniec");
    }
            
    
    public void testSINR(){
        
               
        int rows = users.size();
        int cols = stations.size();
        int max = cols > rows ? cols : rows;
        
        matrix  = new int[max][max];
        
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                matrix[i][j] = computeSINR(users.get(i % rows), stations.get(j % cols));
            }
        }
                
        drawTable();
        removeMinRows();
        drawTable();
        removeMinCols();
        drawTable();
        //System.out.println(findMinCol(1));    
       // System.out.println(findMinRow(1));   
        
    }
    
    public int findMinRow(int num){
        int min = matrix[num][0];
        
        for(int i =0;i<matrix.length;i++){
            if(matrix[num][i]<min)
                min=matrix[num][i];
        }
        
        return min;
    }
    
    public int findMinCol(int num){
        int min = matrix[0][num];
        
        for(int i =0;i<matrix.length;i++){
            if(matrix[i][num]<min)
                min=matrix[i][num];
        }
        
        return min;
    }
    
    public void removeMinRows(){
        for(int i = 0; i < matrix.length; i++){
            int min_val = findMinRow(i);
            for(int j = 0; j < matrix.length; j++){
                matrix[i][j] -= min_val;
            }
        }       
    }
    
    public void removeMinCols(){
        for(int i = 0; i < matrix.length; i++){
            int min_val = findMinCol(i);
            for(int j = 0; j < matrix.length; j++){
                matrix[j][i] -= min_val;
            }
        }       
    }
}
