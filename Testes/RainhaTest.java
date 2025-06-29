package Testes;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import Model.*;


public class RainhaTest {

	@Test
	public void testaMovimentoRainhaJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha = new Rainha("R1", 1);
		tabuleiro.matriz[5][0] = rainha;
		
		tabuleiro.movimento(rainha, tabuleiro, "a3", "a4");
		
		assertEquals(rainha, tabuleiro.matriz[4][0]);
		assertNull(tabuleiro.matriz[5][0]);
		
		tabuleiro.movimento(rainha, tabuleiro, "a4", "c6");
		
		assertEquals(rainha, tabuleiro.matriz[2][2]);
		assertNull(tabuleiro.matriz[4][0]);
		
		tabuleiro.movimento(rainha, tabuleiro, "c6", "f6");
		
		assertEquals(rainha, tabuleiro.matriz[2][5]);
		assertNull(tabuleiro.matriz[2][2]);
		
		tabuleiro.movimento(rainha, tabuleiro, "f6", "g5");
		
		assertEquals(rainha, tabuleiro.matriz[3][6]);
		assertNull(tabuleiro.matriz[2][5]);
		
		tabuleiro.movimento(rainha, tabuleiro, "g5", "g4");
		
		assertEquals(rainha, tabuleiro.matriz[4][6]);
		assertNull(tabuleiro.matriz[3][6]);
		
		tabuleiro.movimento(rainha, tabuleiro, "g4", "f3");
		
		assertEquals(rainha, tabuleiro.matriz[5][5]);
		assertNull(tabuleiro.matriz[4][6]);
		
		
		tabuleiro.movimento(rainha, tabuleiro, "f3", "c3");
		
		assertEquals(rainha, tabuleiro.matriz[5][2]);
		assertNull(tabuleiro.matriz[5][5]);
		
		tabuleiro.movimento(rainha, tabuleiro, "c3", "a5");
		
