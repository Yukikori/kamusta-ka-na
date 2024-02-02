package kamustaatt1;

import Exceptions.GameStartUpUnsuccessfulException;
import Exceptions.MButtonNotFoundException;
import Exceptions.MImageCreationErrorException;
import Exceptions.MImageNotFound;
import Exceptions.MTextCreationErrorException;
import Exceptions.MTextNotFoundException;
import Exceptions.SaveFolderNotFoundException;
import MGraphics.MGraphics;
import Processor.ErrScreen;
import Processor.MBrain;
import Processor.Screen;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * @author Maro
 * Date Started: January 30, 2023
 * Last Day Updated: February 1, 2023
 * Note for the Day: hashes are your friend :3
 */
public class Main {
    private static JFrame mainframe;
    private static Screen screen;
    
    public static void Ini(){
        try {
            mainframe = new JFrame("Kamusta ka na?");
            mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainframe.setLayout(null);
            mainframe.setPreferredSize(new Dimension(1500, 750));

            screen = new Screen();
            mainframe.add(screen);

            mainframe.setResizable(false);
            mainframe.pack();
            mainframe.setLocationRelativeTo(null);
            mainframe.setVisible(true);
        }
        catch (Exception e) {
            Print("ERROR: Window creation failed.");
            System.exit(0);
        }
        Print("CHECKPOINT: Window creation successful!");
    }

    public static void main(String[] args) throws IOException, SaveFolderNotFoundException, GameStartUpUnsuccessfulException, MImageCreationErrorException, MImageNotFound, MTextCreationErrorException, MTextNotFoundException, MButtonNotFoundException {
        Ini();
        MBrain.BootGame();
        Print("CHECKPOINT: Game start up is successful.");
            
    }
    
    public static void Print(String s){
        System.out.println(s);
    }
    
    public static void ForceStopThrown(String errtext) {
        screen = null;
        try {
            
            
            JFrame window = new JFrame("Error From: Kamusta ka na?");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLayout(null);
            window.setPreferredSize(new Dimension(400, 200));

            ErrScreen errscreen = new ErrScreen(errtext);
            window.add(errscreen);

            window.setResizable(false);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            
            //kill all running things
            MBrain.kill();
            MGraphics.kill();
            screen = null;
            Screen.kill();
            mainframe.dispose();
            
            System.gc();
        }
        catch (Exception e) {
            Print("ERROR: Window creation failed.");
            System.exit(0);
        }
    }
    
}
