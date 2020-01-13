package com.rocketmail.vaishnavanil.crisis;

import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.scene.GameScene;
import com.rocketmail.vaishnavanil.crisis.scene.Scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Crisis extends Canvas implements Runnable, MouseMotionListener {
    private static Crisis Instance;

    private Scenes cur_scene;
    private static final int width = 300;
    private static final int height = (width/16)*9;
    private static final int scale = 3;
    private static String title = "Crisis";
    private static boolean running = false;
    private JFrame frame;
    private Thread thread;
    private BufferedImage displayed = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    private int[] dispPixels = ((DataBufferInt)displayed.getRaster().getDataBuffer()).getData();
    private Screen screen;
    public Crisis(){
        Instance = this;
        Dimension dimension = new Dimension(width*scale,height*scale);
        setPreferredSize(dimension);
        frame = new JFrame();
        screen = new Screen(width,height);
        addMouseMotionListener(this);
    }
    public void setScene(Scenes scene){
        cur_scene = scene;
        return;
    }
    public GameScene getScene(){
        return cur_scene.getScene();
    }
    public static void main(String[] args){
        Crisis crisis = new Crisis();
        crisis.frame.setResizable(false);
        crisis.frame.setTitle(title);
        crisis.frame.add(crisis);
        crisis.frame.pack();
        crisis.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crisis.frame.setLocationRelativeTo(null);
        crisis.frame.setVisible(true);
        ;
        crisis.cur_scene = Scenes.levelselect;
        crisis.cur_scene = Scenes.menu;
        crisis.start();

    }


    private synchronized void start(){
        running = true;
        thread = new Thread(this,"Display");
        thread.start();
    }
    private synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        long prevEpoch = System.nanoTime();
        long time = System.currentTimeMillis();
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        int fps = 0;
        int ups = 0;
        requestFocus();
        while (running){
            long curEpoch = System.nanoTime();
            delta += (curEpoch-prevEpoch)/ns;
            prevEpoch = curEpoch;
            while(delta>=1){
                delta--;
                update();
                ups++;
            }
            render();
            fps++;
            if((System.currentTimeMillis()-time) > 1000){
                //System.out.println(fps+" " + ups);
                fps = 0;
                ups = 0;
                time+=1000;
            }
        }
    }
    private void update(){

    }
    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        try {
            cur_scene.getScene().renderScene(screen);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 0; i<dispPixels.length;i++){
            dispPixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(displayed,0,0,getWidth(),getHeight(),null);
        g.dispose();
        bs.show();
    }

    public static Crisis getInstance(){
        return Instance;
    }
    static int MouseX = 0;
    static int MouseY = 0;
    public int getMouseX(){
        return MouseX;
    }
    public int getMouseY(){
        return MouseY;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseX = e.getX()/3;
        MouseY = e.getY()/3;
    }
}
