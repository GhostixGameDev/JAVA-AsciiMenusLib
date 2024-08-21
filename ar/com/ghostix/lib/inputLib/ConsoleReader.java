package ar.com.ghostix.lib.inputLib;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader{
    Scanner scan;
    public ConsoleReader(InputStream inputStream){
        scan = new Scanner(inputStream);
    }
    //String.
    public String input(String prompt){
        String input;
        while(true){
            System.out.print(prompt);
            input = scan.nextLine();
            input = scan.nextLine();
            System.out.println(STR."Estas seguro, profe? Valor introducido: \{input} S/N...");
            String option = scan.next().toLowerCase().substring(0,1);
            if(option.equals("s")){
                return input;
            }
        }
    }
    //String hasta salto de linea.
    public String input(String prompt, boolean lineJump){
        String input;
        while(true){
            System.out.print(prompt);
            if(lineJump){
                input = scan.nextLine();
                input = scan.nextLine();
            }else{
                input = scan.next();
            }
            System.out.println(STR."Estas seguro, profe? Valor introducido: \{input} S/N...");
            String option = scan.next().toLowerCase().substring(0,1);
            if(option.equals("s")){
                return input;
            }
        }

    }
    //Integer.
    public int input(String prompt, int input){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextInt();
                System.out.println(STR."Estas seguro, profe? Valor introducido: \{input} S/N...");
                String option = scan.next().toLowerCase().substring(0,1);
                if(option.equals("s")){
                    return input;
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número entero. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Double.
    public double input(String prompt, double input){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextDouble();
                System.out.println(STR."Estas seguro, profe? Valor introducido: \{input} S/N...");
                String option = scan.next().toLowerCase().substring(0,1);
                if(option.equals("s")){
                    return input;
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
}
