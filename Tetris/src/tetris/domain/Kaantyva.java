
package tetris.domain;

/**
 *Rajapintaluokka määrittelee toiminnallisuuden Tetriksen kuvioille.
 * 
 * @author Johannes
 */
public interface Kaantyva {
    
    /**
     * Määrittää metodin, jonka on tarkoitus kääntää käännettävää kuviota.
     */   
    public void kaanna();
    
    /**
     * Metodi palauttaa paluuarvonaan palan, joka on kaantyvan kuvion rotaatiopisteenä (pysyy paikallaan käännöksissä).
     * @return rotaatiopisteenä oleva pala
     */    
    public Pala getRotaatioPisteenaOlevaPala();
   
}
