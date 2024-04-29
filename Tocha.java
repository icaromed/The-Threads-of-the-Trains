/* ****************************************************************
* Autor: Icaro Medeiros Lobo                                      *
* Matricula: 202310130                                            *
* Data Inicio: 26.03.2024                                         *
* Data Ultima Alteracao: 27.03.2024                               *
* Nome programa: Trabalho 01                                      *
* Funcao codigo: Cria botoes com formato de tocha que sao         *
* responsaveis por alterar a posicao inicial do trem e seu padrao *
* de movimento                                                    *
**************************************************************** */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Tocha extends ImageView {
  private Button botao = new Button(); // cria botao para tocha

  public Tocha(int x, int y, boolean esquerda, boolean cima, Trem trem1, Trem trem2, Scene scene) {
    super(new Image("img/light.png")); // carrega imagem da tocha
    setFitHeight(50); // altura da tocha 
    setFitWidth(50); // largura da tocha

    botao.setGraphic(this); // adiciona simbolo de tocha
    botao.setPrefHeight(20); // altura do botao
    botao.setPrefWidth(20); // largura do botao
    
    //move tocha para posicao inicial
    botao.setTranslateX(botao.getTranslateX() + x); 
    botao.setTranslateY(botao.getTranslateY() + y);
    
    // lida com clique no botao
    botao.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
	trem1.setEsquerda(esquerda); // altera posicaoInicial X do trem
	trem1.setCima(cima); // altera posicaoInicial Y do trem
        trem1.definePosicaoInicial(); // confirma alteracao na posicaoInicial  	
        trem1.resetTrem(); // reinicia primeiro trem
        trem2.resetTrem(); // reinicia segundo trem
      } // fim do handle
    });

// altera cursor para mao quando entra no botao
    botao.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
        scene.setCursor(Cursor.HAND);      
      }
    });
    
    // altera cursor para padrao quando sai do botao
    botao.setOnMouseExited(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
        scene.setCursor(Cursor.DEFAULT);      
      }
    });


  } //  fim do construtor

  /* ****************************************************************
  * Metodo: getBotao                                                *
  * Funcao: retorna botao do seletor                                *
  * Parametros: nenhum                                              *
  * Saida: Button (botao)                                           *
  **************************************************************** */
  public Button getBotao(){
    return botao;
  } // fim do getBotao

  /* ****************************************************************
  * Metodo: criaTochas                                              *
  * Funcao: cria os seletores dos trilhos                           *
  * Parametros: vetor do tipo Tocha, vetor do tipo Trem e cena do   *
  * tipo Scene                                                      *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public static void criaTochas(Tocha[] tochas, Trem[] trens, Scene scene) {
    
    tochas[0] = new Tocha(-140, -285, true, true, trens[0], trens[1], scene); // esquerda-cima 
    tochas[1] = new Tocha(-140, 285, true, false, trens[0], trens[1], scene); // esquerda-baixo
    tochas[2] = new Tocha(140, -285, false, true, trens[1], trens[0], scene); // direita-cima
    tochas[3] = new Tocha(140, 285, false, false, trens[1], trens[0], scene); // direita-baixo
  } // fim criaTochas

} // fim Tocha
