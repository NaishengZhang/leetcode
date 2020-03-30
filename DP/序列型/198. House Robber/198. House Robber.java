// Memory O(n)
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
}

// Memory O(1)
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = nums[0];
        int pre2 = 2;
        int pre1 = 0;
        int now = 1;
        int temp = -1;
        for (int i = 2; i <= n; i++) {
            temp = now;
            now = pre2;
            pre2 = pre1;
            pre1 = temp;
            dp[now] = Math.max(dp[pre1], dp[pre2] + nums[i - 1]);
        }
        return dp[now];
    }
}

// no dp array
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int curMax = 0;
        int preMax = 0;
        for (int num : nums) {
            int newMax = Math.max(preMax + num, curMax);
            int temp = curMax;
            curMax = newMax;
            preMax = temp;
        }
        return curMax;
    }
}