package org.jerraloid.fiets.level;

/**
 *
 * @author Jerry
 */
public enum Sizes {
    
    TILE(16), // de grootte van 1 tile is 16x16
    PLAYER(16); //de grootte van je mannetje is 16x16 (was eerst 32x32)
    
    private final int size;
    
    Sizes(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
    
}
