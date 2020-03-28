class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) { // i color
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {// i - 1 color
                    if (k == j) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int num : dp[n]) {
            min = Math.min(min, num);
        }
        return min;
    }
}