/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Object;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import controllers.FakeController;
import entities.BaseStation;

/**
 *
 * @author maszter
 */
public class Panel extends JPanel{
    int side = 100;
    int height = 500;
    int width = 500;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width, height);
//        for(int i = 0; i < side; i++){            
//            g.drawLine(0, (i+1)*height/side, width, (i+1)*height/side);
//            g.drawLine((i+1)*width/side, 0, (i+1)*width/side, height);
//        }
        test(g);
    }
    
    public void drawObject(Object o, Color c, Graphics g){
        g.setColor(c);
        g.fillRect(o.getX()*width/side, o.getY()*height/side, width/side, height/side);
    }
    
    public void drawRange(BaseStation b, Color c, Graphics g){
        g.setColor(c);
        int range = b.getD()*width/side;
        g.drawOval(b.getX()*width/side-range/2, b.getY()*height/side-range/2, range, range);
    }
    
    // for Test
    public void test(Graphics g){
        FakeController test = new FakeController();
        test.test();
        for(Object s : test.getStations()){
            drawObject(s, Color.BLUE, g);
            drawRange((BaseStation)s, Color.BLUE, g);
        }
        for(Object u : test.getUsers()){
            drawObject(u, Color.YELLOW, g);
        }
    }
}
