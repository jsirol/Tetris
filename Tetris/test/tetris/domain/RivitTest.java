package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tetris.Vari;
import static org.junit.Assert.*;
import tetris.domain.Pala;
import tetris.domain.Rivit;

/**
 *
 * @author Johannes
 */
public class RivitTest {

    Rivit rivit;

    public RivitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //lisätään 2 täyttä riviä joissa palojen y-koordinaatit ovat 4,5,6
    @Before
    public void setUp() {
        rivit = new Rivit(20, 30);
        for (int j = -1; j < 2; j++) {
            for (int i = 0; i < 20; i++) {
                rivit.lisaaPala(new Pala(i, 5 + j, Vari.SININEN));
            }
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiTuhoaRiviToimiiOikein() {
        rivit.tuhoaRivi(5);
        assertEquals("[]", rivit.getRivinPalat(5).toString());        
    }
    
    @Test
    public void metodiRiviTaysiToimiiOikein() {
        assertEquals(true, this.rivit.riviTaysi(4, 20));
        assertEquals(false, this.rivit.riviTaysi(10, 20));
    }
}
