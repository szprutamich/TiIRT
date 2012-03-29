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
public class Controller {

    int[][] copyMatrix;
    int[][] matrix;
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
            for (int j = 0; j < mat[0].length; j++) {
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

        matrix = new int[rows][cols];
        copyMatrix = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                int val = computeSINR(users.get(i % rows), stations.get(j % cols));
                if(i < rows && j < cols){
                    matrix[i][j] = val;
                }
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
        step3();
        drawMatrix(copyMatrix);
        while(coveredCols.size()+coveredRows.size() != matrix.length){        
            step4();
            drawMatrix(copyMatrix);
            step5();
            drawMatrix(copyMatrix);
            step6();
            drawMatrix(copyMatrix);
        }       
        step7();
        System.out.println("");
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
    
    private boolean allZerosAreCovered(){
        for(int i = 0; i < copyMatrix.length; i++){
            for(int j = 0; j < copyMatrix.length; j++){
                if(copyMatrix[i][j] == 0 && !coveredRows.contains(i) && !coveredCols.contains(j)){
                    return false;
                }
            }
        }
        return true;
    }
    
    private int minNotCoveredValue(){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < copyMatrix.length; i++){
            for(int j = 0; j < copyMatrix.length; j++){
                if(copyMatrix[i][j] < min && !coveredRows.contains(i) && !coveredCols.contains(j)){
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
        HashMap<Integer, Integer> mapOfZerosInCol = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> mapOfZerosInRow = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < copyMatrix.length; i++) {
            int count = 0;
            for (int j = 0; j < copyMatrix.length; j++) {
                if (copyMatrix[i][j] == 0) {
                    count++;
                }
            }
            mapOfZerosInRow.put(i, count);
        }
        for (int j = 0; j < copyMatrix.length; j++) {
            int count = 0;
            for (int i = 0; i < copyMatrix.length; i++) {
                if (copyMatrix[i][j] == 0) {
                    count++;
                }
            }
            mapOfZerosInCol.put(j, count);
        }
        while (!allZerosAreCovered()) {
            int maxR = -1;
            int maxC = -1;
            int r = -1;
            int c = -1;
            for(int i = 0; i < mapOfZerosInRow.size(); i++){
                int temp = mapOfZerosInRow.get(i);
                if(temp > maxR){
                    maxR = temp;
                    r = i;
                }
            }
            for(int i = 0; i < mapOfZerosInCol.size(); i++){
                int temp = mapOfZerosInCol.get(i);
                if(temp > maxC){
                    maxC = temp;
                    c = i;
                }
            }
            if(maxR > maxC && !coveredRows.contains(r)){
                coveredRows.add(r);
                mapOfZerosInRow.put(r, -1);
            }
            else if(maxR <= maxC && !coveredCols.contains(c)){
                coveredCols.add(c);
                mapOfZerosInCol.put(c, -1);
            }
        }
    }
    
    private void step4(){
        int val = minNotCoveredValue();
        for(int i = 0; i < copyMatrix.length; i++){
            for(int j = 0; j < copyMatrix.length; j++){
                if(coveredCols.contains(j)){
                    copyMatrix[i][j] += val;
                }
                if(coveredRows.contains(i)){
                    copyMatrix[i][j] += val;
                }
            }
        }
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
        coveredRows = new ArrayList<Integer>();
        coveredCols = new ArrayList<Integer>();
        step3();
    }    
    
    // choose 0s
    private ArrayList<int[]> step7() {
        ArrayList<int[]> list = new ArrayList<int[]>();
        boolean[] colCovered = new boolean[copyMatrix.length];
        boolean[] rowCovered = new boolean[copyMatrix.length];

        ArrayList<int[]> pkts = new ArrayList<int[]>();
                
        for (int i = 0; i < copyMatrix.length; i++) {
            colCovered[i] = false;
            rowCovered[i] = false; 
        }
        // search all single 0 in columns and rows and mark it as covered
        do {
            boolean added = false;            
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
                    added = true;
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
                    added = true;
                }
            }
            //cannot find single 0 in column or row so random one
            if(!added){
                Random rand = new Random();
                int val;
                int r;
                int c;
                do{
                    int len = copyMatrix.length;
                    r = rand.nextInt(len);
                    c = rand.nextInt(len);
                    val = copyMatrix[r][c];
                }
                while(val!=0);
                int[] pkt = new int[2];
                pkt[0] = r;
                pkt[1] = c;
                colCovered[c] = true;
                rowCovered[r] = true;
                list.add(pkt);
            }
        }
        while(list.size() != copyMatrix.length);
        return list;
    }   
}
