package MLife;

import Processor.Vector2;
import java.util.ArrayList;

public abstract class MLife {
    protected static ArrayList<MLife> AllMLife = new ArrayList<>();
    protected static ArrayList<MLivingThing> AllMLivingThing = new ArrayList<>();
    protected static ArrayList<MPerson> AllMPerson = new ArrayList<>();
    protected static ArrayList<MNonLivingThing> AllMNonLivingThing = new ArrayList<>();
    protected static ArrayList<MObject> AllMObject = new ArrayList<>();
    protected static ArrayList<MEnviro> AllMEnviro = new ArrayList<>();
    
    protected static MPlayer Player = null;
    
    protected String ID_Name;
    protected int ID_Hash;
    protected String Description;
    protected Vector2 WorldLocation;
    protected Vector2 PointLocation;
    protected int CurrFrame = 0;
    protected boolean RoL = true;
    protected boolean visible = false;
    
    public void SetWLocation(int pX, int pY) {
        WorldLocation = new Vector2(pX,pY);
    }
    public void SetWLocation(Vector2 V2) {
        WorldLocation = V2;
    }
    
    public void SetPLocation(int pX, int pY) {
        PointLocation = new Vector2(pX,pY);
    }
    public void SetPLocation(Vector2 V2) {
        PointLocation = V2;
    }
    
    public void ChangePLocation(boolean XoY, int val) {
        if (XoY) {
            PointLocation.pX += val;
        }
        else {
            PointLocation.pY += val;
        }
    }
    
    public Vector2 GetPLocation() {
        return PointLocation;
    }
    public Vector2 GetWLocation() {
        return WorldLocation;
    }
    
    public static MPlayer GetPlayer() {
        return Player;
    }
    
    public int GetCurrFrame() {
        return CurrFrame;
    }
    
    public boolean GetVisibility() {
        return visible;
    }
    
    public void ToggleVisibility(boolean tof) {
        visible = tof;
    }
    
    public static MLife FindFromName(String name) {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMLife.size(); i++) {
            if (AllMLife.get(i).ID_Hash == hashie) {
                return AllMLife.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+name+"\"");
        }
        return null;
    }
    
    public static MLife FindFromHash(int hashie) {
        try {
        for (int i=0; i<AllMLife.size(); i++) {
            if (AllMLife.get(i).ID_Hash == hashie) {
                return AllMLife.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+hashie+"\"");
        }
        return null;
    }
}
