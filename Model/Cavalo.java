package Model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cavalo extends Pecas {

	private String cor_jogador;
	private String tipo_peca;

  
  public Cavalo(String cor, String tipo ){
    cor_jogador = cor;
    tipo_peca = tipo;
    
    try {
    	if (cor.equals("branco")) {
    		imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/b_cavalo.gif"));
    	}
    	
    	else {
            imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/p_cavalo.gif"));
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
		
		int diferenca_linha = Math.abs(linha_destino - linha_peca);
		int diferenca_coluna = Math.abs(coluna_destino - coluna_peca);
		
		if ((diferenca_linha == 2 && diferenca_coluna == 1) || (diferenca_linha == 1 && diferenca_coluna == 2))
		{
			if (tabuleiro.matriz[linha_destino][coluna_destino] == null) {
				tabuleiro.matriz[linha_peca][coluna_peca] = null;
				tabuleiro.matriz[linha_destino][coluna_destino] = this;
			}
			
			else {
				System.out.println("Movimento invalido: destino ocupado");
			}
		}
		
		else {
			System.out.println("Movimento invalido para o cavalo");
		}
	  
  }
  */
  
  public boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino)
  {
	  	
		
		int diferenca_linha = Math.abs(linha_destino - linha_peca);
		int diferenca_coluna = Math.abs(coluna_destino - coluna_peca);
		
		if (!((diferenca_linha == 2 && diferenca_coluna == 1) || (diferenca_linha == 1 && diferenca_coluna == 2))){
			return false;
		}
			
		Pecas alvo = tabuleiro.matriz[linha_destino][coluna_destino];
		
		if (alvo == null) {
			return true;
		}
		
		return !(alvo.getCor() == cor_jogador);
	  
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_cavalo, String coordenada_peca_inimiga)
  {
	  char colunaChar_cavalo = coordenada_cavalo.toLowerCase().charAt(0);
	  char linhaChar_cavalo = coordenada_cavalo.charAt(1);

	  int coluna_cavalo = colunaChar_cavalo - 'a';
	  int linha_cavalo = 8 - Character.getNumericValue(linhaChar_cavalo);
		
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);

	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
		
	  int diferenca_linha = Math.abs(linha_peca_inimiga - linha_cavalo);
	  int diferenca_coluna = Math.abs(coluna_peca_inimiga - coluna_cavalo);
	  
	  
	  if ((diferenca_linha == 2 && diferenca_coluna == 1) || (diferenca_linha == 1 && diferenca_coluna == 2))
	  {
		  Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
		  
		  if (peca_inimiga.getCor() == this.getCor()) {
			  System.out.println("Movimento inválido! O Cavalo não pode atacar");
			  return ;
		  }
		  
		  tabuleiro.matriz[linha_cavalo][coluna_cavalo] = null;
		  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
		  	
		  	
	  }
		
	  else {
		  System.out.println("Movimento invalido para o cavalo");
	  }
	  
	  
  }

}
