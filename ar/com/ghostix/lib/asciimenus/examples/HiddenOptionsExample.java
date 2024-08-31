package ar.com.ghostix.lib.asciimenus.examples;

import ar.com.ghostix.lib.asciimenus.SubMenu;

import java.io.InputStream;

public class HiddenOptionsExample {
    private int foo;
    private String bar;

    //Constructors.
    public HiddenOptionsExample() {
        foo = 0;
        bar = "";
    }

    //Getters and Setters.
    public int getFoo() {
        return foo;
    }
    public void setFoo(int foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }
    public void setBar(String bar) {
        this.bar = bar;
    }

    //Instance methods.
    public void updateFoo(int value){
        setFoo(value);
    }
    public void updateBar(String value){
        setBar(value);
    }
    public void printFoo(){
        System.out.println("Foo: " + getFoo());
    }
    public void printBar(){
        System.out.println("Bar: " + getBar());
    }
    public void printValues(){
        System.out.println("Foo: " + getFoo());
        System.out.println("Bar: " + getBar());
    }
    //Run method
    public void run(InputStream inputStream){
        String[] hiddenOptions = {"printFoo", "printBar"};
        SubMenu menu = new SubMenu("Hidden options Example", this, null, hiddenOptions);
        int option = 0;
        while(option != menu.getExit()){
            option = menu.run(inputStream);
        }
    }
}
