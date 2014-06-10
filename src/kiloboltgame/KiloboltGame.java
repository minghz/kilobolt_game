/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

/**
 *
 * @author minghuazhao
 */
public class KiloboltGame extends Applet implements Runnable, KeyListener {

    /* Some notes on class definitions
     extends means the current class inherits from the Applet class
     Thus, inherits all it's methods, which must be declared
     -> init, start, stop, destroy
     implements [seems] to borrow methods from other classes
     Similarly, all methods from the implementeeds must be declared
     */
    private Robot robot;
    private Heliboy hb, hb2;
    private Image image, currentSprite, character, characterDown, characterJumped, background, heliboy;
    private Graphics second;
    private URL base;
    private static Background bg1, bg2;

    @Override
    public void init() {
        /* first run when applet starts
         Define how to applet looks like here
         */
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        addKeyListener(this);

        try {
            //base = getDocumentBase();
            base = getCodeBase();
            System.out.println(base.toString());
        } catch (Exception e) {
            //TODO: Handle it
        }

        //Image Setups
        character = getImage(base, "data/character.png");
        characterDown = getImage(base, "data/down.png");
        characterJumped = getImage(base, "data/jumped.png");
        currentSprite = character;
        background = getImage(base, "data/background.png");
        heliboy = getImage(base, "data/heliboy.png");

    }

    @Override
    public void start() {
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);
        robot = new Robot();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        /* This function is build in
         Comes form "implememnts Runnable"
         Redirects all Threads' running methodology to be defined here
         */

        while (true) {
            robot.update();
            if (robot.isJumped()) {
                currentSprite = characterJumped;
            } else if (robot.isJumped() == false && robot.isDucked() == false) {
                currentSprite = character;
            }
            hb.update();
            hb2.update();
            getBg1().update();
            getBg2().update();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, getBg1().getBgX(), getBg1().getBgY(), this);
        g.drawImage(background, getBg2().getBgX(), getBg2().getBgY(), this);
        g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
        g.drawImage(heliboy, hb.getCenterX() - 48, hb.getCenterY() - 48, this);
        g.drawImage(heliboy, hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //System.out.println("Move Up");
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = characterDown;
                if (robot.isJumped() == false) {
                    robot.setDucked(true);
                    robot.setSpeedX(0);
                }
                break;
            case KeyEvent.VK_LEFT:
                robot.moveLeft();
                robot.setMovingLeft(true);

                break;
            case KeyEvent.VK_RIGHT:
                robot.moveRight();
                robot.setMovingRight(true);

                break;
            case KeyEvent.VK_SPACE:
                robot.jump();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //System.out.println("Stop Move Up");
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = character;
                robot.setDucked(false);
                break;
            case KeyEvent.VK_LEFT:
                robot.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                robot.stopRight();
                break;
            case KeyEvent.VK_SPACE:
                break;

        }
    }

    /**
     * @return the bg1
     */
    public static Background getBg1() {
        return bg1;
    }

    /**
     * @return the bg2
     */
    public static Background getBg2() {
        return bg2;
    }

}
