/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.awt.Color;
import tetris.Vari;

/**
 *
 * @author Johannes
 */
public class Pala {

    private int x;             //palan x-koordinaatti
    private int y;             //palan y-koordinaatti
    private Vari vari;         //palan väri
    
    
    public Pala(int x, int y, Vari vari) {
        this.x=x;
        this.y=y;
        this.vari=vari;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    //palauttaa palan värin
    public Color getVari() {
        return this.vari.getVari();
    }

    
    public void setX(int x) {
        this.x=x;
    }
    
    public void setY(int y) {
        this.y=y;
    }
    
    
    public void setSijainti(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    //pudottaa palan yhden koordinaatin verran alaspäin
    public void pudotaYhdella() {
        this.setSijainti(this.x, this.y + 1);
    }

    
    //palan merkkijonoesitys muodossa (x,y)
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}