		assertEquals(rainha, tabuleiro.matriz[3][0]);
		assertNull(tabuleiro.matriz[5][2]);
		
	}
	
	@Test 
	public void testaPecasEstaoNoCaminhoRainhaJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha= new Rainha("R1", 1);
		tabuleiro.matriz[5][2] = rainha;
		tabuleiro.matriz[3][2] = new Peao("P1", 1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rainha, tabuleiro, "c3", "c6");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d1", "f3");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d1", "g6");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d1", "a1");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	
        tabuleiro.movimento(rainha, tabuleiro, "d1", "a4");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "c3", "c1");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	
        tabuleiro.movimento(rainha, tabuleiro, "c3", "e1");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "c3", "b2");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	@Test
	public void testaMovimentoInvalidoRainhaJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha = new Rainha("R1", 1);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rainha, tabuleiro, "d1", "g2");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido para a Rainha!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d1", "b7");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido para a Rainha!"));
        
	}
	
	//jogador2
	
	@Test
	public void testaMovimentoRainhaJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha = new Rainha("R2", 2);
		tabuleiro.matriz[4][2] = rainha;
		
		tabuleiro.movimento(rainha, tabuleiro, "c4", "c5");
		
		assertEquals(rainha, tabuleiro.matriz[3][2]);
		assertNull(tabuleiro.matriz[4][2]);
		
		tabuleiro.movimento(rainha, tabuleiro, "c5", "d6");
		
		assertEquals(rainha, tabuleiro.matriz[2][3]);
		assertNull(tabuleiro.matriz[3][2]);
		
		tabuleiro.movimento(rainha, tabuleiro, "d6", "g6");
		
		assertEquals(rainha, tabuleiro.matriz[2][6]);
		assertNull(tabuleiro.matriz[2][3]);
		
		tabuleiro.movimento(rainha, tabuleiro, "g6", "h5");
		
		assertEquals(rainha, tabuleiro.matriz[3][7]);
		assertNull(tabuleiro.matriz[2][6]);
		
		tabuleiro.movimento(rainha, tabuleiro, "h5", "h3");
		
		assertEquals(rainha, tabuleiro.matriz[5][7]);
		assertNull(tabuleiro.matriz[3][7]);
		
		tabuleiro.movimento(rainha, tabuleiro, "h3", "d3");
		
		assertEquals(rainha, tabuleiro.matriz[5][3]);
		assertNull(tabuleiro.matriz[5][7]);
		
		
		tabuleiro.movimento(rainha, tabuleiro, "d3", "b5");
		
		assertEquals(rainha, tabuleiro.matriz[3][1]);
		assertNull(tabuleiro.matriz[5][3]);
		
		tabuleiro.movimento(rainha, tabuleiro, "b5", "b6");
		
		assertEquals(rainha, tabuleiro.matriz[2][1]);
		assertNull(tabuleiro.matriz[3][1]);
		
		tabuleiro.movimento(rainha, tabuleiro, "b6", "a5");
		
		assertEquals(rainha, tabuleiro.matriz[3][0]);
		assertNull(tabuleiro.matriz[2][1]);
		
	}
	
	
	
	@Test 
	public void testaPecasEstaoNoCaminhoRainhaJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha= new Rainha("R2", 1);
		tabuleiro.matriz[4][2] = rainha;
		tabuleiro.matriz[3][2] = new Peao("P1", 1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rainha, tabuleiro, "c4", "c6");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "g4");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "d3");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "a3");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	
        tabuleiro.movimento(rainha, tabuleiro, "d8", "a8");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "h8");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	
        tabuleiro.movimento(rainha, tabuleiro, "c4", "g7");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
        
        
        tabuleiro.matriz[3][1] = new Peao("P1", 1);
        tabuleiro.movimento(rainha, tabuleiro, "c4", "a2");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido! Ha pecas no caminho!"));
	}
	
	
	
	@Test
	public void testaMovimentoInvalidoRainhaJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Rainha rainha = new Rainha("R1", 1);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "h7");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido para a Rainha!"));
        
        tabuleiro.movimento(rainha, tabuleiro, "d8", "a1");
        
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento invalido para a Rainha!"));
        
	}

	@Test
	public void testaCapturaRainha() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rainha rainha = new Rainha("R1", 1);
	    Peao inimigo = new Peao("P2", 2);
	    tabuleiro.matriz[4][4] = rainha;
	    tabuleiro.matriz[2][2] = inimigo;
	
	    tabuleiro.movimento(rainha, tabuleiro, "e4", "c6");
	
	    assertEquals(rainha, tabuleiro.matriz[2][2]);
	    assertNull(tabuleiro.matriz[4][4]);
	}
	
	@Test
	public void testaMovimentoParaAliado() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rainha rainha = new Rainha("R1", 1);
	    Peao aliado = new Peao("P1", 1);
	    tabuleiro.matriz[4][4] = rainha;
	    tabuleiro.matriz[2][2] = aliado;
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	
	    tabuleiro.movimento(rainha, tabuleiro, "e4", "c6");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido! Ha uma peca sua no destino!"));
	    assertEquals(rainha, tabuleiro.matriz[4][4]);
	    assertEquals(aliado, tabuleiro.matriz[2][2]);
	}
	
	@Test
	public void testaMovimentoForaDoTabuleiro() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rainha rainha = new Rainha("R1", 1);
	    tabuleiro.matriz[0][0] = rainha;
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	
	    tabuleiro.movimento(rainha, tabuleiro, "a1", "z9");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Coordenada invalida!"));
	    assertEquals(rainha, tabuleiro.matriz[0][0]);
	}
	
	@Test
	public void testaMovimentoMesmoLugar() {
	    Tabuleiro tabuleiro = new Tabuleiro();
	    Rainha rainha = new Rainha("R1", 1);
	    tabuleiro.matriz[4][4] = rainha;
	
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	
	    tabuleiro.movimento(rainha, tabuleiro, "e4", "e4");
	
	    System.setOut(System.out);
	    assertTrue(outContent.toString().contains("Movimento invalido para a Rainha!"));
	    assertEquals(rainha, tabuleiro.matriz[4][4]);
	}
	
}
