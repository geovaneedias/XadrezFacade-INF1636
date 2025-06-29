package Testes;
import View.*;
import Model.*;

import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.*;

public class FrameTest {
    private Frame frame;

    @Before
    public void setUp() {
        frame = new Frame();
    }

    @Test
    public void testInicializacao() {
        assertEquals(Frame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(frame.getContentPane().getComponent(0));
    }

    @Test
    public void testDimensoesPadrao() {
        assertEquals(665, frame.LARG_DEFAULT);
        assertEquals(708, frame.ALT_DEFAULT);
    }
}