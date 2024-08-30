package ar.com.ghostix.lib.asciimenus.examples;

import ar.com.ghostix.lib.arraylib.ArrayUtils;
import ar.com.ghostix.lib.asciimenus.MainMenu;

public class ExampleSelector
{
    public static void main(String[] args){
        //We instantiate objects for all of our classes and a Scanner.
        Point point = new Point(4,3);
        MagicMatrix matrix = new MagicMatrix();
        ArrayUtils<Integer> array = new ArrayUtils<>(Integer.class, 10);
        Integer[] exampleValues = {37, 8, 9, 92, 40, 70, 1, 3, 4, 19};
        //String[]exampleValues = {"Arroz", "Con", "leche", "Chocolate", "Cebolla", "Zorro", "Cebra"};
        array.setArray(exampleValues);
        //Then we store the objects to execute in an array.
        Object[] options = {point, matrix, array};
        //We instantiate a new MainMenu with the parameters of
        //Menu name, Options name, Objects to execute.
        MainMenu menu = new MainMenu("EXAMPLE", "Example ", options);
        menu.run(System.in);
    }
}
