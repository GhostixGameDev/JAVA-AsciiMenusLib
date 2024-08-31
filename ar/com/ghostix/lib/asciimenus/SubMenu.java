package ar.com.ghostix.lib.asciimenus;
import ar.com.ghostix.lib.arraylib.ArrayUtils;
import ar.com.ghostix.lib.inputLib.ConsoleReader;

import java.io.InputStream;
import java.lang.reflect.*;
import java.util.ArrayList;


public class SubMenu implements ITitlePrinter
{
    private String name;
    private Method[] methods;
    private Object object;
    private int options;
    private int exit;
    private boolean custom;
    private String[] customOptions;
    //CLASS CONSTRUCTOR.
    public SubMenu(Object object){
        this.object = object;
        this.name = "SubMenu";
        this.options = 1;
        this.custom = false;
        this.customOptions = null;
        //We now have to exclude non-relevant methods.
        initializeMethodsList(null);
    }
    public SubMenu(String name, Object object){
        this.object = object;
        this.name = name;
        this.options = 1;
        this.custom = false;
        this.customOptions = null;
        //We now have to exclude non-relevant methods.
        initializeMethodsList(null);
    }
    public SubMenu(String name, Object object, String[] customOptions)
    {
        this.name = name;
        this.object = object;
        this.options = 1;
        this.custom = true;
        this.customOptions = customOptions;
        //We now have to exclude non-relevant methods.
        initializeMethodsList(null);

    }
    public SubMenu(String name, Object object, String[] customOptions, String[] hiddenOptions)
    {
        this.name = name;
        this.object = object;
        this.options = 1;
        this.custom = customOptions != null;
        this.customOptions = customOptions;
        //We now have to exclude non-relevant methods.
        initializeMethodsList(hiddenOptions);

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

    //Private methods
    private void initializeMethodsList(String[] hiddenOptions) {
        Method[] methodsToCheck = this.object.getClass().getMethods();
        ArrayList<Method> validMethods = new ArrayList<>();
        String[] excludedMethods = {"run", "wait", "equals", "toString",
                "hashCode", "getClass", "notify", "notifyAll"}; //Run and Object methods
        //Check for the relevant methods
        for(Method method : methodsToCheck){
            String methodName = method.getName();
            if(methodName.startsWith("get") || methodName.startsWith("set")){
                continue;
            }
            if(ArrayUtils.in(methodName, excludedMethods) ||
                    (hiddenOptions != null && ArrayUtils.in(methodName, hiddenOptions)) ||
                    (this.customOptions != null && ArrayUtils.in(methodName, this.customOptions))){
                continue;
            }
            validMethods.add(method);
            this.options++;
        }
        //Store the valid methods into our array.
        this.methods = validMethods.toArray(new Method[0]);
        //Define the exit option.
        this.exit = isCustom() ? getOptions() + this.customOptions.length : getOptions();
    }

    //Public instance methods

    //Automatic asking for non object parameters.
    public Object[] askParameters(int option, ConsoleReader scan){
        int arguments = getMethods()[option].getParameters().length;
        Object[] parameters = new Object[arguments];
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
            int type = scan.input("Selecciona una opción.\n", 0, false);
            System.out.println("==========================");
            //We confirm the user is sure about the input.
            String isSure = scan.input("Estas seguro?. Si no es así escribe DESHACER\n", false);
            if(!isSure.equals("DESHACER")){
                switch(type){
                    case 1:
                        parameters[i] = scan.input("Ingrese el valor int.\n", 0, true);
                        break;
                    case 2:
                        parameters[i] = scan.input("Ingrese el valor String.\n", true);
                        break;
                    case 3:
                        parameters[i] = scan.input("Ingrese el valor double.\n", 0.0, true);
                        break;
                    case 4:
                        parameters[i] = scan.input("Ingrese el valor boolean.\n", Boolean.FALSE, true);
                        break;
                    case 5:
                        parameters[i] = scan.input("Ingrese el valor float.\n", 0.0f, true);
                        break;
                    case 6:
                        parameters[i] = leerArray(scan);
                        break;
                    case 7:
                        System.out.println("Retrocediendo...");
                        i = i == 0 ? i-- : i-2;
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
    }
    //Automatic asking for arrays.
    public Object leerArray(ConsoleReader scan){
        Object[] parameters;
        //We ask for the size
        System.out.println("==========================");
        int size = scan.input("Ingrese la longitud del Array.\n", 0, true);
        //We confirm the user is sure about the input.
        System.out.println("==========================");
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
        int type = scan.input("\n", 0, true);
        //Now we declare and ask values to fill it
        switch(type){
            case 1:
                parameters = new Integer[size];
                for(int j = 0; j<size; j++){
                    System.out.println();
                    parameters[j] = scan.input("Ingrese valor int número " + (j + 1) + "\n", 0, false);
                }
                break;
            case 2:
                parameters = new String[size];
                for(int j = 0; j<size; j++){
                    parameters[j] = scan.input("Ingrese valor String número " + (j + 1) + "\n", false);
                }
                break;
            case 3:
                parameters = new Double[size];
                for(int j = 0; j<size; j++){
                    System.out.println();
                    parameters[j] = scan.input("Ingrese valor double número " + (j + 1) + "\n", 0.0, false);
                }
                break;
            case 4:
                parameters = new Boolean[size];
                for(int j = 0; j<size; j++){
                    parameters[j] = scan.input("Ingrese valor boolean número " + (j + 1) + "\n", Boolean.FALSE, false);
                }
                break;
            case 5:
                parameters = new Float[size];
                for(int j = 0; j<size; j++){
                    parameters[j] = scan.input("Ingrese valor float número " + (j + 1) + "\n", 0.0f, false);
                }
                break;
            case 6:
                parameters = new Object[size];
                for(int j = 0; j<size; j++){
                    System.out.println("Ingrese Array número " + (j + 1));
                    parameters[j] = leerArray(scan);
                }
                break;
            default:
                System.out.println("Opción invalida. Devolviendo null.");
                parameters = null;
        }
        return parameters;
    }
    //Run method
    public int run(InputStream inputStream){
        ConsoleReader scan = new ConsoleReader(inputStream);
        int option;
        printTitle(getName());
        //We print the options after the title
        for(int i = 1; i<getOptions(); i++){
            System.out.println(i + "- " + (getMethods()[i - 1].getName()) + ".");
        }
        //If it has custom options, prints them at the end
        if(isCustom()){
            int customAmount = getCustomOptions().length;
            for(int i = 0; i<customAmount; i++){
                System.out.println((i + getOptions()) + "- " + getCustomOptions()[i] + ".");
            }
        }
        //Print the exit option
        System.out.println(getExit() + "- Salir.");
        //Prompt the user to input.
        System.out.println("==========================");
        option = scan.input("Selecciona una opción.\n", 0, false);
        if(option>0 && option < getOptions()){
            //Handle possible exceptions at the moment of invoking hypothetical methods.
            try{
                Object[] parameters = askParameters(option - 1, scan);
                getMethods()[option-1].invoke(getObject(), (parameters));
            }catch(Exception error){
                error.printStackTrace();
                System.out.println("Algo salio mal...");
            }
        }else if(option == getExit()){
            System.out.println("Cerrando el programa.");
        }else if (!isCustom()){
            System.out.println("Opción inválida.");
        }
        //We return the option.
        return option;
    }

}
