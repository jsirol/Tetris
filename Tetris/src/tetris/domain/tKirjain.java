package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii Kuvio-luokan ja määrittelee tarkemmin minkälainen on T-kirjain.
 * T-kirjain koostuu neljästä palasta, jotka on aseteltu T-kirjaimen muotoon.
 * Kuvion asennon määrittävä pala asetetaan kuvion ArrayListiin paikalle 0.
 * Rotaatiopiste tallennetaan paikkaan 2.
 * 
 * @author Johannes
 */
public class tKirjain extends Kuvio {

    public tKirjain(int x, int y) {
        super();
        Vari harmaa = Vari.HARMAA;
        super.lisaaPala(new Pala(x, y - 1, harmaa)); 
        super.lisaaPala(new Pala(x - 1, y, harmaa));
        super.lisaaPala(new Pala(x, y, harmaa));  
        super.lisaaPala(new Pala(x + 1, y, harmaa));
    }

    /**
     * Metodi kääntää T-kirjainta. 
     * Kääntäminen tapahtuu aina vastapäivään 90 asteen verran kerrallaan.
     * 
     * @see tetris.domain.tKirjain#getRotaatioPisteenaOlevaPala() 
     * @see tetris.domain.tKirjain#getAsento()  
     */
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

    /**
     * Metodi palauttaa T-kirjaimen rotaatiopisteenä olevan palan.
     * 
     * @return rotaatiopisteenä oleva pala
     */
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }

    //asento 0 on asento jossa kuvio tuodaan peliin eli "T". muut asennot saadaan järjestyksessä kiertämällä 90 astetta vastapäivään.
    
    /**
     * Metodi kertoo T-kirjaimen kulloisenkin asennon palauttamalla asentoa vastaavan kokonaisluvun. 
     * Luku 0 vastaa asentoa, johon konstruktori asettaa T-kirjaimen. Muut asennot saadaan kääntämällä 
     * T-kirjainta 90 astetta vastapäivään. Kaikkiaan asentoja on 4 (0,1,2,3).
     * 
     * @return kokonaisluku, joka vastaa asentoa, jossa kuvio on 
     * 
     * @see tetris.domain.tKirjain#getRotaatioPisteenaOlevaPala() 
     * 
     */
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
