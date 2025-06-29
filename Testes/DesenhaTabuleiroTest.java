package Testes;
import View.*;
import Model.*;

import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.*;
import java.util.ArrayList;

public class DesenhaTabuleiroTest {
    
    private DesenhaTabuleiro desenhaTabuleiro;
    private Tabuleiro tabuleiro;
    
    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
        desenhaTabuleiro = new DesenhaTabuleiro();
        setField(desenhaTabuleiro, "tabuleiro", tabuleiro);
        desenhaTabuleiro.setSize(600, 600);
    }
    
    // Método utilitário para acessar campos privados via reflexão
    private Object getField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }
    
    // Método utilitário para modificar campos privados via reflexão
    private void setField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
    }
    
    @Test
    public void testInicializacao() {
        Tabuleiro tab = (Tabuleiro) getField(desenhaTabuleiro, "tabuleiro");
        String turno = (String) getField(desenhaTabuleiro, "turno");
        boolean jogadaIniciada = (boolean) getField(desenhaTabuleiro, "jogada_iniciada");
        boolean notificaClick = (boolean) getField(desenhaTabuleiro, "notifica_click_em_peca");
        
        assertNotNull(tab);
        assertEquals("branco", turno);
        assertFalse(jogadaIniciada);
        assertTrue(notificaClick);
    }
    
    @Test
    public void testDesenhaTabuleiro() throws Exception {
        BufferedImage image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        // Acessando o método privado via reflexão
        Method desenhaTabuleiroMethod = DesenhaTabuleiro.class.getDeclaredMethod("desenhaTabuleiro", Graphics.class);
        desenhaTabuleiroMethod.setAccessible(true);
        desenhaTabuleiroMethod.invoke(desenhaTabuleiro, g2d);
        
        int pixel = image.getRGB(50, 50);
        assertEquals(Color.WHITE.getRGB(), pixel);
        
        pixel = image.getRGB(100, 50);
        assertEquals(Color.BLACK.getRGB(), pixel);
    }
    
    @Test
    public void testNotificaClickEmPeca() throws Exception {
        setField(desenhaTabuleiro, "coordenada_x", 0);
        setField(desenhaTabuleiro, "coordenada_y", 7);
        setField(desenhaTabuleiro, "tileWidth", 75);
        setField(desenhaTabuleiro, "tileHeight", 75);
        
        // Chama o método privado via reflexão
        Method notificaClickEmPeca = DesenhaTabuleiro.class.getDeclaredMethod("notificaClickEmPeca");
        notificaClickEmPeca.setAccessible(true);
        notificaClickEmPeca.invoke(desenhaTabuleiro);
        
        Pecas peca = (Pecas) getField(desenhaTabuleiro, "peca_selecionada");
        boolean notificaClick = (boolean) getField(desenhaTabuleiro, "notifica_click_em_peca");
        
        assertNotNull(peca);
        assertEquals("torre", peca.getPeca());
        assertFalse(notificaClick);
    }
    
    @Test
    public void testEscolheCasaDestinoValida() throws Exception {
        // Prepara estado via reflexão
        setField(desenhaTabuleiro, "peca_selecionada", new Peao("branco", "peao"));
        setField(desenhaTabuleiro, "linha_antiga", 6);
        setField(desenhaTabuleiro, "coluna_antiga", 0);
        setField(desenhaTabuleiro, "pinta_quadrados_rosa", true);
        
        // Cria coordenadas válidas
        ArrayList<ArrayList<Integer>> coordenadas = new ArrayList<>();
        ArrayList<Integer> coord = new ArrayList<>();
        coord.add(5); coord.add(0);
        coordenadas.add(coord);
        
        ArrayList<ArrayList<ArrayList<Integer>>> vetor = new ArrayList<>();
        vetor.add(coordenadas);
        setField(desenhaTabuleiro, "vetor_de_coordenadas", vetor);
        
        setField(desenhaTabuleiro, "coordenada_x", 0);
        setField(desenhaTabuleiro, "coordenada_y", 5);
        
        // Chama método privado
        Method escolheCasaDestino = DesenhaTabuleiro.class.getDeclaredMethod("escolheCasaDestino", ArrayList.class);
        escolheCasaDestino.setAccessible(true);
        escolheCasaDestino.invoke(desenhaTabuleiro, vetor);
        
        boolean escolheDestino = (boolean) getField(desenhaTabuleiro, "escolhe_casa_destino");
        boolean pintaRosa = (boolean) getField(desenhaTabuleiro, "pinta_quadrados_rosa");
        
        assertTrue(escolheDestino);
        assertFalse(pintaRosa);
    }
    
    @Test
    public void testFimDaJogada() throws Exception {
        // Prepara estado via reflexão
        setField(desenhaTabuleiro, "peca_selecionada", new Torre("branco", "torre"));
        setField(desenhaTabuleiro, "jogada_iniciada", true);
        setField(desenhaTabuleiro, "notifica_click_em_peca", false);
        setField(desenhaTabuleiro, "pinta_quadrados_rosa", true);
        setField(desenhaTabuleiro, "escolhe_casa_destino", true);
        setField(desenhaTabuleiro, "turno", "branco");
        
        // Chama método privado
        Method fimDaJogada = DesenhaTabuleiro.class.getDeclaredMethod("fimDaJogada");
        fimDaJogada.setAccessible(true);
        fimDaJogada.invoke(desenhaTabuleiro);
        
        // Verifica resultados
        assertNull(getField(desenhaTabuleiro, "peca_selecionada"));
        assertFalse((boolean) getField(desenhaTabuleiro, "jogada_iniciada"));
        assertTrue((boolean) getField(desenhaTabuleiro, "notifica_click_em_peca"));
        assertFalse((boolean) getField(desenhaTabuleiro, "pinta_quadrados_rosa"));
        assertFalse((boolean) getField(desenhaTabuleiro, "escolhe_casa_destino"));
        assertEquals("preto", getField(desenhaTabuleiro, "turno"));
    }
}
