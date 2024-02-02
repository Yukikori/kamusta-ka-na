package MGUI;

import Exceptions.MButtonNotFoundException;

public class MButton extends MGUI {
    protected int Ax;
    protected int Ay;
    protected int Bx;
    protected int By;
    
    public static void CreateButtonHitbox(String ID, String valuee, int Axx, int Ayy, int Bxx, int Byy) {
        AllMButton.add(new MButton(ID, valuee, Axx, Ayy, Bxx, Byy));
    }
    
    protected MButton(String ID, String valuee, int Axx, int Ayy, int Bxx, int Byy) {
        ID_name = ID;
        ID_Hash = ID.hashCode();
        value = valuee.hashCode();
        Ax = Axx;
        Ay = Ayy;
        Bx = Bxx;
        By = Byy;
        AllGUI.add(this);
    }
    
    public static MButton FindFromName(String name) throws MButtonNotFoundException {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMButton.size(); i++) {
            if (AllMButton.get(i).ID_Hash == hashie) {
                return AllMButton.get(i);
            }
        }
        }
        catch (Exception e) {
            throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+name+"\"");
        }
        return null;
    }
    
    public static MButton FindFromHash(int hashie) throws MButtonNotFoundException {
        try {
        for (int i=0; i<AllMButton.size(); i++) {
            if (AllMButton.get(i).ID_Hash == hashie) {
                return AllMButton.get(i);
            }
        }
        }
        catch (Exception e) {
            throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+hashie+"\"");
        }
        return null;
    }
}
