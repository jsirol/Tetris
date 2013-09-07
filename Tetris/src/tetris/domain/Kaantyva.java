
package tetris.domain;

/**
 *
 * @author Johannes
 */
public interface Kaantyva {
    
    //kääntää kääntyvää
    public void kaanna();
    
    //palauttaa palan joka on kaantyvan rotaatiopisteenä (pysyy paikallaan käännöksissä)
    public Pala getRotaatioPisteenaOlevaPala();
   
}
