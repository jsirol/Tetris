package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.peli.Tetris;

/**
 *
 * @author Johannes
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Tetris tetris;
    private boolean keskeytetty;

    public Nappaimistonkuuntelija(Tetris tetris) {
        this.tetris = tetris;
        this.keskeytetty = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.tetris.peliKaynnissa()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.kaannaKuviota();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.pudotaPutoavaKuvioNiinAlasKuinVoi();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {               
                this.liikutaKuviotaVasemmalle();          
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {            
                this.liikutaKuviotaOikealle();              
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {               
                this.pudotaKuviotaAlasYhdellaJosVoi();           
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.peliTauolle();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    private void liikutaKuviotaVasemmalle() {
        if(this.tetris.KuviotaVoiSiirtaaVasemmalle()) {
            this.tetris.getKuvio().siirraVasemmalle();
        }
    }
    
    private void pudotaKuviotaAlasYhdellaJosVoi() {
        if(!this.tetris.PutoavaKuvioKiinniLattiassa(this.tetris.getKuvio()) && !this.tetris.putoavaKuvioKiinniJossainRivissa(this.tetris.getKuvio())) {
            this.tetris.getKuvio().pudotaYhdella();
        }
    }
    
    private void liikutaKuviotaOikealle() {
        if(this.tetris.KuviotaVoiSiirtaaOikealle()) {
            this.tetris.getKuvio().siirraOikealle();
        }
    }
    
    private void kaannaKuviota() {
        if(this.tetris.PutoavaaKuviotaVoiKaantaa()) {
            this.tetris.getKuvio().kaanna();
        }
    }

    private void pudotaPutoavaKuvioNiinAlasKuinVoi() {
        while (true) {
            if (this.keskeytetty) {
                break;
            }
            if (!this.tetris.PutoavaKuvioAlimmallaRivilla(this.tetris.getKuvio()) && !this.tetris.putoavaKuvioKiinniJossainRivissa(this.tetris.getKuvio())) {
                this.tetris.getKuvio().pudotaYhdella();
            } else {
                break;
            }
        }
    }

    private void peliTauolle() {
        if (!this.keskeytetty) {
            this.tetris.stop();
            this.keskeytetty=true;
        } else {
            this.tetris.start();
            this.keskeytetty=false;
        }

    }
}
