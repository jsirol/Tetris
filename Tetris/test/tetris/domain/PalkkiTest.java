package tetris.domain;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Vari;
import tetris.domain.Palkki;

/**
 *
 * @author Johannes
 */
public class PalkkiTest {

    Palkki palkki;

    public PalkkiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        palkki = new Palkki(5, -2);  
    }

    @After
    public void tearDown() {
    }

    @Test
    public void palkkiLuodaanOikeaanPaikkaan() {
        assertEquals("(5,-3), (5,-2), (5,-1), (5,0)", palkki.toString());
    }

    @Test
    public void getRotaatioPisteenaOlevaPalaPalauttaaOikeanPalan() {
        Pala rotaatioPiste = palkki.getPalat().get(1);
        assertEquals(rotaatioPiste, palkki.getRotaatioPisteenaOlevaPala());
    }

    @Test
    public void metodiKaannaKaantaaPalkinOikein() {
        palkki.kaanna();
        assertEquals("(4,-2), (5,-2), (6,-2), (7,-2)", palkki.toString());
        palkki.kaanna();
        assertEquals("(5,-3), (5,-2), (5,-1), (5,0)", palkki.toString());
    }

    @Test
    public void metodiOnVaakaAsennossaToimii() {
        assertEquals(false, palkki.onVaakaAsennossa());
        palkki.kaanna();
        assertEquals(true, palkki.onVaakaAsennossa());
    }
    
    
}
