package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.domain.Pala;
import tetris.peli.Tetris;

/**
 * Luokka joka määrittelee piirtoalusta, joka piirtää näytölle tarvittavat objektit.
 * Luokka perii luokan JPanel ja toteuttaa rajapinnan Paivitettava.
 * 
 * @author Johannes
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    /**
     * sisältää tiedot piirrettäväistä objekteista
     */
    private Tetris tetris;
    
    /**
     * palan sivun pituus
     */
    private int palanPituus;

    public Piirtoalusta(Tetris tetris, int palanPituus) {
        this.tetris = tetris;
        this.palanPituus = palanPituus;
    }

    /**
     * Metodi piirtää piirtoalustan komponentit uudelleen.
     */
    @Override
    public void paivita() {
        super.repaint();
    }

    /**
     * Metodi määrittelee mitä tapahtuu kun yliluokan paintComponent-metodia kutsutaan.
     * 
     * @param g mihin komponenttiin piirretään
     * 
     * @see tetris.gui.Piirtoalusta#piirraPutoavaKuvio(java.awt.Graphics) 
     * @see tetris.gui.Piirtoalusta#piirraRivit(java.awt.Graphics) 
     * @see tetris.gui.Piirtoalusta#piirraLopetusIlmoitus(java.awt.Graphics) 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.piirraPutoavaKuvio(g);
        this.piirraRivit(g);
        if (!tetris.getPeliKaynnissa()) {
            this.piirraLopetusIlmoitus(g);
        }
    }

    /**
     * Metodi määrittelee miten putoava kuvio piirretään.
     * 
     * @param g komponentti johon piirretään
     */
    private void piirraPutoavaKuvio(Graphics g) {
        g.setColor(this.tetris.getKuvio().getPalat().get(0).getVari());
        for (Pala pala : this.tetris.getKuvio().getPalat()) {
            g.fill3DRect(pala.getX() * palanPituus, pala.getY() * palanPituus, palanPituus, palanPituus, true);
        }
    }

    /**
     * Metodi määrittelee miten Tetriksen rivit piirretään.
     * 
     * @param g komponentti johon piirretään 
     */
    private void piirraRivit(Graphics g) {
        for (Pala pala : this.tetris.getRivit().getPalat()) {
            g.setColor(pala.getVari());
            g.fill3DRect(pala.getX() * palanPituus, pala.getY() * palanPituus, palanPituus, palanPituus, true);
        }
    }

    /**
     * Metodi määrittelee miten pelin päättyessä piirrettävä pelin päättymisen ilmaiseva viesti piirretään.
     * 
     * @param g komponentti johon piirretään 
     */
    private void piirraLopetusIlmoitus(Graphics g) {
        g.setColor(Color.BLACK);
        char[] kirjaimet = {'P', 'E', 'L', 'I', ' ', ' ', ' ', ' ', ' ', 'L', 'O', 'P', 'P', 'U', 'I'};
        g.drawChars(kirjaimet, 0, 15, 6 * palanPituus, 8 * palanPituus);
    }
    
}