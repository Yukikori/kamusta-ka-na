package MGUI;

import Processor.MBrain;
import java.util.ArrayList;

public abstract class MGUI {
    public static boolean MGUIIsDead = false;
    
    protected static ArrayList<MGUI> AllGUI = new ArrayList<>();
    protected static ArrayList<MButton> AllMButton = new ArrayList<>();
    
    protected String ID_name = null;
    protected int ID_Hash;
    protected boolean enabled = false;
    protected int pX = 0;
    protected int pY = 0;
    protected int value;
    
    public static void CheckIfClicked(int Xx, int Yy) {
        for (int i=0; i<AllMButton.size(); i++) {
            MButton totest = AllMButton.get(i);
            if (totest.enabled) {
                if (((Xx>totest.Ax)&&(Xx<totest.Bx))&&((Yy>totest.Ay)&&(Yy<totest.By))) {
                    totest.spitvalue();
                    return;
                }
            }  
        }
    }
    
    public static void KeyPressed(String Key, boolean value) {
        String val = "F";
        if (value) {
            val = "T";
        }
        MBrain.receive(Key.hashCode(), val.hashCode());
    }
    
    public void ToggleEnabled(boolean tof) {
        enabled = tof;
    }
    
    protected void spitvalue() {
        MBrain.receive(ID_Hash, value);
    }
}
