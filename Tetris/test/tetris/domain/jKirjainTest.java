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
public class jKirjainTest {
    
    jKirjain j;
    
    public jKirjainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        j=new jKirjain(8,12);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void metodiKaannaToimiiOikein() {
        j.kaanna();
        assertEquals("[(6,12), (7,12), (8,12), (8,13)]", j.getPalat().toString());
        j.kaanna();
        assertEquals("[(8,14), (8,13), (8,12), (9,12)]", j.getPalat().toString());
        j.kaanna();
        assertEquals("[(10,12), (9,12), (8,12), (8,11)]", j.getPalat().toString());
        j.kaanna();
        assertEquals("[(8,10), (8,11), (8,12), (7,12)]", j.getPalat().toString());
    }
}
