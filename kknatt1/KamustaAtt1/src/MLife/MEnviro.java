package MLife;

import Processor.Vector2;
import java.util.ArrayList;

public class MEnviro extends MNonLivingThing {
    protected ArrayList<MLivingThing> LiveContain = new ArrayList<>();
    
    public static void CreateMEnviro(String ID, int Wx, int Wy) {
        AllMEnviro.add(new MEnviro(ID, Wx, Wy));
    }
    
    protected MEnviro(String name, int Wx, int Wy) {
        ID_Name = name;
        ID_Hash = name.hashCode();
        
        SetWLocation(Wx, Wy);
        SetPLocation(0,0);

        AllMNonLivingThing.add(this);
        AllMLife.add(this);
    }
    
    @Override
    public void RemoveItemFromContainer(MLife ml) {
        Contains.remove(ml);
        ml.ToggleVisibility(false);
    }
    @Override
    public void RemoveItemFromContainer(String name) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromName(name);
        Contains.remove(ml);
        ml.ToggleVisibility(false);
    }
    @Override
    public void RemoveItemFromContainer(int index) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromHash(index);
        Contains.remove(ml);
        ml.ToggleVisibility(false);
    }
    
    @Override
    public void AddItemToContainer(MLife ml) {
        Contains.add(ml);
        ml.ToggleVisibility(true);
    }
    @Override
    public void AddItemToContainer(String name) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromName(name);
        Contains.add(ml);
        ml.ToggleVisibility(true);
    }
    @Override
    public void AddItemToContainer(int index) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromHash(index);
        Contains.add(ml);
        ml.ToggleVisibility(true);
    }
    
    public static MEnviro MEnviroFindFromName(String name) {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMEnviro.size(); i++) {
            if (AllMEnviro.get(i).ID_Hash == hashie) {
                return AllMEnviro.get(i);
            }
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static MEnviro MEnviroFindFromHash(int hashie) {
        try {
        for (int i=0; i<AllMEnviro.size(); i++) {
            if (AllMEnviro.get(i).ID_Hash == hashie) {
                return AllMEnviro.get(i);
            }
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public void ToggleVisibility(boolean tof) {
        visible = tof;
        for (int a=0; a<Contains.size(); a++) {
            Contains.get(a).ToggleVisibility(tof);
        }
    }
}