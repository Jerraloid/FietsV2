/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import org.jerraloid.fiets.entity.mob.Player;
import org.jerraloid.fiets.graphics.Screen;
import org.jerraloid.fiets.input.Keyboard;
import org.jerraloid.fiets.level.Level;
import org.jerraloid.fiets.level.TileCoor;

/**
 *
 * @author Jerry
 */
public class Fiets extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    
    public static String title = "Fiets";
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;
    
    private Screen screen;
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //zet het raster om in een integer array
    
    public Fiets() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        frame = new JFrame();
        
        key = new Keyboard();
        level = Level.spawn;
        TileCoor playerSpawn = new TileCoor(8, 8);
        player = new Player(playerSpawn.xToPixel(), playerSpawn.yToPixel(), key);
        player.init(level);
        
        addKeyListener(key);
    }
    
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display"); //game process
        thread.start(); //roept run() aan
    }
    
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * is het eerste wat hij aanroept in een thread (komt van Runable)
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60; //1 nanoseconde / 60, zodat ie 60 updates per seconde doet
        double delta = 0;
        
        int frames = 0;
        int updates = 0;
        
        requestFocus(); //dan hoef je niet meer te klikken voordat je kan bewegen
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + "  |  " + updates + "ups, " + frames + " fps"); //ups = updates per second, fps = frames per second
                updates = 0;
                frames = 0;
            }
        }
        
        stop();
    }
    
    public void update() { //update = per second
        key.update();
        player.update();
    }
    
    public void render() {
        //bs is gewoon een tijdelijke opslag voor je frames, en wij hebben 3 van die frames in totaal
        BufferStrategy bs = getBufferStrategy(); //haalt bufferstrategy van Canves
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        
        int xScroll = player.x - screen.width / 2; //om de player in het midden te zetten
        int yScroll = player.y - screen.height / 2; // ''
        
        level.render(xScroll, yScroll, screen);
        player.render(screen);
        
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
        g.dispose(); //gooit graphics weg die je niet meer nodig hebt
        bs.show(); //gooit volgende buffer op scherm
    }
    
    public static void main(String[] args) {
        Fiets game = new Fiets();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        game.frame.pack(); //pakt de grootte van ons scherm
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //laat shit niet doorrunnen als je op kruisje klikt
        game.frame.setLocationRelativeTo(null); //start locatie
        game.frame.setVisible(true); 
        
        game.start();
    }
    
}
