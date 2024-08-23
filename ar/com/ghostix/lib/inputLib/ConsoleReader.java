package ar.com.ghostix.lib.inputLib;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader{
    Scanner scan;
    public ConsoleReader(InputStream inputStream){
        scan = new Scanner(inputStream);
    }
    public <T> boolean askConfirmation(T value){
        System.out.println(STR."Estas seguro, profe? Valor introducido: \{value} S/N...");
        String option = scan.next().toLowerCase().substring(0,1);
        return option.equals("s");
    }
    //String.
    public String input(String prompt, boolean askForUserConfirmation){
        String input;
        while(true){
            System.out.print(prompt);
            input = scan.nextLine();
            input = scan.nextLine();
            if(askForUserConfirmation){
                if(askConfirmation(input)){return input;}
            }
        }
    }
    //String hasta salto de linea.
    public String input(String prompt, boolean askForUserConfirmation, boolean lineJump){
        String input;
        while(true){
            System.out.print(prompt);
            if(lineJump){
                input = scan.nextLine();
                input = scan.nextLine();
            }else{
                input = scan.next();
            }
            if(askForUserConfirmation){
                if(askConfirmation(input)){return input;}
            }
        }

    }
    //Integer.
    public int input(String prompt, int input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextInt();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número entero. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Double.
    public double input(String prompt, double input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextDouble();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Float
    public float input(String prompt, float input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextFloat();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Boolean
    public boolean input(String prompt, Boolean input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextBoolean();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un boolean. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Long
    public long input(String prompt, long input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextLong();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Byte
    public byte input(String prompt, byte input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextByte();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un byte. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //Short
    public short input(String prompt, short input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextShort();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //BigDecimal
    public BigDecimal input(String prompt, BigDecimal input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextBigDecimal();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }
    //BigInteger
    public BigInteger input(String prompt, BigInteger input, boolean askForUserConfirmation){
        while(true){
            System.out.print(prompt);
            try{
                input = scan.nextBigInteger();
                if(askForUserConfirmation){
                    if(askConfirmation(input)){return input;}
                }
            }catch(InputMismatchException error){
                System.out.println("El valor ingresado no es un número fraccionario. Ingrese otro...");
                scan.nextLine();
            }
        }
    }

}
