import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {
    static void bubbleSort(int[] arr,String[] arr2) {
        int n = arr.length;
        int temp = 0;
        String temp2 = null;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
                    //swap elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;

                    temp2 = arr2[j-1];
                    arr2[j-1] = arr2[j];
                    arr2[j] = temp2;
                }

            }
        }

    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\home/input.txt"));
            stringBuilder = new StringBuilder();
            char[] buffer = new char[10];
            while (reader.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
                buffer = new char[10];
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String content = stringBuilder.toString();
        String lines[] = content.split("\\r?\\n");
        Integer numberOfEmployees = Integer.parseInt(lines[0].split(":")[1].replaceAll("\\s+",""));

        String items[] = new String[lines.length-4];
        int[] array = new int[lines.length-4]; //prices array
        int itemCounter = 0;
        for(int i=4;i<lines.length;i++) {
            int itemPrice = Integer.parseInt(lines[i].split(":")[1].replaceAll("\\s+","").replaceAll("[^\\d.]",""));
            array[itemCounter] = itemPrice;
            items[itemCounter] = lines[i];
            itemCounter+=1;
        }
        bubbleSort(array, items);
        int[] priceDifferences = new int[items.length-numberOfEmployees];
        for(int i=0;i<items.length-(numberOfEmployees+1);i++) {
            priceDifferences[i] = array[i+(numberOfEmployees-1)] - array[i];
        }
        int index = 0;
        int min = priceDifferences[index];

        for (int i = 1; i < priceDifferences.length; i++){
            if (priceDifferences[i] <= min){
                min = priceDifferences[i];
                index = i;
            }
        }
        System.out.println("Index = "+index);
        System.out.println("The goodies selected for distribution are:");

        for(int i=index-2;i<index+numberOfEmployees-1;i++) {
            System.out.println(items[i]);
        }
        int result = array[numberOfEmployees+index-2]-array[index-1];
        System.out.println("And the difference between the chosen goodie with highest price and the lowest price is "+result);
    }
}
