package com.rocketmail.vaishnavanil.crisis.graphics;

import com.rocketmail.vaishnavanil.crisis.Crisis;
import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.scene.GameScene;
import com.rocketmail.vaishnavanil.crisis.scene.Scenes;
import com.rocketmail.vaishnavanil.crisis.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Button implements MouseMotionListener, MouseInputListener {
    private String shift;
    private int width;
    private int height;
    private int xO,yO;
    private int[] pixels;
    private int[] hover;
    public boolean mousehover = false;
    public void reset(){
        Crisis.getInstance().addMouseMotionListener(this);
        Crisis.getInstance().addMouseListener(this);
        mousehover =false;
    }
    public Button(String path, String hover, int width, int height, int xOff, int yOff, String sceneLink){
        this.width = width;
        this.height = height;
        shift = sceneLink;
        xO = xOff;
        yO = yOff;
        Crisis.getInstance().addMouseMotionListener(this);
        Crisis.getInstance().addMouseListener(this);
        load(path);
        loadhover(hover);
    }
    public Button(int[] pix,int width,int height,int xOff,int yOff,String sceneLink){
        this.width = width;
        this.height = height;
        shift = sceneLink;
        xO = xOff;
        yO = yOff;
        Crisis.getInstance().addMouseMotionListener(this);
        Crisis.getInstance().addMouseListener(this);
        pixels = new int[pix.length];
        hover = new int[pix.length];
        for(int i = 0;i<pix.length;i++){
            pixels[i] = pix[i];
            hover[i] = pix[i]+0xFFCCF;
        }
    }
    public int getxOff(){
        return xO;
    }
    public int getyOff(){
        return yO;
    }

    private void loadhover(String hover) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(hover));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.hover = new int[image.getWidth()*image.getHeight()];
        image.getRGB(0,0,image.getWidth(),image.getHeight(),this.hover,0,image.getWidth());
    }

    public void setXoff(int xoff){
        xO = xoff;
    }
    public void setYoff(int yoff){
        yO = yoff;
    }
    public void render(Screen screen){
        if(mousehover) {
            int[] rndr = hover.clone();
            for(int i = 0;i<pixels.length;i++){
                int rnd = ThreadLocalRandom.current().nextInt(100)+1;
                if(rnd < 10){
                    rndr[i] = ThreadLocalRandom.current().nextInt(0xFFFFFF);
                }
            }
            screen.renderPixels((rndr),xO,yO,width,height);
            return;
        }
        screen.renderPixels((pixels),xO,yO,width,height);
        return;
    }
    private void load(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pixels = new int[image.getWidth()*image.getHeight()];
        image.getRGB(0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX()+" ; " + e.getY());
        if(e.getX()/3 > xO && e.getX()/3< xO+width ){
            if(e.getY()/3 > yO && e.getY()/3 < yO+height){
                mousehover = true;
                return;
            }
        }
        mousehover = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX()/3 > xO && e.getX()/3< xO+width ){
            if(e.getY()/3 > yO && e.getY()/3 < yO+height){
                if(shift!=null)Scenes.valueOf(shift).getScene().reload();
                Crisis.getInstance().setScene(Scenes.valueOf(shift));
                Crisis.getInstance().removeMouseMotionListener(this);
                Crisis.getInstance().removeMouseListener(this);
                return;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
