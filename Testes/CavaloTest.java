package Testes;

import Model.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CavaloTest {

    @Test
    public void testaMovimentosValidosCavalo() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Cavalo cavalo = new Cavalo("C1", 1);
        tabuleiro.matriz[7][1] = cavalo; // b1

        tabuleiro.movimento(cavalo, tabuleiro, "b1", "a3");
        assertEquals(cavalo, tabuleiro.matriz[5][0]);
        assertNull(tabuleiro.matriz[7][1]);

        tabuleiro.movimento(cavalo, tabuleiro, "a3", "b5");
        assertEquals(cavalo, tabuleiro.matriz[3][1]);
        assertNull(tabuleiro.matriz[5][0]);
    }

    @Test
    public void testaMovimentosInvalidosCavalo() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Cavalo cavalo = new Cavalo("C1", 1);
        tabuleiro.matriz[4][4] = cavalo; // e4

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tabuleiro.movimento(cavalo, tabuleiro, "e4", "e5"); // Movimento inválido

        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento inválido! O cavalo se move em L."));
    }

    @Test
    public void testaCapturaPecaAdversariaCavalo() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Cavalo cavalo = new Cavalo("C1", 1);
        Peao peaoAdversario = new Peao("P2", 2);
        tabuleiro.matriz[4][4] = cavalo; // e4
        tabuleiro.matriz[2][5] = peaoAdversario; // f6

        tabuleiro.movimento(cavalo, tabuleiro, "e4", "f6");
        assertEquals(cavalo, tabuleiro.matriz[2][5]);
        assertNull(tabuleiro.matriz[4][4]);
    }

    @Test
    public void testaImpedimentoPorPecaAliadaCavalo() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Cavalo cavalo = new Cavalo("C1", 1);
        Peao peaoAliado = new Peao("P1", 1);
        tabuleiro.matriz[4][4] = cavalo; // e4
        tabuleiro.matriz[2][5] = peaoAliado; // f6

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tabuleiro.movimento(cavalo, tabuleiro, "e4", "f6"); // Tentativa de capturar peça aliada

        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Movimento inválido! Não é possível capturar uma peça aliada."));
    }
}
