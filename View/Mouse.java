package View;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Mouse implements MouseListener{
	Component c;
	
	public Mouse(Component x) {
		c = x;
	}
	
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(c, "Click Efetuado");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
    public void mouseEntered(MouseEvent e) {}
	
	@Override
    public void mouseExited(MouseEvent e) {}
}
