package ar.com.ghostix.lib.asciimenus;
import ar.com.ghostix.lib.arraylib.ArrayUtils;
import java.util.Scanner;
import java.lang.reflect.*;
import java.util.Comparator;


public class SubMenu
{
    private String name;
    private Method[] methods;
    private Object object;
    private int options;
    private int exit;
    private boolean custom;
    private String[] customOptions;
    //CLASS CONSTRUCTOR.
    public SubMenu(String Name, Object Object, boolean Custom, String[] CustomOptions, String[] hiddenOptions)
    {
        name = Name;
        object = Object;
        options = 1;
        int customCount = 0;
        custom = Custom;
        //We add the custom options to an Array for later use in the menu.
        if(Custom){
            if(CustomOptions[0] != null && CustomOptions[0] != ""){
                customOptions = new String[CustomOptions.length];
                customOptions = CustomOptions;
                customCount = customOptions.length;
            }
            else{
                custom = false;
            }
        }
        //We now have to exclude non-relevant methods.
        
        Method[] methodsToCheck = object.getClass().getMethods();
        Method[] validMethods = new Method[methodsToCheck.length];
        String[] excludedMethods = {"run", "wait", "equals", "toString",
            "hashCode", "getClass", "notify", "notifyAll"}; //Run and Object methods
        int originalLength = methodsToCheck.length;
        int excludedCount = excludedMethods.length;
        int bypassedCount = 0;
        int hiddenCount = hiddenOptions.length;
        boolean allowed;
        //We sort the arrays and then
        //CHECK FOR THE RELEVANT METHODS.
        for(int i=0; i<originalLength; i++){
            allowed = true;
            if(!methodsToCheck[i].getName().startsWith("get") && !methodsToCheck[i].getName().startsWith("set")){
                if(ArrayUtils.in(methodsToCheck[i].getName(), excludedMethods)){
                        allowed = false;
                }else if(customOptions!=null){
                    if(ArrayUtils.in(methodsToCheck[i].getName(), customOptions)){
                            allowed = false;
                        }
                }else if(hiddenOptions!=null){
                    if(ArrayUtils.in(methodsToCheck[i].getName(), hiddenOptions)){
                        allowed = false;
                    }
                }
                if(allowed){
                    options++;
                    validMethods[i] = methodsToCheck[i];
                }else{
                    validMethods[i] = null;
                }
            }
        }
        //MAKES THE METHODS LIST
        methods = new Method[options];
        for(int i=0; i<originalLength; i++){
            if(methodsToCheck[i] == validMethods[i]){
                methods[i-bypassedCount] = validMethods[i];
            }else{
                bypassedCount++;
            }
        }
        //WE DEFINE THE EXIT OPTION
        if(isCustom()){
            exit = getOptions() + getCustomOptions().length;
        }else{
            exit = getOptions();
        }
        
        }
    
    //GET AND SET METHODS.
    public String getName(){
        return name;
    }
    public void setName(String newValue){
        name = newValue;
    }
    public Method[] getMethods(){
        return methods;
    }
    public void setMethods(Method[] newValues){
        if(newValues.length <= methods.length){
            methods = newValues;
        }else{
            System.out.println("Array out of length");
        }
    }
    public Object getObject(){
        return object;
    }
    public void setObject(Object newValue){
        object = newValue;
    }

    public int getOptions(){
        return options;
    }
    public void setOptions(int newValue){
        options = newValue;
    }
    public boolean isCustom(){
        return custom;
    }
    public void setCustom(boolean newValue){
        custom = newValue;
    }
    public String[] getCustomOptions(){
        return customOptions;
    }
    public void setCustomOptions(String[] newValues){
        customOptions = newValues;
    }
    public int getExit(){
        return exit;
    }
    public void setExit(int newValue){
        exit = newValue;
    }
    
    
    //METHODS
    
