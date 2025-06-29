package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
import Model.*;
import java.util.ArrayList;
import Controller.Control;

public class DesenhaTabuleiro extends JPanel implements ObserverTabuleiro {

    private Tabuleiro tabuleiro;
    private String peca_selecionada_tipo = null;
    private String peca_selecionada_cor = null;
    private boolean jogada_iniciada = false;
    private boolean notifica_click_em_peca = true;
    private boolean pinta_quadrados_rosa = false;
    private boolean escolhe_casa_destino = false;
    private ArrayList<ArrayList<ArrayList<Integer>>> vetor_de_coordenadas = new ArrayList<>();
    
    private int coordenada_x = -1;
    private int coordenada_y = -1;
    private int linha = -1;
    private int coluna = -1;
    private int tileWidth;
    private int tileHeight;
    private int linha_antiga = -1;
    private int coluna_antiga = -1;
	
	public DesenhaTabuleiro(Tabuleiro tabuleiro) {
    		this.tabuleiro = tabuleiro;
    		tabuleiro.adicionarObservador(this);

    		this.addMouseListener(new Mouse(this) {
        	
    			@Override
				public void mouseClicked(MouseEvent e) {
    				
    				if (SwingUtilities.isRightMouseButton(e)) {
    					Control.getController().salvarJogo();
    				}
    				
    				else {
    					tileWidth = getWidth() / 8;
    		            tileHeight = getHeight() / 8;
    	
    					coordenada_x = e.getX() / tileWidth; // coluna
    					coordenada_y = e.getY() / tileHeight; //linha
    					
    					//Control.get
    					notificaClickEmPeca(); 
    					escolheCasaDestino(vetor_de_coordenadas);
    				}
    				
    				
			}
			
		});
		
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		tileWidth = getWidth() / 8;
	    tileHeight = getHeight() / 8;
		
		desenhaTabuleiro(g);
		
		pintaQuadradosRosas(g);
		
		movimentoPeca();
	}
	
	private void desenhaTabuleiro(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    
	    // Desenha o tabuleiro (casas brancas e pretas)
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            double x = j * tileWidth;
	            double y = i * tileHeight;
	            
	            // Pinta a casa
	            g2d.setColor((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
	            g2d.fill(new Rectangle2D.Double(x, y, tileWidth, tileHeight));
	            
	            // Desenha a peça (se existir)
	            Pecas peca = tabuleiro.matriz[i][j];
	            if (peca != null) {
	                Image img = peca.getImage();
	                if (img != null) {
	                    g2d.drawImage(img, (int)x, (int)y, tileWidth, tileHeight, null);
	                }
	            }
	        }
	    }
	    
	    // Desenha as linhas do tabuleiro
	    g2d.setColor(Color.GRAY);
	    for (int i = 0; i <= 8; i++) {
	        // Linhas horizontais
	        g2d.draw(new Line2D.Double(0, i * tileHeight, 8 * tileWidth, i * tileHeight));
	        // Linhas verticais
	        g2d.draw(new Line2D.Double(i * tileWidth, 0, i * tileWidth, 8 * tileHeight));
	    }
	    
	    // Desenha coordenadas nas bordas (opcional)
	    if (tileWidth > 40) { // Só desenha coordenadas se o tabuleiro for grande o suficiente
	        g2d.setColor(Color.BLACK);
	        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
	        
	        // Letras (colunas)
	        for (int i = 0; i < 8; i++) {
	            String letra = String.valueOf((char)('a' + i));
	            g2d.drawString(letra, (int)((i + 0.5) * tileWidth - 5), 
	                          (int)(8 * tileHeight) - 5);
	        }
	        
	        // Números (linhas)
	        for (int i = 0; i < 8; i++) {
	            String numero = String.valueOf(8 - i);
	            g2d.drawString(numero, 5, (int)((i + 0.7) * tileHeight));
	        }
	    }
	}
	
