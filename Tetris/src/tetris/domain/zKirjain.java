
package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii Kuvio-luokan ja määrittelee tarkemmin minkälainen on Z-kirjain.
 * Z-kirjain koostuu neljästä palasta, jotka on aseteltu Z-kirjaimen muotoon.
 * Rotaatiopisteen paikka kuvion tallentavassa ArrayListissa on 2.
 * Palat sijoitetaan muuten ArrayListiin pienin koordinaatti ensin, ensisijaisesti x-koordinaatin mukaan.
 * 
 * @author Johannes
 */
public class zKirjain extends Kuvio {
        public zKirjain(int x, int y) {
        super();
        Vari oranssi = Vari.ORANSSI;
        super.lisaaPala(new Pala(x-1,y-1, oranssi));
        super.lisaaPala(new Pala(x,y-1, oranssi));   
        super.lisaaPala(new Pala(x,y, oranssi)); 
        super.lisaaPala(new Pala(x+1,y, oranssi));
    }
    
        /**
         * Metodi palauttaa totuusarvona tiedon siitä, onko Z-kirjain vaaka-asennossa.
         * 
         * @return totuusarvo sille, onko Z-kuvio vaaka-asennossa
         * 
         * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int, int) 
         * @see tetris.domain.zKirjain#getRotaatioPisteenaOlevaPala() 
         */
    public boolean onVaakaAsennossa() {
        if(super.onkoKuviossaTietyssaKoordinaatissaJoPala(this.getRotaatioPisteenaOlevaPala().getX()+1, this.getRotaatioPisteenaOlevaPala().getY())) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kääntää Z-kirjainta. Kääntäminen pysty-asentoon tapahtuu vastapäivään
     * ja kääntäminen vaaka-asentoon myötäpäivään.
     * Käännös kääntää Z-kirjainta 90 astetta.
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
        super.getPalat().get(0).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()-1, this.getRotaatioPisteenaOlevaPala().getY()+i);
        super.getPalat().get(3).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()-i, this.getRotaatioPisteenaOlevaPala().getY());
    }
    
    /**
     * Metodi palauttaa Z-kirjaimen rotaatiopisteenä olevan palan.
     * 
     * @return rotaatiopisteenä oleva pala
     */
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }
    
}
