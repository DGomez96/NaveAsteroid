/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danielgomezgil.nave;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
 import javafx.stage.Stage; 

/**
 *
 * @author 1DAW03
 */

public class Main extends Application {
    //!---------------------------------------------------------------------!
   // String & Labels
    String fondo = "fondo.jpg";
    String Color = "51A3A3";
    Label fin = new Label("Game Over , Good Luck the next...\n Manten N  para Reiniciar Juego");
    // Variables de Clase
    public static Nave nave = new Nave();
    public static Pane root = new Pane();
    Scene scene;
    Asteroide asteroide;
    Bala bala = new Bala();

    // ArrayLists de Balas y Asteroides
    ArrayList<Bala> listaBala = new ArrayList();
    ArrayList<Asteroide> listaAsteroides = new ArrayList();
    //!---------------------------------------------------------------------!
    // Booleana
     Boolean gameOver = false;
    @Override
        public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("Logo/Logo.png"));
        fin.setStyle("--fx-background-color: #" + Color);
        Image brochita = new Image("brocha.png");
        ImageView brocha = new ImageView(brochita);
        root.getChildren().add(brocha);
       // brocha.setOnMouseClicked();
        
        scene = new Scene(root, 800 , 600);
        scene.getStylesheets().add("css/style.css");
        //Nave sin fuego
        nave.setXNoFuego(nave.posinaveX);
        nave.setYNoFuego(nave.posinaveY);
        root.getChildren().add(nave.navenofu);
        //nave con fuego
        nave.setXFuego(nave.posinaveX);
        nave.setYFuego(nave.posinaveY);
        nave.setVisibilidadFuego(false);
        root.getChildren().add(nave.navefu);
        //Nave con Frenos activados
        nave.setXFreno(nave.posinaveX);
        nave.setYFreno(nave.posinaveY);
        nave.setVisibilidadFreno(false);
        root.getChildren().add(nave.navez);
        
        // Arraylist de Asteroides
        
        fin.setVisible(false);
        root.getChildren().add(fin);
        root.getChildren().add(nave.GetGeonave());
        scene.setOnKeyPressed((KeyEvent event) -> {
        switch(event.getCode()){
     
            case UP:
                    //Pulsado arriba
                   nave.naveSP += 2.0; 
                   nave.MaxVelo(10);
                   nave.setVisibilidadFreno(false);
            break;
 
            case RIGHT:
            //Tecla derecha
                nave.GiroR(2.0);
            break;
            
            case LEFT:
                nave.GiroI(2.0);
                //Tecla Izquierda
            break;
            
            case SPACE:
                bala = new Bala();
                listaBala.add(bala);
                bala.disparo();
                //Añado a root
                break;
            
            case Z:
                nave.freno(true);
                if (nave.freno(false) == nave.freno(false) ){
                    nave.freno(true);
                }else{
                    nave.freno(false);
                }
                break;   
                
            case N:
                gameOver = false;
                fin.setVisible(false);
                nave.setVisibilidadNoFuego(true);
                break;
                
            }
        scene.setOnKeyReleased((KeyEvent event1) -> {   
            nave.setVisibilidadNoFuego(true);
            nave.setVisibilidadFuego(false);
            nave.setVisibilidadFuego(false);
            nave.setRotacionNoFuego(nave.getRotacionFuego());
            });
        });
        //Recorrer Lista Asteroides
        for ( int i = 0 ; i < 3; i++){
            asteroide = new Asteroide();
            //System.out.println("Ha hecho" + i + "Asteroides la lista consta de : " +listaAsteroides.get(i));
            listaAsteroides.add(asteroide);
            root.getChildren().add(asteroide.getAsteroide());
        }
        primaryStage.setTitle("Asteroid Game por Daniel Gómez");
        primaryStage.setScene(scene);
        primaryStage.show();

         AnimationTimer movNav = new AnimationTimer(){
            @Override         
            public void handle(long now){
                 if (gameOver == true){
                fin.setTranslateX(400);
                fin.setTranslateY(300);
                fin.setScaleX(2);
                fin.setScaleY(2);
                
                nave.setVisibilidadFreno(false);
                nave.setVisibilidadFuego(false);
                nave.setVisibilidadNoFuego(false);
                asteroide.visible(false);
                fin.setVisible(true);    
               }else if (gameOver == false){
                
                //Posiciones actualizandose de Nave Fuego,Nave no Fuego y GeoNave + Movimiento segun el angulo de la nave
                nave.movAN();
                //Visibilidad de la nave sin fuego y con Fuego
                nave.autoVisi();
                //posicion de Frenaje
                nave.posiFreno();
                //Decremento de velocidad de angulo , Posicion y Rotacion de las "Naves" + asteroide
                nave.decreAn();
                /*Posicion y Rotacion de la Nave Geometrica,Imagen y Asteroides*/
                nave.rotaAUTO();
                //Rotacion entre 0 y 360
                nave.rota360();


                //Mover asteroides
                for (int i = 0 ; i < listaAsteroides.size(); i++){
                    asteroide = listaAsteroides.get(i);
                    asteroide.mover();
                }
                
                // Sumo +1 Bala a la Lista y la muevo
                for (int i = 0 ; i < listaBala.size(); i++) {
                    bala = listaBala.get(i);
                    bala.mover();
                }

                if (asteroide != null){
                    if (getColisionN(asteroide.getAsteroide(),nave.morro)){
                        gameOver = true;
                    }else if (getColision(asteroide.getAsteroide(),bala.getBala())){
                        asteroide.visible(false);
                        listaAsteroides.remove(asteroide.getAsteroide());
                    }
                }
                //vuelta al plano
                nave.vuelve();


              
                }
          }
      };
   movNav.start(); 
}       
        private boolean getColision(Polygon obj1, Circle obj2){
            return !Shape.intersect(obj1, obj2).getBoundsInLocal().isEmpty();
        }
        private boolean getColisionN(Polygon obj1, Rectangle obj2){
            return !Shape.intersect(obj1, obj2).getBoundsInLocal().isEmpty();
        }    
}


