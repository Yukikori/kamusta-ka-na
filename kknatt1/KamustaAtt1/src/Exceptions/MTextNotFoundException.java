package Exceptions;

import kamustaatt1.Main;

public class MTextNotFoundException extends Exception {
    public MTextNotFoundException(String root) {
        super("The MText could not be found! This error was reported at: "+root);
        Main.ForceStopThrown("The MText could not be found! This error was reported at: "+root);
    }
}
