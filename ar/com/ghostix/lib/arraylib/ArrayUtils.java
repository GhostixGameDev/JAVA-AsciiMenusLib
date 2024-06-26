package ar.com.ghostix.lib.arraylib;


import java.util.Scanner;
import java.lang.Math;
import java.util.Stack;
import ar.com.ghostix.lib.asciimenus.SubMenu;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Arrays;
//STANDALONE VERSION
//We implement Comparable into our generic Class
public class ArrayUtils <T extends Comparable<T>>{   
    //Generic array
    private T[] array;
    public ArrayUtils(Class<T> type, int size){
        //We instantiate the array
        array = (T[]) Array.newInstance(type, size);
       
    }
    //Get and Set methods
    public T[] getArray(){
        return array;
    }
    public void setArray(T[] newValue){
        array = newValue;
    }
    
    //Instance methods
    public T get(int index){
        return array[index];
    }
    public void set(int index, T newValue){
        array[index] = newValue;
    }
    
    public void changeSize(int newSize){
        //We make a temp array to copy all the stuff in the new sized array
        //And then overwrite the array with the temp one.
        if(newSize>0){
            T[] tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
            int size = array.length;
            int limit = Math.min(size, newSize);
            System.arraycopy(array, 0, tempArray, 0, limit);
            setArray(tempArray);
        }else{
            T[] tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), 0);
        }
    }
    public int length(){
        //We return our array length
        return array.length;
    }
    public void sort(){
        setArray(quickSort(getArray()));
    }
    public void search(T objective){
        this.sort(); //First we sort in order to binarySearch to work well.
        int result = binarySearch(getArray(), objective);
        if(result!=-1){
            System.out.println(objective + " found at index: " + result);
        }else{
            System.out.println("Objective not found.");
            }
        }
    
    public void showArray(){
        //we show the array
        for(int i=0; i<length(); i++){
            if(i!=length()-1){
                System.out.print(getArray()[i] + ", ");       
            }else{
                System.out.println(getArray()[i] + ".");
            }
        }
    }
    public void append(T valueToAdd){
        changeSize(length()+1);
        set(length()-1, valueToAdd);
    }
    public void delete(T valueToDelete, boolean sorted){
        int index;
        if(sorted){
            index = binarySearch(getArray(), valueToDelete);
        }else{
            index = linearSearch(getArray(), valueToDelete);
        }
        int length = length();
        if(index!=-1){
            if(index!=length-1){
                for(int i = index; i < length - 1; i++){
                    set(i, get(i+1));
                }
            }
            changeSize(length - 1);
        }
    }
    public void deleteAll(T valueToDelete, boolean sorted){
        int index;
        int length;
        do{
            if(sorted){
                index = binarySearch(getArray(), valueToDelete);
            }else{
                index = linearSearch(getArray(), valueToDelete);
            }
            if(index!=-1){
                length = length();
                for(int i = index; i < length-1; i++){
                    set(i, get(i+1));
                }
                if(length>1){
                    changeSize(length-1);
                }else{
                    set(0, null);
                }
            }
        }while(index!=-1);
    }
    
    public void fill(T value){
        Arrays.fill(getArray(), value);
    }
    public void clean(T temp){
        fill(temp);
        deleteAll(temp, false);
    }
    //Static methods
    
    public static <T extends Comparable<T>> boolean in(T objective, T[] array){
        array = quickSort(array);
        //If the objective is in the array we return true.
        return binarySearch(array, objective) != -1;
            
    }
    public static <T extends Comparable<T>> int binarySearch(T[] array, T objective) {
        //We handle any exceptions if the array is null or 1 width.
        if(array.length == 1){
            if(array[0]==null){
                return -1;
            }else if(array[0].compareTo(objective) == 0){
                return 0;
            }else{
                return -1;
            }
        }
        //Binary search algorithm, we divide and conquer, we divide it in half and search from left and right until they met.
        //Then we will be able to find our objective.
        int lowest = 0;
        int highest = array.length - 1; 
        //We use the iterative method to save memory and avoid Stack overflow issues.
        while (lowest <= highest) {
            int mid = lowest + (highest - lowest) / 2;
            int comparison = array[mid].compareTo(objective);
            if(comparison == 0){
                return mid;
            }else if(comparison < 0){
                lowest = mid + 1;
            }else{
                highest = mid - 1;
            }
        }
        return -1;
     }
    public static <T> int linearSearch(T[] array, T objective){
         int length = array.length;
         for(int i = 0; i < length; i++){
             if(array[i].equals(objective)){
                return i;
             }
         }
         return -1;
    }
    public static <T extends Comparable<T>> T[] quickSort(T[] array){
        //We check if there is something to sort.
        if(array.length!=1){
            //We use iterative quickSort to save memory.
            //By doing this we avoid potential Stack overflow issues.
            //We will use a stack as support. It will store the right and left
            //Sides and then pop them.
            Stack<int[]> stack = new Stack<>();
            stack.push(new int[] {0, array.length-1});
            //If the stack gets left out without range, it means we finished sorting
            while(!stack.isEmpty()){
                int[] range = stack.pop();
                int lowest = range[0];
                int highest = range[1];
                //We partition between our stack range.
                int pivot = partition(array, lowest, highest);
                //We add the left and right sides of the pivot respectively to the stack.
                if(pivot-1 > lowest){
                    stack.push(new int[] {lowest, pivot-1});
                }
                if(pivot+1 < highest) {
                    stack.push(new int[] {pivot+1, highest});
                }
            }
                
            
        }
        return array;
    }
    private static <T extends Comparable<T>> int partition(T[] array, int lowest, int highest){
        int medianIndex = medianOfThree(array, lowest, highest); //We get the median of three, as it is one if not most of the fastest pivots for quick sort.
        swap(array, medianIndex, highest); //We move the pivot to the right
        T pivot = array[highest];
        int i = lowest - 1;

        for(int j = lowest; j < highest; j++) {
            if(array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j); //We start comparing the numbers from left with our pivot and swapping
            }
        }
        swap(array, i + 1, highest);
        return i + 1;
    }
    private static <T extends Comparable<T>> int medianOfThree(T[] array, int lowest, int highest) {
        //Median of three formula.
        int middle = lowest + (highest - lowest) / 2;
        if(array[lowest].compareTo(array[middle]) > 0) {
            swap(array, lowest, middle);
        }
        if(array[lowest].compareTo(array[highest]) > 0) {
            swap(array, lowest, highest);
        }
        if(array[middle].compareTo(array[highest]) > 0) {
            swap(array, middle, highest);
        }
        return middle;
    }

    public static <T> void swap(T[] array, int i, int j) {
        if(array != null && array.length != 0){
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
    }
    public static <T> T[] changeSize(T[] array, int newSize){
        //We make a temp array to copy all the stuff in the new sized array
        //And then overwrite the array with the temp one.
        T[] tempArray;
        if(newSize>0){
            tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
            int size = array.length;
            int limit = Math.min(size, newSize);
            System.arraycopy(array, 0, tempArray, 0, limit);
        }else{
            tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), 0);
        }
        return tempArray;
    }
    public static <T> T[] append(T[] array, T valueToAdd){
        array = changeSize(array, array.length+1);
        array[array.length-1] = valueToAdd;
        return array;
    }
    public static <T> T[] delete(T[] array, T valueToDelete){
        int index;
        index = linearSearch(array, valueToDelete);
        int length = array.length;
        if(index!=-1){
            if(index!=length-1){
                for(int i = index; i < length - 1; i++){
                    swap(array, i, i+1);
                }
            }
            changeSize(array, length - 1);
        }
        return array;
    }
    
    
    public void run(Scanner scan){
        String[] customOptions = {""};
        String[] hiddenOptions = {"length", "swap", "medianOfThree", "Partition", "quickSort", "in", "binarySearch", "linearSearch"};
        SubMenu menu = new SubMenu("ArrayUtils", this, false, customOptions, hiddenOptions);
        int option = 0;
        while(option!=menu.getExit()){
            option = menu.run(scan);
        }
    }
    
    
    

    
}
