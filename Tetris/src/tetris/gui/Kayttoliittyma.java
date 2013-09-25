
package tetris.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.peli.Tetris;

/**
 *Luokassa määritellään minkälainen käyttöliittymä pelissä on.
 * 
 * @author Johannes
 */
public class Kayttoliittyma implements Runnable {
    
    /**
     * Käyttöliittymän kehys.
     */
    private JFrame frame;
    
    /**
     * Käyttöliittymään liittyvä peli
     */
    private Tetris tetris;
    
    /**
     * Yksittäisen palan sivun pituus, 
     * joka määrittää osaltaan miten iso käyttöliittymästä luodaan
     */
    private int palanPituus;
    
    /**
     * Piirtoalusta johon piirretään pelissä tarvittavat objektit.
     */
    private Piirtoalusta piirtoalusta;
    
    public Kayttoliittyma(Tetris tetris, int sivunPituus) {
        this.tetris=tetris;
        this.palanPituus=sivunPituus;
    }

    /**
     * Metodi määrittelee mitä tapahtuu kun peli käynnistetään.
     * 
     */
    @Override
    public void run() {
        frame = new JFrame("Tetris");
        int leveys = (tetris.getLeveys()+1) * palanPituus;
        int korkeus = (tetris.getKorkeus()+2) * palanPituus;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi joka luo piirtoalustan ja lisää sen käyttöliittymäkehykseen.
     * Lisäksi kehykseen asetetaan näppäimistönkuuntelija.
     * 
     * @param container objekti johon piirtoalusta lisätään
     */
    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(tetris, palanPituus);
        container.add(piirtoalusta);
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(this.tetris);
        frame.addKeyListener(kuuntelija);
    }
    
     public Paivitettava getPaivitettava() {
        return this.piirtoalusta;
    }
    
}
