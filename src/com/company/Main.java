package com.company;

import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        int[] arr = generateAnArray();
        int[] sortedArray = sort(Main::radixSort,arr);
        System.out.println("your array has elements printed below:");
        printAnArray(sortedArray);

        int searchFor = promptTheUserToEnterADigit("Enter the number you are looking for");
        int indexOfSearchFor = find(Main::binarySearch, searchFor, arr);
        System.out.println("index of: " + indexOfSearchFor);
        int firstIndexOf = firstIndexOf(Main::binarySearch, searchFor, arr);
        System.out.println("first index: " + firstIndexOf);

    }

    public static int firstIndexOf(Findable findable, int searchFor, int[] arr) {
        int index  = findable.find(searchFor,arr);
        for (int i = index-1; i >=0;i--){
            if(arr[index]==arr[i]){
                index = i;
            }else {
                break;
            }
        }
        return index;
    }


    public static void printAnArray(int...arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int firstIndex, int secondIndex){
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex]=temp;
    }

    public static int[] generateAnArray(){
        int sizeArr = promptTheUserToEnterADigit("Enter the size of the array");
        int firstValue = promptTheUserToEnterADigit("Enter the min value");
        int lastValue = promptTheUserToEnterADigit("Enter the max value");

        Random random = new Random();

        int[] arr = new int[sizeArr];
        for (int i = 0; i<sizeArr; i++){
            arr[i]=random.nextInt(firstValue, lastValue);
        }

        printAnArray(arr);

        return arr;
    }

    public static int promptTheUserToEnterADigit(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        int digit = scanner.nextInt();
        return digit;
    }

    public static int[] sort(Sortable sortable, int...arr){
       return sortable.sort(arr);
    }

    public static int find(Findable findable,int searchFor, int...arr){
        return findable.find(searchFor,arr);
    }

    public static int[] selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int position = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<min){
                    position=j;
                    min=arr[j];
                }
            }
            swap(arr,i,position);
        }
        return arr;
    }

    public static int[] bubleSort(int[] arr){
        boolean isSorted  = false;
        int temp;
        while (!isSorted){
            isSorted = true;
            for (int i = 0; i<arr.length-1; i++){
                if(arr[i]>arr[i+1]){
                    isSorted= false;
                    swap(arr,i,i+1);
                }
            }
        }
        return arr;
    }

    public static int binarySearch(int n,int[] arr){
        int low = 0;
        int high = arr.length-1;
        while (low<=high) {
            int mid = low + (high - low)/2;
            if (n == arr[mid]) {
                return mid;
            }
            if (n > arr[mid]) {
                low = mid +1;
            } else {
                high = mid -1;
            }
        }

        return -1;
    }

    public static int lineSearch(int n,int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                return i;
            }
        }
        return -1;
    }

    public static int[] radixSort(int[] arr){
        int maxValue = getMaxValue(arr);
        for (int digit = 1; maxValue/digit >0 ; digit*=10) {
            countSort(arr, arr.length, digit);
        }
        return arr;
    }

    private static void countSort(int[] arr, int length, int digit) {
        int[] sortedAnArray = new int[length];
        int[] countDigit = new int[10];
        int i;

        for (i = 0; i < length; i++) {
            countDigit[(arr[i]/digit)%10]++;
        }

        for (i = 1; i < 10; i++) {
            countDigit[i] += countDigit[i-1];
        }

        for (i = length-1; i >=0 ; i--) {
            sortedAnArray[countDigit[(arr[i]/digit)%10]-1]= arr[i];
            countDigit[(arr[i]/digit)%10]--;
        }

        for (i = 0; i < arr.length; i++) {
            arr[i] = sortedAnArray[i];
        }

    }

    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>maxValue){
                maxValue=arr[i];
            }
        }
        return maxValue;
    }
}
