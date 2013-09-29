package tetris;

/**
 * Enum-luokka määrittelee Tetriksessä käytettävät vaikeustasot.
 *
 * @author Johannes
 */
public enum Vaikeustaso {

    ALOITTELIJA(1),
    HELPPO(2),
    NORMAALI(3),
    EDISTYNYT(4),
    VAIKEA(5),
    ERITTAINVAIKEA(6),
    KOVAKSIKEITETTY(7),
    NOLIFE(8),
    UBER(9),
    NIRVANA(10);
    /**
     * Kokonaisluku tiivistää vaikeustason yhteen lukuun. Pienempi on helpompi.
     */
    private final int tasoNro;

    private Vaikeustaso(int tasoNro) {
        this.tasoNro = tasoNro;
    }

    public int getTasoNro() {
        return this.tasoNro;
    }

    /**
     * Metodi palauttaa paluuarvonaan parametrina annettua tasonumeroa vastaavan
     * vaikeustason.
     *
     * @param tasoNro tasonumero
     *
     * @return tasonumeroa vastaava vaikeustaso
     */
    public Vaikeustaso getTasoNroaVastaavaVaikeustaso(int tasoNro) {
        if (tasoNro == 1) {
            return Vaikeustaso.ALOITTELIJA;
        } else if (tasoNro == 2) {
            return Vaikeustaso.HELPPO;
        } else if (tasoNro == 3) {
            return Vaikeustaso.NORMAALI;
        } else if (tasoNro == 4) {
            return Vaikeustaso.EDISTYNYT;
        } else if (tasoNro == 5) {
            return Vaikeustaso.VAIKEA;
        } else if (tasoNro == 6) {
            return Vaikeustaso.ERITTAINVAIKEA;
        } else if (tasoNro == 7) {
            return Vaikeustaso.KOVAKSIKEITETTY;
        } else if (tasoNro == 8) {
            return Vaikeustaso.NOLIFE;
        } else if (tasoNro == 9) {
            return Vaikeustaso.UBER;
        } else if (tasoNro == 10) {
            return Vaikeustaso.NIRVANA;
        } else {
            return null;
        }
    }

    /**
     * Metodi palauttaa sitä kutsuvaa Vaikeustasoa seuraavan Vaikeustason.
     * Jos vaikeustaso on jo korkein, palautetaan korkein vaikeustaso.
     *
     * @return seuraavaksi vaikein vaikeustaso
     */
    public Vaikeustaso seuraavaksiVaikein() {
        if (this.getTasoNro() < 10) {
            return this.getTasoNroaVastaavaVaikeustaso(this.getTasoNro()+1);
        } else {
            return Vaikeustaso.NIRVANA;
        }
    }
}
