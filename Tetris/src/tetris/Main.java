package tetris;

import javax.swing.SwingUtilities;
import tetris.gui.Kayttoliittyma;
import tetris.peli.Tetris;

/**
 * Luokka luo Tetriksen käynnistämiseen tarvittavat oliot ja käynnistää
 * Tetris-pelin.
 *
 * @author Johannes
 */
public class Main {

    public static void main(String[] args) {

        Tetris testi = new Tetris(14, 25, Vaikeustaso.ALOITTELIJA);

        Kayttoliittyma kayttis = new Kayttoliittyma(testi, 25);
        SwingUtilities.invokeLater(kayttis);


        while (kayttis.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        testi.setPaivitettava(kayttis.getPaivitettava());
                
    }
}
