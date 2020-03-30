class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int res1 = rob(nums, 0, n - 1);
        int res2 = rob(nums, 1, n);
        return Math.max(res1, res2);
    }
    public int rob(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curMax = 0;
        int preMax = 0;
        for (int i = start; i < end; i++) {
            int newMax = Math.max(preMax + nums[i], curMax);
            int temp = curMax;
            curMax = newMax;
            preMax = temp;
        }
        return curMax;
    }
}


class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return nums[0];
        }
        int[] arr1 = Arrays.copyOfRange(nums, 0, n - 1);
        int[] arr2 = Arrays.copyOfRange(nums, 1, n);
        int res1 = robSub(arr1);
        int res2 = robSub(arr2);
        return Math.max(res1, res2);
    }
    public int robSub(int[] nums) {
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