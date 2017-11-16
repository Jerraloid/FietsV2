/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jerraloid.fiets.graphics;

import org.jerraloid.fiets.level.Sizes;

/**
 *
 * @author Jerry
 */
public class Sprite {
    
    public final int SIZE; //grootte van de sprite
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite grass = new Sprite(Sizes.TILE.getSize(), 0, 0, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(Sizes.TILE.getSize(), 1, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(Sizes.TILE.getSize(), 2, 0, SpriteSheet.tiles);
    public static Sprite thonking = new Sprite(Sizes.TILE.getSize(), 3, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(Sizes.TILE.getSize(), 0xb7248d);
    
    public static Sprite player_up = new Sprite(Sizes.PLAYER.getSize(), 0, 13, SpriteSheet.tiles);
    public static Sprite player_down = new Sprite(Sizes.PLAYER.getSize(), 2, 13, SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(Sizes.PLAYER.getSize(), 1, 13, SpriteSheet.tiles);
    
    public static Sprite player_up_1 = new Sprite(Sizes.PLAYER.getSize(), 0, 14, SpriteSheet.tiles);
    public static Sprite player_up_2 = new Sprite(Sizes.PLAYER.getSize(), 0, 15, SpriteSheet.tiles);
    
    public static Sprite player_down_1 = new Sprite(Sizes.PLAYER.getSize(), 2, 14, SpriteSheet.tiles);
    public static Sprite player_down_2 = new Sprite(Sizes.PLAYER.getSize(), 2, 15, SpriteSheet.tiles);
    
    public static Sprite player_side_1 = new Sprite(Sizes.PLAYER.getSize(), 1, 14, SpriteSheet.tiles);
    
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }
    
    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColour(colour);
    }
    
    private void setColour(int colour) {
        for (int i = 0; i < SIZE*SIZE; i++) {
            pixels[i] = colour;
        }
    }
    
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
