package ar.com.ghostix.lib.asciimenus;

public interface ITitlePrinter {
    default void printTitle(String name){
        int width = 22 + name.length(); //11 spaces for the right and left sides of the name
        int height = 7;
        //Title printing
        StringBuilder line= new StringBuilder();
        line.append("=".repeat(Math.max(0, width)));
        System.out.println(line);
        for(int y = 0; y<height; y++){
            line = new StringBuilder("|");
            if(y!=3){
                //White spaces
                line.append(" ".repeat(Math.max(0, width - 2)));
            }else{
                //We print the name
                line.append(" ".repeat(10));
                line.append(name);
                int actualLength = line.length();
                line.append(" ".repeat(Math.max(0, width - 1 - actualLength)));
            }
            line.append("|");
            System.out.println(line);
        }
        line = new StringBuilder();
        line.append("=".repeat(Math.max(0, width)));
        System.out.println(line);
        //Title finished
    }
}
