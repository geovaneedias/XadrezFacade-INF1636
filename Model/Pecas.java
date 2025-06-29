package Model;
import java.awt.*;

public abstract class Pecas {
	
	protected Image imagem;
	
	public Image getImage() {
		return imagem;
	}

	public abstract String getPeca();
  
	public abstract String getCor();
  
	//public abstract void movimento(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_destino);

	public abstract boolean movimentoValido(Tabuleiro tabuleiro, int linha_peca, int coluna_peca, int linha_destino, int coluna_destino);
	
	public abstract void ataque(Tabuleiro tabuleiro, String coordenada_peca, String coordenada_peca_inimiga);
	
  
}
