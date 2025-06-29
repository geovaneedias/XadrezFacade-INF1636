package Model;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
public class Peao extends Pecas {
  
  private String cor_jogador;
  private String tipo_peca;
  public String posicao;

  
  public Peao(String cor, String tipo){
    cor_jogador = cor;
    tipo_peca = tipo;
    posicao = "original";
    
    try {
        InputStream is;
        if (cor.equals("branco")) {
            is = getClass().getResourceAsStream("/Imagens/b_peao.gif");
        } else {
            is = getClass().getResourceAsStream("/Imagens/p_peao.gif");
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
  public void movimento(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_destino)
  {
	  	//Conversoes das coordenadas em inteiros
		char colunaChar_peca = coordenada_peca.toLowerCase().charAt(0);
		char linhaChar_peca = coordenada_peca.charAt(1);
				  
		int coluna_peca = colunaChar_peca - 'a';
		int linha_peca = 8 - Character.getNumericValue(linhaChar_peca);
						 
		char colunaChar_destino = coordenada_destino.toLowerCase().charAt(0);
		char linhaChar_destino = coordenada_destino.charAt(1);
		
		int coluna_destino = colunaChar_destino - 'a';
		int linha_destino = 8 - Character.getNumericValue(linhaChar_destino);
		
		int diferenca_linha = linha_destino - linha_peca;
		int direcao = (cor_jogador == "branco") ? -1 : 1;
		
		//System.out.print(diferenca_linha);
		//System.out.print("\n");
		
		if (Math.abs(diferenca_linha) > 2) {
			//System.out.println("Foi condicao");
			System.out.println("Movimento invalido! Peao nao pode andar mais de 2 casas");
			return;
		}
				  
		if (Math.abs(diferenca_linha) == 2 && !this.posicao.equals("original")) {
			System.out.println("Movimento invalido! Peao ja saiu da posicao original");
			return;
		}
		
		
		// Movimento reto 
		if (coluna_peca == coluna_destino) {
		    int distancia = linha_destino - linha_peca;

		    // Movimento de 2 casas
		    if (distancia == 2 * direcao) {
		        int meio = linha_peca + direcao;
		        if (tabuleiro.matriz[meio][coluna_peca] != null || tabuleiro.matriz[linha_destino][coluna_destino] != null) {
		            System.out.println("Movimento invalido! Ha pecas no caminho!");
		            return;
		        }
		    }

		    // Movimento de 1 casa
		    else if (distancia == 1 * direcao) {
		        if (tabuleiro.matriz[linha_destino][coluna_destino] != null) {
		            System.out.println("Movimento invalido! Casa destino ocupada!");
		            return;
		        }
		    }

		}
		
		
		//Atualiza tabuleiro
		this.posicao = "diferente";

		tabuleiro.matriz[linha_destino][coluna_destino] = this;
	
		tabuleiro.matriz[linha_peca][coluna_peca] = null;
		
		
				    
  }
  */
  
  public boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino)
  {
	  
	  if (linha_peca == linha_destino && linha_destino == coluna_destino) {
		  return false;
	  }
	  	
	  int diferenca_linha = linha_destino - linha_peca;
	  int diferenca_coluna = coluna_destino - coluna_peca;
	  int direcao = (cor_jogador == "branco") ? -1 : 1;
		
	  if (Integer.signum(diferenca_linha) != direcao) {
		  return false;
	  }
	  
	  Pecas alvo = tabuleiro.matriz[linha_destino][coluna_destino];
	  
	  if (Math.abs(diferenca_coluna) == 1 && diferenca_linha == direcao) {
		  return alvo != null && !(alvo.getCor() == cor_jogador); 
	  }
	  
	  if (diferenca_coluna != 0) {
		  return false;
	  }
	  
	  if (diferenca_linha == direcao) {
		  return alvo == null;
	  }
	  
	  if (linha_destino == 2 * direcao && posicao == "original") {
		  int meio = linha_peca + direcao;
		  
		  return alvo == null && tabuleiro.matriz[meio][coluna_peca] == null;
	  }
	  
	  return false;
		
		
  }
  
  
  
  public void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga)
  {
	  //Testar para peao do jogador 2
	  char colunaChar_peao = coordenada_peca.toLowerCase().charAt(0);
	  char linhaChar_peao = coordenada_peca.charAt(1);
	  
	  int coluna_peao = colunaChar_peao - 'a';
	  int linha_peao = 8 - Character.getNumericValue(linhaChar_peao);
	  
	  char colunaChar_peca_inimiga = coordenada_peca_inimiga.toLowerCase().charAt(0);
	  char linhaChar_peca_inimiga = coordenada_peca_inimiga.charAt(1);
	  
	  int coluna_peca_inimiga = colunaChar_peca_inimiga - 'a';
	  int linha_peca_inimiga = 8 - Character.getNumericValue(linhaChar_peca_inimiga);
	  
	  int diferenca_linha = linha_peca_inimiga - linha_peao;
	  int diferenca_coluna = coluna_peca_inimiga - coluna_peao;
	  
	  if (Math.abs(diferenca_linha) > 1 && Math.abs(diferenca_coluna) > 1) {
		  System.out.println("Movimento inválido! O peão não pode atacar");
		  return ;
	  }
	  
	  Pecas peca_inimiga = tabuleiro.getPecaNaPosicao(coordenada_peca_inimiga);
	  
	  if (peca_inimiga.getCor() == this.getCor()) {
		  System.out.println("Movimento inválido! O peão não pode atacar");
		  return ;
	  }
	  
	  

	  tabuleiro.matriz[linha_peca_inimiga][coluna_peca_inimiga] = this;
	  tabuleiro.matriz[linha_peao][coluna_peao] = null;
	  
  }
  
}
  
  
