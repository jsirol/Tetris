package tetris.peli;

import tetris.Vaikeustaso;

/**
 *
 * @author Johannes
 */
public class PisteytysLogiikka {
    
    private int kombot;
    
    public PisteytysLogiikka() {
        this.kombot=-1;
    }

    //kombokerroin = kerroin*50*vaikeustaso.getTasoNro(). Esim lvl 7, saadaan kerroin *50*7 pistettä. Kerroin=1 kun tuhottu edellisellä vuorolla 1 rivi.
    public int pisteitaTuhottujenRivienJaVaikeustasonMukaan(int tuhotutRivit, Vaikeustaso vaikeustaso) {
        int pisteet=this.tuhottujaRivejaVastaavaPeruspistemaara(tuhotutRivit);
        int kerroin=0;
        if(this.kombot>0) {
            kerroin = this.kombot;
        }
        if (vaikeustaso.getTasoNro() < 8) {
            return (int) Math.floor(pisteet * (vaikeustaso.getTasoNro() + 1)*0.25) + kerroin*50*vaikeustaso.getTasoNro();
        } else if (vaikeustaso.getTasoNro() == 8) {
            return (int) Math.floor(pisteet * 8) + kerroin*50*vaikeustaso.getTasoNro();
        } else {
            return (vaikeustaso.getTasoNro()-6) * pisteet + kerroin*50*vaikeustaso.getTasoNro();
        }
    }

    public int tuhottujaRivejaVastaavaPeruspistemaara(int tuhotutRivit) {
        if (tuhotutRivit == 0) {      
            this.kombot=-1;
            return 0;
        } else if (tuhotutRivit == 1) {            
            this.kombot++;
            return 100;  
        } else if (tuhotutRivit == 2) {     
            this.kombot++;
            return 250;
        } else if (tuhotutRivit == 3) {        
            this.kombot++;
            return 450;
        } else {        
            this.kombot++;
            return 800;
        }
    }
}
