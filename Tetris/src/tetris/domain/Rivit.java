package tetris.domain;

/**
 *
 * @author Johannes
 */
//luokasta luotu olio pitää sisällään tiedot tetriksen pelialueen mitoista, sekä siitä, mitä paloja pelialueen riveissä kullakin hetkellä on.
//huomaa: rivit tallennettaan "kuviomuodossa" eli palat eivät ole järjestyksessä yliluokan ArrayList:issä.
public class Rivit extends Kuvio {

    private int leveys;
    private int korkeus;

    public Rivit(int leveys, int korkeus) {
        super();
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public void tuhoaRivi(int y) {
        for (Pala pala : super.getRivinPalat(y)) {
            super.poistaPala(pala);
        }
    }

    //palauttaa true jos rivi rivinNro on täysi, muuten false.
    public boolean riviTaysi(int rivinNro, int rivinLeveys) {
        if (this.getRivinPalat(rivinNro).size() == rivinLeveys) {
            return true;
        }
        return false;
    }
}
