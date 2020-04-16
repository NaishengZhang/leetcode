// Time: O(n) Space: O(n)
// 遍历两遍，分别记录i左右两侧所有数的乘积。 边界0 n-1初始值都是1；
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                left[i] = 1;
            } else {
                left[i] = left[i - 1] * nums[i - 1];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                right[i] = 1;
            } else {
                right[i] = right[i + 1] * nums[i + 1];
            }
        }
        for (int i = 0; i < n; i++) {
            left[i] *= right[i];
        }
        return left;
    }
}

//Time: O(n)  Space: O(1) - output array don't count as extra space
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res[i] = 1;
            } else {
                res[i] = res[i - 1] * nums[i - 1];
            }
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}