package Testes;
import Model.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class TorreTest {

	@Test
	public void testaMovimentoTorreJogador1 () {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T1", 1);
		tabuleiro.matriz[5][2] = torre;
		
		tabuleiro.movimento(torre, tabuleiro, "c3", "c5");
		
		assertEquals(torre, tabuleiro.matriz[3][2]);
		assertNull(tabuleiro.matriz[5][2]);
		
		tabuleiro.movimento(torre, tabuleiro, "c5", "g5");
		
		assertEquals(torre, tabuleiro.matriz[3][6]);
		assertNull(tabuleiro.matriz[3][2]);
		
		tabuleiro.movimento(torre, tabuleiro, "g5", "g4");
		
		assertEquals(torre, tabuleiro.matriz[4][6]);
		assertNull(tabuleiro.matriz[3][6]);
		
		tabuleiro.movimento(torre, tabuleiro, "g4", "d4");
		
		assertEquals(torre, tabuleiro.matriz[4][3]);
		assertNull(tabuleiro.matriz[4][6]);
		
	}
	
	@Test
	public void testaMovimentoIncorretoTorreJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T1", 1);
		
		tabuleiro.matriz[5][2] = torre;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(torre, tabuleiro, "c3", "a5");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! A torre so pode andar em linha reta."));
	
		tabuleiro.movimento(torre, tabuleiro, "c3", "f6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! A torre so pode andar em linha reta."));
	}
	
	@Test
	public void testaMovimentoPecasEstaoNoCaminhoTorreJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T1", 1);
		tabuleiro.matriz[5][2] = torre;
		tabuleiro.matriz[5][3] = new Peao("P1", 1);
		tabuleiro.matriz[5][4] = new Peao("P1", 1);
		tabuleiro.matriz[4][2] = new Peao("P1", 1);
		tabuleiro.matriz[5][1] = new Peao("P1", 1);
	
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(torre, tabuleiro, "c3", "h3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		tabuleiro.movimento(torre, tabuleiro, "c3", "c6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		tabuleiro.movimento(torre, tabuleiro, "c3", "a3");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		tabuleiro.matriz[2][3] = torre;
		tabuleiro.matriz[3][3] = new Peao("P1", 1);
		tabuleiro.matriz[4][3] = new Peao("P1", 1);
		
		tabuleiro.movimento(torre, tabuleiro, "d6", "d3");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
	}
	
	//Jogador 2
	
	@Test
	public void testaMovimentoTorreJogador2 () {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T2", 2);
		tabuleiro.matriz[2][5] = torre;
		
		tabuleiro.movimento(torre, tabuleiro, "f6", "f3");
		
		assertEquals(torre, tabuleiro.matriz[5][5]);
		assertNull(tabuleiro.matriz[2][5]);
		
		tabuleiro.movimento(torre, tabuleiro, "f3", "a3");
		
		assertEquals(torre, tabuleiro.matriz[5][0]);
		assertNull(tabuleiro.matriz[5][5]);
		
		tabuleiro.movimento(torre, tabuleiro, "a3", "a5");
		
		assertEquals(torre, tabuleiro.matriz[3][0]);
		assertNull(tabuleiro.matriz[5][0]);
		
		tabuleiro.movimento(torre, tabuleiro, "a5", "e5");
		
		assertEquals(torre, tabuleiro.matriz[3][4]);
		assertNull(tabuleiro.matriz[3][0]);
		
	}
	
	
	@Test
	public void testaMovimentoIncorretoTorreJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T2", 2);
		
		tabuleiro.matriz[2][5] = torre;
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(torre, tabuleiro, "f6", "h4");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! A torre so pode andar em linha reta."));
	
		tabuleiro.movimento(torre, tabuleiro, "f6", "c3");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! A torre so pode andar em linha reta."));
	}
	
	
	@Test
	public void testaMovimentoPecasEstaoNoCaminhoTorreJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Torre torre = new Torre("T2", 2);
		tabuleiro.matriz[2][5] = torre;
		tabuleiro.matriz[3][5] = new Peao("P1", 1);
		tabuleiro.matriz[4][5] = new Peao("P1", 1);
		tabuleiro.matriz[2][4] = new Peao("P1", 1);
		tabuleiro.matriz[2][3] = new Peao("P1", 1);
		tabuleiro.matriz[2][2] = new Peao("P1", 1);
		tabuleiro.matriz[2][6] = new Peao("P1", 1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(torre, tabuleiro, "f6", "f3");
        
        System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		
		tabuleiro.movimento(torre, tabuleiro, "f6", "a6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		
		tabuleiro.movimento(torre, tabuleiro, "f6", "g6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
		
		tabuleiro.matriz[4][3] = torre;
		tabuleiro.matriz[3][3] = new Peao("P1", 1);
		
		tabuleiro.movimento(torre, tabuleiro, "d4", "d6");
		
		System.setOut(System.out);
		assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
		
	}

	@Test
	public void testaCapturaPecaInimigaTorreJogador1() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Torre torre = new Torre("T1", 1);
	    Peao inimigo = new Peao("P2", 2);
	    
	    tabuleiro.matriz[5][2] = torre;   // c3
	    tabuleiro.matriz[2][2] = inimigo; // c6
	
	    tabuleiro.movimento(torre, tabuleiro, "c3", "c6");
	
	    assertEquals(torre, tabuleiro.matriz[2][2]);
	    assertNull(tabuleiro.matriz[5][2]);
	}

	@Test
	public void testaCapturaPecaAliadaTorreJogador1() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Torre torre = new Torre("T1", 1);
	    Peao aliado = new Peao("P1", 1);
	    
	    tabuleiro.matriz[5][2] = torre;   // c3
	    tabuleiro.matriz[2][2] = aliado;  // c6
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    tabuleiro.movimento(torre, tabuleiro, "c3", "c6");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Ha uma peca aliada no destino."));
	    assertEquals(torre, tabuleiro.matriz[5][2]); // não moveu
	    assertEquals(aliado, tabuleiro.matriz[2][2]);
	}

	@Test
	public void testaMovimentoParaMesmaPosicaoTorre() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Torre torre = new Torre("T1", 1);
	    
	    tabuleiro.matriz[5][2] = torre; // c3
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	
	    tabuleiro.movimento(torre, tabuleiro, "c3", "c3");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! A torre deve se mover para uma nova casa."));
	}

	@Test
	public void testaMovimentoForaDoTabuleiroTorre() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Torre torre = new Torre("T1", 1);
	    
	    tabuleiro.matriz[5][2] = torre; // c3
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	
	    tabuleiro.movimento(torre, tabuleiro, "c3", "z9");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Coordenada invalida!"));
	}
	
}