    private void notificaClickEmPeca() {
        linha = coordenada_y;
        coluna = coordenada_x;
        
        if (!notifica_click_em_peca) {
            return;
        }
        
        String tipoPeca = Control.getController().getTipoPeca(linha, coluna);
        String corPeca = Control.getController().getCorPeca(linha, coluna);
        
        if (tipoPeca != null) {
            if (corPeca.equals(Control.getController().getTurno())) {
                peca_selecionada_tipo = tipoPeca;
                peca_selecionada_cor = corPeca;
                jogada_iniciada = true;
                notifica_click_em_peca = false;
                linha_antiga = linha;
                coluna_antiga = coluna;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Espaço vazio clicado");
        }
        
        repaint();
    }
	
    private void pintaQuadradosRosas(Graphics g) {
        if (!jogada_iniciada || peca_selecionada_tipo == null) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<ArrayList<Integer>> coordenadas_vertical = new ArrayList<>();
        ArrayList<ArrayList<Integer>> coordenadas_diagonal = new ArrayList<>();
        ArrayList<ArrayList<Integer>> coordenadas_horizontal = new ArrayList<>();
        ArrayList<ArrayList<Integer>> coordenadas_cavalo = new ArrayList<>();
        
        if (peca_selecionada_tipo.equals("peao")) {
            int direcao = peca_selecionada_cor.equals("branco") ? -1 : 1;
            int linha_frente = linha + direcao;
            
            if (movimentoPeao(g2d, linha_frente, coluna, coordenadas_vertical)) {
                boolean peao_na_base = (peca_selecionada_cor.equals("branco") && linha == 6) ||
                                      (peca_selecionada_cor.equals("preto") && linha == 1);
                
                if (peao_na_base) {
                    int linha_dois_passos = linha + (2 * direcao);
                    movimentoPeao(g2d, linha_dois_passos, coluna, coordenadas_vertical);
                }
            }
            
            int linha_ataque = linha + direcao;
            ataquePeao(g2d, linha_ataque, coluna + 1, peca_selecionada_cor, coordenadas_diagonal);
            ataquePeao(g2d, linha_ataque, coluna - 1, peca_selecionada_cor, coordenadas_diagonal);
            
            vetor_de_coordenadas.add(coordenadas_vertical);
            if (!coordenadas_diagonal.isEmpty()) {
                vetor_de_coordenadas.add(coordenadas_diagonal);
            }
        }
        else if (peca_selecionada_tipo.equals("torre")) {
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna, peca_selecionada_cor, coordenadas_vertical)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna, peca_selecionada_cor, coordenadas_vertical)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha, coluna + i, peca_selecionada_cor, coordenadas_horizontal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha, coluna - i, peca_selecionada_cor, coordenadas_horizontal)) {
                    break;
                }
            }
            
            vetor_de_coordenadas.add(coordenadas_horizontal);
            vetor_de_coordenadas.add(coordenadas_vertical);
        }
        else if (peca_selecionada_tipo.equals("rainha")) {
            // Movimentos verticais e horizontais (como a torre)
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna, peca_selecionada_cor, coordenadas_vertical)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna, peca_selecionada_cor, coordenadas_vertical)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha, coluna + i, peca_selecionada_cor, coordenadas_horizontal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha, coluna - i, peca_selecionada_cor, coordenadas_horizontal)) {
                    break;
                }
            }
            
            // Movimentos diagonais (como o bispo)
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna + i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna - i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna + i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna - i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            vetor_de_coordenadas.add(coordenadas_horizontal);
            vetor_de_coordenadas.add(coordenadas_vertical);
            vetor_de_coordenadas.add(coordenadas_diagonal);
        }
        else if (peca_selecionada_tipo.equals("cavalo")) {
            int[][] offsets = {
                {+2, +1}, {+2, -1}, {-2, +1}, {-2, -1},
                {+1, +2}, {+1, -2}, {-1, +2}, {-1, -2}
            };
            
            for (int[] offset : offsets) {
                int nova_linha = linha + offset[0];
                int nova_coluna = coluna + offset[1];
                pintaSeVazio(g2d, nova_linha, nova_coluna, peca_selecionada_cor, coordenadas_cavalo);
            }
            
            vetor_de_coordenadas.add(coordenadas_cavalo);
        }
        else if (peca_selecionada_tipo.equals("bispo")) {
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna + i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha + i, coluna - i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna + i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            for (int i = 1; i < 8; i++) {
                if (!pintaSeVazio(g2d, linha - i, coluna - i, peca_selecionada_cor, coordenadas_diagonal)) {
                    break;
                }
            }
            
            vetor_de_coordenadas.add(coordenadas_diagonal);
        }
        else if (peca_selecionada_tipo.equals("rei")) {
            // Movimento de uma casa em todas as direções
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // Pula a posição atual do rei
                    pintaSeVazio(g2d, linha + i, coluna + j, peca_selecionada_cor, coordenadas_diagonal);
                }
            }
            
            vetor_de_coordenadas.add(coordenadas_diagonal);
        }
        
        pinta_quadrados_rosa = true;
        jogada_iniciada = false;
    }
	
    private void escolheCasaDestino(ArrayList<ArrayList<ArrayList<Integer>>> vetor_coordenadas) {
        if (!pinta_quadrados_rosa) {
            return;
        }
        
        int linha_destino = coordenada_y;
        int coluna_destino = coordenada_x;
        boolean destino_valido = false;
        
        for (ArrayList<ArrayList<Integer>> coordenadas : vetor_coordenadas) {
            for (ArrayList<Integer> coordenada : coordenadas) {
                if (coordenada.get(0) == linha_destino && coordenada.get(1) == coluna_destino) {
                    destino_valido = true;
                    break;
                }
            }
            if (destino_valido) break;
        }
        
        if (destino_valido) {
            escolhe_casa_destino = true;
            pinta_quadrados_rosa = false;
            repaint();
            return;
        }
        
        String tipo_peca_escolhida = Control.getController().getTipoPeca(linha_destino, coluna_destino);
        String cor_peca_escolhida = Control.getController().getCorPeca(linha_destino, coluna_destino);
        
        if (tipo_peca_escolhida != null && tipo_peca_escolhida.equals("torre") && 
            cor_peca_escolhida.equals(Control.getController().getTurno())) {
            
            if (Control.getController().roque(linha_destino, coluna_destino, linha_antiga, 
                                            coluna_antiga, peca_selecionada_tipo, peca_selecionada_cor)) {
                fimDaJogada(0);
                return;
            }
        }
        
        if (tipo_peca_escolhida != null && cor_peca_escolhida.equals(Control.getController().getTurno())) {
            coordenada_y = linha_destino;
            coordenada_x = coluna_destino;
            jogadaReiniciada();
            return;
        }
        
        fimDaJogada(1);
    }
	
	//Colocar no controle
    private void movimentoPeca() {
        if (!escolhe_casa_destino) {
            return;
        }
        
        int linha_destino = coordenada_y;
        int coluna_destino = coordenada_x;
        
        String turno_atual = Control.getController().getTurno();
        boolean rei_em_xeque = turno_atual.equals("preto") ? 
            Control.getController().getEstadoReiJogador2() : 
            Control.getController().getEstadoReiJogador1();

        if (rei_em_xeque && !peca_selecionada_tipo.equals("rei")) {
            JOptionPane.showMessageDialog(this, "Rei em xeque!! Movimente o rei");
            fimDaJogada(1);
            return;
        }
        
        if (peca_selecionada_tipo.equals("peao") && (linha_destino == 7 || linha_destino == 0)) {
        	String nova_peca = Control.getController().promocaoPeao(this);
        	if (nova_peca == null) {
        	    JOptionPane.showMessageDialog(this, "Promoção cancelada.");
        	    fimDaJogada(1); // Não troca o turno nem mexe nada
        	    return;
        	}
            Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, 
                                                    nova_peca, peca_selecionada_cor);
            Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, null, null);
            fimDaJogada(0);
            return;
        }
        
        Control.getController().atualizaTabuleiro(linha_destino, coluna_destino, 
                                                peca_selecionada_tipo, peca_selecionada_cor);
        Control.getController().atualizaTabuleiro(linha_antiga, coluna_antiga, null, null);
        
        fimDaJogada(0);
    }
	
    private void fimDaJogada(int condicao) {
        notifica_click_em_peca = true;
        jogada_iniciada = false;
        pinta_quadrados_rosa = false;
        escolhe_casa_destino = false;
        peca_selecionada_tipo = null;
        peca_selecionada_cor = null;
        vetor_de_coordenadas.clear();
        linha_antiga = -1;
        coluna_antiga = -1;
        linha = -1;
        coluna = -1;
        coordenada_y = -1;
        coordenada_x = -1;

        if (condicao != 1) {
            Control.getController().mudancaDeTurno();
        }
        
        repaint();
    }
	
	
    private void jogadaReiniciada() {
        peca_selecionada_tipo = Control.getController().getTipoPeca(coordenada_y, coordenada_x);
        peca_selecionada_cor = Control.getController().getCorPeca(coordenada_y, coordenada_x);
        pinta_quadrados_rosa = false;
        jogada_iniciada = true;
        linha_antiga = linha;
        coluna_antiga = coluna;
        vetor_de_coordenadas.clear();
        repaint();
    }
	
	
    private boolean ataquePeao(Graphics2D g2d, int linha, int coluna, String cor_atual, ArrayList<ArrayList<Integer>> coordenada_movimento) {
	if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
	return false;
	}
	
	Pecas peca = tabuleiro.matriz[linha][coluna];
	
	// Verifica se há uma peça inimiga na diagonal
	if (peca != null && !peca.getCor().equals(cor_atual)) {
	pintaCasaRosa(g2d, linha, coluna);
	
	// Adiciona coordenada à lista de ataques válidos
	ArrayList<Integer> coordenadas = new ArrayList<>();
	coordenadas.add(linha);
	coordenadas.add(coluna);
	coordenada_movimento.add(coordenadas);
	
	return true;
	}
	return false;
		
	}
	
	private boolean movimentoPeao(Graphics2D g2d, int linha, int coluna, ArrayList<ArrayList<Integer>> coordenada_movimento) {
	if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
	return false;
	}
	
	// Verifica se a casa está vazia
	if (tabuleiro.matriz[linha][coluna] == null) {
	pintaCasaRosa(g2d, linha, coluna);
	
	// Adiciona coordenada à lista de movimentos válidos
	ArrayList<Integer> coordenadas = new ArrayList<>();
	coordenadas.add(linha);
	coordenadas.add(coluna);
	coordenada_movimento.add(coordenadas);
	
	return true;
	}
	return false;
		
		
	}
	
	private boolean pintaSeVazio(Graphics2D g2d, int linha, int coluna, String cor_atual, ArrayList<ArrayList<Integer>> coordenada_movimento) {
		if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
		return false;
		}
		
		String tipoPeca = Control.getController().getTipoPeca(linha, coluna);
		String corPeca = Control.getController().getCorPeca(linha, coluna);
		
		if (tipoPeca == null) {
		pintaCasaRosa(g2d, linha, coluna);
		ArrayList<Integer> coordenadas = new ArrayList<>();
		coordenadas.add(linha);
		coordenadas.add(coluna);
		coordenada_movimento.add(coordenadas);
		return true;
		}
		
		if (corPeca != null && !corPeca.equals(cor_atual)) {
		pintaCasaRosa(g2d, linha, coluna);
		ArrayList<Integer> coordenadas = new ArrayList<>();
		coordenadas.add(linha);
		coordenadas.add(coluna);
		coordenada_movimento.add(coordenadas);
		return false;
		}
		
		return false;
	}
	
	private void pintaCasaRosa(Graphics2D g2d, int linha, int coluna) {
	    double leftX = coluna * tileWidth;
	    double topY = linha * tileHeight;
	    Rectangle2D casa = new Rectangle2D.Double(leftX, topY, tileWidth, tileHeight);
	    g2d.setPaint(Color.PINK);
	    g2d.fill(casa);
	}
	
	
    @Override
    public void atualizar(Tabuleiro tabuleiro) {
        repaint();
    }
    
}