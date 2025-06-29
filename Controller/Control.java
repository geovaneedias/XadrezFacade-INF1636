package Controller;

import javax.swing.*;
import Model.XadrezFacade;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Control {
    
    private XadrezFacade facade;
    private static Control controller = null;
    private String turno;
    private boolean roque_disponivel_jogador1;
    private boolean roque_disponivel_jogador2;
    private boolean torre1_ja_movimentou_jogador1;
    private boolean torre1_ja_movimentou_jogador2;
    private boolean torre2_ja_movimentou_jogador1;
    private boolean torre2_ja_movimentou_jogador2;
    private boolean rei_ja_movimentou_jogador1;
    private boolean rei_ja_movimentou_jogador2;
    public boolean rei_esta_em_xeque_jogador1;
    public boolean rei_esta_em_xeque_jogador2;
    public int pecas_atacando_rei_jogador1;
    public int pecas_atacando_rei_jogador2;
    private int[] posicao_rei_jogador1 = new int[2];
    private int[] posicao_rei_jogador2 = new int[2];
    
    public Control() {
        this.turno = "branco";
        this.roque_disponivel_jogador1 = true;
        this.roque_disponivel_jogador2 = true;
        this.torre1_ja_movimentou_jogador1 = false;
        this.torre1_ja_movimentou_jogador2 = false;
        this.torre2_ja_movimentou_jogador1 = false;
        this.torre2_ja_movimentou_jogador2 = false;
        this.rei_ja_movimentou_jogador1 = false;
        this.rei_ja_movimentou_jogador2 = false;
        this.rei_esta_em_xeque_jogador1 = false;
        this.rei_esta_em_xeque_jogador2 = false;
        this.pecas_atacando_rei_jogador1 = 0;
        this.pecas_atacando_rei_jogador2 = 0;
        this.posicao_rei_jogador1[0] = 7;
        this.posicao_rei_jogador1[1] = 4;    
        this.posicao_rei_jogador2[0] = 0;
        this.posicao_rei_jogador2[1] = 4;
    }
    
    public static Control getController() {
        if (controller == null) {
            synchronized (Control.class) {
                if (controller == null) {
                    controller = new Control();
                }
            }
        }
        return controller;
    }
    
    public String getTurno() {
        return turno;
    }
    
    public void setTurno(String turno) {
        if (turno.equals("branco") || turno.equals("preto")) {
            this.turno = turno;
        } else {
            System.out.println("Cor de turno inválida: " + turno);
        }
    }

    public void atualizaTabuleiro(int linha, int coluna, String tipoPeca, String corPeca) {
        facade.atualizaTabuleiro(linha, coluna, tipoPeca, corPeca);
    }
    
    public String getTipoPeca(int linha, int coluna) {
        return facade.getTipoPeca(linha, coluna);
    }
    
    public String getCorPeca(int linha, int coluna) {
        return facade.getCorPeca(linha, coluna);
    }
    
    public int[] getPosicaoReiJogador1() {
        return posicao_rei_jogador1;
    }
    
    public int[] getPosicaoReiJogador2() {
        return posicao_rei_jogador2;
    }
    
    public void setPosicaoReiJogador1(int linha, int coluna) {
        posicao_rei_jogador1[0] = linha;
        posicao_rei_jogador1[1] = coluna;
    }
    
    public void setPosicaoReiJogador2(int linha, int coluna) {
        posicao_rei_jogador2[0] = linha;
        posicao_rei_jogador2[1] = coluna;
    }
    
    public void mudancaDeTurno() {
        if (turno.equals("branco")) {
            turno = "preto";
        } else {
            turno = "branco";
        }
    }
    
    public void descobreTorre(int coluna_torre, String cor) {
        if (cor.equals("branco")) {
            if (coluna_torre == 0) {
                torre1_ja_movimentou_jogador1 = true;
            } else {
                torre2_ja_movimentou_jogador1 = true;
            }
        } else {
            if (coluna_torre == 0) {
                torre1_ja_movimentou_jogador2 = true;
            } else {
                torre2_ja_movimentou_jogador2 = true;
            }
        }
    }
    
    public boolean roque(int linha, int coluna, int linha_antiga, int coluna_antiga, String tipoPeca, String corPeca) {
        int inicio = Math.min(coluna, coluna_antiga) + 1;
        int fim = Math.max(coluna, coluna_antiga);
        
        for (int cont = inicio; cont < fim; cont++) {
            if (facade.getTipoPeca(linha_antiga, cont) != null) {
                return false;
            }
        }
        
        if (corPeca.equals("branco")) {
            if (!roque_disponivel_jogador1 || rei_ja_movimentou_jogador1) {
                return false;
            }
            
            if (coluna != 0) {
                if (torre2_ja_movimentou_jogador1) {
                    return false;
                }
                facade.atualizaTabuleiro(linha_antiga, coluna_antiga + 1, "torre", "branco");
                facade.atualizaTabuleiro(linha, coluna - 1, tipoPeca, corPeca);
            } else {
                if (torre1_ja_movimentou_jogador1) {
                    return false;
                }
                facade.atualizaTabuleiro(linha_antiga, coluna_antiga - 1, "torre", "branco");
                facade.atualizaTabuleiro(linha, coluna + 2, tipoPeca, corPeca);
            }
            
            rei_ja_movimentou_jogador1 = true;
            roque_disponivel_jogador1 = false;
        } else {
            if (!roque_disponivel_jogador2 || rei_ja_movimentou_jogador2) {
                return false;
            }
            
            if (coluna != 0) {
                if (torre2_ja_movimentou_jogador2) {
                    return false;
                }
                facade.atualizaTabuleiro(linha_antiga, coluna_antiga + 1, "torre", "preto");
                facade.atualizaTabuleiro(linha, coluna - 1, tipoPeca, corPeca);
            } else {
                if (torre1_ja_movimentou_jogador2) {
                    return false;
                }
                facade.atualizaTabuleiro(linha_antiga, coluna_antiga - 1, "torre", "preto");
                facade.atualizaTabuleiro(linha, coluna + 2, tipoPeca, corPeca);
            }
            
            rei_ja_movimentou_jogador2 = true;
            roque_disponivel_jogador2 = false;
        }
        
        facade.atualizaTabuleiro(linha_antiga, coluna_antiga, null, null);
        facade.atualizaTabuleiro(linha, coluna, null, null);
        return true;
    }
    
    private boolean estaDentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    private int confereXeque(int linha, int coluna, String cor_rei) {
        if (!estaDentroDosLimites(linha, coluna)) {
            return 1;
        }
        
        String tipoPeca = facade.getTipoPeca(linha, coluna);
        String corPeca = facade.getCorPeca(linha, coluna);
        
        if (tipoPeca == null) {
            return 0;
        }
        
        if (corPeca.equals(cor_rei)) {
            return 1;
        }
        
        return 2;
    }
    
    public int xequeRei(int linha, int coluna, String cor_rei) {
        int pecas_atacando_rei = 0;
        
        // Verificação vertical e horizontal (torre/rainha)
        int[][] direcoes = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : direcoes) {
            for (int i = 1; i < 8; i++) {
                int nova_linha = linha + dir[0] * i;
                int nova_coluna = coluna + dir[1] * i;
                
                int resultado = confereXeque(nova_linha, nova_coluna, cor_rei);
                if (resultado == 0) continue;
                if (resultado == 1) break;
                
                String tipo = facade.getTipoPeca(nova_linha, nova_coluna);
                if (i == 1 && tipo.equals("rei")) {
                    pecas_atacando_rei++;
                    break;
                }
                if (tipo.equals("torre") || tipo.equals("rainha")) {
                    pecas_atacando_rei++;
                    break;
                }
                break;
            }
        }
        
        // Verificação diagonal (bispo/rainha)
        int[][] diagonais = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for (int[] diag : diagonais) {
            for (int i = 1; i < 8; i++) {
                int nova_linha = linha + diag[0] * i;
                int nova_coluna = coluna + diag[1] * i;
                
                int resultado = confereXeque(nova_linha, nova_coluna, cor_rei);
                if (resultado == 0) continue;
                if (resultado == 1) break;
                
                String tipo = facade.getTipoPeca(nova_linha, nova_coluna);
                if (i == 1) {
                    if (tipo.equals("rei")) {
                        pecas_atacando_rei++;
                        break;
                    }
                    if (tipo.equals("peao") && 
                        ((cor_rei.equals("branco") && diag[0] == 1) || 
                         (cor_rei.equals("preto") && diag[0] == -1))) {
                        pecas_atacando_rei++;
                        break;
                    }
                }
                if (tipo.equals("bispo") || tipo.equals("rainha")) {
                    pecas_atacando_rei++;
                    break;
                }
                break;
            }
        }
        
        // Verificação cavalo
        int[][] cavalo = {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {1,-2}, {-1,2}, {-1,-2}};
        for (int[] mov : cavalo) {
            int nova_linha = linha + mov[0];
            int nova_coluna = coluna + mov[1];
            
            if (confereXeque(nova_linha, nova_coluna, cor_rei) == 2 && 
                facade.getTipoPeca(nova_linha, nova_coluna).equals("cavalo")) {
                pecas_atacando_rei++;
                break;
            }
        }
        
        return pecas_atacando_rei;
    }
    
    public boolean getEstadoReiJogador1() {
        return rei_esta_em_xeque_jogador1;
    }
    
    public boolean getEstadoReiJogador2() {
        return rei_esta_em_xeque_jogador2;
    }
    
    public void ReiSaiuDoXequeJogador1() {
        if (rei_esta_em_xeque_jogador1) {
            rei_esta_em_xeque_jogador1 = false;
        }
    }
    
    public void ReiSaiuDoXequeJogador2() {
        if (rei_esta_em_xeque_jogador2) {
            rei_esta_em_xeque_jogador2 = false;
        }
    }
    
    public String promocaoPeao(Component parent) {
        Object[] opcoes = {"rainha", "torre", "bispo", "cavalo"};
        String escolha = (String) JOptionPane.showInputDialog(
        	    null,
        	    "Escolha a peça para promoção:",
        	    "Promoção de Peão",
        	    JOptionPane.QUESTION_MESSAGE,
        	    null,
        	    opcoes,
        	    "rainha"
        	);
        return escolha; // pode ser null se cancelar
    }


    
    private boolean existeLanceQueRemoveXeque(int linha, int coluna, String cor_rei) {
        for (int origem_linha = 0; origem_linha < 8; origem_linha++) {
            for (int origem_coluna = 0; origem_coluna < 8; origem_coluna++) {
                String tipoPeca = facade.getTipoPeca(origem_linha, origem_coluna);
                String corPeca = facade.getCorPeca(origem_linha, origem_coluna);
                
                if (tipoPeca == null || !corPeca.equals(cor_rei)) {
                    continue;
                }
                
                for (int destino_linha = 0; destino_linha < 8; destino_linha++) {
                    for (int destino_coluna = 0; destino_coluna < 8; destino_coluna++) {
                        if (origem_linha == destino_linha && origem_coluna == destino_coluna) {
                            continue;
                        }

                        // Simula movimento
                        String tipoDestino = facade.getTipoPeca(destino_linha, destino_coluna);
                        String corDestino = facade.getCorPeca(destino_linha, destino_coluna);
                        
                        facade.atualizaTabuleiro(origem_linha, origem_coluna, null, null);
                        facade.atualizaTabuleiro(destino_linha, destino_coluna, tipoPeca, corPeca);

                        int salva_rei_linha = linha;
                        int salva_rei_coluna = coluna;
                        
                        if (tipoPeca.equals("rei")) {
                            linha = destino_linha;
                            coluna = destino_coluna;
                        }

                        boolean ainda_em_xeque = xequeRei(linha, coluna, cor_rei) != 0;

                        // Reverte movimento
                        facade.atualizaTabuleiro(origem_linha, origem_coluna, tipoPeca, corPeca);
                        facade.atualizaTabuleiro(destino_linha, destino_coluna, tipoDestino, corDestino);
                        
                        if (tipoPeca.equals("rei")) {
                            linha = salva_rei_linha;
                            coluna = salva_rei_coluna;
                        }

                        if (!ainda_em_xeque) return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean xequeMate(int linha, int coluna, String cor_rei) {
        int[][] direcoes = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        String tipoRei = facade.getTipoPeca(linha, coluna);
        String corRei = facade.getCorPeca(linha, coluna);
        
        for (int[] direcao : direcoes) {
            int linha_nova = linha + direcao[0];
            int coluna_nova = coluna + direcao[1];
            
            if (!estaDentroDosLimites(linha_nova, coluna_nova)) {
                continue;
            }
            
            String tipoDestino = facade.getTipoPeca(linha_nova, coluna_nova);
            String corDestino = facade.getCorPeca(linha_nova, coluna_nova);
            
            if (tipoDestino != null && corDestino.equals(cor_rei)) {
                continue;
            }
            
            // Simula movimento do rei
            facade.atualizaTabuleiro(linha, coluna, null, null);
            facade.atualizaTabuleiro(linha_nova, coluna_nova, tipoRei, corRei);
            
            boolean ainda_em_xeque = xequeRei(linha_nova, coluna_nova, cor_rei) != 0;
            
            // Reverte movimento
            facade.atualizaTabuleiro(linha, coluna, tipoRei, corRei);
            facade.atualizaTabuleiro(linha_nova, coluna_nova, tipoDestino, corDestino);
            
            if (!ainda_em_xeque) {
                return false;
            }
        }
        
        if (existeLanceQueRemoveXeque(linha, coluna, cor_rei)) {
            return false;
        }
        
        return true;
    }
    
    public void salvarJogo() {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showSaveDialog(fileChooser);
        
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                for (int linha = 0; linha < 8; linha++) {
                    for (int coluna = 0; coluna < 8; coluna++) {
                        String tipoPeca = facade.getTipoPeca(linha, coluna);
                        String corPeca = facade.getCorPeca(linha, coluna);
                        
                        if (tipoPeca == null) {
                            writer.write(linha + "," + coluna + ",null");
                        } else {
                            writer.write(linha + "," + coluna + "," + corPeca + "," + tipoPeca);
                        }
                        writer.newLine();
                    }
                }
                writer.write("TURNO=" + getTurno());
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void carregarJogo(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int escolha = fileChooser.showOpenDialog(frame);
        
        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha_arquivo;
                
                while ((linha_arquivo = reader.readLine()) != null) {
                    if (linha_arquivo.startsWith("TURNO=")) {
                        setTurno(linha_arquivo.substring(6));
                        continue;
                    }
                    
                    String[] partes = linha_arquivo.split(",");
                    
                    if (partes.length < 3) {
                        System.err.println("Linha mal formatada: " + linha_arquivo);
                        continue;
                    }
                    
                    int linha = Integer.parseInt(partes[0]);
                    int coluna = Integer.parseInt(partes[1]);
                    
                    if (partes[2].equals("null")) {
                        facade.atualizaTabuleiro(linha, coluna, null, null);
                        continue;
                    }
                    
                    String cor = partes[2];
                    String tipo = partes[3];
                    facade.atualizaTabuleiro(linha, coluna, tipo, cor);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void resetarEstadoJogo() {
        this.roque_disponivel_jogador1 = true;
        this.roque_disponivel_jogador2 = true;
        this.rei_ja_movimentou_jogador1 = false;
        this.rei_ja_movimentou_jogador2 = false;
        this.torre1_ja_movimentou_jogador1 = false;
        this.torre1_ja_movimentou_jogador2 = false;
        this.torre2_ja_movimentou_jogador1 = false;
        this.torre2_ja_movimentou_jogador2 = false;
        this.rei_esta_em_xeque_jogador1 = false;
        this.rei_esta_em_xeque_jogador2 = false;
        this.pecas_atacando_rei_jogador1 = 0;
        this.pecas_atacando_rei_jogador2 = 0;
    }
    
    public void setFacade(XadrezFacade facade) {
        this.facade = facade;
    }
}