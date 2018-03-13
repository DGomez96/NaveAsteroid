
package danielgomezgil.nave;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Asteroide {
    //Variables de clase
    Polygon asteroide;
    double veloAX = 0.5;
    double veloAY = 0.5; 
    public double posiAX = 0 ; //Math.random() * (255 - 0) + 0;
    public double posiAY = 0 ;
    Random aleato = new Random();
    //*****************
    
    
    public Asteroide( double tam ){
    asteroide = new Polygon();
    asteroide.setFill(Color.AQUA);
        asteroide.getPoints().addAll(new Double[]{
            100.0 *tam, 25.0 *tam, 
            200.0 *tam, 25.0*tam, 
            250.0 *tam, 75.0*tam,          
            200.0 *tam, 125.0*tam, 
            100.0 *tam, 125.0*tam,                   
            50.0 *tam, 50.0*tam, 
         });
        asteroide.setLayoutX(aleato.nextInt(800));
        asteroide.setLayoutY(aleato.nextInt(200));
    }
    
    public void mover(){
       aleato = new Random();
       asteroide.setLayoutX(asteroide.getLayoutX() + veloAX);
       asteroide.setLayoutY(asteroide.getLayoutY() + veloAY);
       asteroide.setRotate(asteroide.getRotate() + aleato.nextInt(16));
    }
    
    public void vuelve(){
    if (asteroide.getLayoutX() >= 800 ){
        asteroide.setLayoutX(-1);
        System.out.println("caso 1");
    }else if(asteroide.getLayoutX() <= -2 ){
        asteroide.setLayoutX(800);
        System.out.println("caso 2");
    }else if ( asteroide.getLayoutY() <= 0){
        asteroide.setLayoutY(600);
        System.out.println("caso 3");
    }else if (asteroide.getLayoutY() >= 600){
        asteroide.setLayoutY(0);
        System.out.println("caso 4");
    }
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


