/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerraloid.fiets.level.tile;

import org.jerraloid.fiets.graphics.Sprite;

/**
 *
 * @author Jerry
 */
public class RockTile extends Tile {

    public RockTile(Sprite sprite) {
        super(sprite);
    }
    
    @Override
    public boolean solid() {
        return true;
    }
    
}
