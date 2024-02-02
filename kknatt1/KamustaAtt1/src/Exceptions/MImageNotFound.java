package Exceptions;

import kamustaatt1.Main;

public class MImageNotFound extends Exception {
    public MImageNotFound(String root) {
        super("The MImage could not be found! This error was reported at: "+root);
        Main.ForceStopThrown("The MImage could not be found! This error was reported at: "+root);
    }
}
