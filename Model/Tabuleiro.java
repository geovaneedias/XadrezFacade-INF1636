package Model;

import java.util.*;
import Model.ObserverTabuleiro;

public class Tabuleiro {
  private final List<ObserverTabuleiro> observadores = new ArrayList<>();

  public Pecas[][] matriz = new Pecas[8][8];
  private int cont1;
  private int cont2;
  
  public Tabuleiro(){
    
    inicializaTabuleiroNormal();
    //ataquePeaoCavalo();
    //roqueLongoCurto();
	//xequeDireto();
	//xequeDescoberto();
	//xequeMate();
    //promocaoDoPeao();
	//congelamento();
    
  }
  
  public Pecas getPecaNaPosicao(String coordenada) {
	  if (coordenada.length() != 2) {
		  return null;
	  }
	  
	  char colunaChar = coordenada.toLowerCase().charAt(0);
	  char linhaChar = coordenada.charAt(1);
	  
	  int coluna = colunaChar - 'a';
	  int linha = 8 - Character.getNumericValue(linhaChar);
	  
	  if (coluna < 0 || coluna > 7 || linha < 0 || linha > 7) {
		  return null;
	  }
	  
	  
	  return matriz[linha][coluna];
  }
  
  
  
  public void ataque(Pecas peca, Tabuleiro tabuleiro, String coordenada, String coordenada_destino)
  {
	  if (peca instanceof Peao) {
	
		  Peao peao = (Peao)peca;
		 
		  peao.ataque(tabuleiro, coordenada, coordenada_destino);;
	  }
	  
	  
	  else if (peca instanceof Torre) {
		  Torre torre = (Torre)peca;
		  
		  torre.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  else if (peca instanceof Bispo) {
		  Bispo bispo = (Bispo)peca;
		  
		  bispo.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Rainha) {
		  Rainha rainha = (Rainha)peca;
		  
		  rainha.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Rei) {
		  Rei rei = (Rei) peca;
		  
		  rei.ataque(tabuleiro, coordenada, coordenada_destino);
	  }
	  
	  
	  
	  else if (peca instanceof Cavalo) {
		  Cavalo cavalo = (Cavalo) peca;
		  
		  cavalo.ataque(tabuleiro, coordenada, coordenada_destino);
		  
	  }
	  
	  
  }
  
  public void printaTabuleiro(){
    
    for (cont1 = 0; cont1 < 8; cont1++)
    {
      for (cont2 = 0; cont2 < 8; cont2++)
      {
        if (matriz[cont1][cont2] == null){
          System.out.print("00");
        }
        
        else{
          System.out.print("" + matriz[cont1][cont2].getPeca() + "");  
        }
        
      }
      
      System.out.println();
    }
    
  }
  
  public void inicializaTabuleiroNormal() {
	
	  //Posicionar as pecas do jogador2 
	  matriz[0][0] = new Torre("preto", "torre");
	  matriz[0][7] = new Torre("preto", "torre");
	  matriz[0][1] = new Cavalo("preto", "cavalo");
	  matriz[0][6] = new Cavalo("preto", "cavalo");
	  matriz[0][2] = new Bispo("preto", "bispo");
	  matriz[0][5] = new Bispo("preto", "bispo");
	  matriz[0][3] = new Rainha("preto", "rainha");
	  matriz[0][4] = new Rei("preto", "rei");
	    
	  //Posicionar peao do jogador2
	    
	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[1][cont2] = new Peao("preto", "peao");
	  }
	      
	  //Resto do Tabuleiro
	  for (cont1 = 2; cont1 < 6; cont1++)
	  {
		  for (cont2 = 0; cont2 < 8; cont2++)
		  {
			  matriz[cont1][cont2] = null;
		  } 
	  }
	    
	  //Posicionar peao do jogador1
	    

	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[6][cont2] = new Peao("branco", "peao");
	  }
 
