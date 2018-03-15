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
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
 import javafx.stage.Stage; 

/**
 *
 * @author 1DAW03
 */

public class Main extends Application {
    //!---------------------------------------------------------------------!
    //Variables de posicion Asteroide Eliminado en distintas fases. 
    double posiAsteX;
    double posiAsteY;
    //Variable cambio de Style
    int cambioStyle = 0;
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
    //Bala a eliminar
    Bala balaelimi;
    //Asteroide a eliminar
    Asteroide astelimi;
    //Layout Score y Score
    Text textScore;
    int score;
    int numOleada = 1;
    //Seleccion de Oleada
    boolean setOleada;
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
        brocha.setLayoutX(750);
        brocha.setLayoutY(550);
        root.getChildren().add(brocha);
        brocha.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            if (cambioStyle == 0 ){
                scene.getStylesheets().add("css/style1.css");
                cambioStyle = 1;
            }else if ( cambioStyle == 1){
                scene.getStylesheets().add("css/style2.css");
                cambioStyle = 2;
            }else if( cambioStyle == 2){
                scene.getStylesheets().add("css/style3.css");
                cambioStyle = 3 ;
            }else if ( cambioStyle == 3){
                scene.getStylesheets().add("css/style.css");
                cambioStyle = 0;
                System.out.println(cambioStyle);
                }    
            }
        });

       //Layouts para puntuaciones.
       //Layout Principal
       HBox paneScores = new HBox();
       paneScores.setTranslateY(20);
       paneScores.setMinWidth(800);
       paneScores.setAlignment(Pos.CENTER);
       paneScores.setSpacing(100);
       root.getChildren().add(paneScores);

       //Layout para puntuacion Actual
       HBox paneCurrentScore = new HBox();
       paneCurrentScore.setSpacing(10);
       paneScores.getChildren().add(paneCurrentScore);

       //Texto de la Etiqueta de la puntuacion
       Text textTitleScore = new Text("Score: ");
       textTitleScore.setFont(Font.font(15));
       textTitleScore.setFill(WHITE);

       //Texto para la puntuacion
       textScore = new Text("0");
       textScore.setFont(Font.font(15));
       textScore.setFill(WHITE);
       
       //Texto de la Etiqueta de la puntuacion
       Text oleada = new Text("Oleada: ");
       oleada.setFont(Font.font(15));
       oleada.setFill(RED);
       
       //Texto para la puntuacion
       Text oleadaT = new Text("1");
       oleadaT.setFont(Font.font(15));
       oleadaT.setFill(RED);

       //Añadir textos
       paneCurrentScore.getChildren().add(textTitleScore);
       paneCurrentScore.getChildren().add(textScore);
       paneCurrentScore.getChildren().add(oleada);
       paneCurrentScore.getChildren().add(oleadaT);
       root.getChildren().add(paneCurrentScore);
       
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
        
        // Aqui añado la proteccion
        
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
        for ( int i = 0 ; i < 5 ; i++){
            asteroide = new Asteroide(1,1);
            listaAsteroides.add(asteroide);
            root.getChildren().add(asteroide.getAsteroide());
        }
        root.getChildren().add(asteroide.getProteccion());
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

                fin.setVisible(true);    
               }else if (gameOver == false){
                //Meto la proteccion
                asteroide.setProtec();
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
                //Compruebo si asteroide choco contra el morro de la nave
                for(int i = 0; i < listaAsteroides.size() ; i++){
                    asteroide = listaAsteroides.get(i);
                    if (getColisionN(asteroide.getAsteroide(),nave.morro)){
                        asteroide.visible(false);
                        listaAsteroides.remove(asteroide);
                        gameOver = true;
                    }      
                }
                
                //Comprobar si la bala choco contra asteroide
                for (int i = 0 ; i < listaBala.size() ; i++) {
                    bala= listaBala.get(i);
                    for (int b= 0 ; b < listaAsteroides.size(); b++){
                        asteroide = listaAsteroides.get(b);
                        if (getColision(asteroide.getAsteroide(),bala.getBala())){
                            balaelimi = bala;
                            astelimi = asteroide; 
                            posiAsteX = astelimi.getTranslateX();
                            posiAsteY = astelimi.getTranslateY();
                            balaelimi.ver(false);
                            ++score;
                            textScore.setText(String.valueOf(score));
                            asteroide.visible(false);
                        }  
                    }
                }
                listaBala.remove(balaelimi);
                listaAsteroides.remove(astelimi); 
                
                if (score == 5 && setOleada == false){
                    for ( int i = 0 ; i < 15 ; i++){
                    asteroide = new Asteroide(1,2);
                    listaAsteroides.add(asteroide);
                    root.getChildren().add(asteroide.getAsteroide());
                    score = 0;
                    setOleada = true;
                    numOleada = 2; 
                    oleadaT.setText(String.valueOf(numOleada));
                    }
                }
                
                //vuelta al plano
                nave.vuelve();
                //Vuelta al plano de los asteroides
                for (int i = 0 ;  i < listaAsteroides.size() ; i++){
                    asteroide = listaAsteroides.get(i);
                    asteroide.vuelve();
                }
                    
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


