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
    double veloBala = 0.0; //Velocidad Bala 
    double veloBX; //Velocidad Bala X inicial
    double veloBY; //Velocidad Bala Y inicial
    double posiBalaX;
    double posiBalaY;
    String fondo = "fondo.jpg";
    int numAste = 15;
    int numBala;
    Nave nave = new Nave();
    Bala bala;
    Label fin = new Label("Game Over , Good Luck the next...");
    Label presion = new Label("Presiona 'N' Para reiniciar el juego" );
    ArrayList<Asteroide> listaAsteroides = new ArrayList();
    ArrayList<Bala> listaBala = new ArrayList();
    // Booleana
     Boolean gameOver;
    @Override
        public void start(Stage primaryStage) {
        Pane root = new Pane();
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
                   bala = new Bala();
                   numBala += 1 ;
                   listaBala.add(bala);
                   bala.visibilidad(true);
                   bala.posiX(nave.getXFuego() + 30);
                   bala.posiY(nave.getYFuego() + 26);
                   root.getChildren().add(bala.getBala());
                    posiBalaX = veloBala * Math.cos(nave.getANMV());
                    posiBalaY = veloBala * Math.sin(nave.getANMV());
                   veloBala += (3 *2) ;
                    if (veloBala > 15){
                        veloBala = 15;
                    }
                    //añadimos a Root
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
                if ( bala != null ){
                    for (int i = 0 ; i < listaBala.size(); i++) {
                    Bala lis = listaBala.get(i);
                    lis.posiX(bala.getX() + posiBalaX);
                    lis.posiY(bala.getY() + posiBalaY); 
                    }
                }
            
                     
                //Primer Codigo de Rotacion
               /* if (navefu.getRotate() >= -10 && navefu.getRotate() <= 10){
                    // Posicion de la bala
                    bala.setCenterX(navefu.getX() + 22 );
                    bala.setCenterY(navefu.getY());
                }else if  (navefu.getRotate() >= 10 && navefu.getRotate() <= 91 || navefu.getRotate() <= -200 && navefu.getRotate() >= -271){
                    // Posicion de la bala
                    bala.setCenterX(navefu.getX() + 55);
                    bala.setCenterY(navefu.getY() +30);
                }else if  (navefu.getRotate() >= 91 && navefu.getRotate() <= 180 ||navefu.getRotate() <= -159 && navefu.getRotate() >= -181  ){
                    //Posicionde la Bala
                    bala.setCenterX(navefu.getX() + 22);
                    bala.setCenterY(navefu.getY() +60);
                }else if  (navefu.getRotate() >= 181 && navefu.getRotate() <= 280 || navefu.getRotate() <= -10 && navefu.getRotate() >= -94 ){
                    //Posicion la Bala
                    bala.setCenterX(navefu.getX() - 10);
                    bala.setCenterY(navefu.getY() +30);
                } */
                //vuelta al plano
                nave.vuelve();
//                   if (getColision(asteroide.getAsteroide(), bala.getBala())){
//                       asteroide.visible(false);
//                       asteroide.transX(asteroide.getLayoutX() + 5000);
//                    }
//                  
//                  if (getColisionN(asteroide.getAsteroide(), nave.morro)){
//                       nave.morro.setVisible(false);
//                      nave.setVisibilidadFuego(false);
//                      gameOver = true;
//                      
//                   }
            
                //movBol();
               // rstBol();
               
                }
          }
      };
   movNav.start(); 
}
        
        
        
        
        // Metodo para movimiento de Bola y seguimiento del morro
    /*   private void movBol(){
           /* posiBalaX = bala.getCenterX();
            posiBalaY = bala.getCenterY();
            veloBX = veloBala * Math.cos(anguloMv);
            veloBY = veloBala * Math.sin(anguloMv);
            anguloMv = Math.toRadians(navefu.getRotate());
            bala.setCenterX(posiBalaX + veloBala);
            bala.setCenterY(posiBalaY + veloBala);
           
           bala.setCenterX((posiBalaX + navefu.getX())+65);
           bala.setCenterY((posiBalaY + navefu.getY())+23);
         };*/
       //Reseteo de Bola

/*        private void rstBol(){
            if (bala.getTranslateX() >= 800 ){
                    
                }else if(bala.getTranslateX() <= -400 ){
                    bala.setTranslateX(bala.getTranslateX() + 402);
                    veloBala = 0.0;
                    bala.setVisible(false);
                }else if (bala.getTranslateY() <= -300){
                    bala.setTranslateY(bala.getTranslateY() + 300);
                    veloBala = 0.0;
                    bala.setVisible(false);
                }else if (bala.getTranslateY() >= 300){
                    bala.setTranslateY(bala.getTranslateY() - 300);
                    veloBala = 0.0;
                    bala.setVisible(false);
                }else if (bala.getTranslateX() >= 400){
                    bala.setTranslateX(bala.getTranslateX() - 402);
                    veloBala = 0.0;
                    bala.setVisible(false);
                }
        }*/
        //Metodo para Freno

        private boolean getColision(Polygon obj1, Circle obj2){
            return !Shape.intersect(obj1, obj2).getBoundsInLocal().isEmpty();
        }
        private boolean getColisionN(Polygon obj1, Rectangle obj2){
            return !Shape.intersect(obj1, obj2).getBoundsInLocal().isEmpty();
        }     

}


