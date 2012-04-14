/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Object;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import controllers.MainController;
import entities.BaseStation;
import entities.User;
import java.util.HashMap;

/**
 *
 * @author maszter
 */
public class Panel extends JPanel {

    int side = 100;
    int height = 500;
    int width = 500;
    private MainController controller;
    private boolean drawConnections = false;
    private boolean grid = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width, height);
        if (controller != null) {
            drawObjects(controller, g);
        }
        if (drawConnections) {
            drawConnections(controller, g);
        }
    }

    private void drawObject(Object o, Color c, Graphics g) {
        g.setColor(c);
        g.fillRect(o.getX() * width / side, o.getY() * height / side, width / side, height / side);
    }

    private void drawRange(BaseStation b, Color c, Graphics g) {
        g.setColor(c);
        int range = b.getD() * width / side;
        g.drawOval(b.getX() * width / side - range, b.getY() * height / side - range, range * 2, range * 2);        
    }

    private void drawObjects(MainController controller, Graphics g) {
        drawGrid(g, grid);
        for (Object s : controller.getStations()) {
            drawObject(s, new Color(70, 128, 224), g);
            drawRange((BaseStation) s, new Color(70, 128, 224), g);
            g.drawString(String.valueOf(s.getResourcesOrRequirements()), s.getX() * width / side, s.getY() * height / side + 20);
        }
        for (Object u : controller.getUsers()) {
            drawObject(u, new Color(0, 0, 0), g);
            g.drawString(String.valueOf(u.getResourcesOrRequirements()), u.getX() * width / side, u.getY() * height / side + 20);
        }
    }

    private void drawGrid(Graphics g, boolean draw) {
        if (draw) {
            for (int i = 0; i < side; i = i + 10) {
                g.drawLine(0, (i + 10) * height / side, width, (i + 10) * height / side);
                g.drawLine((i + 10) * width / side, 0, (i + 10) * width / side, height);
            }
        }
    }

    public void setDrawGrid() {
        grid = !grid;
    }

    private void drawConnection(Graphics g, BaseStation bs, User u, Color c) {
        g.setColor(c);
        g.drawLine(bs.getX() * width / side + 2, bs.getY() * height / side + 2, u.getX() * width / side + 2, u.getY() * height / side + 2);
    }

    public void setMainController(MainController controller) {
        this.controller = controller;
    }

    public void setDrawConnections(boolean b) {
        drawConnections = b;
    }

    private void drawConnections(MainController controller, Graphics g) {
        HashMap<Integer, Integer> map = controller.getUserStations();
        for (int i = 0; i < controller.getUsers().size(); i++) {
            Integer val = map.get(i);
            if (val != null) {
                drawConnection(g, controller.getStations().get(map.get(i)), controller.getUsers().get(i), new Color(235, 92, 92));
            }
        }
    }
}
