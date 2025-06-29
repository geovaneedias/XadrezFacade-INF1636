package Testes;
import Model.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TabuleiroTest {

	@Test
	public void peaoNasPosicoesCorretasJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();

		int coluna;
		
		for (coluna = 0; coluna < 8; coluna++)
		{
			assertEquals("P1", tabuleiro.matriz[6][coluna].getPeca());
		}

	}
	
	@Test
	public void peaoNasPosicoesCorretasJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();

		int coluna;
			
		for (coluna = 0; coluna < 8; coluna++)
		{
			assertEquals("P2", tabuleiro.matriz[1][coluna].getPeca());
		}
		
	}
	
	@Test
	public void areaDoMeioLimpa() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		int coluna;
		int linha;
		
		for (linha = 0; linha < 4; linha++)
		{
			for (coluna = 0; coluna < 8; coluna ++)
			{
				assertNull(tabuleiro.matriz[linha+2][coluna]);
			}
		}
	}
	
	@Test
	public void pecasPosicoesCorretasJogador1() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		assertEquals("T1", tabuleiro.matriz[7][0].getPeca());
		assertEquals("C1", tabuleiro.matriz[7][1].getPeca());
		assertEquals("B1", tabuleiro.matriz[7][2].getPeca());
		assertEquals("R1", tabuleiro.matriz[7][3].getPeca());
		assertEquals("r1", tabuleiro.matriz[7][4].getPeca());
		assertEquals("B1", tabuleiro.matriz[7][5].getPeca());
		assertEquals("C1", tabuleiro.matriz[7][6].getPeca());
		assertEquals("T1", tabuleiro.matriz[7][7].getPeca());
	}
	
	@Test
	public void pecasPosicoesCorretasJogador2() {
		Tabuleiro tabuleiro = new Tabuleiro();
		
		assertEquals("T2", tabuleiro.matriz[0][0].getPeca());
		assertEquals("C2", tabuleiro.matriz[0][1].getPeca());
		assertEquals("B2", tabuleiro.matriz[0][2].getPeca());
		assertEquals("R2", tabuleiro.matriz[0][3].getPeca());
		assertEquals("r2", tabuleiro.matriz[0][4].getPeca());
		assertEquals("B2", tabuleiro.matriz[0][5].getPeca());
		assertEquals("C2", tabuleiro.matriz[0][6].getPeca());
		assertEquals("T2", tabuleiro.matriz[0][7].getPeca());
	}
	
	//testar getPecaNaPosicao
	@Test
	public void testaGetPecaNaPosicao() {
		String coordenada1 = "a1";
		String coordenada2 = "h8";
		String coordenada3 = "3";
		String coordenada4 = "i2";
		String coordenada5 = "c9";
		Tabuleiro tabuleiro = new Tabuleiro();
		int coluna1 = 0;
		int linha1 = 7;
		int linha2 = 0;
		int coluna2 = 7;
		
		Pecas peca1 = tabuleiro.getPecaNaPosicao(coordenada1);
		Pecas peca2 = tabuleiro.getPecaNaPosicao(coordenada2);
		Pecas peca3 = tabuleiro.getPecaNaPosicao(coordenada3);
		Pecas peca4 = tabuleiro.getPecaNaPosicao(coordenada4);
		Pecas peca5 = tabuleiro.getPecaNaPosicao(coordenada5);
		
		
		assertEquals(tabuleiro.matriz[linha1][coluna1], peca1);
		assertEquals(tabuleiro.matriz[linha2][coluna2], peca2);
	
		assertNull(peca3);
		assertNull(peca4);
		assertNull(peca5);
	}
	

	
	
}
