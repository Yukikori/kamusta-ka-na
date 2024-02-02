package MEvents;

import MGraphics.MGraphics;
import java.util.ArrayList;

public class MCutsceneGraphicsOnly extends MEvents {
    
    public ArrayList<MGraphics> grphwlayrs = new ArrayList<>();
    
    public static void NewMCutsceneGraphicsOnly(String ID) {
        
    }
    
    protected MCutsceneGraphicsOnly(String ID) {
        
    }
    
    public static MCutsceneGraphicsOnly FindFromName(String name) {
        int hashie = name.hashCode();
        for (int i=0; i<AllMCutsceneGraphicsOnly.size(); i++) {
            if (AllMCutsceneGraphicsOnly.get(i).ID_Hash == hashie) {
                return AllMCutsceneGraphicsOnly.get(i);
            }
        }
        //throw new MImageNotFound("MImage.FindFromName tried to find \""+name+"\"");
        return null;
    }
    
    public static MCutsceneGraphicsOnly FindFromHash(int hashie) {
        for (int i=0; i<AllMCutsceneGraphicsOnly.size(); i++) {
            if (AllMCutsceneGraphicsOnly.get(i).ID_Hash == hashie) {
                return AllMCutsceneGraphicsOnly.get(i);
            }
        }
        //throw new MImageNotFound("MImage.FindFromName tried to find \""+hashie+"\"");
        return null;
    }
}
