/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danielgomezgil.nave;

import javafx.scene.shape.Circle;

/**
 *
 * @author 1DAW03
 */
public class Bala {
    
    private final Circle formBala = new Circle();
    double posiBalaX;
    double posiBalaY;
    public Bala(){
        formBala.setRadius(5);
        
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
