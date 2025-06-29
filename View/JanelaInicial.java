package View;

import javax.swing.*;
import java.awt.event.*;
import Model.XadrezFacade;

public class JanelaInicial extends JFrame {
    
    public JanelaInicial(String titulo, XadrezFacade facade) {
        super(titulo);
        
        JButton iniciar_jogo = new JButton("Iniciar Jogo");
        JButton continuar_jogo = new JButton("Continuar Jogo");
        JPanel p = new JPanel();
        
        iniciar_jogo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                dispose();
                facade.iniciarNovoJogo();
            }
        });
        
        continuar_jogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                facade.continuarJogo();
            }
        });
        
        p.add(iniciar_jogo);
        p.add(continuar_jogo);
        
        getContentPane().add(p);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}