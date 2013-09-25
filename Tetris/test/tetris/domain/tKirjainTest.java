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
public class tKirjainTest {
    
    tKirjain t;
    
    public tKirjainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t= new tKirjain(13,13);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void metodiGetRotaatioPisteenaOlevaPalaToimiiOikein() {
         assertEquals("(13,13)", t.getRotaatioPisteenaOlevaPala().toString());
     }
     
     @Test
     public void metodiGetAsentoTunnistaaAsennon0Oikein() {
         assertEquals(0, t.getAsento());
     }
     
     @Test
     public void metodiKaannaToimiiOikein() {
         t.kaanna();
         assertEquals("(12,13), (13,14), (13,13), (13,12)", t.toString());
         t.kaanna();
         assertEquals("(13,14), (14,13), (13,13), (12,13)", t.toString());
         t.kaanna();
         assertEquals("(14,13), (13,12), (13,13), (13,14)", t.toString());
         t.kaanna();
         assertEquals("(13,12), (12,13), (13,13), (14,13)", t.toString());
     }
}
