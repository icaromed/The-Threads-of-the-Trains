/* ****************************************************************
* Autor: Icaro Medeiros Lobo                                      *
* Matricula: 202310130                                            *
* Data Inicio: 27.03.2024                                         *
* Data Ultima Alteracao: 30.04.2024                               *
* Nome programa: Trabalho 01                                      *
* Funcao codigo: Configura botao para resetar o movimento e       * 
* a posicao dos trens                                             *
**************************************************************** */

import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.event.EventHandler;

public class BotaoReset extends Button {
  Trem[] trens; // armazena os trens

  public BotaoReset(Scene scene, Trem[] trens) {
    this.trens = trens; // copia os trens do programa principal

    this.setPrefHeight(100); // altura do botao
    this.setPrefWidth(100); // largura do botao
    this.setTranslateX(this.getTranslateX() + 330); // move para direita
    this.setTranslateY(this.getTranslateY() + 260); // move para baixo
    this.setOpacity(0); // deixa invisivel
    
    // altera cursor para mao quando entra no botao
    this.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
        scene.setCursor(Cursor.HAND);      
      }
    });
    
    // altera cursor para padrao quando sai do botao
    this.setOnMouseExited(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
        scene.setCursor(Cursor.DEFAULT);      
      }
    });

    // chama metodo reset ao ser clicado
    this.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me){
        reset();
      }
    });
  } // fim do construtor
  
  /* ****************************************************************
  * Metodo: reset                                                   *
  * Funcao: reinicia a posicao e velocidade dos trens               *
  * Parametros: nenhum                                              *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void reset(){
    trens[0].resetTrem();
    trens[1].resetTrem();
  } // fim reset
} // fim da classe
