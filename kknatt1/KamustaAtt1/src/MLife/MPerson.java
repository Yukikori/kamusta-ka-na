package MLife;

public abstract class MPerson extends MLivingThing {
    protected float[] Grades = new float[]{0};
    protected boolean AniActive = false;
    
    /*
        Animation Keys
        0 - idle_R
        1 - idle_L
        2 - walk_R_0
        3 - walk_R_1
        4 - walk_R_2
        5 - walk_R_3
        6 - walk_L_0
        7 - walk_L_1
        8 - walk_L_2
        9 - walk_L_3
    */
    
    public void Move(int pix) {
        AniMove(this, pix);
    }
    
    public void AniMove(MPerson target, int pix) {
        if (target.AniActive == false) {
            target.AniActive = true;
            boolean RoLtmp = true;
            if (pix < 0) {
               RoLtmp = false;
            }
            RoL = RoLtmp;
            
            Object lock = new Object();
            new Thread (new Runnable() {
                public void run() {
                        synchronized (lock) {
                            try {
                                target.ChangePLocation(true, Math.round(pix/4));
                                //frame 0 walk
                                if (target.RoL) {
                                    CurrFrame = 2;
                                }
                                else {
                                    CurrFrame = 6;
                                }
                                lock.wait(100);
                                target.ChangePLocation(true, Math.round(pix/4));
                                //frame 1 walk
                                CurrFrame++;
                                lock.wait(100);
                                target.ChangePLocation(true, Math.round(pix/4));
                                //frame 2 walk
                                CurrFrame++;
                                lock.wait(100);
                                target.ChangePLocation(true, Math.round(pix/4));
                                //frame 3 walk
                                CurrFrame++;
                                lock.wait(100);
                                //idle
                                if (target.RoL) {
                                    CurrFrame = 2;
                                }
                                else {
                                    CurrFrame = 6;
                                }
                            } catch (InterruptedException ex) {
                                ;
                            }

                    }
                target.AniActive = false;
                }
            }).start();
        }
    }
}
