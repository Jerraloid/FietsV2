/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Jerry
 */
public class SpriteSheet {
    
    private String path; //waar is de sheet?
    public final int SIZE; //grootte van de spritesheet
    public int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    
    public SpriteSheet(String path, int size) {
        this.path = path; 
        this.SIZE = size; 
        pixels = new int[SIZE * SIZE];
        load();
    }
    
    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
