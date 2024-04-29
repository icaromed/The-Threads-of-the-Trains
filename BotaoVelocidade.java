/* ****************************************************************
* Autor: Icaro Medeiros Lobo                                      *
* Matricula: 202310130                                            *
* Data Inicio: 27.03.2024                                         *
* Data Ultima Alteracao: 27.03.2024                               *
* Nome programa: Trabalho 01                                      *
* Funcao codigo: Configura botao para controlar a velocidade do   *
* trem                                                            * 
**************************************************************** */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class BotaoVelocidade extends Button {
  
  private Image imagem; // imagem do sinal
  private ImageView icone; // imagem do sinal para javafx

  public BotaoVelocidade(int num, Trem trem) {
    super();
    
    if(num == -1) { // se -1, menos; se 1, mais
      imagem = new Image("img/minus.png"); // sinal de menos
    } else if(num == 1) {
      imagem = new Image("img/plus.png"); // sinal de mais
    }
    
    icone = new ImageView(imagem); // imagem tratavel pelo javafx
    icone.setFitWidth(20); // largura
    icone.setFitHeight(20); // altura

    this.setGraphic(icone); // adiciona icone ao botao
    
    // quando botao clicado
    this.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae){
        if(trem.getVelocidade() >= 0 && trem.getVelocidade() <= 6) {
          trem.incrementaVelocidade(0.25 * num); // multiplica sinal por 0.25px
    	          				 // para incrementar ou decrementar
    					         // a velocidade 
        }
	if(trem.getVelocidade() <= 0) { // impede velocidade negativa
	  trem.setVelocidade(0);
	}
	if(trem.getVelocidade() >= 6) { // impede extrapolar velocidade maxima
	  trem.setVelocidade(6);
	}
	
      }
    });
  } // fim construtor

  /* ****************************************************************
  * Metodo: criaBotoes                                              *
  * Funcao: cria os botoes                                          *
  * Parametros: vetor do tipo BotaoVelocidade e vetor do tipo Trem  *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public static void criaBotoes(BotaoVelocidade[] botoesVelocidade, Trem[] trens) {
    // cria botoes que manipulam a velocidade dos trens
    botoesVelocidade[0] = new BotaoVelocidade(-1, trens[0]);
    botoesVelocidade[1] = new BotaoVelocidade(1, trens[0]);
    botoesVelocidade[2] = new BotaoVelocidade(-1, trens[1]);
    botoesVelocidade[3] = new BotaoVelocidade(1, trens[1]);
    
    // posiciona botoes na tela
    botoesVelocidade[0].setTranslateX(botoesVelocidade[0].getTranslateX() - 190);
    botoesVelocidade[1].setTranslateX(botoesVelocidade[1].getTranslateX() - 150);
    botoesVelocidade[2].setTranslateX(botoesVelocidade[2].getTranslateX() + 150);
    botoesVelocidade[3].setTranslateX(botoesVelocidade[3].getTranslateX() + 190);
  } // fim criaBotoes 

} // fim BotaoVelocidade
