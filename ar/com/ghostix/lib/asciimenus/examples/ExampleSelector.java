package ar.com.ghostix.lib.asciimenus.examples;

import ar.com.ghostix.lib.arraylib.ArrayUtils;
import ar.com.ghostix.lib.asciimenus.MainMenu;

public class ExampleSelector
{
    public static void main(String[] args){
        //We instantiate objects for all of our classes and a Scanner.
        Point point = new Point(4,3);
        MagicMatrix matrix = new MagicMatrix();
        HiddenOptionsExample hiddenOptionsExample = new HiddenOptionsExample();
        //Then we store the objects to execute in an array.
        Object[] options = {point, matrix, hiddenOptionsExample};
        //We instantiate a new MainMenu with the parameters of
        //Menu name, Options name, Objects to execute.
        MainMenu menu = new MainMenu("EXAMPLE", "Example ", options);
        menu.run(System.in);
    }
}
