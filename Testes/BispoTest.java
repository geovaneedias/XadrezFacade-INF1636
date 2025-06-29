package Testes;
import Model.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class BispoTest {

	@Test
	public void testaMovimentoBispoJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B1", 1);
		tabuleiro.matriz[6][3] = null;
		
		tabuleiro.movimento(bispo, tabuleiro, "c1", "f4");
		assertEquals(bispo, tabuleiro.matriz[4][5]);
		assertNull(tabuleiro.matriz[7][2]);
		
		tabuleiro.movimento(bispo, tabuleiro, "f4", "d6");
		assertEquals(bispo, tabuleiro.matriz[2][3]);
		assertNull(tabuleiro.matriz[4][5]);
		
		tabuleiro.movimento(bispo, tabuleiro, "d6", "b4");
		assertEquals(bispo, tabuleiro.matriz[4][1]);
		assertNull(tabuleiro.matriz[2][3]);
		
		tabuleiro.movimento(bispo, tabuleiro, "b4", "c3");
		assertEquals(bispo, tabuleiro.matriz[5][2]);
		assertNull(tabuleiro.matriz[4][1]);
		
		Bispo bispo2 = new Bispo("B1", 1);
		tabuleiro.matriz[6][4] = null;
		
		tabuleiro.movimento(bispo2, tabuleiro, "f1", "a6");
		assertEquals(bispo2, tabuleiro.matriz[2][0]);
		assertNull(tabuleiro.matriz[7][5]);
		
	}
	
	//Mais testes
	@Test
	public void testaMovimentoIncorretoNaHorizontalBispoJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B1", 1);
		tabuleiro.matriz[6][2] = null;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(bispo, tabuleiro, "c1", "c4");
       
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento inválido! O bispo só pode se mover na diagonal"));
		
	}
	
	//Mais testes
	@Test
	public void testaMovimentoIncorretoNaVerticalBispoJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B1", 1);
		
		tabuleiro.matriz[4][2] = bispo;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(bispo, tabuleiro, "c4", "g4");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento inválido! O bispo só pode se mover na diagonal"));
		
	}
	
	@Test
	public void testaPecasEstaoNoCaminhoBispoJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B1", 1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(bispo, tabuleiro, "c1", "f4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo2 = new Bispo("B1", 1);
		tabuleiro.movimento(bispo2, tabuleiro, "f1", "e2");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo3 = new Bispo("B1", 1);
		tabuleiro.matriz[2][7] = bispo3;
		tabuleiro.matriz[3][6] = new Peao("P1", 1);
		tabuleiro.matriz[4][5] = new Peao("P1", 1);
		
		tabuleiro.movimento(bispo3, tabuleiro, "h6", "e3");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo4 = new Bispo("B1", 1);
		tabuleiro.matriz[2][0] = bispo4;
		tabuleiro.matriz[3][1] = new Peao("P1", 1);
		tabuleiro.matriz[4][2] = new Peao("P1", 1);
		
		tabuleiro.movimento(bispo3, tabuleiro, "a6", "d3");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	//jogador2
	
	@Test
	public void testaMovimentoBispoJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B2", 2);
		tabuleiro.matriz[1][3] = null;
		
		tabuleiro.movimento(bispo, tabuleiro, "c8", "f5");
		assertEquals(bispo, tabuleiro.matriz[3][5]);
		assertNull(tabuleiro.matriz[0][2]);
		
		tabuleiro.movimento(bispo, tabuleiro, "f5", "d3");
		assertEquals(bispo, tabuleiro.matriz[5][3]);
		assertNull(tabuleiro.matriz[3][5]);
		
		tabuleiro.movimento(bispo, tabuleiro, "d3", "b5");
		assertEquals(bispo, tabuleiro.matriz[3][1]);
		assertNull(tabuleiro.matriz[5][3]);
		
		tabuleiro.movimento(bispo, tabuleiro, "b5", "c6");
		assertEquals(bispo, tabuleiro.matriz[2][2]);
		assertNull(tabuleiro.matriz[3][1]);
		
		Bispo bispo2 = new Bispo("B2", 2);
		tabuleiro.matriz[1][4] = null;
		
		tabuleiro.movimento(bispo2, tabuleiro, "f8", "a3");
		assertEquals(bispo2, tabuleiro.matriz[5][0]);
		assertNull(tabuleiro.matriz[0][5]);
		
	}
	
	
	//Mais testes
	@Test
	public void testaMovimentoIncorretoNaHorizontalBispoJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B2", 2);
		tabuleiro.matriz[1][2] = null;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(bispo, tabuleiro, "c1", "c3");
       
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento inválido! O bispo só pode se mover na diagonal"));
		
		Bispo bispo2 = new Bispo("B2", 2);
		tabuleiro.matriz[5][2] = new Bispo("B2", 2);
		
		tabuleiro.movimento(bispo2, tabuleiro, "c3", "c5");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento inválido! O bispo só pode se mover na diagonal"));
			
	}
	
	
	//Mais testes
	@Test
	public void testaMovimentoIncorretoNaVerticalBispoJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B1", 1);
		
		tabuleiro.matriz[4][2] = bispo;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
		
		tabuleiro.movimento(bispo, tabuleiro, "c4", "g4");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento inválido! O bispo só pode se mover na diagonal"));
		
	}
	
	@Test
	public void testaPecasEstaoNoCaminhoBispoJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Bispo bispo = new Bispo("B2", 2);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(bispo, tabuleiro, "c8", "f5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo2 = new Bispo("B2", 2);
		tabuleiro.movimento(bispo2, tabuleiro, "f8", "e7");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo3 = new Bispo("B2", 2);
		tabuleiro.matriz[5][7] = bispo3;
		tabuleiro.matriz[3][5] = new Peao("P1", 1);
		tabuleiro.matriz[4][6] = new Peao("P1", 1);
		
		tabuleiro.movimento(bispo3, tabuleiro, "h3", "e6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		Bispo bispo4 = new Bispo("B2", 2);
		tabuleiro.matriz[5][0] = bispo4;
		tabuleiro.matriz[3][2] = new Peao("P1", 1);
		tabuleiro.matriz[4][1] = new Peao("P1", 1);
		
		tabuleiro.movimento(bispo3, tabuleiro, "a3", "d6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	@Test
	public void testaCapturaBispoJogador1() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Bispo bispo = new Bispo("B1", 1);
	    Peao peaoInimigo = new Peao("P2", 2);
	
	    tabuleiro.matriz[7][2] = bispo;
	    tabuleiro.matriz[4][5] = peaoInimigo;
	
	    tabuleiro.matriz[6][3] = null;
	    tabuleiro.matriz[5][4] = null;
	
	    tabuleiro.movimento(bispo, tabuleiro, "c1", "f4");
	    assertEquals(bispo, tabuleiro.matriz[4][5]);
	    assertNull(tabuleiro.matriz[7][2]);
	    assertNotEquals(peaoInimigo, tabuleiro.matriz[4][5]);
	}
	
	@Test
	public void testaCapturaBispoJogador2() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Bispo bispo = new Bispo("B2", 2);
	    Peao peaoInimigo = new Peao("P1", 1);
	
	    tabuleiro.matriz[0][2] = bispo;
	    tabuleiro.matriz[3][5] = peaoInimigo;
	
	    tabuleiro.matriz[1][3] = null;
	    tabuleiro.matriz[2][4] = null;
	
	    tabuleiro.movimento(bispo, tabuleiro, "c8", "f5");
	    assertEquals(bispo, tabuleiro.matriz[3][5]);
	    assertNull(tabuleiro.matriz[0][2]);
	    assertNotEquals(peaoInimigo, tabuleiro.matriz[3][5]);
	}
	
}
