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
import tetris.Vari;
import tetris.domain.Kuvio;
import tetris.domain.Pala;
import tetris.domain.Palkki;

/**
 *
 * @author Johannes
 */
public class PeliTest {

    Peli tetris;

    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tetris = new Peli(20, 30);
        this.taytaKaksiAlintaRivia();
        this.tetris.getRivit().lisaaPala(new Pala(7, 5, Vari.KELTAINEN));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiPudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyyToimiiOikein() {
        tetris.pudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyy(29);
        assertEquals("[(7,27)]", this.tetris.getRivit().getRivinPalat(27).toString());
    }
    
    @Test
    public void metodituhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspainToimiiOikein() {
        tetris.tuhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspain();
        assertEquals("[(7,29)]", this.tetris.getRivit().getRivinPalat(29).toString());
    }
    
    @Test
    public void metodiPutoavaKuvioAlimmallaRivillaToimiiOikein() {
        assertEquals(false, tetris.putoavaKuvioAlimmallaRivilla(tetris.getKuvio()));
        tetris.setKuvio(new Palkki(4,27));
        assertEquals(true, tetris.putoavaKuvioAlimmallaRivilla(tetris.getKuvio()));
    }
    
    @Test
    public void metodiPutoavaKuvioKiinniYlimmassaRivissaToimiiOikein() {
        tetris.setKuvio(new Palkki(5,-3));
        assertEquals(false, tetris.kuvioKiinniYlimmassaRivissa(tetris.getKuvio()));
        this.taytaYlinRivi();
        assertEquals(true, tetris.kuvioKiinniYlimmassaRivissa(tetris.getKuvio()));
    }
    
    @Test
    public void metodiPutoavaKuvioKiinniTietyssaRivissaToimiiOikein() {
        assertEquals(false, tetris.kuvioKiinniTietyssaRivissa(29, tetris.getKuvio()));
        tetris.setKuvio(new Palkki(5,26));
        assertEquals(true, tetris.kuvioKiinniTietyssaRivissa(29, tetris.getKuvio()));
    }
    
    @Test
    public void metodiKuviotaVoiSiirtaaSuuntaanOsumattaRiviinToimiiOikein() {
        assertEquals(true, tetris.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1));
        tetris.setKuvio(new Palkki(2,26));
        assertEquals(false, tetris.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1));
        assertEquals(false, tetris.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(-1));
        
    }
    
    @Test
    public void metodiPudotaPutoavaKuvioNiinAlasKuinVoiToimiiOikein() {
        tetris.setKuvio(new Palkki(5,5));
        tetris.pudotaKuvioNiinAlasKuinVoi(tetris.getKuvio());
        assertEquals("[(5,23), (5,24), (5,25), (5,26), (5,27)]", tetris.getKuvio().getPalat().toString());
    }
    
    
    public void taytaKaksiAlintaRivia() {
        for (int j = 29; j > 27; j--) {
            for (int i = 0; i < 20; i++) {
                tetris.getRivit().lisaaPala(new Pala(i, j, Vari.SININEN));
            }
        }
    }
    
    public void taytaYlinRivi() {
        for (int i = 0; i < 20; i++) {
                tetris.getRivit().lisaaPala(new Pala(i, 0, Vari.SININEN));
            }
    }
    
}
