class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = Integer.MIN_VALUE;
        
        for (int i = 1; i<= n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i - 1];
            } else {
                dp[i] = dp[i - 1] + nums[i - 1];
            }
        }
        int res = Integer.MIN_VALUE;
        for (int x : dp) {
            res = Math.max(res, x);
        }
        return res;
    }
}

// Divide and Conquer
class Solution {
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private int helper(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left == right) {
            return nums[mid];
        }
        
        int leftMax = helper(nums, left, mid);
        int rightMax = helper(nums, mid + 1, right);
        int crossMax = cross(nums, left, right, mid);
        
        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }
    
    private int cross(int[] nums, int left, int right, int mid) {
        int max1 = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            max1 = Math.max(max1, sum);
        }
        sum = 0;
        int max2 = Integer.MIN_VALUE;
        for (int i = mid + 1; i <=right; i++) {
            sum += nums[i];
            max2 = Math.max(max2, sum);
        }
        
        return max1 + max2;
        
    }
    
}