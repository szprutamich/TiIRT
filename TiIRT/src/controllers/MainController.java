package controllers;

import entities.BaseStation;
import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maszter
 */
public class MainController {

    double[][] matrix;
    int countLines;
    ArrayList<Integer> coveredRows = new ArrayList<Integer>();
    ArrayList<Integer> coveredCols = new ArrayList<Integer>();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
    HungarianAlgorithm algorithm;
    HashMap<Integer, Integer> userStations;

    public MainController(HungarianAlgorithm algorithm) {
        stations.add(new BaseStation(-100, -100, 0, 100));
        userStations = new HashMap<Integer, Integer>();
        this.algorithm = algorithm;
    }

    public HashMap<Integer, Integer> getUserStations() {
        return userStations;
    }

    public void createUser(int X, int Y) {
        users.add(new User(X, Y));
    }

    public void createBaseStation(int X, int Y, int Range, int Res) {
        stations.add(new BaseStation(X, Y, Range, Res));
    }

    public void createRandomUsers() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            users.add(new User(r.nextInt(100), r.nextInt(100)));
        }
    }   

    public void createRandomBaseStations() {
        stations.add(new BaseStation(-100, -100, 0, 100));
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            stations.add(new BaseStation(r.nextInt(100), r.nextInt(100), r.nextInt(10)+15, r.nextInt(9)+1));
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

    public int computeSINR(User u, BaseStation b) {
        double PL = u.computeDistance(b);
        double P = b.getD() / PL;
        P = P >= 1 ? P * 50 : 0;
        int I = 0;
        for (BaseStation s : getStations()) {
            if (s.equals(b)) {
                continue;
            }
            I += s.getD() / u.computeDistance(s);
        }
        return (int) (P / (I + b.getN()));
    }

    public void drawMatrix(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%3.0f", mat[i][j]);
            }
            System.out.println("");
        }
        for (int i = 0; i < mat.length; i++) {
            System.out.print("---");
        }
        System.out.println("");
    }

    public void createMatrix() {
        int rows = users.size();
        int cols = countColums();
        matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols;) {
                BaseStation bs = stations.get(count);
                int res = bs.getResourcesOrRequirements();
                while (res > 0) {
                    int val = computeSINR(users.get(i % rows), bs);
                    matrix[i][j] = val;
                    res--;
                    j++;
                }
                count++;
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    private int countColums() {
        ArrayList<BaseStation> list = getStations();
        int count = 0;
        for (BaseStation bs : list) {
            count += bs.getResourcesOrRequirements();
        }
        return count;
    }

    public void start() {
        algorithm.start(matrix, this);
    }

    public int getNumberOfStation(int resourceNumber) {
        int result;
        for (result = 0; result < stations.size() && resourceNumber >= 0; ++result) {
            int res = stations.get(result).getResourcesOrRequirements();
            resourceNumber -= res;
        }
        return result - 1;
    }
}
