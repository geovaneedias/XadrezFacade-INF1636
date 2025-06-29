package Model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rei extends Pecas {

	private String cor_jogador;
	private String tipo_peca;

  
  public Rei(String cor, String tipo){
    cor_jogador = cor;
    tipo_peca = tipo;
    
    try {
    	if (cor.equals("branco")) {
    		imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/b_rei.gif"));
    	}
    	
    	else {
            imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/p_rei.gif"));
        }
    	
    } catch (IOException e) {
        e.printStackTrace();
    }
    

  }
  
  public String getPeca(){
    return tipo_peca;
  }
  
  public String getCor() {
	  return cor_jogador;
  }
  
  
  /*
  public void movimento(Tabuleiro tabuleiro, String coordenada_da_peca, String coordenada_do_destino)
  {
	char colunaChar_peca = coordenada_da_peca.toLowerCase().charAt(0);
	char linhaChar_peca = coordenada_da_peca.charAt(1);
	
	int coluna_peca = colunaChar_peca - 'a';
	int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
			
	char colunaChar_destino = coordenada_do_destino.toLowerCase().charAt(0);
	char linhaChar_destino = coordenada_do_destino.charAt(1);
	
	int coluna_destino = colunaChar_destino - 'a';
	int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
			
	int diferenca_linha = linha_destino - linha_peca;
	int diferenca_coluna = coluna_destino - coluna_peca;
			
	if (Math.abs(diferenca_linha) > 1 || Math.abs(diferenca_coluna) > 1) {
		 System.out.println("Movimento invalido! O rei so pode andar uma casa em qualquer direcao.");
	     return;
	}
	
	if (tabuleiro.matriz[linha_destino][coluna_destino] != null) {
		System.out.println("Movimento invalido! Ja existe uma peca na posicao de destino");
		return;
	}
	
	tabuleiro.matriz[linha_peca][coluna_peca] = null;
	tabuleiro.matriz[linha_destino][coluna_destino] = this;
	
	
  }
  */
  
  public boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino)
  {
			
	int diferenca_linha = linha_destino - linha_peca;
	int diferenca_coluna = coluna_destino - coluna_peca;
			
	if (Math.abs(diferenca_linha) > 1 || Math.abs(diferenca_coluna) > 1) {
		return false;
	}

	if (diferenca_linha == 0 && diferenca_coluna == 0) {
		return false;
	}
	
	Pecas alvo = tabuleiro.matriz[linha_destino][coluna_destino];
	
	if (alvo == null) {
		return true;
	}
	
	return !(alvo.getCor() == cor_jogador);
	
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
	{
		char colunaChar_rei = coordenada_peca.toLowerCase().charAt(0);
		char linhaChar_rei = coordenada_peca.charAt(1);
		  
		int coluna_rei = colunaChar_rei - 'a';
		int linha_rei = 8 - Character.getNumericValue(linhaChar_rei);
		  
		char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
		char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);
		  
		int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
		int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
		  
		int diferenca_linha = linha_peca_inimiga - linha_rei;
		int diferenca_coluna = coluna_peca_inimiga - coluna_rei;
				
		if (Math.abs(diferenca_linha) > 1 || Math.abs(diferenca_coluna) > 1) {
			 System.out.println("Movimento invalido! O rei so pode andar uma casa em qualquer direÃ§Ã£o.");
		     return;
		}
		
		Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
		  
		if (peca_inimiga.getCor() == this.getCor()) {
			System.out.println("Movimento invalido! O rei nao pode atacar");
			return ;
		}
		
		tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
		tabuleiro.matriz[linha_rei][coluna_rei] = null;
		
	}

}
