package View;

import Model.*;


import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class JanelaPromocaoDoPeao extends JPanel{
	
	private DialogPromocaoPeao dialog;
	
	public JanelaPromocaoDoPeao(String cor_jogador, DialogPromocaoPeao dialog) {
		
		this.dialog = dialog;
		setLayout(new GridLayout(1, 4));
		
		
		add(criarBotao(new Rainha(cor_jogador, "rainha"), "rainha"));
		add(criarBotao(new Torre(cor_jogador, "torre"), "torre"));
		add(criarBotao(new Bispo(cor_jogador, "bispo"), "bispo"));
		add(criarBotao(new Cavalo(cor_jogador, "cavalo"), "cavalo"));
	}
	
	private JButton criarBotao(Pecas peca, String id) {
        Image img = peca.getImage();
        JButton botao = new JButton(new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        botao.addActionListener((ActionEvent e) -> dialog.setEscolha(id));
        return botao;
    }

}