    //Automatic asking for non object parameters.
    public Object[] askParameters(int option, Scanner scan){
        int arguments = getMethods()[option-1].getParameters().length;
        Object[] parameters = new Object[arguments];
        String isSure;
        try{
            //We declare an array of parameters, ask the type, and then prompt the user to input.
            for(int i=0; i<arguments; i++){
                System.out.println("==========================");
                System.out.println("ARGUMENTO NÚMERO: " + (i+1));
                System.out.println("==========================");
                System.out.println("TIPO DE ARGUMENTO.");
                System.out.println("1- Int. ");
                System.out.println("2- String. ");
                System.out.println("3- Double. ");
                System.out.println("4- Boolean. ");
                System.out.println("5- Float. ");
                System.out.println("6- Array. ");
                System.out.println("7- Retroceder. ");
                System.out.println("==========================");
                System.out.println("Selecciona una opción.");
                int type = scan.nextInt();
                System.out.println("==========================");
                //We confirm the user is sure about the input.
                System.out.println("Estas seguro?. Si no es así escribe DESHACER");
                isSure = scan.nextLine();
                if(isSure==""){
                    isSure = scan.nextLine();
                }
                
                if(!isSure.equals("DESHACER")){
                    switch(type){
                        case 1:
                            System.out.println("Ingrese el valor int.");
                            parameters[i] = new Integer(scan.nextInt());
                            break;
                        case 2:
                            System.out.println("Ingrese el valor String.");
                            parameters[i] = new String(scan.nextLine());
                            break;
                        case 3:
                            System.out.println("Ingrese el valor double.");
                            parameters[i] = new Double(scan.nextDouble());
                            break;
                        case 4:
                            System.out.println("Ingrese el valor boolean.");
                            parameters[i] = new Boolean(scan.nextBoolean());
                            break;
                        case 5:
                            System.out.println("Ingrese el valor float.");
                            parameters[i] = new Float(scan.nextFloat());
                            break;
                        case 6:
                            parameters[i] = leerArray(scan);
                            break;
                        case 7:
                            System.out.println("Retrocediendo.");
                            if(i==0){
                                i--;
                            }else{
                                i = i-2;
                            }
                            break;
                        default:
                            System.out.println("Opción invalida. Vuelve a ingresarla.");
                            i--;
                        }
                    }else{
                        System.out.println("Retrocediendo.");
                        i--;
                            
                    }
                }
            return parameters;
        }catch(Exception error){
            //We handle any possible exceptions caused by bad inputs.
            System.out.println("OCURRIO UN ERROR. VUELVA A INGRESAR.");
            error.printStackTrace();
            return askParameters(option, scan);
        }
    }
    //Automatic asking for arrays.
    public Object leerArray(Scanner scan){
        Object[] parameters; 
        String isSure;
        try{
            //We ask for the size
            System.out.println("==========================");
            System.out.println("Ingrese la longitud del Array.");
            int size = scan.nextInt();
            //We confirm the user is sure about the input.
            System.out.println("==========================");
            System.out.println("Estas seguro?. Si no es así escribe DESHACER");
            isSure = scan.nextLine();
            if(isSure==""){
                isSure = scan.nextLine();
            }
            
            if(!isSure.equals("DESHACER")){
                //Now we ask for the type
                System.out.println("==========================");
                System.out.println("TIPO DE ARGUMENTO.");
                System.out.println("1- Int. ");
                System.out.println("2- String. ");
                System.out.println("3- Double. ");
                System.out.println("4- Boolean. ");
                System.out.println("5- Float. ");
                System.out.println("6- Array. ");
                System.out.println("==========================");
                int type = scan.nextInt();
                //We confirm AGAIN if the user is sure about the input.
                System.out.println("Estas seguro?. Si no es así escribe DESHACER");
                isSure = scan.nextLine();
                if(isSure==""){
                    isSure = scan.nextLine();
                }
                
                if(!isSure.equals("DESHACER")){
                    //Now we declare and ask values to fill it
                    switch(type){
                        case 1:
                            parameters = new Integer[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese valor int numero " + (j + 1));
                                parameters[j] = scan.nextInt();
                            }
                            break;
                        case 2:
                            parameters = new String[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese valor String numero " + (j + 1));
                                parameters[j] = scan.nextLine();
                            }
                            break;
                        case 3:
                            parameters = new Double[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese valor double numero " + (j + 1));
                                parameters[j] = scan.nextDouble();
                            }
                            break;
                        case 4:
                            parameters = new Boolean[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese valor boolean numero " + (j + 1));
                                parameters[j] = scan.nextBoolean();
                            }
                            break;
                        case 5:
                            parameters = new Float[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese valor float numero " + (j + 1));
                                parameters[j] = scan.nextDouble();
                            }
                            break;
                        case 6:
                            parameters = new Object[size];
                            for(int j = 0; j<size; j++){
                                System.out.println("Ingrese Array numero " + (j + 1));
                                parameters[j] = leerArray(scan);
                            }
                            break;
                        default:
                            System.out.println("Opción invalida. Devolviendo null.");
                            parameters = null;
                        }
                    }else{
                        return leerArray(scan);
                    }
                }
            else{
                return leerArray(scan);
            }
            return parameters;
        }catch(Exception error){
            //We handle any possible exceptions caused by bad input.
            System.out.println("OCURRIO UN ERROR. Ingrese otra vez.");
            error.printStackTrace();
            return leerArray(scan);
        }

    }
    //Run method
    public int run(Scanner scan){
        
        int width = 22 + getName().length(); //11 spaces for the right and left sides of the name
        int height = 7;
        int option = 0;
        
    
        //Title printing in ASCII
        String line="";
        for(int i = 0; i<width; i++){
            line = line + "=";
        }
        System.out.println(line);
        for(int y = 0; y<height; y++){
            if(y!=3){
                //Forbid spaces
                line = "|";
                for(int x = 1; x<width-1; x++){
                    line = line + " ";
                }
                line = line + "|";
                System.out.println(line);
            }else{
                //Printing the name
                line = "|";
                for(int x = 0; x<10; x++){
                    line = line + " ";
                }
                line = line + getName();
                int actualLength = line.length();
                for(int x = actualLength; x<width - 1; x++){
                    line = line + " ";
                }
                line = line + "|";
                System.out.println(line);
            }
        }
        line = "";
        for(int i = 0; i<width; i++){
            line = line + "=";
        }
        System.out.println(line);
        //Title finished, printing options.
        for(int i = 1; i<getOptions(); i++){
            System.out.println(i + "- " +String.valueOf(getMethods()[i-1].getName())+".");
        }
        //If it has custom options, prints them at the end
        if(isCustom()){
            int customAmount = getCustomOptions().length;
            for(int i = 0; i<customAmount; i++){
                System.out.println((i + getOptions()) + "- " + getCustomOptions()[i] + ".");
            }
            
        }
        //We print the exit option
        System.out.println(exit + "- Salir.");
        //Prompt the user to input.
        System.out.println("==========================");
        System.out.println("Selecciona una opción.");
        option = scan.nextInt();
        if(option!=getOptions() && option>0 && option<getOptions()){
           //We handle possible exceptions at the moment of invoking hypothetical methods.
            try{
                Object[] parameters = askParameters(option, scan);
                getMethods()[option-1].invoke(getObject(), (parameters));
            }catch(Exception error){
                error.printStackTrace();
                
            }
        //Now we check if the option is out of range.
        }else if(isCustom()){
            if(option<1 && option>getExit()+1){
                System.out.println("Opcion invalida.");
            }else if(option==exit){
                System.out.println("Cerrando el programa.");
            }
        }else{
            if(option>getExit() && option<0){
                System.out.println("Opcion invalida.");
            }else if(option==getExit()){
                System.out.println("Cerrando el programa.");
            }
        }
        //We return the option.
        return option;
    }

}
