/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Jerry
 */
public class SpawnLevel extends Level {
        
    public SpawnLevel(String path) {
        super(path);
    }
    
    
    @Override
    protected void generateLevel() {
        
    }
    
    @Override
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
            
        }
        catch (IOException ex) {
            System.out.println("could not load level file.");
        }
    }
    
}
