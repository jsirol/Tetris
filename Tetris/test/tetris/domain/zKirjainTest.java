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
public class zKirjainTest {
    
    zKirjain z;
    
    public zKirjainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        z= new zKirjain(18,21);
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void metodiGetRotaatioPisteenaOlevaPalaToimiiOikein() {
         assertEquals("(18,21)", z.getRotaatioPisteenaOlevaPala().toString());
     }
     
     @Test
     public void metodiOnVaakaAsennossaTunnistaaVaakaAsennonOikein() {
         assertEquals(true, z.onVaakaAsennossa());
     }
     
     @Test
     public void metodiKaannaToimiiOikein() {
         z.kaanna();
         assertEquals("(17,22), (18,20), (18,21), (17,21)", z.toString());
         z.kaanna();
         assertEquals("(17,20), (18,20), (18,21), (19,21)", z.toString());
     }
}
