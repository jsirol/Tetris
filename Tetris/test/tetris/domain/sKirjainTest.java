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
public class sKirjainTest {

    sKirjain s;

    public sKirjainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = new sKirjain(17, 19);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiGetRotaatioPisteenaOlevaPalaToimiiOikein() {
        assertEquals("(17,19)", s.getRotaatioPisteenaOlevaPala().toString());
    }
    
    @Test
    public void metodiOnVaakaAsennossaTunnistaaVaakaAsennonOikein() {
        assertEquals(true, s.onVaakaAsennossa());
    }

    @Test
    public void metodiKaannaToimiiOikein() {
        s.kaanna();
        assertEquals("(18,19), (17,19), (17,18), (18,20)", s.toString());
        s.kaanna();
        assertEquals("(16,19), (17,19), (17,18), (18,18)", s.toString());
    }
    
}
