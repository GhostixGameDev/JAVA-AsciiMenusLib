package ar.com.ghostix.lib.asciimenus.examples;


import java.io.InputStream;
import java.util.Scanner;
import java.lang.Math;
import ar.com.ghostix.lib.asciimenus.SubMenu;

public class MagicMatrix
{
    private Integer[][] matrix;
    //Class constructor
    public Integer[][] getMatrix(){
        return matrix;
    }
    public void setMatrix(Integer[][] newValues){
        matrix = newValues;
    }
    public void createBase(int size){
        setMatrix(new Integer[size][size]); 
    }
    //We charge the matrix with values.
    public void chargeMatrix(int index, Integer[] array){
        int baseLength = getMatrix().length;        
        if(index>=0 && index < baseLength){
            if(baseLength == array.length){
                getMatrix()[index] = array;
            }else{
                System.out.println("Your arrays must have a size of" + baseLength); 
            }
        }else{
            System.out.println("Index out of boundaries.");
        }
        
    }
    //We check if the sum of rows, colums and diagonals are equals.
    public boolean isMagic(){
        int length = getMatrix().length;
        int[] sum = new int[length *2 + 2];
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if(getMatrix()[j][i]==null){
                    System.out.println("Matrix is not filled.");
                    return false;
                }
            }
        }
        //Rows
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                sum[i] = sum[i]+getMatrix()[j][i];
            }
        }
        //Columns
        for(int i=length; i<length*2; i++){
            for(int j=0; j<length; j++){
                sum[i] = sum[i]+getMatrix()[i-length][j];
            }
        }
        //First diagonal
        for(int i=0; i<length; i++){
            sum[length*2] = sum[length*2]+getMatrix()[i][i];
        }
        //Second diagonal
        for(int i=length-1; i>-1; i--){
            sum[length*2+1] = sum[length*2+1] + getMatrix()[i][i];
        }
        for(int i=1; i<length*2+2; i++){
            if(sum[i]!=sum[i-1]){
                return false;
            }
        }
        return true;
    }
    public void run(InputStream inputStream){
        //We give SubMenu a name, the object, if it has custom options, an array of custom options and an array of hidden options.
        SubMenu menu = new SubMenu("Magic Matrix", this);
        int option = 0;
        //We set up the submenu loop.
        //While the option isnt the exit option, we call run and
        //save it in an integer.
        while(option!=menu.getExit()){
            option = menu.run(inputStream);
        }
    }
    
    
    

    
}
