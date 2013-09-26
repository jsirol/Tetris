package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.peli.Tetris;

/**
 * Luokka määrittelee näppäimistönkuuntelijan ja sen toiminnallisuuden.
 * 
 * @author Johannes
 */
public class Nappaimistonkuuntelija implements KeyListener {

    /**
     * kuunneltava objekti
     */
    private Tetris tetris;
    
    /**
     * muuttuja sisältää tiedon siitä, 
     * onko peli keskeytetty (huom! eri kuin loppunut)
     */
    private boolean keskeytetty;

    public Nappaimistonkuuntelija(Tetris tetris) {
        this.tetris = tetris;
        this.keskeytetty = false;
    }

    /**
     * Metodi määrittelee mitä tapahtuu 
     * kun tiettyjä näppäimiä painetaan näppäimistöllä.
     * 
     * @param e näppäimen painallus
     * 
     * @see tetris.peli.Tetris#kaannaKuviota() 
     * @see tetris.peli.Tetris#pudotaKuvioNiinAlasKuinVoi(tetris.domain.Kuvio) 
     * @see tetris.peli.Tetris#liikutaKuviotaVasemmalle() 
     * @see tetris.peli.Tetris#liikutaKuviotaOikealle() 
     * @see tetris.peli.Tetris#pudotaKuvioNiinAlasKuinVoi(tetris.domain.Kuvio) 
     * @see tetris.peli.Tetris#pudotaKuviotaAlasYhdellaJosVoi(tetris.domain.Kuvio) 
     * @see tetris.gui.Nappaimistonkuuntelija#keskeytaTaiJatkaPelia() 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (this.tetris.getPeliKaynnissa()) {
            if (!this.keskeytetty) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.tetris.kaannaKuviota();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    this.tetris.pudotaKuvioNiinAlasKuinVoi(tetris.getKuvio());
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.tetris.liikutaKuviotaVasemmalle();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.tetris.liikutaKuviotaOikealle();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.tetris.pudotaKuviotaAlasYhdellaJosVoi(tetris.getKuvio());
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.keskeytaTaiJatkaPelia();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodi keskeyttää tetriksen laskurin jos se on käynnissä.
     * Jos laskuri ei ole käynnissä, metodi käynnistää sen.
     */
    private void keskeytaTaiJatkaPelia() {
        if (!this.keskeytetty) {
            this.tetris.stop();
            this.keskeytetty = true;
        } else {
            this.tetris.start();
            this.keskeytetty = false;
        }
    }
}
