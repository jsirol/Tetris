
package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii luokan LjaJmuotti ja määrittää tarkemmin minkälainen kuvio on J-kirjain.
 * J-kirjain koostuu neljästä palasta, jotka on aseteltu J-kirjaimen muotoon.
 * Konstruktorin parametrina annetaan rotaatiopalan sijainti.
 * Kuvion säilyttävään ArrayListiin lisätään ensimmäiselle paikalle J-kirjaimen asennon identifioiva pala.
 * Rotaatiopala tallennetaan ArrayListin paikkaan 2.
 * 
 * @author Johannes
 */
public class jKirjain extends LjaJmuotti {

    public jKirjain(int x, int y) {
        super();
        Vari pinkki = Vari.PINKKI;
        super.lisaaPala(new Pala(x, y - 2, pinkki)); 
        super.lisaaPala(new Pala(x, y - 1, pinkki));
        super.lisaaPala(new Pala(x, y, pinkki));    
        super.lisaaPala(new Pala(x - 1, y, pinkki));
    }

    /**
     * Metodi kääntää J kirjainta 90 asteen verran vastapäivään.
     * 
     * @see tetris.domain.LjaJmuotti#getAsento() 
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala() 
     * @see tetris.domain.LjaJmuotti#kaanna()         
     */
    @Override
    public void kaanna() {
        int asento = super.getAsento();
        Pala rp = super.getRotaatioPisteenaOlevaPala();
        super.kaanna();
        if (asento == 0) {
            this.getPalat().get(3).setSijainti(rp.getX(), rp.getY()+1);
        } else if(asento==1) {
             this.getPalat().get(3).setSijainti(rp.getX()+1, rp.getY());
        } else if(asento==2) {
            this.getPalat().get(3).setSijainti(rp.getX(),rp.getY()-1);
        } else {
            this.getPalat().get(3).setSijainti(rp.getX()-1, rp.getY());
        }
    }

}
