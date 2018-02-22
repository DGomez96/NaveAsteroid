package danielgomezgil.nave;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Nave {
    Image image1 = new Image("navenofu.png"); //Creacion imagen Nave sin fuego
    ImageView navenofu = new ImageView(image1);
    Image image2 = new Image("navefu.png"); //Creacion imagen con Fuego
    ImageView navefu = new ImageView(image2);
    Image image3 = new Image("navez.png"); //Creacion imagen con Fuego
    ImageView navez = new ImageView(image3); 

    //Creo Grupo de la GeoNave 
    Group geonave = new Group();
    Rectangle morro = new Rectangle();
    Rectangle turbider = new Rectangle();
    Rectangle turbiizq = new Rectangle();

    //Variables de la Nave
    public double posinaveX = 400;
    public double posinaveY = 300;
    public int naveSP =0; // Velocidad Nave
    double naveAnSP; //Velocidad Angulo
    double anguloMv = navefu.getRotate();
    double velX;
    double velY;
    
    public Nave(){
            //Haciendo GeoNave
        morro.setWidth(50);
        morro.setHeight(20);
        morro.setX(410);
        morro.setY(312);
        
        turbider.setWidth(20);
        turbider.setHeight(10);
        turbider.setX(417);
        turbider.setY(335);
        
        turbiizq.setWidth(20);
        turbiizq.setHeight(10);
        turbiizq.setX(417);
        turbiizq.setY(300);
              
     
        geonave.getChildren().add(morro);
        geonave.getChildren().add(turbider);
        geonave.getChildren().add(turbiizq); 
        geonave.setVisible(false);
    }
    

    public void vuelve(){
                if (navefu.getX() >= 800 ){
                    navefu.setX(-1);
                    navenofu.setX(-1);
                    navez.setX(-1);
                }else if(navefu.getX() <= -2 ){
                    navefu.setX(800);
                    navenofu.setX(800);
                    navez.setX(800);
                }else if ( navefu.getY() <= 0){
                    navefu.setY(600);
                    navenofu.setY(600);
                    navez.setY(600);
                }else if (navefu.getY() >= 600){
                    navefu.setY(0);
                    navenofu.setY(0);
                    navez.setY(0);
                }
    }
    public void autoVisi(){
            if (naveSP <= 0.0 && naveSP >= -1.00 ){
                    
                    navenofu.setX(navefu.getX());
                    navefu.setY(navefu.getY());
                    navenofu.setVisible(true);
                    
                    navez.setX(navefu.getX());
                    navez.setY(navefu.getY());
                    navez.setVisible(false);
                    navefu.setVisible(false);
                } else if (naveSP >= 0.0) { 
                    navenofu.setVisible(false);
                    navefu.setVisible(true);
                }
    }
    public boolean freno( boolean fre){
            if (fre == true){
                 if (naveSP <= 0.00 ){
                       naveSP = naveSP + 1;
                       navenofu.setVisible(true);
                       navefu.setVisible(false);
                       navez.setVisible(false);
                       navenofu.setLayoutX(navefu.getLayoutX());
                       navenofu.setX(navefu.getX());
                       navenofu.setLayoutY(navefu.getLayoutY());
                       navenofu.setY(navefu.getY());
                    System.out.println("Frenos Activados");
                   }else if(naveSP == 0.00 ){
                        naveSP = 0;
                }else if (naveSP > 0.00){
                        naveSP = naveSP - 1;
                        navefu.setVisible(false);
                        navenofu.setVisible(false);
                        navez.setVisible(true);
                        navenofu.setLayoutX(navefu.getLayoutX());
                        navenofu.setX(navefu.getX());
                        navenofu.setLayoutY(navefu.getLayoutY());
                        navenofu.setY(navefu.getY());
                }
            }      
        return false;
    }
    
    public void movAN(){
        posinaveX = navefu.getX();
        posinaveY = navefu.getY();
        velX = naveSP * Math.cos(anguloMv);
        velY = naveSP * Math.sin(anguloMv);            
        anguloMv = Math.toRadians(navefu.getRotate());
        
        navefu.setX(posinaveX + velX);
        navefu.setY(posinaveY + velY);
        
    }
    
    public void rota360(){
        if (navefu.getRotate() >= 360  ){
                    navefu.setRotate(navefu.getRotate() -360); 
                    geonave.setRotate(navefu.getRotate() -360);
             
                }else if  (navefu.getRotate() <= -360 ){
                   navefu.setRotate(navefu.getRotate()+360);  
                   geonave.setRotate(navefu.getRotate()+360);
                   
                }
    }
    
    public void rotaAUTO(){
        geonave.setRotate(navefu.getRotate() + (naveAnSP));
        navenofu.setRotate(navenofu.getRotate() + (naveAnSP));
        navefu.setRotate(navefu.getRotate() + (naveAnSP));
        navez.setRotate(navefu.getRotate() + (naveAnSP));
    }
    public void decreAn(){
        if (naveAnSP >= 0.1){
                          naveAnSP -=  0.2 ;  
                } else if (naveAnSP <= -0.1){
                          naveAnSP += 0.2;
                }    
    }
    
    public void posiFreno(){
         navez.setX(posinaveX + velX);
         navez.setY(posinaveY + velY);
    }
    
    public double getANMV(){
        return anguloMv;
    }
    public int MaxVelo(int velo){
        if (naveSP >= velo){
                       naveSP = velo;
      }
        return naveSP;
    }
    public Group GetGeonave(){
        return geonave;
    }
    public double GiroR( double añadido){
      naveAnSP = naveAnSP + añadido;
        return naveAnSP;
    }
    public double GiroI( double añadido){
      naveAnSP = naveAnSP - añadido;
        return naveAnSP;
    }
    
    public Rectangle morro(){
        return morro;
    }
    
    public ImageView naveFuego(){
        return navefu;
    }
    
    public ImageView naveNoFuego(){
        return navenofu;
    }
    
    public ImageView naveFreno(){
        return navez;
    }

    public void setRotacionFuego( double valor){
      navefu.setRotate(valor);
    }
    
    public void setRotacionNoFuego( double valor){
      navenofu.setRotate(valor);
    }
    
    public void setRotacionFreno( double valor){
      navez.setRotate(valor);
    }
    
    public void setRotacionGeo (double valor){
      geonave.setRotate(valor);
    }
    
    public double getRotacionFuego(){
        navefu.getRotate();
        return navefu.getRotate();
    }
    
    public double getRotacionNoFuego(){
        navenofu.getRotate();
        return navenofu.getRotate();
    }
    
    public double getRotacionDeceleracion(){
        navez.getRotate();
        return navez.getRotate();
    }
    
    public boolean setVisibilidadFuego(boolean valor){
        navefu.setVisible(valor);
        return false;
    }
    public boolean setVisibilidadNoFuego(boolean valor){
        navenofu.setVisible(valor);
        return false;
    }
    public boolean setVisibilidadFreno(boolean valor){
        navez.setVisible(valor);
        return false;
    }
    
    public void setXFuego(double X){
        navefu.setX(X);
    }
    
    public void setXNoFuego(double X){
        navenofu.setX(X);
    }
    
    public void setXFreno(double X){
        navez.setX(X);
    }
    
    public void setXLayout(double X){
        geonave.setLayoutX(X);
    }
    
    public void setYFuego(double Y){
        navefu.setY(Y);
    }
    
    public void setYNoFuego(double Y){
        navenofu.setY(Y);
    }
    
    public void setYFreno(double Y){
        navez.setY(Y);
    }
    
    public void setYLayout(double Y){
        geonave.setLayoutY(Y);
    }
    
    public double getXFuego(){
      return navefu.getX();
    }
    
    public double getXNoFuego(){
      return navenofu.getX();
    }
    
    public double getXFreno(){
      return navez.getX();
    }
    
    public double getYFuego(){
      return navefu.getY();
    }
    
    public double getYNoFuego(){
      return navenofu.getY();
    }
    
    public double getYFreno(){
      return navez.getY();
    }
    
}
