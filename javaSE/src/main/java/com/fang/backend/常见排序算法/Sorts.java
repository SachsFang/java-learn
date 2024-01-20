package com.fang.backend.常见排序算法;

/**
 * Created by SachsFang on 2021/5/13 10:05
 */
public class Sorts {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 9};
//        Sorts.selectSort(arr);
//        Sorts.bubbleSort(arr);
//        Sorts.quickSort(arr);
        Sorts.mergeSort(arr);
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
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
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
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && index >= arr[i]) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = index;
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            mergeSort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}
