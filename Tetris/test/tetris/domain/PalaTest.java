package tetris.domain;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Vari;
import tetris.Vari;
import tetris.domain.Pala;

/**
 *
 * @author Johannes
 */
public class PalaTest {
    
    Pala ekaPala;
    Pala tokaPala;
    Pala kolmasPala;

    
    public PalaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ekaPala = new Pala(10,10,Vari.KELTAINEN);
        tokaPala = new Pala(8,3, Vari.VIHREA);
        kolmasPala = new Pala(11,10, Vari.SININEN);
    }
    
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaSijainninOikein() {
        assertEquals("(10,10)", getPalanSijainti(ekaPala));       
    }
    
    @Test
    public void konstruktoriAsettaaVarinOikein() {
        assertEquals("KELTAINEN", ekaPala.getVarinNimi().toString());
    }
    
    @Test
    public void palaPutoaaOikein() {
        ekaPala.pudotaYhdella();
        for(int i=0; i<5; i++) {
            tokaPala.pudotaYhdella();
        }
        assertEquals("(10,11)", getPalanSijainti(ekaPala));
        assertEquals("(8,8)", getPalanSijainti(tokaPala));
    }
    
    @Test
    public void setSijaintiAsettaaArvonOikein() {
        ekaPala.setSijainti(0, -1);
        tokaPala.setSijainti(6, 12);
        assertEquals("(0,-1)", getPalanSijainti(ekaPala));
        assertEquals("(6,12)", getPalanSijainti(tokaPala));
    }
    
    @Test
    public void metodiVieressaOnPalaToimiiOikein() {
        assertEquals(true, ekaPala.vieressaOnPala(1, kolmasPala));
        assertEquals(false, ekaPala.vieressaOnPala(-1, kolmasPala));
    }
    
    
    private String getPalanSijainti(Pala pala) {
        return "("+pala.getX() + "," + pala.getY() + ")";
    }
    
    
}
