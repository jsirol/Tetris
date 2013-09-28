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
            System.out.println(this.tetris.getTuhottujenRivienMaara());
            this.tetris.nollaaTuhotutRivit();
        }       
    }

    public void setVaikeustaso(Vaikeustaso vaikeustaso) {
        this.vaikeustaso = vaikeustaso;
        if (vaikeustaso == Vaikeustaso.JUMALMOODI) {
            this.tetris.setDelay(90);
        } else if (vaikeustaso == Vaikeustaso.UBER) {
            this.tetris.setDelay(110);
        } else if (vaikeustaso == Vaikeustaso.KOVAKSIKEITETTY) {
            this.tetris.setDelay(140);
        } else if (vaikeustaso == Vaikeustaso.NOLIFE) {
            this.tetris.setDelay(170);
        } else if (vaikeustaso == Vaikeustaso.ERITTAINVAIKEA) {
            this.tetris.setDelay(200);
        } else if (vaikeustaso == Vaikeustaso.VAIKEA) {
            this.tetris.setDelay(230);
        } else if (vaikeustaso == Vaikeustaso.EDISTYNYT) {
            this.tetris.setDelay(260);
        } else if (vaikeustaso == Vaikeustaso.NORMAALI) {
            this.tetris.setDelay(290);
        } else if (vaikeustaso == Vaikeustaso.HELPPO) {
            this.tetris.setDelay(320);
        } else {
            this.tetris.setDelay(350);
        }
    }
}
