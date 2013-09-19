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
            if (!this.keskeytetty) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.tetris.kaannaKuviota();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    this.tetris.pudotaPutoavaKuvioNiinAlasKuinVoi();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.tetris.liikutaKuviotaVasemmalle();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.tetris.liikutaKuviotaOikealle();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.tetris.pudotaKuviotaAlasYhdellaJosVoi();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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

    private void peliTauolle() {
        if (!this.keskeytetty) {
            this.tetris.stop();
            this.keskeytetty = true;
        } else {
            this.tetris.start();
            this.keskeytetty = false;
        }

    }
}
