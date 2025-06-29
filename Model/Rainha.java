package Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Rainha extends Pecas {

	private String cor_jogador;
	private String tipo_peca;
  
  public Rainha(String cor, String tipo){
    cor_jogador = cor;
    tipo_peca = tipo;
    
    try {
        InputStream is;
        if (cor.equals("branco")) {
            is = getClass().getResourceAsStream("/Imagens/b_dama.gif");
        } else {
            is = getClass().getResourceAsStream("/Imagens/p_dama.gif");
        }

        if (is == null) {
            throw new IOException("Imagem não encontrada!");
        }

        imagem = ImageIO.read(is);

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
			
	boolean movimento_diagonal = Math.abs(diferenca_linha) == Math.abs(diferenca_coluna);
	boolean movimento_reto = diferenca_linha == 0 || diferenca_coluna == 0;
			
	if (movimento_diagonal || movimento_reto) {
	
			int direcao_linha = Integer.signum(diferenca_linha);
			int direcao_coluna = Integer.signum(diferenca_coluna);
					
			int linha = linha_peca + direcao_linha;
			int coluna = coluna_peca + direcao_coluna;
					
			//Verificando se hÃ¡ peÃ§as no meio do caminho
			while(linha != linha_destino ||  coluna != coluna_destino)
			{
				if (tabuleiro.matriz[linha][coluna] != null) {
					System.out.println("Movimento invalido! Ha pecas no caminho!");
				    return;
				}
						
				linha += direcao_linha;
				coluna += direcao_coluna;
			}
		
		
		
		tabuleiro.matriz[linha_peca][coluna_peca] = null;
		tabuleiro.matriz[linha_destino][coluna_destino] = this;

	}
  
  	else {
		System.out.println("Movimento invalido para a Rainha!");
	}
	
  }
  */
  
  public boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino)
  {
	
	int diferenca_linha = linha_destino - linha_peca;
	int diferenca_coluna = coluna_destino - coluna_peca;
			
	boolean movimento_diagonal = Math.abs(diferenca_linha) == Math.abs(diferenca_coluna);
	boolean movimento_reto = diferenca_linha == 0 || diferenca_coluna == 0;
	
	if (!movimento_diagonal && !movimento_reto) {
		return false;
	}
	
	if (diferenca_linha == 0 && diferenca_coluna == 0) {
		return false;
	}
			

	
	int direcao_linha = Integer.signum(diferenca_linha);
	int direcao_coluna = Integer.signum(diferenca_coluna);
						
	int linha = linha_peca + direcao_linha;
	int coluna = coluna_peca + direcao_coluna;
					
	//Verificando se hÃ¡ peÃ§as no meio do caminho
	while(linha != linha_destino ||  coluna != coluna_destino)
	{
		if (tabuleiro.matriz[linha][coluna] != null) {
			return false;
		}
						
		linha += direcao_linha;
		coluna += direcao_coluna;
	}
		
	
	
	Pecas alvo = tabuleiro.matriz[linha_destino][coluna_destino];
	
	if (alvo == null) {
		return true;
	}
  
  	return !(alvo.getCor() == cor_jogador);
	
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
  {
	  char colunaChar_rainha = coordenada_peca.toLowerCase().charAt(0);
	  char linhaChar_rainha = coordenada_peca.charAt(1);
		  
	  int coluna_rainha = colunaChar_rainha - 'a';
	  int linha_rainha = 8 - Character.getNumericValue(linhaChar_rainha);
		  
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);
		  
	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
		  
	  int diferenca_linha = linha_peca_inimiga - linha_rainha;
	  int diferenca_coluna = coluna_peca_inimiga - coluna_rainha;
				
	  boolean movimento_diagonal = Math.abs(diferenca_linha) == Math.abs(diferenca_coluna);
	  boolean movimento_reto = diferenca_linha == 0 || diferenca_coluna == 0;
				
	  if (movimento_diagonal || movimento_reto) {
			
		  //if (movimento_diagonal == true) {
				//DireÃ§Ã£o da movimentaÃ§Ã£o
		  int direcao_linha = Integer.signum(diferenca_linha);
		  int direcao_coluna = Integer.signum(diferenca_coluna);
						
				int linha = linha_rainha + direcao_linha;
				int coluna = coluna_rainha + direcao_coluna;
						
						//Verificando se hÃ¡ peÃ§as no meio do caminho
				while(linha != linha_peca_inimiga ||  coluna != coluna_peca_inimiga)
				{
					if (tabuleiro.matriz[linha][coluna] != null) {
						System.out.println("Caminho bloqueado! Nao eh possivel mover a rainha.");
					    return;
					}
							
					linha += direcao_linha;
					coluna += direcao_coluna;
				}
			
			Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
				  
			if (peca_inimiga.getCor() == this.getCor()) {
				System.out.println("Movimento invalido! A rainha nao pode atacar");
				return ;
			}
			
			
			tabuleiro.matriz[linha_rainha][coluna_rainha] = null;
			tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;

		}
	  
	  	else {
			System.out.println("Movimento invalido para a Rainha!");
		}
	}

  
}
