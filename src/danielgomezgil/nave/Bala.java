/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danielgomezgil.nave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author 1DAW03
 */
public class Bala {
    double veloBala = 0.0; //Velocidad Bala 
    double veloBX; //Velocidad Bala X inicial
    double veloBY; //Velocidad Bala Y inicial
    double posiBalaX;
    double posiBalaY;
    int numBala;
    private final Circle formBala = new Circle();
    
    public Bala(){
        formBala.setRadius(5);
        formBala.setFill(Color.LIGHTSKYBLUE);
    }
    
    public void disparo(){
        numBala += 1 ;
        formBala.setCenterX(Main.nave.getXFuego() + 30);
        formBala.setCenterY(Main.nave.getYFuego() + 26);
        Main.root.getChildren().add(formBala);
        posiBalaX = veloBala * Math.cos(Main.nave.getANMV());
        posiBalaY = veloBala * Math.sin(Main.nave.getANMV());
        veloBala += 6;
        if (veloBala > 15){
            veloBala = 15;
        }
    }

    public void posiX( double posi){
        formBala.setCenterX(posi);
   
    }
    public void posiY (double posi){
        formBala.setCenterY(posi);
    }
    
    public boolean visibilidad( boolean result ){
      formBala.setVisible(result);
        return false;
    }
    
    public Circle getBala(){
        return formBala;
    }
    
    public double getX(){
        formBala.getCenterX();
        return formBala.getCenterX();
    }
    public double getY(){
        formBala.getCenterY();
        return formBala.getCenterY();
    }

}
