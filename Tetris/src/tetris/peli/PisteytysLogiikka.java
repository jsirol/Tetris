package tetris.peli;

import tetris.Vaikeustaso;

/**
 *
 * @author Johannes
 */
public class PisteytysLogiikka {

    public PisteytysLogiikka() {
    }

    public int pisteitaTuhottujenRivienJaVaikeustasonMukaan(int tuhotutRivit, Vaikeustaso vaikeustaso) {
        if (vaikeustaso.getTasoNro() < 8) {
            return (int) Math.floor(this.tuhottujaRivejaVastaavaPeruspistemaara(tuhotutRivit) * (vaikeustaso.getTasoNro() + 1)*0.25);
        } else if (vaikeustaso.getTasoNro() == 8) {
            return (int) Math.floor(this.tuhottujaRivejaVastaavaPeruspistemaara(tuhotutRivit) * 8);
        } else {
            return (vaikeustaso.getTasoNro()-6) * this.tuhottujaRivejaVastaavaPeruspistemaara(tuhotutRivit);
        }
    }

    public int tuhottujaRivejaVastaavaPeruspistemaara(int tuhotutRivit) {
        if (tuhotutRivit == 0) {
            return 0;
        } else if (tuhotutRivit == 1) {
            return 100;
        } else if (tuhotutRivit == 2) {
            return 250;
        } else if (tuhotutRivit == 3) {
            return 450;
        } else {
            return 800;
        }
    }
}
