package tetris.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tetris.Vari;
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
    public void kuvioSiirtyyOikeinSivuttain() {
        kuvio.siirraKuviotaSivuttain(1);
        kuvio.siirraKuviotaSivuttain(-1);
        kuvio.siirraKuviotaSivuttain(-1);
        assertEquals("(-3,10), (-1,0), (-4,-6), (0,-1)", kuvio.toString());
    }
    
    @Test
    public void metodiGetRivinPalatPalauttaaOikeatPalat() {     
        ArrayList<Pala> rivinOikeatPalat = new ArrayList<Pala>();
        rivinOikeatPalat.add(palautaKuvionPaikassaOlevaPala(1));
        assertEquals(rivinOikeatPalat, kuvio.getRivinPalat(0));
    }

    @Test
    public void metodiPoistaPalaPoistaaOikeanPalan() {
        Pala poistettavaPala = new Pala(2, 4, Vari.ORANSSI);
        Pala lisattavaPala = new Pala(9, 9, Vari.ORANSSI);
        kuvio.lisaaPala(poistettavaPala);
        kuvio.lisaaPala(lisattavaPala);
        kuvio.poistaPala(poistettavaPala);       
        assertEquals(lisattavaPala, palautaKuvionPaikassaOlevaPala(4));
    }

    @Test
    public void metodiGetPalaJollaSuurinXKoordinaattiToimii() {
        assertEquals(palautaKuvionPaikassaOlevaPala(3), kuvio.getPalaJollaSuurinXKoordinaatti());
    }

    @Test
    public void metodiGetPalaJollaSuurinYKoordinaattiToimii() {
        assertEquals(palautaKuvionPaikassaOlevaPala(0), kuvio.getPalaJollaSuurinYKoordinaatti());
    }

    @Test
    public void metodiGetPalaJollaPieninXKoordinaattiToimii() {
        assertEquals(palautaKuvionPaikassaOlevaPala(2), kuvio.getPalaJollaPieninXKoordinaatti());
    }

    @Test
    public void metodiGetPalaJollaPieninYKoordinaattiToimii() {
        assertEquals(palautaKuvionPaikassaOlevaPala(2), kuvio.getPalaJollaPieninYKoordinaatti());
    }
    
    
    private Pala palautaKuvionPaikassaOlevaPala(int paikkanro) {
        return this.kuvio.getPalat().get(paikkanro);
    }
    
    
}
