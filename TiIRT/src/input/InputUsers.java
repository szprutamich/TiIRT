package input;

import entities.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maszter
 */
public class InputUsers {
    public static final String FILE_NAME_PATH = "parse.txt";
    
    public ArrayList<ArrayList<User>> read() throws Exception{
        File file = new File(FILE_NAME_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int usersCount = -1;
        int nr = -1;
        int iterationsCount = -1;
        ArrayList<ArrayList<User>> usersInTime = null;
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
                usersInTime.get(nr-1).add(new User(x, y));
            }
        }        
        return usersInTime;
    }
    
    public static void main(String args[]){
        InputUsers iU = new InputUsers();
        try {
            ArrayList<ArrayList<User>> cos = iU.read();
        } catch (Exception ex) {
            Logger.getLogger(InputUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}