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
public class PisteytysLogiikkaTest {

    PisteytysLogiikka pl;

    public PisteytysLogiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pl = new PisteytysLogiikka();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiTuhottujaRivejaVastaavaPeruspistemaaraToimiiOikein() {
        pl.tuhottujaRivejaVastaavaPeruspistemaara(1);
        pl.tuhottujaRivejaVastaavaPeruspistemaara(3);
        pl.tuhottujaRivejaVastaavaPeruspistemaara(2);
        assertEquals(2, pl.getKombot());
        pl.tuhottujaRivejaVastaavaPeruspistemaara(0);
        assertEquals(-1, pl.getKombot());
    }
    
    @Test
    public void metodiPisteitaTuhottujenRivienJaVaikeustasonMukaanPalauttaaOikeanPeruspistemaaran() {
        assertEquals(900, pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(3, Vaikeustaso.KOVAKSIKEITETTY));
        pl.setKombot(-1);
        assertEquals(625, pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(2, Vaikeustaso.NOLIFE));
        pl.setKombot(-1);
        assertEquals(2400, pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(4, Vaikeustaso.UBER));
        pl.setKombot(-1);
        assertEquals(400, pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(1, Vaikeustaso.NIRVANA));
    }
    
    @Test
    public void pisteitaTuhottujenRivienJaVaikeustasonMukaanHuomioiKombot() {
        pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(1, Vaikeustaso.ALOITTELIJA);
        pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(1, Vaikeustaso.ALOITTELIJA);       
        assertEquals(2250,pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(3, Vaikeustaso.UBER));     
        assertEquals(550,pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(4, Vaikeustaso.ALOITTELIJA));
    }
    
    @Test
    public void pisteitaTuhottujenRivienJaVaikeustasonMukaanLaskeeKombokertoimenOikein() {            
             assertEquals(50,pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(1, Vaikeustaso.ALOITTELIJA));
             pl.setKombot(0);
             assertEquals(100 ,pl.pisteitaTuhottujenRivienJaVaikeustasonMukaan(1, Vaikeustaso.ALOITTELIJA));
    }
}
