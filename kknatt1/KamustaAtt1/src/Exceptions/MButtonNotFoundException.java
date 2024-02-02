package Exceptions;

import kamustaatt1.Main;

public class MButtonNotFoundException extends Exception {
    public MButtonNotFoundException(String root) {
        super("The MButton could not be found! This error was reported at: "+root);
        Main.ForceStopThrown("The MButton could not be found! This error was reported at: "+root);
    }
}
