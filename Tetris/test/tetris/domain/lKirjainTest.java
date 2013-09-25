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
public class lKirjainTest {
    
    lKirjain l;

    public lKirjainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        l= new lKirjain(6,6);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiKaannaToimiiOikein() {
        l.kaanna();
        assertEquals("[(4,6), (5,6), (6,6), (6,5)]", l.getPalat().toString());
        l.kaanna();
        assertEquals("[(6,8), (6,7), (6,6), (5,6)]", l.getPalat().toString());
        l.kaanna();
        assertEquals("[(8,6), (7,6), (6,6), (6,7)]", l.getPalat().toString());
        l.kaanna();
        assertEquals("[(6,4), (6,5), (6,6), (7,6)]", l.getPalat().toString());
    }
}
