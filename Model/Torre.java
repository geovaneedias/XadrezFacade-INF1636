package Model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Torre extends Pecas {

	private String cor_jogador;
	private String tipo_peca;

  
  public Torre(String cor, String tipo){
    cor_jogador = cor;
    tipo_peca = tipo;
    
    try {
    	if (cor.equals("branco")) {
    		imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/b_torre.gif"));
    	}
    	
    	else {
            imagem = ImageIO.read(new File("D:/java_2025.1/Pecas/Pecas_1/p_torre.gif"));
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
	
	boolean linha_reta = (coluna_peca == coluna_destino || linha_peca == linha_destino);
	
	if (!linha_reta) {
		System.out.println("Movimento invalido! A torre so pode andar em linha reta.");
        return;
	}
	
	if (linha_peca == linha_destino) {
        int inicio = Math.min(coluna_peca, coluna_destino) + 1;
        int fim = Math.max(coluna_peca, coluna_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[linha_peca][cont] != null) {
                System.out.println("Movimento invalido! Ha pecas no caminho!");
                return;
            }
        }
    }
	
	else if (coluna_peca == coluna_destino) {
        int inicio = Math.min(linha_peca, linha_destino) + 1;
        int fim = Math.max(linha_peca, linha_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[cont][coluna_peca] != null) {
                System.out.println("Movimento invalido! Ha pecas no caminho!");
                return;
            }
        }
    }
		  
	
	tabuleiro.matriz[linha_peca][coluna_peca] = null;
	tabuleiro.matriz[linha_destino][coluna_destino] = this;
	
  }
  */
  
  public boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino)
  {
	
	boolean linha_reta = (coluna_peca == coluna_destino || linha_peca == linha_destino);
	
	if (linha_peca == linha_destino && coluna_peca == coluna_destino) {
		return false;
	}
	
	if (!linha_reta) {
        return false;
	}
	
	if (linha_peca == linha_destino) {
        int inicio = Math.min(coluna_peca, coluna_destino) + 1;
        int fim = Math.max(coluna_peca, coluna_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[linha_peca][cont] != null) {
                return false;
            }
        }
    }
	
	else if (coluna_peca == coluna_destino) {
        int inicio = Math.min(linha_peca, linha_destino) + 1;
        int fim = Math.max(linha_peca, linha_destino);
        for (int cont = inicio; cont < fim; cont++)
        {
            if (tabuleiro.matriz[cont][coluna_peca] != null) {
                return false;
            }
        }
    }
		  
	Pecas alvo = tabuleiro.matriz[linha_destino][coluna_destino];
	
	if (alvo == null) {
		return true;
	}
	
	return !(alvo.getCor() == cor_jogador);
	
  }
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
  {
	  char colunaChar_torre = coordenada_peca.toLowerCase().charAt(0);
	  char linhaChar_torre = coordenada_peca.charAt(1);

	  int coluna_torre = colunaChar_torre - 'a';
	  int linha_torre = 8 - Character.getNumericValue(linhaChar_torre);
		
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);

	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
	  
	  boolean linha_reta = (coluna_torre == coluna_peca_inimiga || linha_torre == linha_peca_inimiga);
		
	if (!linha_reta) {
		System.out.println("Movimento invalido! A torre so pode atacar em linha reta.");
	       return;
	}
		
	if (linha_torre == linha_peca_inimiga) {
	    int inicio = Math.min(coluna_torre, coluna_peca_inimiga) + 1;
	    int fim = Math.max(coluna_torre, coluna_peca_inimiga);
	    for (int cont = inicio; cont < fim; cont++)
	    {
	        if (tabuleiro.matriz[linha_torre][cont] != null) {
	           System.out.println("Ha pecas no caminho!");
	           return;
	        }
	        }
	    }
		
	else if (coluna_torre == coluna_peca_inimiga) {
		int inicio = Math.min(linha_torre, linha_peca_inimiga) + 1;
		int fim = Math.max(linha_torre, linha_peca_inimiga);
		for (int cont = inicio; cont < fim; cont++)
		{
			if (tabuleiro.matriz[cont][coluna_torre] != null) {
				System.out.println("Ha pecas no caminho!");
				return;
	        }
	    }
	}
	
	Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
	
	if (peca_inimiga.getCor() == this.getCor()) {
		System.out.println("Movimento invalido! A torre nao pode atacar");
		return ;
	}
	
	tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
	tabuleiro.matriz[linha_torre][coluna_torre] = null;
	
  }
}
