//n个房子，每个房子k种颜色，对于dp[i][j]第i个房子在j颜色的情况下，找第i-1个房子k-1种颜色中cost最小的 =》O(nk^2)
class Solution {
    // O(nk^2)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = costs[i - 1][j];
                int min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m == j) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][m] + costs[i - 1][j]);
                    dp[i][j] = min;
                }
            }
        }
        
        for (int i = 0; i < k; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }
}

// n个房子，每个房子k种颜色，对于dp[i][j]第i个房子在j颜色的情况下，先遍历一遍i-1个房子中cost最小值以及最大值 =》n*(2k)
class Solution {
    // O(nk)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE; // acctually no used
            int j1 = 0, j2 = 0;
            //Find least smallest and the seconde smallest 
            for (int j = 0; j < k; j++) {
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    j2 = j1;
                    min1 = dp[i - 1][j];
                    j1 = j;
                } else if(dp[i - 1][j] < min2) {
                    min2 = dp[i - 1][j];
                    j2 = j;
                }
            }
            
            for (int j = 0; j < k; j++) {
                dp[i][j] = costs[i - 1][j];
                if (j == j1) {
                    dp[i][j] = costs[i - 1][j] + dp[i - 1][j2];
                } else {
                    dp[i][j] = costs[i - 1][j] + dp[i - 1][j1];
                }
            }
        }
        
        for (int i = 0; i < k; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }
}