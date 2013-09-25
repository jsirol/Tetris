
package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii Kuvio-luokan ja määrittelee tarkemmin minkälainen on S-kirjain.
 * S-kirjain koostuu neljästä palasta, jotka on aseteltu S-kirjaimen muotoon.
 * Rotaatiopisteen paikka kuvion tallentavassa ArrayListissa on 1.
 * Palat sijoitetaan muuten ArrayListiin pienin koordinaatti ensin, ensisijaisesti x-koordinaatin mukaan.
 * 
 * @author Johannes
 */
public class sKirjain extends Kuvio {
       
    public sKirjain(int x, int y) {
        super();
        Vari vihrea = Vari.VIHREA;
        super.lisaaPala(new Pala(x-1,y, vihrea));
        super.lisaaPala(new Pala(x,y, vihrea));  
        super.lisaaPala(new Pala(x,y-1, vihrea));
        super.lisaaPala(new Pala(x+1,y-1, vihrea));
    }
    
    /**
         * Metodi palauttaa totuusarvona tiedon siitä, onko S-kirjain vaaka-asennossa.
         * 
         * @return totuusarvo sille, onko S-kuvio vaaka-asennossa
         * 
         * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int, int) 
         * @see tetris.domain.zKirjain#getRotaatioPisteenaOlevaPala() 
         */
    public boolean onVaakaAsennossa() {
        if(super.onkoKuviossaTietyssaKoordinaatissaJoPala(this.getRotaatioPisteenaOlevaPala().getX()-1, this.getRotaatioPisteenaOlevaPala().getY())) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kääntää S-kirjainta. Kääntäminen pysty-asentoon tapahtuu myötäpäivään
     * ja kääntäminen vaaka-asentoon vastapäivään.
     * Käännös kääntää S-kirjainta 90 astetta.
     * 
     * @see tetris.domain.zKirjain#getRotaatioPisteenaOlevaPala() 
     * 
     */
    @Override
    public void kaanna() {
        int i=-1;
        if(this.onVaakaAsennossa()) {
            i=1;
        }
        super.getPalat().get(0).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()+i, this.getRotaatioPisteenaOlevaPala().getY());
        super.getPalat().get(3).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()+1, this.getRotaatioPisteenaOlevaPala().getY()+i);
    }
    
    /**
     * Metodi palauttaa S-kirjaimen rotaatiopisteenä olevan palan.
     * 
     * @return rotaatiopisteenä oleva pala
     */
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(1);
    }
    
}
