package downloaded;

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
public class MainController {

    double[][] copyMatrix;
    double[][] matrix;
    int countLines;
    ArrayList<Integer> coveredRows = new ArrayList<Integer>();
    ArrayList<Integer> coveredCols = new ArrayList<Integer>();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<BaseStation> stations = new ArrayList<BaseStation>();

    public void test() {
        users.add(new User(20, 50));
        users.add(new User(40, 40));
        users.add(new User(35, 25));
        users.add(new User(40, 30));
        users.add(new User(35, 30));
        users.add(new User(40, 35));
        users.add(new User(45, 25));
        stations.add(new BaseStation(30, 25, 30));
        stations.add(new BaseStation(25, 30, 30));
        stations.add(new BaseStation(20, 40, 30));
        stations.add(new BaseStation(50, 60, 30));
        //stations.add(new BaseStation(90, 80, 30));
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

    public void drawMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%3d", mat[i][j]);
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
        int cols = stations.size();
        int max = cols > rows ? cols : rows;

        matrix = new double[rows][cols];
        copyMatrix = new double[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                int val = computeSINR(users.get(i % rows), stations.get(j % cols));
                if (i < rows && j < cols) {
                    matrix[i][j] = val;
                }
                copyMatrix[i][j] = val;
            }
        }        
    }
    
    public double[][] getCopyMatrix(){
        return copyMatrix;
    }
    
    public double[][] getMatrix(){
        return matrix;
    }
}
