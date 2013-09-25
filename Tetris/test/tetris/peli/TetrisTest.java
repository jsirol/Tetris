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

    Tetris peli;

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
        peli = new Tetris(20, 30);
        this.taytaKaksiAlintaRivia();
        this.peli.getRivit().lisaaPala(new Pala(7, 5, Vari.KELTAINEN));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodipudotaYlempanaOlevienRivienPalojaYhdellaToimiiOikein() {
        peli.pudotaYlempanaOlevienRivienPalojaYhdella(29);
        assertEquals("[(7,6)]", this.peli.getRivit().getRivinPalat(6).toString());
    }
    
    @Test
    public void metodituhoaTaydetRivitJaJaPudotaYlempanaOleviaPalojaAlaspainToimiiOikein() {
        peli.tuhoaTaydetRivitJaPudotaYlempanaOleviaPalojaAlaspain();
        assertEquals("[(7,7)]", this.peli.getRivit().getRivinPalat(7).toString());
    }
    
    @Test
    public void metodiPutoavaKuvioAlimmallaRivillaToimiiOikein() {
        assertEquals(false, peli.kuvioAlimmallaRivilla(peli.getKuvio()));
        peli.setKuvio(new Palkki(4,27));
        assertEquals(true, peli.kuvioAlimmallaRivilla(peli.getKuvio()));
    }
    
    @Test
    public void metodiPutoavaKuvioKiinniYlimmassaRivissaToimiiOikein() {
        peli.setKuvio(new Palkki(5,-3));
        assertEquals(false, peli.kuvioKiinniYlimmassaRivissa(peli.getKuvio()));
        this.taytaYlinRivi();
        assertEquals(true, peli.kuvioKiinniYlimmassaRivissa(peli.getKuvio()));
    }
    
    @Test
    public void metodiPutoavaKuvioKiinniTietyssaRivissaToimiiOikein() {
        assertEquals(false, peli.kuvioKiinniTietyssaRivissa(29, peli.getKuvio()));
        peli.setKuvio(new Palkki(5,26));
        assertEquals(true, peli.kuvioKiinniTietyssaRivissa(29, peli.getKuvio()));
    }
    
    @Test
    public void metodiKuviotaVoiSiirtaaSuuntaanOsumattaRiviinToimiiOikein() {
        assertEquals(true, peli.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1));
        peli.setKuvio(new Palkki(2,26));
        assertEquals(false, peli.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1));
        assertEquals(false, peli.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(-1));
        
    }
    
    @Test
    public void metodiPudotaPutoavaKuvioNiinAlasKuinVoiToimiiOikein() {
        peli.setKuvio(new Palkki(5,5));
        peli.pudotaKuvioNiinAlasKuinVoi(peli.getKuvio());
        assertEquals("[(5,23), (5,24), (5,25), (5,26), (5,27)]", peli.getKuvio().getPalat().toString());
    }
    
    
    public void taytaKaksiAlintaRivia() {
        for (int j = 29; j > 27; j--) {
            for (int i = 0; i < 20; i++) {
                peli.getRivit().lisaaPala(new Pala(i, j, Vari.SININEN));
            }
        }
    }
    
    public void taytaYlinRivi() {
        for (int i = 0; i < 20; i++) {
                peli.getRivit().lisaaPala(new Pala(i, 0, Vari.SININEN));
            }
    }
    
}
