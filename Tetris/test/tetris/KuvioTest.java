package tetris;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.Kuvio;
import tetris.domain.Pala;

/**
 *
 * @author Johannes
 */
public class KuvioTest {

    Kuvio kuvio;
    Pala pala;

    public KuvioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //koordinaateissa (10,10),(10,11),(11,10),(11,11) oleva keltainen "neliö"
    @Before
    public void setUp() {
        kuvio = new Kuvio();
        kuvio.lisaaPala(new Pala(-2, 10, Vari.KELTAINEN));
        kuvio.lisaaPala(new Pala(0, 0, Vari.KELTAINEN));
        kuvio.lisaaPala(new Pala(-3, -6, Vari.KELTAINEN));
        kuvio.lisaaPala(new Pala(1, -1, Vari.KELTAINEN));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void palatLisataanKuvioonOikein() {
        assertEquals("(-2,10), (0,0), (-3,-6), (1,-1)", kuvio.toString());
    }

    @Test
    public void kuvioPutoaaOikein() {
        kuvio.pudotaYhdella();
        kuvio.pudotaYhdella();
        assertEquals("(-2,12), (0,2), (-3,-4), (1,1)", kuvio.toString());
    }

    @Test
    public void kuvioSiirtyyOikeinVasemmalle() {
        kuvio.siirraVasemmalle();
        assertEquals("(-3,10), (-1,0), (-4,-6), (0,-1)", kuvio.toString());
    }

    @Test
    public void kuvioSiirtyyOikeinOikealle() {
        kuvio.siirraOikealle();
        assertEquals("(-1,10), (1,0), (-2,-6), (2,-1)", kuvio.toString());
    }

    @Test
    public void getPalatPalauttaaOikeatPalat() {
        assertEquals("[(-2,10), (0,0), (-3,-6), (1,-1)]", kuvio.getPalat().toString());
    }

    @Test
    public void poistaPalaPoistaaOikeanPalan() {
        Pala poistettavaPala = new Pala(2, 4, Vari.ORANSSI);
        Pala lisattavaPala = new Pala(9, 9, Vari.ORANSSI);
        kuvio.lisaaPala(poistettavaPala);
        kuvio.lisaaPala(lisattavaPala);
        kuvio.poistaPala(poistettavaPala);
        assertEquals("(-2,10), (0,0), (-3,-6), (1,-1), (9,9)", kuvio.toString());
    }

    @Test
    public void getSuurimmanXKoordinaatinPalaToimii() {
        assertEquals("(1,-1)", kuvio.getSuurimmanXKoordinaatinPala().toString());
    }

    @Test
    public void getSuurimmanYKoordinaatinPalaToimii() {
        assertEquals("(-2,10)", kuvio.getSuurimmanYKoordinaatinPala().toString());
    }

    @Test
    public void getPienimmanXKoordinaatinPalaToimii() {
        assertEquals("(-3,-6)", kuvio.getPienimmänXKoordinaatinPala().toString());
    }

    @Test
    public void getPienimmanYKoordinaatinPalaToimii() {
        assertEquals("(-3,-6)", kuvio.getPienimmänYKoordinaatinPala().toString());
    }
}
