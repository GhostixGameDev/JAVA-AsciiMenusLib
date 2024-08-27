package ar.com.ghostix.lib.asciimenus;
import ar.com.ghostix.lib.inputLib.ConsoleReader;

import java.io.InputStream;
import java.util.Scanner;
import java.lang.reflect.*;

//Automatic ascii menus library for my highschool assignments.
public class MainMenu
{
    private String name;
    private Object[] objects;
    private String pattern;
    private int options;
    //CLASS CONSTRUCTOR
    public MainMenu(String Name, String itemsName, Object[] Objects)
    {
        name = Name;
        options = Objects.length + 1;
        objects = Objects;
        pattern = itemsName;
    }
    public MainMenu(String name, Object[] objects){
        this.name = name;
        this.objects = objects;
        this.options = objects.length + 1;
        this.pattern = "Option ";
    }
    public MainMenu(Object[] objects){
        this.name = "Ascii-Menus";
        this.objects = objects;
        this.options = objects.length + 1;
        this.pattern = "Option ";
    }
    //Get and set Methods
    public String getName(){
        return name;
    }
    public void setName(String newValue){
        name = newValue;
    }
    public String getPattern(){
        return pattern;
    }
    public void setPattern(String newValue){
        pattern = newValue;
    }
    public Object[] getObjects(){
        return objects;
    }
    public void setObjects(Object[] newValues){
        if(newValues.length <= objects.length){
            objects = newValues;
        }else{
            System.out.println("Array out of length");
        }
    }
    public int getOptions(){
        return options;
    }
    public void setOptions(int newValue){
        options = newValue;
    }
    //Main method
    public void run(InputStream inputStream){
        ConsoleReader scan = new ConsoleReader(inputStream);
        int width = 22 + getName().length(); //11 spaces for the right and left sides of the name
        int height = 7;
        int option = 0;
        Class[] cArg = new Class[1];
        cArg[0] = InputStream.class; //We save this for the run method reflection.
        while(option!=getOptions()){
            //Title printing
            StringBuilder line= new StringBuilder();
            line.append("=".repeat(Math.max(0, width)));
            System.out.println(line);
            for(int y = 0; y<height; y++){
                if(y!=3){
                    //White spaces
                    line = new StringBuilder("|");
                    line.append(" ".repeat(Math.max(0, width - 2)));
                    line.append("|");
                }else{
                    //We print the name
                    line = new StringBuilder("|");
                    line.append(" ".repeat(10));
                    line.append(getName());
                    int actualLength = line.length();
                    line.append(" ".repeat(Math.max(0, width - 1 - actualLength)));
                    line.append("|");
                }
                System.out.println(line);
            }
            line = new StringBuilder();
            line.append("=".repeat(Math.max(0, width)));
            System.out.println(line);
            //Title finished, now we print the options.
            for(int i = 1; i<getOptions(); i++){
                System.out.println(i + "- " + getPattern() + i + ".");
            }
            System.out.println(getOptions()+"- Salir.");
            System.out.println("==========================");
            option = scan.input("Selecciona una opción.\n", 0, false);
            //We prompt the user to input and then call the respective method or close the program.
            if(option!=getOptions() && option>0 && option<getOptions()){
                try{
                    Method method = getObjects()[option-1].getClass().getMethod("run", cArg);
                    method.invoke(getObjects()[option-1], inputStream);
                }catch(Exception error){
                    error.printStackTrace();
                    System.out.println("TODOS TUS OBJETOS EN EL MENU DEBEN TENER UN METODO RUN Y QUE SEA PUBLICO.");
                }
            }else{
                if(option!=getOptions()){
                    System.out.println("Opcion invalida.");
                }else{
                    System.out.println("Cerrando el programa.");
                }
            }
        }
    }

}
