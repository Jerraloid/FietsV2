/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.entity.mob;

import org.jerraloid.fiets.graphics.Screen;
import org.jerraloid.fiets.graphics.Sprite;
import org.jerraloid.fiets.input.Keyboard;
import org.jerraloid.fiets.level.Sizes;

/**
 *
 * @author Jerry
 */
public class Player extends Mob {
    
    private Keyboard input;
    private Sprite sprite;
    private int animation = 0;
    private boolean walking = false;
    
    private int stepCount = 0; //telt hoeveel pixels hij is gemoved per stap
    
    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_down;
    }
    
    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }
    
    @Override
    public void update() {
        int xAs = 0, yAs = 0;
        
        if (animation < 7500) animation++; //zodat ie niet crasht als iemand de game telang open laat
        else animation = 0;
        
        //geeft de eerste input voor de richting en start de tile counter
        if (input.up && stepCount == 0) {dir = 0; stepCount = 1;} 
        if (input.down && stepCount == 0) {dir = 2; stepCount = 1;} 
        if (input.left && stepCount == 0) {dir = 3; stepCount = 1;} 
        if (input.right && stepCount == 0) {dir = 1; stepCount = 1;} 
        
        //tile counter, player loopt zoveel pixels zo groot als een tile
        if (stepCount >= 1 && stepCount <= Sizes.TILE.getSize()) {
            if (dir == 0) yAs--;
            if (dir == 2) yAs++;
            if (dir == 3) xAs--;
            if (dir == 1) xAs++;
            stepCount = ++stepCount % (Sizes.TILE.getSize() +1);
        }
        
        if (xAs != 0|| yAs != 0) { 
            move(xAs, yAs);
            walking = true;
        }
        else {
            walking = false;
        }
    }
    
    @Override
    public void render(Screen screen) {
        int flip = 0;
        
        if (dir == 0)  {
            sprite = Sprite.player_up;
            if (walking) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_up_1;
                }
                else {
                    sprite = Sprite.player_up_2;
                }
            }
        }
        if (dir == 1) {
            sprite = Sprite.player_side;
            if (walking) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_side;
                }
                else {
                    sprite = Sprite.player_side_1;
                }
            }
        }
        if (dir == 2) { 
            sprite = Sprite.player_down;
            if (walking) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_down_1;
                }
                else {
                    sprite = Sprite.player_down_2;
                }
            }
        }
        if (dir == 3) {
            sprite = Sprite.player_side;
            flip = 1;
            if (walking) {
                if (animation % 20 > 10) {
                    sprite = Sprite.player_side;
                }
                else {
                    sprite = Sprite.player_side_1;
                }
            }
        }
        
        screen.renderPlayer(x - (Sizes.PLAYER.getSize() / 2), y - (Sizes.PLAYER.getSize() / 2), sprite, flip);
    }
    
}
