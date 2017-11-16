/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.level;

/**
 *
 * @author Jerry
 */
public class TileCoor {
    
    private int x, y;
    private final int TILE_SIZE = Sizes.TILE.getSize();
    
    public TileCoor(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * veranderd een coordinaat per tile naar een coordinaat per pixel
     * 
     * @return coordinaat per pixel
     */
    public int xToPixel() {
        return x << (int)(Math.sqrt((double)TILE_SIZE)); //doet x * 16, maar het staat zo genoteerd voor performance
    }
    
    /**
     * veranderd een coordinaat per tile naar een coordinaat per pixel
     * 
     * @return coordinaat per pixel
     */
    public int yToPixel() {
        return y << (int)(Math.sqrt((double)TILE_SIZE));
    }
    
    /**
     * veranderd een coordinaat per pixel, in een coordinaat per tile
     * 
     * @return coordinaat per tile
     */
    public int xToTile() {
        return x >> (int)(Math.sqrt((double)TILE_SIZE)); //doet x / 16
    }
    
    /**
     * veranderd een coordinaat per pixel, in een coordinaat per tile
     * 
     * @return coordinaat per tile
     */
    public int yToTile() {
        return y >> (int)(Math.sqrt((double)TILE_SIZE));
    }
    
    public int[] xy() {
        return new int[]{x, y};
    }
}
