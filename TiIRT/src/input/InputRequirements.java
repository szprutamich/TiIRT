package input;

import entities.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author maszter
 */
public class InputRequirements {
    public static final String FILE_PATH = "/TiIRT/TiIRT";
    
    public ArrayList<ArrayList<User>> read(ArrayList<ArrayList<User>> usersInTime, JFrame frame) throws Exception{
        if(usersInTime == null){
            JOptionPane.showMessageDialog(null, "Wybierz najpierw model");
        }
        else{
            JFileChooser chooser = new JFileChooser(FILE_PATH);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(frame);
            String path;

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getPath();
                File file = new File(path);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int nr = -1;
                while((line = reader.readLine())!= null && !line.equals("EOF")){
                    StringTokenizer token = new StringTokenizer(line);
                    String type = token.nextToken();
                    if(type.startsWith("USERS:")){
                        int usersCount = Integer.parseInt(token.nextToken());
                        if(usersCount != usersInTime.get(0).size())
                            throw new Exception("Invalid users size");
                    }
                    else if(type.startsWith("ITERATIONS:")){
                        int iterationsCount = Integer.parseInt(token.nextToken());
                        if(iterationsCount != usersInTime.size())
                            throw new Exception("Invalid iterations count");
                    }
                    else if(type.startsWith("ITERATION:")){
                        nr = Integer.parseInt(token.nextToken());
                    }
                    else{
                        int i = Integer.parseInt(type);
                        int r = Integer.parseInt(token.nextToken());
                        usersInTime.get(nr-1).get(i-1).setResources(r);
                    }
                }           
            }
        }
        return usersInTime;
    }
}