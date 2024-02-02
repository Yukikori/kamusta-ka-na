package MGraphics;

import Exceptions.MImageNotFound;
import Processor.Screen;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MGraphics {
    private static boolean MGraphicsIsDead = false;
    
    protected static ArrayList<MGraphics> AllGraphics = new ArrayList<>();
    protected static ArrayList<MImage> AllImages = new ArrayList<>();
    protected static ArrayList<MAniImage> AllAniImages = new ArrayList<>();
    protected static ArrayList<MText> AllTexts = new ArrayList<>();
    
    protected String ID_name = null;
    protected int ID_Hash;
    protected boolean visibility = false;
    protected int pX = 0;
    protected int pY = 0;
    protected int Layer = 0;
    
    public static void PaintAllVisibleWithLayers(Graphics g, Graphics2D g2d) throws MImageNotFound {
        //check: is MGraphics dead?
        if (MGraphicsIsDead == false) {
            //do: sort the layers
            
            ArrayList<ArrayList<MGraphics>> GraphicsLayers = new ArrayList<>();
            GraphicsLayers.add(new ArrayList<>()); //0
            GraphicsLayers.add(new ArrayList<>()); //1
            GraphicsLayers.add(new ArrayList<>()); //2
            
            for (int a=0; a<AllGraphics.size(); a++) {
                GraphicsLayers.get(AllGraphics.get(a).Layer).add(AllGraphics.get(a));
            }
            
            //do: print all MGraphics by layer
            for (int b=0; b<GraphicsLayers.size(); b++) {
                for (int c=0; c<GraphicsLayers.get(b).size(); c++) {
                    switch (GraphicsLayers.get(b).get(c).getClass().getSimpleName()) {
                        case "MImage":
                            MImage ToBePrinted;
                            try {
                                ToBePrinted = MImage.FindFromHash(GraphicsLayers.get(b).get(c).ID_Hash);
                                if (ToBePrinted.visibility) {
                                    g.drawImage(ToBePrinted.image, ToBePrinted.pX, ToBePrinted.pY, Screen.ThisInstance);
                                }
                            }
                            catch (MImageNotFound ex) {
                                throw new MImageNotFound("MGraphics.PaintAllVisibleLayers(), tried to find \""+GraphicsLayers.get(b).get(c).ID_name+"\"");
                            }
                            break;
                            
                        case "MText":
                        MText ToBeWritten;
                        try {
                            ToBeWritten = MText.FindFromHash(GraphicsLayers.get(b).get(c).ID_Hash);
                            if (ToBeWritten.visibility) {
                                g2d.setColor(ToBeWritten.color);
                                g2d.setFont(ToBeWritten.font);
                                for (int d=0; d<ToBeWritten.textcontent.size(); d++) {
                                g2d.drawString(ToBeWritten.textcontent.get(d), ToBeWritten.pX, ToBeWritten.pY+(d*ToBeWritten.Line_Spacing));
                                }
                            }
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                        
                        case "MAniImage":
                            MAniImage ToBePainted;
                            try {
                                ToBePainted = MAniImage.FindFromHash(GraphicsLayers.get(b).get(c).ID_Hash);
                                if (ToBePainted.visibility) {
                                    g.drawImage(ToBePainted.GetCurrFrame(), ToBePainted.pX, ToBePainted.pY, Screen.ThisInstance);
                                }
                            }
                            catch (Exception e) {
                                System.out.println(e);
                            }
                    }
                }
            }
        }
    }
    
    public String getID_name() {
        return ID_name;
    }
    public int getID_hash() {
        return ID_Hash;
    }
    
    public void SetPxPy(int Xx, int Yy) {
        pX = Xx;
        pY = Yy;
    }
    
    public void DeleteSelf() {
        AllGraphics.remove(this);
        if (this.getClass().getSimpleName().equals("MImage")) {
            AllImages.remove(this);
        }
        else if (this.getClass().getSimpleName().equals("MText")) {
            AllTexts.remove(this);
        }
    }
    
    public void ToggleVisibility(boolean tof) {
        visibility = tof;
    }
    
    public static void kill() {
        MGraphicsIsDead = true;
        AllGraphics.clear();
        AllImages.clear();
    }
}
