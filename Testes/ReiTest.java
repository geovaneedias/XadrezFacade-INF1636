package Testes;
import Model.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ReiTest {

	@Test
	public void testaMovimentoReiJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r1", 1);
		tabuleiro.matriz[4][3] = rei;
		
		tabuleiro.movimento(rei, tabuleiro, "d4", "d5");
		
		assertEquals(rei, tabuleiro.matriz[3][3]);
		assertNull(tabuleiro.matriz[4][3]);
		
		tabuleiro.movimento(rei, tabuleiro, "d5", "e6");
		
		assertEquals(rei, tabuleiro.matriz[2][4]);
		assertNull(tabuleiro.matriz[3][3]);
		
		tabuleiro.movimento(rei, tabuleiro, "e6", "f6");
		
		assertEquals(rei, tabuleiro.matriz[2][5]);
		assertNull(tabuleiro.matriz[2][4]);
		
		tabuleiro.movimento(rei, tabuleiro, "f6", "g5");
		
		assertEquals(rei, tabuleiro.matriz[3][6]);
		assertNull(tabuleiro.matriz[2][5]);
		
		tabuleiro.movimento(rei, tabuleiro, "g5", "g4");
		
		assertEquals(rei, tabuleiro.matriz[4][6]);
		assertNull(tabuleiro.matriz[3][6]);
		
		tabuleiro.movimento(rei, tabuleiro, "g4", "f3");
		
		assertEquals(rei, tabuleiro.matriz[5][5]);
		assertNull(tabuleiro.matriz[4][6]);
		
		tabuleiro.movimento(rei, tabuleiro, "f3", "e3");
		
		assertEquals(rei, tabuleiro.matriz[5][4]);
		assertNull(tabuleiro.matriz[5][5]);
		
		tabuleiro.movimento(rei, tabuleiro, "e3", "d4");
		
		assertEquals(rei, tabuleiro.matriz[4][3]);
		assertNull(tabuleiro.matriz[5][4]);
		
	}
	
	@Test
	public void testaMovimentoIncorretoReiJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r1", 1);
		tabuleiro.matriz[4][3] = rei;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rei, tabuleiro, "d4", "d6");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "f6");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "h4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "b4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
		
		tabuleiro.matriz[3][4] = rei;
		tabuleiro.movimento(rei, tabuleiro, "e5", "g3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
		
		tabuleiro.movimento(rei, tabuleiro, "e5", "e3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "e5", "c3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	}
	
	@Test 
	public void testaMovimentoDestinoEstaOcupadoReiJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r1", 1);
		tabuleiro.matriz[4][3] = rei;
		tabuleiro.matriz[3][3] = new Peao("P2", 2);
		tabuleiro.matriz[4][4] = new Peao("P2", 2);
		tabuleiro.matriz[5][4] = new Peao("P2", 2);
		tabuleiro.matriz[3][4] = new Peao("P2", 2);
		tabuleiro.matriz[5][3] = new Peao("P2", 2);
		tabuleiro.matriz[5][2] = new Peao("P2", 2);
		tabuleiro.matriz[4][2] = new Peao("P2", 2);
		tabuleiro.matriz[3][2] = new Peao("P2", 2);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rei, tabuleiro, "d4", "d5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "e5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "e4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "e3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "d3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "c3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "c4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "d4", "c5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
		
	}
	
	//jogador2
	
	@Test
	public void testaMovimentoReiJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r2", 2);
		tabuleiro.matriz[4][4] = rei;
		
		tabuleiro.movimento(rei, tabuleiro, "e4", "e5");
		
		assertEquals(rei, tabuleiro.matriz[3][4]);
		assertNull(tabuleiro.matriz[4][4]);
		
		tabuleiro.movimento(rei, tabuleiro, "e5", "f6");
		
		assertEquals(rei, tabuleiro.matriz[2][5]);
		assertNull(tabuleiro.matriz[3][4]);
		
		tabuleiro.movimento(rei, tabuleiro, "f6", "g6");
		
		assertEquals(rei, tabuleiro.matriz[2][6]);
		assertNull(tabuleiro.matriz[2][5]);
		
		tabuleiro.movimento(rei, tabuleiro, "g6", "h5");
		
		assertEquals(rei, tabuleiro.matriz[3][7]);
		assertNull(tabuleiro.matriz[2][6]);
		
		tabuleiro.movimento(rei, tabuleiro, "h5", "h4");
		
		assertEquals(rei, tabuleiro.matriz[4][7]);
		assertNull(tabuleiro.matriz[3][7]);
		
		tabuleiro.movimento(rei, tabuleiro, "h4", "g3");
		
		assertEquals(rei, tabuleiro.matriz[5][6]);
		assertNull(tabuleiro.matriz[4][7]);
		
		tabuleiro.movimento(rei, tabuleiro, "g3", "f3");
		
		assertEquals(rei, tabuleiro.matriz[5][5]);
		assertNull(tabuleiro.matriz[5][6]);
		
		tabuleiro.movimento(rei, tabuleiro, "f3", "e4");
		
		assertEquals(rei, tabuleiro.matriz[4][4]);
		assertNull(tabuleiro.matriz[5][5]);
		
	}
	
	
	@Test
	public void testaMovimentoIncorretoReiJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r2", 2);
		tabuleiro.matriz[4][4] = rei;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rei, tabuleiro, "e4", "e6");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "g6");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "h4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.matriz[3][4] = rei;
		
		tabuleiro.movimento(rei, tabuleiro, "e5", "g3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
		
		
		tabuleiro.movimento(rei, tabuleiro, "e5", "e3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
		
		tabuleiro.movimento(rei, tabuleiro, "e5", "c3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	
		tabuleiro.movimento(rei, tabuleiro, "e5", "a5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! O rei so pode andar uma casa em qualquer direcao."));
	}
	
	
	
	@Test 
	public void testaMovimentoDestinoEstaOcupadoReiJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rei rei = new Rei("r2", 2);
		tabuleiro.matriz[4][4] = rei;
		tabuleiro.matriz[3][3] = new Peao("P1", 1);
		tabuleiro.matriz[3][4] = new Peao("P1", 1);
		tabuleiro.matriz[3][5] = new Peao("P1", 1);
		tabuleiro.matriz[4][3] = new Peao("P1", 1);
		tabuleiro.matriz[5][4] = new Peao("P1", 1);
		
		tabuleiro.matriz[5][3] = new Peao("P1", 1);
		tabuleiro.matriz[5][5] = new Peao("P1", 1);
		tabuleiro.matriz[4][5] = new Peao("P1", 1);
		
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rei, tabuleiro, "e4", "d5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "e5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "f5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "f4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "f3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "e3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "d3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
	
		tabuleiro.movimento(rei, tabuleiro, "e4", "d4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ja existe uma peca na posicao de destino"));
		
	}
	@Test
	public void testaCapturaReiJogador1() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rei rei = new Rei("r1", 1);
	    tabuleiro.matriz[4][3] = rei;
	
	    Peao peaoAdversario = new Peao("P2", 2);
	    tabuleiro.matriz[3][3] = peaoAdversario;

	    tabuleiro.movimento(rei, tabuleiro, "d4", "d5");
	    assertEquals(rei, tabuleiro.matriz[3][3]);
	    assertNull(tabuleiro.matriz[4][3]);
	}

	@Test
	public void testaCapturaReiJogador2() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rei rei = new Rei("r2", 2);
	    tabuleiro.matriz[4][4] = rei; 
	    
	    Peao peaoInimigo = new Peao("P1", 1);
	    tabuleiro.matriz[3][3] = peaoInimigo;
	    
	    tabuleiro.movimento(rei, tabuleiro, "e4", "d5");
	    assertEquals(rei, tabuleiro.matriz[3][3]);
	    assertNull(tabuleiro.matriz[4][4]);
	    assertNotEquals(peaoInimigo, tabuleiro.matriz[3][3]);
	}

}
