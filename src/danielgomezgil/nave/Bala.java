/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danielgomezgil.nave;

import java.util.ArrayList;
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
    ArrayList<Circle> listaBala = new ArrayList();
    private Circle formBala = new Circle();
    
    public Bala(){
        formBala.setRadius(5);
        formBala.setFill(Color.LIGHTSKYBLUE);
    }
    
    public void newBala(){
        formBala = new Circle();
        formBala.setVisible(true);
        numBala += 1 ;
        listaBala.add(formBala);
        formBala.setCenterX(Main.nave.getXFuego() + 30);
        formBala.setCenterY(Main.nave.getYFuego() + 26);
        Main.root.getChildren().add(formBala);
        posiBalaX = veloBala * Math.cos(Main.nave.getANMV());
        posiBalaY = veloBala * Math.sin(Main.nave.getANMV());
        veloBala += (3 *2);
        System.out.println("joe picha funciona");
        if (veloBala > 15){
            veloBala = 15;
        }
    }
    public void apuntoBala(){
        if ( formBala != null ){
            for (int i = 0 ; i < listaBala.size(); i++) {
                Circle lis = listaBala.get(i);
                lis.setCenterX(lis.getCenterX() + posiBalaX);
                lis.setCenterY(lis.getCenterY() + posiBalaY); 
            }
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
