package MLife;

import Processor.Vector2;
import java.util.ArrayList;

public abstract class MNonLivingThing extends MLife{
    protected ArrayList<MLife> Contains = new ArrayList<>();
    
    public void RemoveItemFromContainer(MLife ml) {
    }
    public void RemoveItemFromContainer(String name) {
    }
    public void RemoveItemFromContainer(int index) {
    }
    
    public void AddItemToContainer(MLife ml) {
    }
    public void AddItemToContainer(String name) {
    }
    public void AddItemToContainer(int index) {
    }
    
    @Override
    public void SetWLocation(int pX, int pY) {
        WorldLocation = new Vector2(pX,pY);
        UpdateContainerLocation();
    }
    @Override
    public void SetWLocation(Vector2 V2) {
        WorldLocation = V2;
        UpdateContainerLocation();
    }
    
    protected void UpdateContainerLocation() {
        for (int a=0; a<Contains.size(); a++) {
            Contains.get(a).SetWLocation(WorldLocation);
        }
    }
    
    public static MNonLivingThing MNLTFindFromName(String name) {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMNonLivingThing.size(); i++) {
            if (AllMNonLivingThing.get(i).ID_Hash == hashie) {
                return AllMNonLivingThing.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+name+"\"");
        }
        return null;
    }
    
    public static MNonLivingThing MNLTFindFromHash(int hashie) {
        try {
        for (int i=0; i<AllMNonLivingThing.size(); i++) {
            if (AllMNonLivingThing.get(i).ID_Hash == hashie) {
                return AllMNonLivingThing.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+hashie+"\"");
        }
        return null;
    }
}
