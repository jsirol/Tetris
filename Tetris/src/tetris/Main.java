
package tetris;

import javax.swing.SwingUtilities;
import tetris.gui.Kayttoliittyma;
import tetris.peli.Peli;





/**
 *
 * @author Johannes
 */
public class Main {


    public static void main(String[] args) {
        
        Peli testi = new Peli(15, 20);
        
        Kayttoliittyma kali = new Kayttoliittyma(testi, 20);                     //toinen parametri palan sivun pituus
        SwingUtilities.invokeLater(kali);
        

        while (kali.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        testi.setPaivitettava(kali.getPaivitettava()); 
        testi.start();    
        
    }
}
