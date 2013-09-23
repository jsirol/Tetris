package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.domain.Pala;
import tetris.peli.Peli;

/**
 *
 * @author Johannes
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli tetris;
    private int palanPituus;

    public Piirtoalusta(Peli tetris, int palanPituus) {
        this.tetris = tetris;
        this.palanPituus = palanPituus;
    }

    @Override
    public void paivita() {
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.piirraPutoavaKuvio(g);
        this.piirraRivit(g);
        if (!tetris.peliKaynnissa()) {
            this.piirraLopetusIlmoitus(g);
        }
    }

    private void piirraPutoavaKuvio(Graphics g) {
        g.setColor(this.tetris.getKuvio().getPalat().get(0).getVari());
        for (Pala pala : this.tetris.getKuvio().getPalat()) {
            g.fill3DRect(pala.getX() * palanPituus, pala.getY() * palanPituus, palanPituus, palanPituus, true);
        }
    }

    private void piirraRivit(Graphics g) {
        for (Pala pala : this.tetris.getRivit().getPalat()) {
            g.setColor(pala.getVari());
            g.fill3DRect(pala.getX() * palanPituus, pala.getY() * palanPituus, palanPituus, palanPituus, true);
        }
    }

    private void piirraLopetusIlmoitus(Graphics g) {
        g.setColor(Color.BLACK);
        char[] kirjaimet = {'P', 'E', 'L', 'I', ' ', ' ', ' ', ' ', ' ', 'L', 'O', 'P', 'P', 'U', 'I'};
        g.drawChars(kirjaimet, 0, 15, 6 * palanPituus, 8 * palanPituus);
    }
    
}