package MLife;

public abstract class MLivingThing extends MLife {
    protected int[] Needs = new int[]{0};
    protected boolean isDead = false;
    
    public void Interact(MLife target) {
        System.out.println("ai");
    }
    
    protected void beInteracted(MLife active) {
        
    }
    
    protected void Die(boolean NeedsOrMurder) {
        isDead = true;
    }
    
    public static MLivingThing LTFindFromName(String name) {
        int hashie = name.hashCode();
        try {
        for (int i=0; i<AllMLivingThing.size(); i++) {
            if (AllMLivingThing.get(i).ID_Hash == hashie) {
                return AllMLivingThing.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+name+"\"");
        }
        return null;
    }
    
    public static MLivingThing LTFindFromHash(int hashie) {
        try {
        for (int i=0; i<AllMLivingThing.size(); i++) {
            if (AllMLivingThing.get(i).ID_Hash == hashie) {
                return AllMLivingThing.get(i);
            }
        }
        }
        catch (Exception e) {
            //throw new MButtonNotFoundException("MButton.FindFromName() tried to find \""+hashie+"\"");
        }
        return null;
    }
}
