package edu.nyu.sort;


import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;

        sort(arr, low, mid, temp);
        sort(arr, mid + 1, high, temp);
        merge(arr, low, mid, high, temp);
    }

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= high) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (low <= high) {
            arr[low++] = temp[t++];
        }
    }

}
