/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * View.java
 *
 * Created on 2012-03-20, 22:06:59
 */
package views;

import controllers.HungarianAlgorithm;
import controllers.MainController;
import controllers.NumericDocument;
import entities.BaseStation;
import entities.User;
import input.InputRequirements;
import input.InputStations;
import input.InputUsers;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author maszter
 */
public class View extends JFrame {

    MainController controller = new MainController(new HungarianAlgorithm());
    ArrayList<ArrayList<User>> users = null;
    boolean interrupted = false;
    //GUI ELEMENTS
    private JButton btnAddStation;
    private JButton btnAddUser;
    private JButton btnReadRequirements;
    private JButton btnReadStations;
    private JButton btnRemoveStations;
    private JButton btnRemoveUsers;
    private JButton btnStartSimulation;
    private JCheckBox checkBox;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JLabel lab4;
    private JLabel lab5;
    private JLabel lab6;
    private JLabel lab7;
    private JLabel lab8;
    private Panel panel;
    private JButton btnReadUsers;
    private JButton btnStop;

    public View() {
        setLayout(null);
        initComponents();
        setLayout();
        repaint();
        setNumericDocumentsToTextFields();
        setBounds(340, 100, 695, 550);
        setResizable(false);
        controller.createMatrix();
        panel.setMainController(controller);
    }

