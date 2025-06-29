package View;

import javax.swing.*;
import Model.XadrezFacade;
import Model.Tabuleiro; // Add this import
import java.awt.*;

public class Frame extends JFrame {
	public final int LARG_DEFAULT = 665;
	public final int ALT_DEFAULT = 708;
	
	public Frame() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Tabuleiro tabuleiro = new Tabuleiro();
		DesenhaTabuleiro view = new DesenhaTabuleiro(tabuleiro);
		getContentPane().add(view);	
	}
}
