package MLife;

import Processor.Vector2;

public class MPlayer extends MPerson {
    protected String Player_Name;
    
    public static void CreatePlayer(String PName) {
        if (Player == null) {
            new MPlayer(PName);  
        }
    }
    
    public MPlayer(String PName) {
         Player_Name = PName;
         WorldLocation = new Vector2(0,0);
         PointLocation = new Vector2(300,250);
         
         Player = this;
         AllMLivingThing.add(this);
         AllMPerson.add(this);
         AllMLife.add(this);
    }
}
