
package danielgomezgil.nave;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Asteroide {
    //Variables de clase
    Polygon asteroide;
    double veloAX = 3;
    double veloAY = 3; 
    public double posiAX = 0 ; //Math.random() * (255 - 0) + 0;
    public double posiAY = 0 ;
    Random aleato = new Random();
    //*****************
    
    
    public Asteroide( double tam ){
    asteroide = new Polygon();
    asteroide.setFill(Color.RED);
        asteroide.getPoints().addAll(new Double[]{
            0.0, 0.0,
            0.0, 20.0 * tam,
            50.0 *tam , 20.0 *tam,
            50.0 *tam , 20.0 * tam,
         });
        asteroide.setTranslateX(aleato.nextInt(800));
        asteroide.setTranslateY(aleato.nextInt(600));
    }
    
    public void mover(){    
//        asteroide.setLayoutX(asteroide.getLayoutX() + veloAX);
//        asteroide.setLayoutY(asteroide.getLayoutY() + veloAY);
//        asteroide.setRotate(asteroide.getRotate() + 25);
    }
    
    public Polygon getAsteroide(){
        return asteroide;
    }
    public Boolean visible(boolean valor){
        asteroide.setVisible(valor);
        return null;
    }
    public double getTranslateX(){
        asteroide.getTranslateX();
        return asteroide.getTranslateX();
    }
    public double getTranslateY(){
        asteroide.getTranslateY();
        return asteroide.getTranslateY();
    }
    public double setTX(double posX){
        asteroide.setTranslateX(posX);
        return 0;
    }
    public double setTY(double posY){
        asteroide.setTranslateY(posY);
        return 0;
    }
    
    public double transX( double transX){
        asteroide.setTranslateX(transX);
        return 0;
    }
    public double tansY ( double transY){
        asteroide.setTranslateY(transY);
        return 0;
    }
    public void autoRotacion( double rot ){
        asteroide.setRotate(rot + asteroide.getRotate());
    }

}


