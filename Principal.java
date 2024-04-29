/* ****************************************************************
* Autor: Icaro Medeiros Lobo                                      *
* Matricula: 202310130                                            *
* Data Inicio: 13.03.2024                                         *
* Data Ultima Alteracao: 29.04.2024                               *
* Nome programa: Trabalho 01                                      *
* Funcao codigo: Cria e renderiza a parte visual do programa      * 
* alem de controlar e criar os objetos dos elementos graficos     *
**************************************************************** */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Principal extends Application {

  Trem[] trens = new Trem[2]; // cria vetor global para os trens
  Tocha[] tochas = new Tocha[4]; // cria vetor global para as tochas
  BotaoVelocidade[] botoesVelocidade = new BotaoVelocidade[4]; // cria vetor global para botoes de velocidade
  Text velocimetro1 = new Text(); // velocimetro do primeiro trem
  Text velocimetro2 = new Text(); // velocimetro do segundo trem
  Group fundo = new Group(); // armazena elementos a serem renderizados  

  /* ****************************************************************
  * Metodo: start                                                   *
  * Funcao: inicia o palco do javafx, o "root" do programa          *
  * Parametros: palco                                               *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  @Override
  public void start(Stage stage) { // inicia javafx com palco

    StackPane layout = new StackPane(); // cria layout
    Scene scene = new Scene(layout, 800, 650); // cria cena com 600px x 500px
     
    BotaoReset botaoReset = new BotaoReset(scene, trens); // cria botao reset invisivel
    
    configuracoesIniciais(stage, scene, fundo); // configuracoes iniciais do programa
    
    // cria objetos graficos 
    Trem.criaTrens(trens, velocimetro1, velocimetro2); // cria os trens
    Tocha.criaTochas(tochas, trens, scene); // cria os seletores de trilhos
    BotaoVelocidade.criaBotoes(botoesVelocidade, trens); // cria botoes de velocidade
    ajustaVelocimetros();
    

    trens[0].start(); // inicia movimento do primeiro trem
    trens[1].start(); // inicia movimento do segundo trem

    stage.setOnCloseRequest(event -> paraThreads(trens[0], trens[1])); // ao clicar em sair, para as threads

    renderizar(layout, botaoReset); // renderiza os elementos visuais  
  
    stage.show(); // renderiza elementos do palco			   
  } // fim do start

  /* ****************************************************************
  * Metodo: main                                                    *
  * Funcao: iniciar programa                                        *
  * Parametros: argumentos String                                   *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public static void main(String args[]){
    launch(args); // inicia a aplicacao
  } // fim do main
 
  /* ****************************************************************
  * Metodo: reset                                                   *
  * Funcao: reinicia a posicao e velocidade dos trens               *
  * Parametros: nenhum                                              *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void reset(){
    trens[0].resetTrem();
    trens[1].resetTrem();
  } // fim do reset
  
  /* ****************************************************************
  * Metodo: configuracoesIniciais                                   *
  * Funcao: iniciar configuracoes iniciais do programa              *
  * Parametros: Stage (palco), Scene(cena) e Group (fundo da tela)  *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void configuracoesIniciais(Stage stage, Scene scene, Group grupoFundo) {
    
    stage.setResizable(false); // impede usuario de alterar tamanho da janela
    stage.setScene(scene); // adiciona cena ao palco
    stage.setTitle("On the rails! Woot Woot!"); // titulo do canto superior esquerdo
    
    Image fundoImagem = new Image("img/background.png"); // carrega imagem de fundo com trilhos
    ImageView fundo = new ImageView(fundoImagem); // transforma imagem do fundo em elemento do javafx
    grupoFundo.getChildren().add(fundo); // adiciona fundo aos renderizaveis

    Image icone = new Image("img/thomas.png"); // carrega imagem do icone
    stage.getIcons().add(icone); // adiciona icone ao programa
  } // fim de configuracoesIniciais
  
  /* ****************************************************************
  * Metodo: ajustaVelocimetros                                      *
  * Funcao: ajusta a posicicao dos velocimetros na tela             *
  * Parametros: nenhum                                              *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void ajustaVelocimetros() {
    velocimetro1.setTranslateX(velocimetro1.getTranslateX() - 165);
    velocimetro1.setTranslateY(velocimetro1.getTranslateY() + 30);
    velocimetro2.setTranslateX(velocimetro2.getTranslateX() + 165);
    velocimetro2.setTranslateY(velocimetro2.getTranslateY() + 30);
  } // fim de ajustaVelocimetro

  /* ****************************************************************
  * Metodo: renderizar                                              *
  * Funcao: renderiza elementos graficos do programa                *
  * Parametros: StackPane (layout), BotaoReste (botao de resetar)   *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void renderizar(StackPane layout, BotaoReset botaoReset) {
    layout.getChildren().add(fundo); 
    layout.getChildren().add(botaoReset);
    layout.getChildren().add(trens[0].getImg()); 
    layout.getChildren().add(trens[1].getImg());
    layout.getChildren().add(tochas[0].getBotao());
    layout.getChildren().add(tochas[1].getBotao());
    layout.getChildren().add(tochas[2].getBotao());
    layout.getChildren().add(tochas[3].getBotao());
    layout.getChildren().add(botoesVelocidade[0]);
    layout.getChildren().add(botoesVelocidade[1]);
    layout.getChildren().add(botoesVelocidade[2]);
    layout.getChildren().add(botoesVelocidade[3]);
    layout.getChildren().add(velocimetro1);
    layout.getChildren().add(velocimetro2);
  } // fim de renderizar

  /* ****************************************************************
  * Metodo: paraTrhreads                                            *
  * Funcao: encerra a execucao das threads dos trens                *
  * Parametros: nenhum                                              *
  * Saida: nenhuma                                                  *
  **************************************************************** */
  public void paraThreads(Trem t1, Trem t2) {
    t1.stop(); // para o primeiro trem
    t2.stop(); // para o segundo trem
  } // fim de para threads

} // fim do Principal



