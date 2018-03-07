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
    private double veloBala = 0.0; //Velocidad Bala 
    private double posiBalaX;
    private double posiBalaY;
    private final Circle formBala = new Circle();
    
    public Bala(){
        formBala.setRadius(5);
        formBala.setFill(Color.LIGHTSKYBLUE);
    }
    
    public void disparo(){
        veloBala = 15;
        formBala.setCenterX(Main.nave.getXFuego() + 30);
        formBala.setCenterY(Main.nave.getYFuego() + 26);
        posiBalaX = veloBala * Math.cos(Main.nave.getANMV());
        posiBalaY = veloBala * Math.sin(Main.nave.getANMV());
        Main.root.getChildren().add(formBala);
    }
   
    public void mover(){
        formBala.setCenterX(formBala.getCenterX() + posiBalaX);
        formBala.setCenterY(formBala.getCenterY() + posiBalaY);
    }
    public boolean ver(boolean v){
        formBala.setVisible(v);
        return v;
    }
    public Circle getBala(){
        return formBala;
    }
  }

