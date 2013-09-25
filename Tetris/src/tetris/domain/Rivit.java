package tetris.domain;

/**
 *Luokka perii Kuvio-luokan ja sisältää tiedot Tetriksen pelialueen mitoista, 
 * sekä siitä, mitä paloja pelialueen riveissä kullakin hetkellä on.
 * 
 * @author Johannes
 */

public class Rivit extends Kuvio {

    /**
     * Tetriksen pelialueen leveys.
     */ 
    private int leveys;
    
    /**
     * Tetriksen pelialueen korkeus.
     */  
    private int korkeus;
    

    public Rivit(int leveys, int korkeus) {
        super();
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    /**
     * Metodi poistaa rivit muodostavasta kuviosta metodin parametrina annetulla rivillä sijaitsevat palat.
     * 
     * @param y rivinumero
     * 
     * @see tetris.domain.Kuvio#poistaPala(tetris.domain.Pala) 
     */
    public void tuhoaRivi(int y) {
        for (Pala pala : super.getRivinPalat(y)) {
            super.poistaPala(pala);
        }
    }

    /**
     * Metodi ilmoittaa totuusarvolla, onko parametrina annettu rivi täysi. 
     * Täyden rivin määrittää toisena parametrina annettava rivin leveys.
     * 
     * @param rivinNro tarkistettavan rivin järjestysnumero. Isompi arvo tarkoittaa alempaa riviä
     * @param rivinLeveys tarkistettavan rivin leveys
     * 
     * @return totuusarvo sille, onko rivi täysi 
     * 
     * @see tetris.domain.Kuvio#getRivinPalat(int) 
     * 
     */
    public boolean riviTaysi(int rivinNro, int rivinLeveys) {
        if (this.getRivinPalat(rivinNro).size() == rivinLeveys) {
            return true;
        }
        return false;
    }  
    
}
