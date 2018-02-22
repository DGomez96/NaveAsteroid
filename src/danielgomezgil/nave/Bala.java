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
    Nave nave;
    Bala bala;
    int numBala;
    ArrayList<Bala> listaBala = new ArrayList();
    private final Circle formBala = new Circle();
    public Bala(){
        formBala.setRadius(5);
        formBala.setFill(Color.LIGHTSKYBLUE);
    }
    public void newBala(){
        bala = new Bala();
                numBala += 1 ;
                listaBala.add(bala);
                bala.visibilidad(true);
                bala.posiX(nave.getXFuego() + 30);
                bala.posiY(nave.getYFuego() + 26);
                posiBalaX = veloBala * Math.cos(nave.getANMV());
                posiBalaY = veloBala * Math.sin(nave.getANMV());
                veloBala += (3 *2) ;
                if (veloBala > 15){
                    veloBala = 15;
                }
    }
    public void apuntoBala(){
        if ( bala != null ){
            for (int i = 0 ; i < listaBala.size(); i++) {
                Bala lis = listaBala.get(i);
                lis.posiX(bala.getX() + posiBalaX);
                lis.posiY(bala.getY() + posiBalaY); 
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
