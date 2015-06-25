/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Point;
import model.Casa;
import model.Peca;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Regra;


/**
 *
 * @author luis fernando
 */
public class RegraTest {
    Point p = new Point(1, 1);
    Peca peca = new Peca(p.x, p.y, false, "peca_azul.png");
        
    Casa casaSelecionada = new Casa(p);
    Casa casaDestino = new Casa(p);
        
    public RegraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    
    public void podeAndarTest() {
        
        casaSelecionada.setPeca(peca);
        
        casaSelecionada.setPosicaoI(2);
        
        casaSelecionada.setPosicaoJ(1);
        
        casaDestino.setPosicaoI(3);
        casaDestino.setPosicaoJ(2);
       
        boolean podeAndar = Regra.podeAndar(casaSelecionada, casaDestino);
        
        assertTrue(podeAndar);
        
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

   

}
