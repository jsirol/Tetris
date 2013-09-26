/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johannes
 */
public class LjaJmuottiTest {
    
    lKirjain l;
    jKirjain j;
    
    public LjaJmuottiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        j=new jKirjain(10,10);
        l=new lKirjain(5,15);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void metodiGetAsentoPalauttaaAsennon0Oikein() {
        assertEquals(0, j.getAsento());      
    }
    
    @Test
    public void metoditKaannaPaaAkseliVaakaanJaKaannaPaaAkseliPystyynToimivatOikein() {
        j.kaannaPaaAkseliVaakaan();
        assertEquals("(8,10),(9,10),(10,10)", this.kolmenEnsimmaisenPalanMerkkijonoEsitys(j));
        j.kaannaPaaAkseliPystyyn();
        assertEquals("(10,12),(10,11),(10,10)", this.kolmenEnsimmaisenPalanMerkkijonoEsitys(j));
        j.kaannaPaaAkseliVaakaan();
        assertEquals("(12,10),(11,10),(10,10)", this.kolmenEnsimmaisenPalanMerkkijonoEsitys(j));
        j.kaannaPaaAkseliPystyyn();
        assertEquals("(10,8),(10,9),(10,10)", this.kolmenEnsimmaisenPalanMerkkijonoEsitys(j));
    }
    
    @Test
    public void metodiGetRotaatioPisteenaOlevaPalaToimiiOikein() {
        assertEquals("(10,10)", j.getRotaatioPisteenaOlevaPala().toString());
    }
    
    private String kolmenEnsimmaisenPalanMerkkijonoEsitys(LjaJmuotti kirjain) {
       return kirjain.getPalat().get(0).toString()+","+kirjain.getPalat().get(1).toString()+","+kirjain.getPalat().get(2).toString();
    }
    
}
