package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */
public class lKirjain extends LjaJmuotti {

    public lKirjain(int x, int y) {
        super();
        Vari keltainen = Vari.KELTAINEN;
        super.lisaaPala(new Pala(x, y - 2, keltainen)); //asennon identifioiva pala
        super.lisaaPala(new Pala(x, y - 1, keltainen));
        super.lisaaPala(new Pala(x, y, keltainen));    //rotaatiopala
        super.lisaaPala(new Pala(x + 1, y, keltainen));
    }

    @Override
    public void kaanna() {
        int asento = super.getAsento();
        Pala rp = super.getRotaatioPisteenaOlevaPala();
        super.kaanna();
        if (asento == 0) {
            this.getPalat().get(3).setSijainti(rp.getX(), rp.getY()-1);
        } else if(asento==1) {
             this.getPalat().get(3).setSijainti(rp.getX()-1, rp.getY());
        } else if(asento==2) {
            this.getPalat().get(3).setSijainti(rp.getX(),rp.getY()+1);
        } else {
            this.getPalat().get(3).setSijainti(rp.getX()+1, rp.getY());
        }
    }
  
}
