package org.jerraloid.fiets.graphics;

import org.jerraloid.fiets.level.Sizes;
import org.jerraloid.fiets.level.tile.Tile;

/**
 * Dit is een model voor alle pixels op het Scherm
 * 
 * @author Jerry
 */
public class Screen {
    public int width, height;
    public int[] pixels;
    
    public int xOffset, yOffset;
    
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height]; //50400 verschillende pixels
    }
    
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    public void renderTile(int xPosition, int yPosition, Tile tile) {
        xPosition -= xOffset; //zodat de map naar links beweegt als je naar rechts loopt
        yPosition -= yOffset; //zodat de map naar boven gaat als je naar onder loopt
        
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + yPosition;
            
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xPosition;
                
                if(xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; //rendert alleen de tiles op het scherm
                if (xAbsolute < 0) xAbsolute = 0;
                pixels[xAbsolute+yAbsolute*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE]; 
            }
        }
    }
    
    public void renderPlayer(int xPosition, int yPosition, Sprite sprite, int flip) {
        xPosition -= xOffset; //zodat de player naar links beweegt als je naar rechts loopt
        yPosition -= yOffset; //zodat de player naar boven gaat als je naar onder loopt
        
        for (int y = 0; y < Sizes.PLAYER.getSize(); y++) {
            int yAbsolute = y + yPosition;
            
            int ySprite = y;
            if (flip == 2 || flip == 3) ySprite = Sizes.PLAYER.getSize() - 1 - y; //draait de image verticaal
            
            for (int x = 0; x < Sizes.PLAYER.getSize(); x++) {
                int xAbsolute = x + xPosition;
                
                int xSprite = x;
                if (flip == 1 || flip == 3) xSprite = Sizes.PLAYER.getSize() - 1 - x; //draait de image horizontaal
                
                if(xAbsolute < -Sizes.PLAYER.getSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; //rendert alleen de tiles op het scherm
                if (xAbsolute < 0) xAbsolute = 0;
                
                int col = sprite.pixels[xSprite + ySprite * Sizes.PLAYER.getSize()]; //alle pixels van de sprite op het spritesheet (van de player)
                if (col != 0xff9700FF) { //laat alles wat paars is, transparant
                    pixels[xAbsolute+yAbsolute*width] = col;
                }
            }
        }
    }
    
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
}
