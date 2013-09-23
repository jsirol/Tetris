package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */
public class tKirjain extends Kuvio {

    public tKirjain(int x, int y) {
        super();
        Vari harmaa = Vari.HARMAA;
        super.lisaaPala(new Pala(x, y - 1, harmaa)); //id pala
        super.lisaaPala(new Pala(x - 1, y, harmaa));
        super.lisaaPala(new Pala(x, y, harmaa));  //rotaatiopiste
        super.lisaaPala(new Pala(x + 1, y, harmaa));
    }

    @Override
    public void kaanna() {
        int x = this.getRotaatioPisteenaOlevaPala().getX();
        int y = this.getRotaatioPisteenaOlevaPala().getY();
        if (this.getAsento() == 0) {
            this.getPalat().get(0).setSijainti(x-1, y);
            this.getPalat().get(1).setSijainti(x, y+1);
            this.getPalat().get(3).setSijainti(x, y-1);
        } else if(this.getAsento() == 1) {
            this.getPalat().get(0).setSijainti(x, y+1);
            this.getPalat().get(1).setSijainti(x+1, y);
            this.getPalat().get(3).setSijainti(x-1, y);
        } else if(this.getAsento() == 2) {
            this.getPalat().get(0).setSijainti(x+1, y);
            this.getPalat().get(1).setSijainti(x, y-1);
            this.getPalat().get(3).setSijainti(x, y+1);
        } else if(this.getAsento() == 3) {
            this.getPalat().get(0).setSijainti(x, y-1);
            this.getPalat().get(1).setSijainti(x-1, y);
            this.getPalat().get(3).setSijainti(x+1, y);
        }
    }

    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }

    //asento 0 on asento jossa kuvio tuodaan peliin eli "T". muut asennot saadaan järjestyksessä kiertämällä 90 astetta vastapäivään.
    public int getAsento() {
        if (this.getRotaatioPisteenaOlevaPala().getY() - 1 == this.getPalat().get(0).getY()) {
            return 0;
        } else if (this.getRotaatioPisteenaOlevaPala().getX() - 1 == this.getPalat().get(0).getX()) {
            return 1;
        } else if (this.getRotaatioPisteenaOlevaPala().getY() + 1 == this.getPalat().get(0).getY()) {
            return 2;
        } else {
            return 3;
        }
    }
    
}