    private void initComponents() {
        panel = new Panel();
        btnStartSimulation = new JButton();
        lab1 = new JLabel();
        lab2 = new JLabel();
        text1 = new JTextField();
        lab3 = new JLabel();
        text2 = new JTextField();
        lab4 = new JLabel();
        btnAddUser = new JButton();
        lab5 = new JLabel();
        text3 = new JTextField();
        lab6 = new JLabel();
        text4 = new JTextField();
        lab7 = new JLabel();
        text5 = new JTextField();
        lab8 = new JLabel();
        text6 = new JTextField();
        checkBox = new JCheckBox();
        btnRemoveUsers = new JButton();
        btnRemoveStations = new JButton();
        btnAddStation = new JButton();
        btnReadStations = new JButton();
        btnReadRequirements = new JButton();
        btnReadUsers = new JButton();
        btnStop = new JButton();

        add(panel);
        add(btnStartSimulation);
        add(lab1);
        add(lab2);
        add(text1);
        add(lab3);
        add(text2);
        add(lab4);
        add(btnAddUser);
        add(lab5);
        add(text3);
        add(lab6);
        add(text4);
        add(lab7);
        add(text5);
        add(lab8);
        add(text6);
        add(checkBox);
        add(btnRemoveUsers);
        add(btnRemoveStations);
        add(btnAddStation);
        add(btnReadStations);
        add(btnReadRequirements);
        add(btnReadUsers);
        add(btnStop);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //LABELS
        lab1.setText("Dodaj nowego użytkownika:");
        lab2.setText("X:");
        lab3.setText("Y:");
        lab4.setText("Dodaj nową stacje bazową:");
        lab5.setText("X:");
        lab6.setText("Y:");
        lab7.setText("Liczba zasobów:");
        lab8.setText("Zasięg:");

        //TEXTS
        text1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        text2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        text3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        text4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        text5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        text6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        //CHECKBOX
        checkBox.setText("siatka");
        checkBox.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        //BUTTONS
        btnStartSimulation.setText("Rozpocznij symulację");
        btnStartSimulation.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowConnectionsActionPerformed(evt);
            }
        });

        btnAddUser.setText("Dodaj");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnRemoveUsers.setText("Usuń użytkowników");
        btnRemoveUsers.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveUsersActionPerformed(evt);
            }
        });

        btnRemoveStations.setText("Usuń stacje bazowe");
        btnRemoveStations.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStationsActionPerformed(evt);
            }
        });

        btnAddStation.setText("Dodaj");
        btnAddStation.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStationActionPerformed(evt);
            }
        });

        btnReadStations.setText("Wczytaj stacje bazowe");
        btnReadStations.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnReadStationsActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnReadRequirements.setText("Wczytaj wymagania");
        btnReadRequirements.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadRequirementsActionPerformed(evt);
            }
        });

        btnReadUsers.setText("Wczytaj model");
        btnReadUsers.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadUsersActionPerformed(evt);
            }
        });

        btnStop.setText("Przerwij symulację");
        btnStop.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
    }

    private void btnShowConnectionsActionPerformed(java.awt.event.ActionEvent evt) {
        thread();
    }

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {
        if (!text1.getText().equals("") && !text2.getText().equals("")) {
            controller.createUser(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()));
            text1.setText("");
            text2.setText("");
            repaint();
        }
    }

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {
        panel.setDrawGrid();
        repaint();
    }

    private void btnRemoveUsersActionPerformed(java.awt.event.ActionEvent evt) {
        controller.getUsers().clear();
        repaint();
    }

    private void btnRemoveStationsActionPerformed(java.awt.event.ActionEvent evt) {
        BaseStation bs = controller.getStations().get(0);
        controller.getStations().clear();
        controller.getUserStations().clear();
        controller.getStations().add(bs);
        repaint();
    }

    private void btnAddStationActionPerformed(java.awt.event.ActionEvent evt) {
        if (!text3.getText().equals("") && !text4.getText().equals("") && !text5.getText().equals("") && !text6.getText().equals("")) {
            controller.createBaseStation(Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()), Integer.parseInt(text6.getText()), Integer.parseInt(text5.getText()));
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            repaint();
        }
    }

    private void btnReadStationsActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        controller.getUserStations().clear();
        controller.getStations().clear();
        InputStations input = new InputStations();
        controller.setStations(input.read(this));
        repaint();
    }

    private void setNumericDocumentsToTextFields() {
        text1.setDocument(new NumericDocument(2));
        text2.setDocument(new NumericDocument(2));
        text3.setDocument(new NumericDocument(2));
        text4.setDocument(new NumericDocument(2));
        text5.setDocument(new NumericDocument(2));
        text6.setDocument(new NumericDocument(2));
    }

    private void btnReadRequirementsActionPerformed(java.awt.event.ActionEvent evt) {
        InputRequirements input = new InputRequirements();
        try {
            input.read(users, this);
        } catch (Exception ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }

    private void btnReadUsersActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            InputUsers input = new InputUsers();
            users = input.read(this);
        } catch (Exception ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
        interrupted = true;
    }

    private void thread() {
        interrupted = false;
        Thread t = new Thread() {
            @Override
            public void run() {
                if (users != null) {
                    StringBuilder sb = new StringBuilder("InRange\tAssigned\tPartially\n");
                    for (int i = 0; i < users.size() && !interrupted; i++) {
                        controller.setUsers(users.get(i));
                        controller.createMatrix();
                        controller.getUserStations().clear();
                        controller.start();
                        panel.setDrawConnections(true);
                        repaint();
                        sb.append(panel.getState());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    saveResults(sb.toString());
                }
            }
        };
        t.start();
    }

    private void setLayout() {
        int maxWidth = 160;
        int height = 20;
        int margin = 10;

        panel.setBounds(margin + maxWidth + margin, margin, 501, 501);
        btnReadStations.setBounds(margin, margin, maxWidth, height);
        btnReadUsers.setBounds(margin, 40, maxWidth, height);
        btnReadRequirements.setBounds(margin, 70, maxWidth, height);
        btnStartSimulation.setBounds(margin, 100, maxWidth, height);
        btnStop.setBounds(margin, 130, maxWidth, height);
        btnRemoveUsers.setBounds(margin, 160, maxWidth, height);
        btnRemoveStations.setBounds(margin, 190, maxWidth, height);
        lab1.setBounds(margin, 220, maxWidth, height);
        lab2.setBounds(margin, 250, 10, height);
        text1.setBounds(30, 251, 50, height - 2);
        lab3.setBounds(100, 250, 10, height);
        text2.setBounds(120, 251, 50, height - 2);
        btnAddUser.setBounds(margin, 280, maxWidth, height);
        lab4.setBounds(margin, 310, maxWidth, height);
        lab5.setBounds(margin, 340, 10, height);
        text3.setBounds(30, 341, 50, height - 2);
        lab6.setBounds(100, 340, 10, height);
        text4.setBounds(120, 341, 50, height - 2);
        lab7.setBounds(margin, 370, 100, height);
        text5.setBounds(120, 371, 50, height - 2);
        lab8.setBounds(margin, 400, 100, height);
        text6.setBounds(120, 401, 50, height - 2);
        btnAddStation.setBounds(margin, 430, maxWidth, height);
        checkBox.setBounds(margin, 460, maxWidth, height);
    }
    
    private void saveResults(String results) {
        JFileChooser chooser = new JFileChooser(InputStations.FILE_PATH);
        int returnVal = chooser.showSaveDialog(panel);
        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(results);
                out.close();
            } 
            catch (IOException e) 
            { 
                JOptionPane.showMessageDialog(panel, e.getMessage(), "Problem przy zapisie wyników", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}