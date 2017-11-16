/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.level;

import org.jerraloid.fiets.graphics.Screen;
import org.jerraloid.fiets.level.tile.Tile;

/**
 *
 * @author Jerry
 */
public class Level {
    
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    
    public static Level spawn = new SpawnLevel("/levels/level.png");
    
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }
    
    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }
    
    protected void generateLevel() {
        
    }
    
    protected void loadLevel(String path) {
        
    }
    
    public void update() {
        
    }
    
    private void time() {
        
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        
        TileCoor scrollCoor1 = new TileCoor(xScroll, yScroll); //is voor boven en links
        TileCoor scrollCoor2 = new TileCoor(xScroll + screen.width + (Sizes.TILE.getSize() * 2), yScroll + screen.height + (Sizes.TILE.getSize() * 2)); //is voor rechts en onder
        //de tilesize * 2 wordt hierboven meegegeven om zwarte balken te voorkomen
        
        int x0 = scrollCoor1.xToTile(); //coordinaat van linkerkant van het scherm
        int x1 = scrollCoor2.xToTile();
        int y0 = scrollCoor1.yToTile(); //bovenkant
        int y1 = scrollCoor2.yToTile(); //onderkant
        
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen); //rendert een tile op het scherm
            }
        }
    }
    
    public Tile getTile(int x, int y) { //WERKT MET TILE COORDINATEN, NIET MET PIXELS
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile; //ga je out of map? geef een void tile
        
        if (tiles[x+y*width] == Tile.COL_GRASS) return Tile.grass;
        if (tiles[x+y*width] == Tile.COL_FLOWER) return Tile.flower;
        if (tiles[x+y*width] == Tile.COL_ROCK) return Tile.rock;
        if (tiles[x+y*width] == Tile.COL_THONKING) return Tile.thonking;
        
        return Tile.voidTile;
    }
    
}
