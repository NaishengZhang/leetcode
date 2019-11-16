package edu.nyu.sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int range = 6;
        int[] arr = {5, 1, 3, 2, 0, 0};
//        System.out.println(Arrays.toString(sort(arr, range)));
        System.out.println(Arrays.toString(sort2(arr)));
    }

    //
    public static int[] sort2(int[] nums) {
        int max = 0, min = 0, range = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        range = max - min;
        System.out.println(max);
        System.out.println(min);
        System.out.println(range);
        int[] count = new int[range + 1];
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int index = 0;
        for (int j = 0; j < range + 1; j++) {
            while (count[j] > 0) {
                result[index++] = j;
                count[j]--;
            }
        }
        return result;
    }

    //算法导论
    public static int[] sort(int[] nums, int range) {
        int[] count = new int[range];
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        for (int j = 1; j < range; j++) {
            count[j] += count[j - 1];
        }
        for (int k = 0; k < nums.length; k++) {
            result[count[nums[k]] - 1] = nums[k];
            count[nums[k]]--;
        }
        return result;
    }

}
