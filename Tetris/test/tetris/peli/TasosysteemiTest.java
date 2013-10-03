/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.peli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Vaikeustaso;

/**
 *
 * @author Johannes
 */
public class TasosysteemiTest {
    
    Tasosysteemi ts;
    
    public TasosysteemiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ts=new Tasosysteemi(new Tetris(14, 25, Vaikeustaso.ALOITTELIJA));
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void metodiKasvataVaikeustasoaKymmenenTuhotunRivinValein() {
        ts.setVaikeustaso(Vaikeustaso.NORMAALI);
        ts.getTetris().setTuhottujenRivienMaara(9);
        ts.kasvataVaikeustasoa();
        assertEquals(Vaikeustaso.NORMAALI, ts.getVaikeustaso());
        ts.getTetris().setTuhottujenRivienMaara(10);
        ts.kasvataVaikeustasoa();
        assertEquals(Vaikeustaso.EDISTYNYT, ts.getVaikeustaso());
    }
    
    @Test
    public void metodiKasvataVaikeustasoaTunnistaaKorkeimmanTason() {
        ts.setVaikeustaso(Vaikeustaso.NIRVANA);
        ts.getTetris().setTuhottujenRivienMaara(10);
        ts.kasvataVaikeustasoa();
        assertEquals(Vaikeustaso.NIRVANA, ts.getVaikeustaso());
    }
    
}
