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
public class Controller {

    int[][] copyMatrix;
    int[][] matrix;
    int countLines;
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

    private void drawMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.printf("%3d", mat[i][j]);
            }
            System.out.println("");
        }
        for(int i = 0; i < mat.length; i++){
            System.out.print("---");
        }
        System.out.println("");
    }

    public void createMatrix() {
        int rows = users.size();
        int cols = stations.size();
        int max = cols > rows ? cols : rows;

        matrix = new int[max][max];
        copyMatrix = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                int val = computeSINR(users.get(i % rows), stations.get(j % cols));
                matrix[i][j] = val;
                copyMatrix[i][j] = val;
            }
        }

        //algorithm
        drawMatrix(matrix);
        drawMatrix(copyMatrix);
        step1();
        drawMatrix(copyMatrix);
        step2();
        drawMatrix(copyMatrix);
//        step3();
//        drawMatrix(copyMatrix);
//        while(countLines != matrix.length){        
//            step4();
//            drawMatrix(copyMatrix);
//            step5();
//            drawMatrix(copyMatrix);
//            step6();
//            drawMatrix(copyMatrix);
//        }       
//        step7();
    }

    private int findMinInRow(int num) {
        int min = copyMatrix[num][0];
        for (int i = 0; i < copyMatrix.length; i++) {
            if (copyMatrix[num][i] < min) {
                min = copyMatrix[num][i];
            }
        }
        return min;
    }

    private int findMinInCol(int num) {
        int min = copyMatrix[0][num];
        for (int i = 0; i < copyMatrix.length; i++) {
            if (copyMatrix[i][num] < min) {
                min = copyMatrix[i][num];
            }
        }
        return min;
    }

    private int findMinInMatrix() {
        int min = copyMatrix[0][0];
        for (int i = 0; i < copyMatrix.length; i++) {
            for (int j = 0; j < copyMatrix.length; j++) {
                if (copyMatrix[i][j] < min) {
                    min = copyMatrix[i][j];
                }
            }
        }
        return min;
    }
    
    //remove min in rows
    private void step1() {
        for (int i = 0; i < copyMatrix.length; i++) {
            int min_val = findMinInRow(i);
            for (int j = 0; j < copyMatrix.length; j++) {
                copyMatrix[i][j] -= min_val;
            }
        }
    }

    //remove min in cols
    private void step2() {
        for (int i = 0; i < copyMatrix.length; i++) {
            int min_val = findMinInCol(i);
            for (int j = 0; j < copyMatrix.length; j++) {
                copyMatrix[j][i] -= min_val;
            }
        }
    }

    private void step3(){
        
    }
    
    private void step4(){
        
    }
    
    private void step5(){
        int min_val = findMinInMatrix();
        for (int i = 0; i < copyMatrix.length; i++) {
            for (int j = 0; j < copyMatrix.length; j++) {
                copyMatrix[j][i] -= min_val;
            }
        }
    }
    
    private void step6(){
        step3();
    }    
    
    // choose 0s
    private ArrayList<int[]> step7() {
        ArrayList<int[]> list = new ArrayList<int[]>();
        boolean[] colCovered = new boolean[copyMatrix.length];
        boolean[] rowCovered = new boolean[copyMatrix.length];        
        for (int i = 0; i < copyMatrix.length; i++) {
            colCovered[i] = false;
            rowCovered[i] = false; 
        }
        // search all single 0 in columns and rows and mark it as covered
        do {
            for (int i = 0; i < copyMatrix.length; i++) {
                int count = 0;
                int j;
                int[] pkt = new int[2];
                for (j = 0; j < copyMatrix.length; j++) {
                    if (copyMatrix[i][j] == 0 && !colCovered[j] && !rowCovered[i]) {
                        count++;
                        pkt = new int[2];
                        pkt[0] = i;
                        pkt[1] = j;
                    }
                }
                if (count == 1) {
                    rowCovered[pkt[0]] = true;
                    colCovered[pkt[1]] = true;
                    list.add(pkt);
                }
            }
            for (int i = 0; i < copyMatrix.length; i++) {
                int count = 0;
                int j;
                int[] pkt = new int[2];
                for (j = 0; j < copyMatrix.length; j++) {
                    if (copyMatrix[j][i] == 0 && !colCovered[i] && !rowCovered[j]) {
                        count++;
                        pkt = new int[2];
                        pkt[0] = i;
                        pkt[1] = j;
                    }
                }
                if (count == 1) {
                    colCovered[pkt[1]] = true;
                    rowCovered[pkt[0]] = true;
                    list.add(pkt);
                }
            }
        }
        while(list.size() != copyMatrix.length);
        return list;
    }
}
