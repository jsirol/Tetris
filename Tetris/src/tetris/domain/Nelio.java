
package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */


//nelio on kuvio jossa on neliön muodossa 4 palaa. Konstruktorissa annettavat parametrit määrittävät neliön vasemman yläkulman palan.
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
