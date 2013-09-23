
package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */
public class zKirjain extends Kuvio {
        public zKirjain(int x, int y) {
        super();
        Vari oranssi = Vari.ORANSSI;
        super.lisaaPala(new Pala(x-1,y-1, oranssi));
        super.lisaaPala(new Pala(x,y-1, oranssi));   
        super.lisaaPala(new Pala(x,y, oranssi)); //rotaatiopiste paikassa 2
        super.lisaaPala(new Pala(x+1,y, oranssi));
    }
    
    public boolean onVaakaAsennossa() {
        if(super.onkoKuviossaTietyssaKoordinaatissaJoPala(this.getRotaatioPisteenaOlevaPala().getX()+1, this.getRotaatioPisteenaOlevaPala().getY())) {
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
        super.getPalat().get(0).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()-1, this.getRotaatioPisteenaOlevaPala().getY()+i);
        super.getPalat().get(3).setSijainti(this.getRotaatioPisteenaOlevaPala().getX()-i, this.getRotaatioPisteenaOlevaPala().getY());
    }
    
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }
    
}