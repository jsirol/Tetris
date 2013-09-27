package tetris;

import java.awt.Color;

/**
 * Enum-luokka määrittelee Tetriksen paloille värivaihtoehdot.
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
    
    /**
     * Olion väri.
     */
    private Color vari;

    /**
     * Metodi asettaa olion väriksi parametrina annetun värin.
     * 
     * @param vari 
     */
    private Vari(Color vari) {
        this.vari = vari;
    }
    
    public Color getVari() {
        return this.vari;      
    }   
    
}
