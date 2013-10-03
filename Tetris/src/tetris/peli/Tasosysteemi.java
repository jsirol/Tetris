package tetris.peli;

import tetris.Vaikeustaso;

/**
 * Luokkan vastuulla on Tetriksen vaikeustason päivittäminen tarpeen vaatiessa.
 *
 * @author Johannes
 */
public class Tasosysteemi {

    /**
     * Tetris johon tasosysteemi liittyy.
     */
    private Tetris tetris;
    /**
     * Tetrikselle asetettava vaikeustaso.
     */
    private Vaikeustaso vaikeustaso;

    public Tasosysteemi(Tetris tetris) {
        this.tetris = tetris;
        this.vaikeustaso = Vaikeustaso.ALOITTELIJA;
    }

    public Tetris getTetris() {
        return this.tetris;
    }

    public Vaikeustaso getVaikeustaso() {
        return this.vaikeustaso;
    }

    /**
     * Metodi kasvattaa tetriksen vaikeustasoa yhdellä jos se ei vielä ole
     * korkein mahdollinen. Vaikeustason ollessa korkein mahdollinen metodi ei
     * tee mitään.
     *
     * @see tetris.peli.Tetris#nollaaTuhotutRivit()
     * @see tetris.Vaikeustaso#seuraavaksiVaikein()
     */
    public void kasvataVaikeustasoa() {
        if (this.vaikeustaso.getTasoNro() < 10 && this.tetris.getTuhottujenRivienMaara() == 10) {
            this.setVaikeustaso(vaikeustaso.seuraavaksiVaikein());
            this.tetris.nollaaTuhotutRivit();
        }
    }

    /**
     * Metodi asettaa parametrikseen annettavan vaikeustason Tetrikselle ja
     * nostaa samalla Tetriksen nopeutta.
     *
     * @param vaikeustaso Tetrikselle asetettava vaikeustaso
     */
    public void setVaikeustaso(Vaikeustaso vaikeustaso) {
        this.vaikeustaso = vaikeustaso;
        if (vaikeustaso == Vaikeustaso.NIRVANA) {
            this.tetris.setDelay(90);
        } else if (vaikeustaso == Vaikeustaso.UBER) {
            this.tetris.setDelay(105);
        } else if (vaikeustaso == Vaikeustaso.NOLIFE) {
            this.tetris.setDelay(120);
        } else if (vaikeustaso == Vaikeustaso.KOVAKSIKEITETTY) {
            this.tetris.setDelay(150);
        } else if (vaikeustaso == Vaikeustaso.ERITTAINVAIKEA) {
            this.tetris.setDelay(280);
        } else if (vaikeustaso == Vaikeustaso.VAIKEA) {
            this.tetris.setDelay(210);
        } else if (vaikeustaso == Vaikeustaso.EDISTYNYT) {
            this.tetris.setDelay(240);
        } else if (vaikeustaso == Vaikeustaso.NORMAALI) {
            this.tetris.setDelay(270);
        } else if (vaikeustaso == Vaikeustaso.HELPPO) {
            this.tetris.setDelay(300);
        } else {
            this.tetris.setDelay(330);
        }
    }
}
