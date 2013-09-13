
package tetris.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.peli.Tetris;

/**
 *
 * @author Johannes
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Tetris tetris;
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;
    
    public Kayttoliittyma(Tetris tetris, int sivunPituus) {
        this.tetris=tetris;
        this.sivunPituus=sivunPituus;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        int leveys = (tetris.getLeveys() + 1) * sivunPituus;
        int korkeus = (tetris.getKorkeus() + 2) * sivunPituus;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(tetris, sivunPituus);
        container.add(piirtoalusta);
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(this.tetris);
        frame.addKeyListener(kuuntelija);
    }
    
     public Paivitettava getPaivitettava() {
        return this.piirtoalusta;
    }
    
}
