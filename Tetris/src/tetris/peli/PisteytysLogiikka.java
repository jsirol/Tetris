package tetris.peli;

import tetris.Vaikeustaso;

/**
 * Pisteytyslogiikka-luokka määrittelee säännöt, joiden mukaan rivien
 * tuhoamisesta saa pisteitä.
 *
 * @author Johannes
 */
public class PisteytysLogiikka {

    /**
     * Kertoo kuinka monella peräkkäisellä vuorolla on saatu tuhottua rivejä
     * kulloisessakin pelitilanteessa. Laskukaava: peräkkäisten vuorojen määrä
     * joilla tuhottuja rivejä - 1
     */
    private int kombot;

    public PisteytysLogiikka() {
        this.kombot = -1;
    }

    //kombokerroin = kerroin*50*vaikeustaso.getTasoNro(). Esim lvl 7, saadaan kerroin *50*7 pistettä. Kerroin=1 kun tuhottu edellisellä vuorolla 1 rivi.
    /**
     * Metodi saa parametreikseen kerralla tuhottujen rivien määrän
     * kokonaislukuna, sekä pelin vaikeustason. Paluuarvonaan metodi palauttaa
     * kokonaisluvun, joka kertoo pelaajalle rivien tuhoamisesta annettavan
     * pistemäärän.
     *
     * Metodi huomioi tuhotut rivit, mahdolliset kombot, sekä pelin
     * vaikeustason.
     *
     * @param tuhotutRivit kerralla tuhottujen rivien määrä
     * @param vaikeustaso pelin vaikeustaso
     * @return pelaajalle annettava pistemäärä
     */
    public int pisteitaTuhottujenRivienJaVaikeustasonMukaan(int tuhotutRivit, Vaikeustaso vaikeustaso) {
        int pisteet = this.tuhottujaRivejaVastaavaPeruspistemaara(tuhotutRivit);
        int kerroin = 0;
        if (this.kombot > 0) {
            kerroin = this.kombot;
        }
        if (vaikeustaso.getTasoNro() < 8) {
            return (int) Math.floor(pisteet * (vaikeustaso.getTasoNro() + 1) * 0.25) + kerroin * 50 * vaikeustaso.getTasoNro();
        } else if (vaikeustaso.getTasoNro() == 8) {
            return (int) Math.floor(pisteet * 2.5) + kerroin * 50 * vaikeustaso.getTasoNro();
        } else {
            return (vaikeustaso.getTasoNro() - 6) * pisteet + kerroin * 50 * vaikeustaso.getTasoNro();
        }
    }

    /**
     * Metodi saa parametrinaan tiedon tuhottujen rivien lukumäärästä ja
     * palauttaa paluuarvonaan rivien tuhoamisesta saatavan peruspistemäärän.
     *
     * @param tuhotutRivit tuhottujen rivien lukumäärä
     * @return tuhottujen rivien lukumäärää vastaava pistemäärä
     */
    public int tuhottujaRivejaVastaavaPeruspistemaara(int tuhotutRivit) {
        this.kombot++;
        if (tuhotutRivit == 1) {
            return 100;
        } else if (tuhotutRivit == 2) {
            return 250;
        } else if (tuhotutRivit == 3) {
            return 450;
        } else if (tuhotutRivit == 4) {
            return 800;
        } else {
            this.kombot = -1;
            return 0;
        }
    }
    
    public int getKombot() {
        return this.kombot;
    }
    
    public void setKombot(int kombot) {
        this.kombot=kombot;
    }
}
