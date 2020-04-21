// no duplicates
//给一个无序数组，用二分法搜索数组，找出能用二分法搜到的所有数字
//一个印度小哥，给定一个没有排序的随机数组，使用二分法找数组里的各个元素，返回有多少元素是用二分法永远找不到的。要求算法复杂度为O(n),最后给出了nlog（n）的解法，估计最后挂在这了。
// 可以用类似于判断树是否为BST的思路：维护一个“值域”R=(lo, hi)（即在当前搜索下标范围[i, j]内，只有在R范围内的数才有可能被二分法找到）。求得当前搜索范围[i, j]的中点mid。如果A[mid]在当前的R中，那A[mid]就一定能被找到。然后分别递归mid的左右两侧[i, mid-1]和[mid+1, j]。
//递归左侧时，允许的值域是(lo, A[mid])；同理右侧的值域是(A[mid], hi)。如果任何时刻发现值域R已经不包含任何整数，那就表示[i, j]这个下标范围内没有任何能找到的数字。

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

    // private static void dfs(int[] nums, List<Integer> res, int lo, int hi, int min, int max) {
    //     if (lo > hi) {
    //         return;
    //     }
    //     int mid = lo + (hi - lo) / 2;
    //     int value = nums[mid];
    //     if (value < min || value > max) {
    //         res.add(mid); // return index
    //     }
    //     dfs(nums, res, lo, mid - 1, min, Math.min(value, max));
    //     dfs(nums, res, mid + 1, hi, Math.max(value, min), max);
    // }


    // public static int binarySearch(int[] nums, int target) {
    //     if (nums == null || nums.length == 0) {
    //         return -1;
    //     }

    //     int left = 0, right = nums.length - 1;
    //     while (left + 1 < right) { //终止条件 left + 1 = right [left, right] [2,3]
    //         int mid = left + (right - left) / 2;
    //         if (nums[mid] == target) {
    //             return mid;
    //         } else if (nums[mid] < target) {
    //             left = mid;
    //         } else {
    //             right = mid;
    //         }
    //     }
    //     if (nums[left] == target) return left;
    //     if (nums[right] == target) return right;
    //     return -1;
    // }
}