	  //Posicionar as pecas do jogador1
	  matriz[7][0] = new Torre("branco", "torre");
	  matriz[7][7] = new Torre("branco", "torre");
	  matriz[7][1] = new Cavalo("branco", "cavalo");
	  matriz[7][6] = new Cavalo("branco", "cavalo");
	  matriz[7][2] = new Bispo("branco", "bispo");
	  matriz[7][5] = new Bispo("branco", "bispo");
	  matriz[7][3] = new Rainha("branco", "rainha");
	  matriz[7][4] = new Rei("branco", "rei");
  }
  
  public void ataquePeaoCavalo() {
	
	  //Posicionar as pecas do jogador2 
	  matriz[0][0] = new Torre("preto", "torre");
	  matriz[0][7] = new Torre("preto", "torre");
	  matriz[0][1] = new Cavalo("preto", "cavalo");
	  matriz[0][6] = new Cavalo("preto", "cavalo");
	  matriz[0][2] = new Bispo("preto", "bispo");
	  matriz[0][5] = new Bispo("preto", "bispo");
	  matriz[0][3] = new Rainha("preto", "rainha");
	  matriz[0][4] = new Rei("preto", "rei");
	    
	  //Posicionar peao do jogador2
	    
	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[1][cont2] = new Peao("preto", "peao");
	  }
	      
	  //Resto do Tabuleiro
	  for (cont1 = 2; cont1 < 6; cont1++)
	  {
		  for (cont2 = 0; cont2 < 8; cont2++)
		  {
			  matriz[cont1][cont2] = null;
		  } 
	  }
	    
	  //Posicionar peao do jogador1
	    

	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[6][cont2] = new Peao("branco", "peao");
	  }
 
	  //Posicionar as pecas do jogador1
	  matriz[7][0] = new Torre("branco", "torre");
	  matriz[7][7] = new Torre("branco", "torre");
	  matriz[7][1] = new Cavalo("branco", "cavalo");
	  matriz[7][6] = new Cavalo("branco", "cavalo");
	  matriz[7][2] = new Bispo("branco", "bispo");
	  matriz[7][5] = new Bispo("branco", "bispo");
	  matriz[7][3] = new Rainha("branco", "rainha");
	  matriz[7][4] = new Rei("branco", "rei");
  
	  matriz[5][4] = new Peao("preto", "peao");
	  matriz[3][3] = new Cavalo("preto", "cavalo");
	  
  }
  
  public void roqueLongoCurto() {
	  
	  //Posicionar as pecas do jogador2 
	  matriz[0][0] = new Torre("preto", "torre");
	  matriz[0][7] = new Torre("preto", "torre");

	  matriz[0][4] = new Rei("preto", "rei");
	    
	  //Posicionar peao do jogador2
	    
	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[1][cont2] = new Peao("preto", "peao");
	  }
	      
	  //Resto do Tabuleiro
	  for (cont1 = 2; cont1 < 6; cont1++)
	  {
		  for (cont2 = 0; cont2 < 8; cont2++)
		  {
			  matriz[cont1][cont2] = null;
		  } 
	  }
	    
	  //Posicionar peao do jogador1
	    

	  for (cont2 = 0; cont2 < 8; cont2++)
	  {
		  matriz[6][cont2] = new Peao("branco", "peao");
	  }
 
	  //Posicionar as pecas do jogador1
	  matriz[7][0] = new Torre("branco", "torre");
	  matriz[7][7] = new Torre("branco", "torre");

	  matriz[7][4] = new Rei("branco", "rei");
  
  }
  
  public void xequeDireto() {
	  
	  matriz[6][0] = new Rei("preto", "rei");
	  matriz[0][7] = new Torre("branco", "torre");
	  
  }
  
  public void xequeDescoberto() {
	  
	  matriz[0][7] = new Bispo("branco", "bispo");
	  matriz[1][6] = new Torre("branco", "torre"); //problema com peao
	  
	  matriz[7][0] = new Rei("preto", "rei");
  }
  
  public void xequeMate() {
	  
	  matriz[0][7] = new Torre("branco", "torre");
	  matriz[1][2] = new Rainha("branco", "rainha");
	  
	  matriz[7][7] = new Rei("preto", "rei");
	  
  }
  
  public void promocaoDoPeao() {
	  
	  matriz[1][0] = new Peao("branco", "peao");
  }
  
  public void congelamento() {
	  
	  
	  matriz[5][5] = new Rei("branco", "rei");
	  matriz[1][0] = new Bispo("preto", "bispo");
	  matriz[6][3] = new Rainha("preto", "rainha");
	  
	  
  }
  
  

  public void adicionarObservador(ObserverTabuleiro o) {
	  observadores.add(o);
  }

  private void notificarObservadores() {
	  
	  for (ObserverTabuleiro o : observadores) {
		  o.atualizar(this);
	  }
    
	  notificarObservadores();
  }
    
}
