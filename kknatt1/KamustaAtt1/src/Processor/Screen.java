package Processor;

import Exceptions.MImageNotFound;
import MGUI.MGUI;
import MGraphics.MGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import kamustaatt1.Main;

public class Screen extends JPanel{
    public static Screen ThisInstance = null;
    private static Object lock = new Object();
    
    private ML Mousey;
    
    public Screen() {
        setBounds(0,0,1500,750);
        setBackground(Color.black);
        setLayout(null);
        
        ThisInstance = this;
        Mousey = new ML(this);
        
        
        //register keybinds
        ActionMap aM = this.getActionMap();
            InputMap iM = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            
            iM.put(KeyStroke.getKeyStroke("UP"),"up-p");
            aM.put("up-p", new KL("UpArr",true));
            iM.put(KeyStroke.getKeyStroke("released UP"),"up-r");
            aM.put("up-r", new KL("UpArr",false));
            
            iM.put(KeyStroke.getKeyStroke("DOWN"),"down-p");
            aM.put("down-p", new KL("BackArr",true));
            iM.put(KeyStroke.getKeyStroke("released DOWN"),"down-r");
            aM.put("down-r", new KL("BackArr",false));
            
            iM.put(KeyStroke.getKeyStroke("RIGHT"),"right-p");
            aM.put("right-p", new KL("RightArr",true));
            iM.put(KeyStroke.getKeyStroke("released RIGHT"),"right-r");
            aM.put("right-r", new KL("RightArr",false));

            iM.put(KeyStroke.getKeyStroke("LEFT"),"left-p");
            aM.put("left-p", new KL("LeftArr",true));
            iM.put(KeyStroke.getKeyStroke("released LEFT"),"left-r");
            aM.put("left-r", new KL("LeftArr",false));
            
            iM.put(KeyStroke.getKeyStroke("ENTER"),"ENTER-p");
            aM.put("ENTER-p", new KL("ENTER",true));
            iM.put(KeyStroke.getKeyStroke("released ENTER"),"ENTER-r");
            aM.put("ENTER-r", new KL("ENTER",false));
    }
    
    class KL extends AbstractAction {
        String input;
        boolean isPressed;
        
        public KL(String Key, Boolean PR) {
            input = Key;
            isPressed = PR;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MGUI.KeyPressed(input, isPressed);
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        //set up graphics
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        
        try {
            //-- insert layer rendering here --//
            MGraphics.PaintAllVisibleWithLayers(g, g2d);
        } catch (MImageNotFound ex) {
            Main.ForceStopThrown("The MImage could not be found! This error was reported at: Screen.paint() tried to perform MGraphics.PaintAllVisibleWithLayers()");
        }
        
        
        g.dispose();
    }
    
    public static void Refresh() {
        new Thread (new Runnable() {
            public void run() {
                while(true) {
                    synchronized (lock) {
                        try {
                            lock.wait(50);
                        } catch (InterruptedException ex) {
                            ;
                        }
                        
                        //-- insert task here --//
                        ThisInstance.repaint();
                    }
                }
            }
        }).start();
    }
    
    public class ML implements MouseListener {
        public ML(Screen scr) {
            scr.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            ;
        }

        @Override
        public void mousePressed(MouseEvent me) {
            ;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
                MGUI.CheckIfClicked(me.getX(), me.getY());
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            ;
        }

        @Override
        public void mouseExited(MouseEvent me) {
            ;
        }
        
        
    }
    
    public static void kill() {
        ThisInstance = null;
    }
}
