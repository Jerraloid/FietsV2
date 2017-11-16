/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.entity.mob;

import org.jerraloid.fiets.entity.Entity;
import org.jerraloid.fiets.graphics.Sprite;
import org.jerraloid.fiets.level.Sizes;

/**
 *
 * @author Jerry
 */
public abstract class Mob extends Entity {
    
    protected Sprite sprite;
    protected int dir = 2; //is de direction, richting waar ie heen loopt: 0 noord, 1 oost, 2 zuid, 3 west
    protected boolean moving = false;
    
    public void move(int xAs, int yAs) {
        if (xAs != 0 && yAs != 0) { //zorgt ervoor dat je langs de muur kan sliden
            move(xAs, 0);
            move(0, yAs);
            return;
        }
        
        if (xAs > 0) dir = 1;
        if (xAs < 0) dir = 3;
        if (yAs > 0) dir = 2;
        if (yAs < 0) dir = 0;
        
        if (!collision(xAs, yAs)) {
            x += xAs;
            y += yAs;
        }
    }
    
    @Override
    public void update() {
        
    }
    
    private boolean collision(int xAs, int yAs) {
        boolean solid = false;
        for (int i = 0; i < 4; i++) { //4 want er zijn 4 kanten op een tile
            int xKant = ((x + xAs) + i % 2 * 15) / Sizes.TILE.getSize();
            int yKant = ((y + yAs) + i / 2 * 15 ) / Sizes.TILE.getSize();
            
            if (level.getTile(xKant, yKant).solid()) 
                solid = true;
        }
        
        
        return solid;
    }
    
    public void render() {
        
    }
    
}
