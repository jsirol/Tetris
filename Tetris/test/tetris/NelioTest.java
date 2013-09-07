/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.Nelio;

/**
 *
 * @author Johannes
 */
public class NelioTest {
    
    Nelio nelio;
    
    public NelioTest() {   
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nelio=new Nelio(0,0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nelioLuodaanOikeaanPaikkaan() {
        assertEquals("(0,0), (0,1), (1,0), (1,1)", nelio.toString());
    }   
    
}
