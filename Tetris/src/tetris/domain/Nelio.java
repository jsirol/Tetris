
package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii Kuvio-luokan ja määrittelee tarkemmin minkälainen on Neliö-kuvio.
 * Neliössä on 4 palaa neliön muodossa.
 * Konstruktorille annettavat palat määräävät neliön vasemman yläkulman palan sijainnin.
 * 
 * @author Johannes
 */


public class Nelio extends Kuvio {
    
        public Nelio(int x, int y) {     
        super();
        Vari sininen =  Vari.SININEN;
        super.lisaaPala(new Pala(x,y, sininen));
        super.lisaaPala(new Pala(x,y+1, sininen));
        super.lisaaPala(new Pala(x+1,y, sininen));       
        super.lisaaPala(new Pala(x+1,y+1, sininen));
    } 
    
}
