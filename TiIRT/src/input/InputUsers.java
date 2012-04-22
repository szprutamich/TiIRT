package input;

import entities.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author maszter
 */
public class InputUsers {
    public static final String FILE_PATH = "E:/tiirt/TiIRT";
    
    public ArrayList<ArrayList<User>> read(JFrame frame) throws Exception{
        JFileChooser chooser = new JFileChooser(FILE_PATH);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        String path;
        ArrayList<ArrayList<User>> usersInTime = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int usersCount = -1;
            int nr = -1;
            int iterationsCount = -1;

            while((line = reader.readLine())!= null && !line.equals("EOF")){
                StringTokenizer token = new StringTokenizer(line);
                String type = token.nextToken();
                if(type.startsWith("USERS:")){
                    usersCount = Integer.parseInt(token.nextToken());
                }
                else if(type.startsWith("ITERATIONS:")){
                    iterationsCount = Integer.parseInt(token.nextToken());
                    usersInTime = new ArrayList(iterationsCount);
                    for(int i = 0; i< iterationsCount; i++){
                        usersInTime.add(new ArrayList<User>(usersCount));
                    }
                }
                else if(type.startsWith("ITERATION:")){
                    nr = Integer.parseInt(token.nextToken());
                }
                else{
                    int x = Integer.parseInt(token.nextToken());
                    int y = Integer.parseInt(token.nextToken());
                    usersInTime.get(nr-1).add(new User(x*2, y*2));
                }
            }           
        }
        return usersInTime;
    }
}