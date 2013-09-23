
package tetris.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.peli.Peli;

/**
 *
 * @author Johannes
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Peli tetris;
    private int palanPituus;
    private Piirtoalusta piirtoalusta;
    
    public Kayttoliittyma(Peli tetris, int sivunPituus) {
        this.tetris=tetris;
        this.palanPituus=sivunPituus;
    }

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
