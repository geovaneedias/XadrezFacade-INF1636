package View;

import java.awt.*;

import javax.swing.*;

public class DialogPromocaoPeao extends JDialog{
	

	private String escolha;
	
	public DialogPromocaoPeao(String cor_jogador, JFrame parent) {
		super(parent, "Promoção do peão", true);
		
		setSize(400, 150);
		
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		getContentPane().add(new JanelaPromocaoDoPeao(cor_jogador, this));
		
	}
	
	public void setEscolha(String escolha) {
        this.escolha = escolha;
        dispose();
    }
	
	public String getEscolha() {
        return escolha;
    }

}
