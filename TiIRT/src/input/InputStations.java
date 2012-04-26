package input;

import entities.BaseStation;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author maszter
 */
public class InputStations {

    public static final String FILE_PATH = "/TiIRT/TiIRT";

    public ArrayList<BaseStation> read(JFrame frame) {
        JFileChooser chooser = new JFileChooser(FILE_PATH);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        String path;
        ArrayList<BaseStation> stations = new ArrayList<BaseStation>();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                stations.add(new BaseStation(-100, -100, 0, 100));
                path = chooser.getSelectedFile().getPath();
                File file = new File(path);
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    stations.add(new BaseStation(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
                }
            } catch (Exception ex) {
                Logger.getLogger(InputStations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stations;
    }
}