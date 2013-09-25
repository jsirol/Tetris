
package tetris.domain;

import java.awt.Color;
import tetris.Vari;

/**
 *Luokka sisältää määrittelyn paloille, 
 *jotka toimivat Tetris pelin osina muodostaen erilaisia kuvioita.
 * 
 * @see tetris.domain.Kuvio
 * 
 * @author Johannes
 */
public class Pala {

    /**
     * Palan x-koordinaatin arvo.
     */
    private int x;      
    /**
     * Palan y-koordinaatin arvo.
     */
    private int y;   
    /**
     * Palan väri.
     */
    private Vari vari;         

    public Pala(int x, int y, Vari vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Metodi palauttaa palan todellisen värin.
     * 
     * @see tetris.domain.Pala#getVarinNimi()   
     * 
     * @return palan väri
     */
    public Color getVari() {
        return this.vari.getVari();
    }

    /**
     * Metodi palauttaa pala värin nimen.
     * 
     * @see tetris.domain.Pala#getVari() 
     * 
     * @return palan värin nimi       
     */
    public Vari getVarinNimi() {
        return this.vari;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSijainti(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Metodi asettaa palan sijainnin y-koordinaatille arvon, joka on tämänhetkisen y-koordinaatin arvo +1.
     */
    public void pudotaYhdella() {
        this.setSijainti(this.x, this.y + 1);
    }

    /**
     * Metodi saa parametrinaan suunnan, josta tarkistetaan onko toisena parametrina olevan pala palan vieressä.
     * 
     * @param negatiivenOnVasenMuutenOikea suunta, negatiivinen tarkoittaa vasenta ja positiivinen oikeaa.
     * @param verrattava pala josta tarkistetaan, onko se metodia kutsuvan palan vieressä
     * 
     * @return totuusarvo, onko verrattava pala metodia kutsuvan palan vieressä
     */   
    public boolean vieressaOnPala(int negatiivenOnVasenMuutenOikea, Pala verrattava) {
        if (negatiivenOnVasenMuutenOikea < 0) {
            if (this.getX() - 1 == verrattava.getX() && this.getY() == verrattava.getY()) {
                return true;
            }
        } else if (this.getX() + 1 == verrattava.getX() && this.getY() == verrattava.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa palan merkkijonoesityksen.
     * 
     * @return palan merkkijonoesitys
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}
