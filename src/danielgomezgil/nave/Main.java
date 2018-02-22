/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danielgomezgil.nave;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
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

   //Variables de Nave y Balas 
    String fondo = "fondo.jpg";
    int numAste = 15;
    public static Nave nave = new Nave();
    Bala bala = new Bala();
    Label fin = new Label("Game Over , Good Luck the next...");
    Label presion = new Label("Presiona 'N' Para reiniciar el juego" );
    public static Pane root = new Pane();
    ArrayList<Asteroide> listaAsteroides = new ArrayList();
    // Booleana
     Boolean gameOver;
    @Override
        public void start(Stage primaryStage) {
        root.setStyle("-fx-background-image: url('" + fondo + "');"
                      + "-fx-background-position: center center;"
                      + "-fx-background-repeat: strech;");
        gameOver = false;
        
       
            
        
        Scene scene = new Scene(root, 800, 600);

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
        for ( int i = 0 ; i<numAste ; i++ ){
            Asteroide asteroide = new Asteroide();
            listaAsteroides.add(asteroide);
        }
 
        
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
            bala.newBala();
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
//                asteroide.setVisible(false);
                nave.setXNoFuego(400);
                nave.setYNoFuego(300);
                fin.setVisible(true);        
               }else{
                   
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
                if (numAste == 0){
                 numAste = 0 ;   
                } else if (numAste > 0) {
                    numAste -= 1;
                }
                //root.getChildren().add(listaAsteroides.);
                //Movimiento del asteroid
                //Rotacion entre 0 y 360
                nave.rota360();
                // Apunto a Bala
                bala.apuntoBala();
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


