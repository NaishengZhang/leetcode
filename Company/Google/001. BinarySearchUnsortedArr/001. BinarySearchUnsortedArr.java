// no duplicates

import java.util.ArrayList;
import java.util.List;

public class BinarySearchUnsortedArr {
    public static void main(String[] args) {
        int[] nums1 = {8, 3, 5, 2, 7, 10}; // 8, 2
        int[] nums2 = {2, 3, 5, 7, 10};
        int[] nums3 = {8, 3, 5, 2, 7, 10, 11};
//        int[] nums3 = {4, 3, 5, 7, 6, 8, 2, 9, 1};
//        Time: O(nlogn)
//        for (int i : nums1) {
//            int index = binarySearch(nums1, i);
//            System.out.println("num" + i + "index" + index);
//        }

//        searchUnsorted(nums1);

        System.out.println(failsBinarySearch(nums1));
    }

    private static void dfs(int[] nums, int start, int end, int lower, int upper, List<Integer> res) {
        if (start > end)
            return;
        int mid = start + (end - start) / 2;
        if ((nums[mid] < lower || nums[mid] > upper)) {
            res.add(nums[mid]);
        }
        dfs(nums, start, mid - 1, lower, nums[mid], res);
        dfs(nums, mid + 1, end, nums[mid], upper, res);

    }

    public static List<Integer> failsBinarySearch(int[] nums) {
        List<Integer> res = new ArrayList<>();
        dfs(nums, 0, nums.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, res);
        return res;
    }

    private static void dfs(int[] nums, List<Integer> res, int lo, int hi, int min, int max) {
        if (lo > hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        int value = nums[mid];
        if (value < min || value > max) {
            res.add(mid); // return index
        }
        dfs(nums, res, lo, mid - 1, min, Math.min(value, max));
        dfs(nums, res, mid + 1, hi, Math.max(value, min), max);
    }


    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) { //终止条件 left + 1 = right [left, right] [2,3]
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
