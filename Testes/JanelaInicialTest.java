package Testes;
import View.*;
import Model.*;

import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.awt.event.*;

public class JanelaInicialTest {
    
    private JanelaInicial janela;
    
    @Before
    public void setUp() {
        janela = new JanelaInicial("Teste Xadrez");
    }
    
    @Test
    public void testComponentesIniciais() {
        // Verifica se os botões foram criados
        assertNotNull(janela.getContentPane().getComponent(0)); // JPanel
        JPanel panel = (JPanel) janela.getContentPane().getComponent(0);
        
        assertEquals(2, panel.getComponentCount()); // Dois botões
        
        // Verifica os botões
        JButton iniciar = (JButton) panel.getComponent(0);
        JButton continuar = (JButton) panel.getComponent(1);
        
        assertEquals("Iniciar Jogo", iniciar.getText());
        assertEquals("Continuar Jogo", continuar.getText());
    }
    
    @Test
    public void testAcaoBotaoIniciar() {
        JPanel panel = (JPanel) janela.getContentPane().getComponent(0);
        JButton iniciar = (JButton) panel.getComponent(0);
        
        // Simula o clique no botão
        for (ActionListener listener : iniciar.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(iniciar, ActionEvent.ACTION_PERFORMED, ""));
        }
        
        // Verifica se a janela foi fechada (dispose chamado)
        assertFalse(janela.isVisible());
    }
    
    @Test
    public void testConfiguracaoJanela() {
        assertEquals("Teste Xadrez", janela.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, janela.getDefaultCloseOperation());
        assertTrue(janela.getSize().width == 400 && janela.getSize().height == 300);
    }
}