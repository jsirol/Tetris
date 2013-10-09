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
import tetris.Vari;
import tetris.domain.Nelio;
import tetris.domain.Pala;
import tetris.domain.Palkki;
import tetris.domain.jKirjain;
import tetris.domain.lKirjain;
import tetris.domain.zKirjain;

/**
 *
 * @author Johannes
 */
public class PalojenKaantoLogiikkaTest {

    //muokkaa riippumattomaksi luokasta tetris
    Tetris tetris;
    KuvioidenKaantoLogiikka kaanto;

    public PalojenKaantoLogiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tetris = new Tetris(15, 30, Vaikeustaso.EDISTYNYT);
        kaanto = new KuvioidenKaantoLogiikka(tetris.getRivit(), tetris.getLeveys(), tetris.getKorkeus());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiPalkkiaVoiKaantaaToimiiOikein() {
        tetris.setKuvio(new Palkki(2, 5));
        assertEquals(true, kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        tetris.getRivit().lisaaPala(new Pala(1, 3, Vari.ORANSSI));
        tetris.getRivit().lisaaPala(new Pala(4, 6, Vari.ORANSSI));
        assertEquals(false, kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        assertEquals(false, kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
    }

    
    //testataan muutamaa tilannetta
    @Test
    public void metodiPutoavaaKuviotaVoiKaantaaToimii() {
        this.taytaKaksiAlintaRivia();
        tetris.setKuvio(new lKirjain(8, 27));
        //kuvio lattian sisässä
        assertEquals(false, kaanto.putoavaaKuviotaVoiKaantaa(tetris.getKuvio()));
        tetris.setKuvio(new jKirjain(1,7));
        //kuvio vasemman seinän vieressä
        assertEquals(false, kaanto.putoavaaKuviotaVoiKaantaa(tetris.getKuvio()));       
        tetris.setKuvio(new zKirjain(5,29));
        //kuvio kääntyisi lattian sisään
        assertEquals(false, kaanto.putoavaaKuviotaVoiKaantaa(tetris.getKuvio()));
        tetris.setKuvio(new Nelio(5,5));
        //tarkistetaan että metodi palauttaa myös true totuusarvoa
        assertEquals(true, kaanto.putoavaaKuviotaVoiKaantaa(tetris.getKuvio()));
        
    }

    public void taytaKaksiAlintaRivia() {
        for (int j = 29; j > 27; j--) {
            for (int i = 0; i < 20; i++) {
                tetris.getRivit().lisaaPala(new Pala(i, j, Vari.SININEN));
            }
        }
    }
}
