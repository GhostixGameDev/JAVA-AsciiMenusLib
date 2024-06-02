package ar.com.ghostix.lib.asciimenus.examples;


import java.util.Scanner;
import java.lang.Math;
import ar.com.ghostix.lib.asciimenus.SubMenu;

public class Point
{
    
    private int[] coords = new int[2];
    
    
    //CLASS CONSTRUCTOR
    public Point(int initX, int initY){
        coords[0] = initX;
        coords[1] = initY;
    }
    
    public int[] getCoords(){
        return coords;
    }
    public void setCoords(int x, int y){
        coords[0] = x;
        coords[1] = y;
    }
    
    public void sumarValor(int value){
        setCoords(getCoords()[0] + value, getCoords()[1] + value);
    }
    public void sumarPunto(Point point){
        setCoords(getCoords()[0] + point.getCoords()[0], getCoords()[1] + point.getCoords()[1]);
    }
    public double distanciaDe(Point point){
        return Math.sqrt((Math.pow(point.getCoords()[0] - getCoords()[0], 2) 
        + Math.pow(point.getCoords()[1] - getCoords()[1], 2))); //Calculamos distancia por Pitágoras.
    }
    public double distanciaAlOrigen(){
        return Math.sqrt(Math.pow(getCoords()[0], 2) + Math.pow(getCoords()[1], 2));
    }
    public void mostrarValores(){
        System.out.println("COORDS: " + getCoords()[0] + ", " + getCoords()[1]);
        System.out.println("DISTANCIA AL ORIGEN: " + distanciaAlOrigen());
    }
    public void run(Scanner scan){
        String[] customOptions = {"sumarPunto", "distanciaDe"};
        String[] hiddenOptions = {""};
        SubMenu menu = new SubMenu("Ejercicio 1", this, true, customOptions, hiddenOptions);
        int option = 0;
        int tempX = 0;
        int tempY = 0;
        while(option!=menu.getExit()){
            option = menu.run(scan);
            switch(option){
                case 4:
                    System.out.println("Ingrese X del nuevo punto");
                    tempX = scan.nextInt();
                    System.out.println("Ingrese Y del nuevo punto");
                    tempY = scan.nextInt();
                    System.out.println("La distancia es: " + distanciaDe(new Point(tempX, tempY)));
                    break;
                case 5:
                    System.out.println("Ingrese X del nuevo punto");
                    tempX = scan.nextInt();
                    System.out.println("Ingrese Y del nuevo punto");
                    tempY = scan.nextInt();
                    sumarPunto(new Point(tempX, tempY));
                    break;
                }
        }
    }
    

    
}
