package tetris.peli;

import tetris.Vaikeustaso;

/**
 *
 * @author Johannes
 */
public class Tasosysteemi {

    private Tetris tetris;
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
    
    public void kasvataVaikeustasoa() {
        if(this.vaikeustaso.getTasoNro()<10 && this.tetris.getTuhottujenRivienMaara()==10) {
            this.setVaikeustaso(vaikeustaso.seuraavaksiVaikein());
            this.tetris.nollaaTuhotutRivit();
        }       
    }

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
