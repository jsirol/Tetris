package tetris.domain;

import java.util.ArrayList;

/**
 *
 * @author Johannes
 */
//luokasta luotu olio pitää sisällään tiedot tetriksen pelialueen mitoista, sekä siitä, mitä paloja pelialueen riveissä kullakin hetkellä on.
//huomaa: rivit tallennettaan "kuviomuodossa" eli palat eivät ole järjestyksessä yliluokan ArrayList:issä.
public class Rivit extends Kuvio {

    private int leveys;
    private int korkeus;
    
    //testi
    private ArrayList<Kuvio> kuviot;

    public Rivit(int leveys, int korkeus) {
        super();
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.kuviot=new ArrayList<Kuvio>();
    }

    public void tuhoaRivi(int y) {
        for (Pala pala : super.getRivinPalat(y)) {
            super.poistaPala(pala);
        }
        //testiosa alkoi
        for(Kuvio kuvio : this.kuviot) {
            for(Pala pala : kuvio.getRivinPalat(y)) {
                kuvio.poistaPala(pala);
            }
        }
        //testiosa loppui
    }

    //palauttaa true jos rivi rivinNro on täysi, muuten false.
    public boolean riviTaysi(int rivinNro, int rivinLeveys) {
        if (this.getRivinPalat(rivinNro).size() == rivinLeveys) {
            return true;
        }
        return false;
    }
    
    public ArrayList<Kuvio> getKuviot() {
        return this.kuviot;
    }
    
    public ArrayList<Kuvio> getKuviotJoillaPalojaRivilla(int riviNro) {
        ArrayList<Kuvio> kuviot = new ArrayList<Kuvio>();
        for(Kuvio kuvio : this.kuviot) {
            for(Pala pala : kuvio.getPalat()) {
                if(pala.getY()==riviNro) {
                    kuviot.add(kuvio);
                    continue;
                }
            }
        }
        return kuviot;
    }
    
    
}
