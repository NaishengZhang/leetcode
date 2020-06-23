// brute force 2^n
class Solution {
    public int lengthOfLIS(int[] nums) {
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;
    }
    // 以 nums[index] 为结尾的最长上升子序列的长度
    public int getMaxLength(int[] nums, int index) {
        if (index == 0) {
            return 1;
        }
        int max = 1;
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                max = Math.max(max, getMaxLength(nums, i) + 1);
            }
        }
        return max;
    }
}

// Recursion with Memoization
class Solution {
    public int lengthOfLIS(int[] nums) {
        int res = 0;
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i, memo));
        }
        return res;
    }
    
    // 以 nums[index] 为结尾的最长上升子序列的长度
    public int getMaxLength(int[] nums, int index, int[] memo) {
        if (memo[index] != -1) {
            return memo[index];
        }
        if (index == 0) {
            return 1;
        }
        int max = 1;
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                max = Math.max(max, getMaxLength(nums, i, memo) + 1);
            }
        }
        return memo[index] = max;
    }
}

//dp time O(n^2) spaceO(n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }
}