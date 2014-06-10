/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kiloboltgame;

/**
 *
 * @author minghuazhao
 */
public class Background {
    
    private int bgX, bgY, speedX;
    
    public Background(int x, int y){
        bgX = x;
        bgY = y;
        speedX = 0;
    }
    
    public void update(){
        setBgX(getBgX() + getSpeedX());
        if (getBgX() <= -2160){
            setBgX(getBgX() + 4320);
        }
    }

    /**
     * @return the bgX
     */
    public int getBgX() {
        return bgX;
    }

    /**
     * @return the bgY
     */
    public int getBgY() {
        return bgY;
    }

    /**
     * @return the speedX
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * @param bgX the bgX to set
     */
    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    /**
     * @param bgY the bgY to set
     */
    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    /**
     * @param speedX the speedX to set
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    
}
