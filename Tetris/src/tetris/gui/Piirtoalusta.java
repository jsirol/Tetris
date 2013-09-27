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
     * @see tetris.gui.Piirtoalusta#tyhjennaPelialue(java.awt.Graphics) 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.piirraPutoavaKuvio(g);
        this.piirraRivit(g);
        this.piirraPaavalikonErottavaViiva(g);
        this.piirraAlareunanTyhjanTilanTayttavaViiva(g);
        if(!tetris.getPeliKaynnissa()) {
            this.piirraPelinPaattymisIlmoitus(g);
        }
        if (tetris.getUusiKierrosAlkoi()) {
            this.tyhjennaPelialue(g);            
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
     * Metodi tyhjentää piirtoalustan.
     * 
     * @param g komponentti johon piirretään 
     */
    private void tyhjennaPelialue(Graphics g) {        
        g.clearRect(0, 0, (tetris.getLeveys() + 1) * palanPituus, (tetris.getKorkeus() + 2) * palanPituus);
    }
    /**
     * Metodi piirtää piirtoalustaan pelin päättyessä 
     * ilmoituksen pelin päättymisestä ja ohjeet jatkamisesta.
     * 
     * @param g komponentti johon piirretään
     */
    private void piirraPelinPaattymisIlmoitus(Graphics g) {
        g.setColor(Color.BLACK);    
        g.drawString("PELI LOPPUI!", 6 * palanPituus, 8 * palanPituus);
        g.drawString("PAINA MITÄ TAHANSA NÄPPÄINTÄ JATKAAKSESI", 2 * palanPituus, 10 * palanPituus);
    }
    
    /**
     * Metodi piirtää Paavalikon ja itse tetris pelin väliin pystysuoran viivan.
     * 
     * @param g komponentti johon piirretään
     */
    private void piirraPaavalikonErottavaViiva(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((tetris.getLeveys()) * palanPituus, 0, 10, (tetris.getKorkeus() + 2) * palanPituus);     
    }
    
    /**
     * Metodi piiraa piirtoalustan kehyksen alalaitaan viivan, 
     * joka täyttää Tetriksen pelialueen alle jäävän tyhjän tilan.
     * @param g 
     */
    private void piirraAlareunanTyhjanTilanTayttavaViiva(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, tetris.getKorkeus() * palanPituus, tetris.getLeveys() * palanPituus, 11);
    }
    
}