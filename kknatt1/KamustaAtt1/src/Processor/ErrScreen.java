package Processor;

import MGraphics.MGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ErrScreen extends JPanel{
    public static ErrScreen ThisInstance = null;
    private static Object lock = new Object();
    private static String FatalError = "what the fuck happened. you really fucked up.";
    
    public ErrScreen(String FE) {
        setBounds(0,0,400,200);
        setBackground(Color.black);
        setLayout(null);
        
        ThisInstance = this;
        FatalError = FE;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        //set up graphics
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        
        g2d.setColor(Color.white);
        // replace with shortened ver of MText
        ArrayList<String> texts = WrapText(FatalError);
        for (int i=0; i<texts.size(); i++) {
            g2d.drawString(texts.get(i), 25, 25+(i*15));
        }
        
        g.dispose();
    }
    
    public static ArrayList<String> WrapText(String text) {
        //apply text wrapping
            
            //split chunk into words
            String[] words = text.split(" ");
            
            //group words into under a limit of letters
            ArrayList<String> toPrint = new ArrayList<>();
            
            for (int i=0; i<words.length; i++) {
                int templength = 0;
                int times = 0;
                String tempstring = "";
                for (int j=0; j<words.length-i; j++) {
                    templength += words[i+j].length()+1;
                    tempstring += words[i+j]+" ";
                    times++;
                    if (templength > 60) {
                        templength -= words[i+j].length()+1;
                        tempstring = tempstring.substring(0, tempstring.length()-(words[i+j].length()+1));
                        break;
                    }
                }
                i += times;
                toPrint.add(tempstring);
            }
            
        return toPrint;
    }
}