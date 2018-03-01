
package danielgomezgil.nave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Asteroide {
    //Variables de clase
    Polygon asteroide;
    double veloA = 6;
    double posiAX;
    double posiAY;
    //*****************
    
    public Asteroide(){
    asteroide = new Polygon();
    asteroide.setFill(Color.RED);
        asteroide.getPoints().addAll(new Double[]{
            0.0, 0.0,
            0.0, 20.0,
            50.0, 20.0,
            50.0, 20.0,
         });
    
    }
    
    public void mover(){
        asteroide.setTranslateX(Main.nave.getXFuego() + 30);
        asteroide.setTranslateY(Main.nave.getYFuego() + 26);
        posiAX = 255 ; //Math.random() * (255 - 0) + 0;
        posiAY = 255 ; //Math.random() * (400 - 0) + 0;
        asteroide.setLayoutX(asteroide.getLayoutX() + posiAX);
        asteroide.setLayoutY(asteroide.getLayoutY() + posiAY);

    }
    
    
    public Polygon getAsteroide(){
        return asteroide;
    }
    public Boolean visible(boolean valor){
        asteroide.setVisible(valor);
        return null;
    }
    public double getLayoutX(){
        asteroide.getLayoutX();
        return 0;
    }
    public double setLX(double posX){
        asteroide.setLayoutX(posX);
        return 0;
    }
    public double setLY(double posY){
        asteroide.setLayoutY(posY);
        return 0;
    }
    
    public double transX( double transX){
        asteroide.setTranslateX(transX);
        return 0;
    }
    
    public void autoRotacion( double rot ){
        asteroide.setRotate(rot + asteroide.getRotate());
    }
    
}


