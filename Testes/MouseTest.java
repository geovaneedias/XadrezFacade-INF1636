package Testes;
import View.*;
import Model.*;

import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;
import java.awt.event.MouseEvent;

public class MouseTest {
    private Mouse mouse;
    private JPanel panel;

    @Before
    public void setUp() {
        panel = new JPanel();
        mouse = new Mouse(panel);
    }

    @Test
    public void testMouseClicked() {
        // Simula um clique do mouse
        MouseEvent mockEvent = new MouseEvent(
            panel, 
            MouseEvent.MOUSE_CLICKED, 
            System.currentTimeMillis(), 
            0, 
            10, 10, 
            1, 
            false
        );
        
        mouse.mouseClicked(mockEvent);
        
    }

    @Test
    public void testOtherMouseMethods() {
        // Testa que os outros métodos não fazem nada (comportamento esperado)
        MouseEvent mockEvent = new MouseEvent(
            panel, 
            MouseEvent.MOUSE_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            10, 10, 
            1, 
            false
        );
        
        mouse.mousePressed(mockEvent);
        mouse.mouseReleased(mockEvent);
        mouse.mouseEntered(mockEvent);
        mouse.mouseExited(mockEvent);
        
        // Se chegou aqui sem exceções, o teste passou
        assertTrue(true);
    }
}
