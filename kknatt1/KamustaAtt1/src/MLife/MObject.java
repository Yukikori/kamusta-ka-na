package MLife;

import Processor.Vector2;

public class MObject extends MNonLivingThing {
    public static void CreateMObject(String name, int Xx, int Yy) {
        AllMObject.add(new MObject(name, Xx, Yy));
    }
    
    protected MObject (String name, int Xx, int Yy) {
        ID_Name = name;
        ID_Hash = name.hashCode();
        
        SetWLocation(new Vector2(0, 0));
        SetPLocation(new Vector2(Xx, Yy));
        
        AllMObject.add(this);
        AllMNonLivingThing.add(this);
        AllMLife.add(this);
    }
    
    @Override
    public void RemoveItemFromContainer(MLife ml) {
        Contains.remove(ml);
        ml.ToggleVisibility(true);
    }
    @Override
    public void RemoveItemFromContainer(String name) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromName(name);
        Contains.remove(ml);
        ml.ToggleVisibility(true);
    }
    @Override
    public void RemoveItemFromContainer(int index) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromHash(index);
        Contains.remove(ml);
        ml.ToggleVisibility(true);
    }
    
    @Override
    public void AddItemToContainer(MLife ml) {
        Contains.add(ml);
        ml.ToggleVisibility(false);
    }
    @Override
    public void AddItemToContainer(String name) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromName(name);
        Contains.add(ml);
        ml.ToggleVisibility(false);
    }
    @Override
    public void AddItemToContainer(int index) {
        MNonLivingThing ml = MNonLivingThing.MNLTFindFromHash(index);
        Contains.add(ml);
        ml.ToggleVisibility(false);
    }
    
    public static MObject MObjectFindFromName(String name) {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMObject.size(); i++) {
            if (AllMObject.get(i).ID_Hash == hashie) {
                return AllMObject.get(i);
            }
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static MObject MObjectFindFromHash(int hashie) {
        try {
        for (int i=0; i<AllMObject.size(); i++) {
            if (AllMObject.get(i).ID_Hash == hashie) {
                return AllMObject.get(i);
            }
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
