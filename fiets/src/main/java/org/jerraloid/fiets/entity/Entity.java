/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.entity;

import java.util.Random;

import org.jerraloid.fiets.graphics.Screen;
import org.jerraloid.fiets.level.Level;

/**
 *
 * @author Jerry
 */
public abstract class Entity {
    
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    
    public void update() {
        
    }
    
    public void render(Screen screen) {
        
    }
    
    public void remove() {
        //haalt entity van het level
        
        
        removed = true;
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void init(Level level) {
        this.level = level;
    }
    
}
