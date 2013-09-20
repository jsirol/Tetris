
package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */
public class sKirjain extends Kuvio {
    
    //konstruktorin parametrina rotaatiopisteen sijainti. Palat annetaan järjestyksessä x-koordinaatin mukaan siten, että pienin koordinaatti annetaan ensin
    public sKirjain(int x, int y) {
        super();
        Vari vihrea = Vari.VIHREA;
        super.lisaaPala(new Pala(x-1,y, vihrea));
        super.lisaaPala(new Pala(x,y, vihrea));   //rotaatiopiste paikassa 1
        super.lisaaPala(new Pala(x,y-1, vihrea));
        super.lisaaPala(new Pala(x+1,y-1, vihrea));
    }
    
    public boolean onVaakaAsennossa() {
        if(super.onkoKuviossaTietyssaKoordinaatissaJoPala(this.getRotaatioPisteenaOlevaPala().getX()-1, this.getRotaatioPisteenaOlevaPala().getY())) {
            return true;
        }
        return false;
    }
    
    @Override
    public void kaanna() {
        int i=-1;
        if(this.onVaakaAsennossa()) {
            i=1;
        }
        super.getPalat().get(0).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()+i, this.getRotaatioPisteenaOlevaPala().getY());
        super.getPalat().get(3).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()+1, this.getRotaatioPisteenaOlevaPala().getY()+i);
    }
    
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(1);
    }
    
}
