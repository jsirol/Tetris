package tetris;

import java.awt.Color;

/**
 *
 * @author Johannes
 */
public enum Vari {

    PUNAINEN(Color.RED),
    KELTAINEN(Color.YELLOW),
    ORANSSI(Color.ORANGE),
    VIHREA(Color.GREEN),
    PINKKI(Color.PINK),
    HARMAA(Color.GRAY),
    SININEN(Color.BLUE);    
    
    
    private Color vari;

    private Vari(Color vari) {
        this.vari = vari;
    }
    
    public Color getVari() {
        return this.vari;      
    }   
    
}
