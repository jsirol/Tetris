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
import tetris.domain.Pala;
import tetris.domain.Palkki;

/**
 *
 * @author Johannes
 */
public class PalojenKaantoTest {
    
    
    //muokkaa riippumattomaksi luokasta tetris
    Peli tetris;
    PalojenKaanto kaanto;
    
    public PalojenKaantoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tetris=new Peli(15,20);
        kaanto=new PalojenKaanto(tetris.getRivit(), tetris.getLeveys(), tetris.getKorkeus());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void metodiPalkkiaVoiKaantaaToimiiOikein() {
        tetris.setKuvio(new Palkki(2,5));
        assertEquals(true,kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        tetris.getRivit().lisaaPala(new Pala(1,3,Vari.ORANSSI));
        tetris.getRivit().lisaaPala(new Pala(4,6,Vari.ORANSSI));
        assertEquals(false, kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));
        assertEquals(false, kaanto.palkkiaVoiKaantaa((Palkki) tetris.getKuvio()));       
    }
}
