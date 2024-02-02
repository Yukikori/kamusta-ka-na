package MGraphics;

import Exceptions.MTextCreationErrorException;
import Exceptions.MTextNotFoundException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MText extends MGraphics{
    
    protected ArrayList<String> textcontent = new ArrayList<>();
    protected Font font;
    protected String location;
    protected Color color;
    protected int Line_Spacing;
    
    
    public static void CreateMText(String ID, String fulltext, String fontt, int fontsizee, int maxwidthh, int LS, int Xx, int Yy, String colorr, int layerr) throws MTextCreationErrorException {
        // Wrap Text
            //apply text wrapping

               //split chunk into words
               String[] words = fulltext.split(" ");

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
                       if (templength > maxwidthh) {
                           templength -= words[i+j].length()+1;
                           tempstring = tempstring.substring(0, tempstring.length()-(words[i+j].length()+1));
                           break;
                       }
                   }
                   i += times;
                   toPrint.add(tempstring);
               }
        
        // Set Font
        Font fonttt = null;
        switch (fontt) {
            case "bad":
                break;
                       
            default:
                try {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    Font BM = Font.createFont(Font.TRUETYPE_FONT, new File("game_files/resources/font/Onciale PhF01.ttf"));
                    ge.registerFont(BM);
                } catch (IOException|FontFormatException e) {
                    throw new MTextCreationErrorException("MText.CreateMText() - applying font: onciale");
                }
                fonttt = new Font( "onciale phf", Font.PLAIN, fontsizee );
                break;
          
        }
        
        //Set Color
        Color colorrr = null;
        
        switch (colorr) {
            case "WHITE":
                colorrr = Color.WHITE;
                break;
                
            default:
                colorrr = Color.BLACK;
                break;
        }
        
        AllTexts.add(new MText(ID, toPrint, fonttt, "custom", Xx, Yy, LS, colorrr, layerr));
    }
    
    public MText(String ID, ArrayList<String> text, Font fontt, String locationn, int Xx, int Yy, int LS, Color colorr, int layerr) {
        ID_name = ID;
        ID_Hash = ID.hashCode();
        textcontent = text;
        font = fontt;
        location = locationn;
        pX = Xx;
        pY = Yy;
        color = colorr;
        Line_Spacing = LS;
        Layer = layerr;
        AllGraphics.add(this);
    }
    
    public static MText FindFromName(String name) throws MTextNotFoundException {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllTexts.size(); i++) {
            if (AllTexts.get(i).ID_Hash == hashie) {
                return AllTexts.get(i);
            }
        }
        }
        catch (Exception e) {
            throw new MTextNotFoundException("MText.FindFromName() tried to find "+name);
        }
        return null;
    }
    
    public static MText FindFromHash(int hashie) throws MTextNotFoundException {
        try {
        for (int i=0; i<AllTexts.size(); i++) {
            if (AllTexts.get(i).ID_Hash == hashie) {
                return AllTexts.get(i);
            }
        }
        }
        catch (Exception e) {
            throw new MTextNotFoundException("MText.FindFromName() tried to find "+hashie);
        }
        return null;
    }
}
