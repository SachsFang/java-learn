package com.fang.后端.常见排序算法;

/**
 * Created by SachsFang on 2021/5/13 10:05
 */
public class Sorts {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 9};
//        Sorts.selectSort(arr);
//        Sorts.bubbleSort(arr);
        Sorts.quickSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /*选择排序*/
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /*冒泡排序*/
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j+1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low;
        int j = high;
        int index = arr[i];
        while (i < j) {
            while (i < j && index <= arr[j]) {
                j--;
            }
            if (i<j){ arr[i++] = arr[j];}
            while (i < j && index >= arr[i]) {
                i++;
            }
            if (i<j){ arr[j--] = arr[i];}
        }
        arr[i] = index;
        quickSort(arr, low, i-1);
        quickSort(arr, i + 1, high);
    }

}
