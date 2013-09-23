package tetris.domain;

/**
 *
 * @author Johannes
 */
public abstract class LjaJmuotti extends Kuvio {

    @Override
    public void kaanna() {
        int asento = this.getAsento();
        if (asento == 0 || asento == 2) {
            this.kaannaPaaAkseliVaakaan();
        } else {
            this.kaannaPaaAkseliPystyyn();
        }
    }

    
    //siirtää paikoissa 0 ja 1 olevia palasia oikeaan kohtaan
    public void kaannaPaaAkseliPystyyn() {
        Pala rp = this.getRotaatioPisteenaOlevaPala();       
        int j=2;
        int k=1;
        if(this.getAsento()==3) {
            j=-2;
            k=-1;
        }
        for (int i = 0; i < 2; i++) {
            this.getPalat().get(i).setSijainti(rp.getX(), rp.getY()+j-k*i);
        }       
    }

    //siirtää paikoissa 0 ja 1 olevia palasia oikeaan kohtaan
    public void kaannaPaaAkseliVaakaan() {
        Pala rp = this.getRotaatioPisteenaOlevaPala();
        int j=2;
        int k=1;
        if(this.getAsento()==2) {
            j=-2;
            k=-1;           
        }
        for (int i = 0; i < 2; i++) {
            this.getPalat().get(i).setSijainti(rp.getX()+k*i-j, rp.getY());
        }
    }

    //0 on perusasento eli L käännökset vastapäivään 90 astetta ja 3=viimeinen palautettava numero.
    public int getAsento() {
        if (this.getRotaatioPisteenaOlevaPala().getY() - 2 == this.getPalat().get(0).getY()) {
            return 0;
        } else if (this.getRotaatioPisteenaOlevaPala().getX() - 2 == this.getPalat().get(0).getX()) {
            return 1;
        } else if (this.getRotaatioPisteenaOlevaPala().getY() + 2 == this.getPalat().get(0).getY()) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }
}
