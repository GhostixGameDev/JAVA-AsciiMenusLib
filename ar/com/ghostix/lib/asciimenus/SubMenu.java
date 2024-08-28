package ar.com.ghostix.lib.asciimenus;
import ar.com.ghostix.lib.arraylib.ArrayUtils;
import ar.com.ghostix.lib.inputLib.ConsoleReader;

import java.io.InputStream;
import java.lang.reflect.*;


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
        initializeMethodsList(new String[]{""});
    }
    public SubMenu(String Name, Object Object, boolean Custom, String[] CustomOptions, String[] hiddenOptions)
    {
        name = Name;
        object = Object;
        options = 1;
        custom = Custom;
        //We add the custom options to an Array for later use in the menu.
        if(Custom){
            if(CustomOptions[0] != null && !CustomOptions[0].isEmpty()){
                this.customOptions = CustomOptions;
            }
            else{
                custom = false;
            }
        }
        //We now have to exclude non-relevant methods.
        initializeMethodsList(null);

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
        Method[] methodsToCheck = object.getClass().getMethods();
        Method[] validMethods = new Method[methodsToCheck.length];
        String[] excludedMethods = {"run", "wait", "equals", "toString",
                "hashCode", "getClass", "notify", "notifyAll"}; //Run and Object methods
        int originalLength = methodsToCheck.length;
        int bypassedCount = 0;
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
                }else if(hiddenOptions !=null){
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
        this.methods = new Method[this.options];
        for(int i=0; i<originalLength; i++){
            if(methodsToCheck[i] == validMethods[i]){
                this.methods[i-bypassedCount] = validMethods[i];
            }else{
                bypassedCount++;
            }
        }
        //WE DEFINE THE EXIT OPTION
        if(isCustom()){
            this.exit = getOptions() + getCustomOptions().length;
        }else{
            this.exit = getOptions();
        }
    }

    //Public instance methods

    //Automatic asking for non object parameters.
    public Object[] askParameters(int option, ConsoleReader scan){
        int arguments = getMethods()[option-1].getParameters().length;
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
                        parameters[i] = scan.input("Ingrese el valor String.\n", 0, true);
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
                    parameters[j] = scan.input("Ingrese valor int número " + (j + 1) + "\n", false);
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
        //We print the exit option
        System.out.println(getExit() + "- Salir.");
        //Prompt the user to input.
        System.out.println("==========================");
        option = scan.input("Selecciona una opción.\n", 0, false);
        if(option!=getOptions() && option>0 && option<getOptions()){
            //We handle possible exceptions at the moment of invoking hypothetical methods.
            try{
                Object[] parameters = askParameters(option, scan);
                getMethods()[option-1].invoke(getObject(), (parameters));
            }catch(Exception error){
                error.printStackTrace();
                System.out.println("Algo salio mal...");
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
                System.out.println("Opción invalida.");
            }else if(option==getExit()){
                System.out.println("Cerrando el programa.");
            }
        }
        //We return the option.
        return option;
    }

}
