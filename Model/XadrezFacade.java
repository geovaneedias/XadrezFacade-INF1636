package Model;

import Controller.Control;
import View.*;
import javax.swing.*;

public class XadrezFacade {
    private Control control;
    private DesenhaTabuleiro view;
    private Frame frame;
    private Tabuleiro tabuleiro;

    public XadrezFacade(Control control) {
        this.control = control;
        this.tabuleiro = new Tabuleiro();
        this.view = new DesenhaTabuleiro(tabuleiro);
        this.frame = new Frame();
        
        frame.getContentPane().removeAll();
        frame.getContentPane().add(view);
        frame.revalidate();
        
        control.setFacade(this);
    }

    public void iniciarJogo() {
        JanelaInicial janela = new JanelaInicial("Xadrez", this);
        janela.setVisible(true);
    }

    public void iniciarNovoJogo() {
        tabuleiro.inicializaTabuleiroNormal();
        control.setTurno("branco");
        control.resetarEstadoJogo();
        frame.setVisible(true);
        view.repaint();
    }

    public void continuarJogo() {
        frame.setVisible(true);
        control.carregarJogo(frame);
        view.repaint();
    }

    public boolean isXeque(String corRei) {
        int[] posicaoRei = corRei.equals("branco") 
            ? control.getPosicaoReiJogador1() 
            : control.getPosicaoReiJogador2();
        
        return control.xequeRei(posicaoRei[0], posicaoRei[1], corRei) > 0;
    }

    public void atualizarView() {
        if (view != null) {
            view.repaint();
        }
    }
    
    public String obterTurnoAtual() {
        return control.getTurno();
    }
    
    public void reiniciarJogo() {
        tabuleiro.inicializaTabuleiroNormal();
        control.setTurno("branco");
        control.resetarEstadoJogo();
        atualizarView();
    }
    
    public void salvarJogo() {
        control.salvarJogo();
    }
    
    public void finalizarJogo() {
        frame.dispose();
    }

    // --- Métodos adicionados para o Control acessar apenas o Facade ---
    public String getTipoPeca(int linha, int coluna) {
        Pecas peca = tabuleiro.matriz[linha][coluna];
        return peca != null ? peca.getPeca() : null;
    }

    public String getCorPeca(int linha, int coluna) {
        Pecas peca = tabuleiro.matriz[linha][coluna];
        return peca != null ? peca.getCor() : null;
    }

    public void atualizaTabuleiro(int linha, int coluna, String tipoPeca, String corPeca) {
        Pecas peca = null;
        if (tipoPeca != null && corPeca != null) {
            switch (tipoPeca) {
                case "peao":
                    peca = new Peao(corPeca, "peao");
                    break;
                case "torre":
                    peca = new Torre(corPeca, "torre");
                    break;
                case "bispo":
                    peca = new Bispo(corPeca, "bispo");
                    break;
                case "cavalo":
                    peca = new Cavalo(corPeca, "cavalo");
                    break;
                case "rainha":
                    peca = new Rainha(corPeca, "rainha");
                    break;
                case "rei":
                    peca = new Rei(corPeca, "rei");
                    break;
            }
        }
        tabuleiro.matriz[linha][coluna] = peca;
        view.repaint();
    }
}