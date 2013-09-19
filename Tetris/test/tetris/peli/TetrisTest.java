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
public class TetrisTest {

    Tetris tetris;

    public TetrisTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tetris = new Tetris(20, 30);
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
        assertEquals(false, tetris.putoavaKuvioKiinniYlimmassaRivissa(tetris.getKuvio()));
        this.taytaYlinRivi();
        assertEquals(true, tetris.putoavaKuvioKiinniYlimmassaRivissa(tetris.getKuvio()));
    }
    
    @Test
    public void metodiPutoavaKuvioKiinniTietyssaRivissaToimiiOikein() {
        assertEquals(false, tetris.putoavaKuvioKiinniTietyssaRivissa(29, tetris.getKuvio()));
        tetris.setKuvio(new Palkki(5,26));
        assertEquals(true, tetris.putoavaKuvioKiinniTietyssaRivissa(29, tetris.getKuvio()));
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
        tetris.pudotaPutoavaKuvioNiinAlasKuinVoi();
        assertEquals("[(5,23), (5,24), (5,25), (5,26), (5,27)]", tetris.getKuvio().getPalat().toString());
    }
    
    @Test
    public void metodiPalkkiaVoiKaantaaToimiiOikein() {
        tetris.setKuvio(new Palkki(2,5));
        assertEquals(true,tetris.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        tetris.getRivit().lisaaPala(new Pala(1,3,Vari.ORANSSI));
        tetris.getRivit().lisaaPala(new Pala(4,6,Vari.ORANSSI));
        assertEquals(false, tetris.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        assertEquals(false, tetris.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));       
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
