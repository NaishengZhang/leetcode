class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] dp = new int[n][m];
        int[][] res = new int[n][m];
        
        //up
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        dp[i][j] = 1;
                    }
                    if (i > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                }
                res[i][j] += dp[i][j];
            }
        }
        
        //down
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        dp[i][j] = 1;
                    }
                    if (i < n - 1) {
                        dp[i][j] += dp[i + 1][j];
                    }
                }
                res[i][j] += dp[i][j];
            }
        }
        
        //left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        dp[i][j] = 1;
                    }
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
                res[i][j] += dp[i][j];
            }
        }
        
        //right
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 0;
                    if (grid[i][j] == 'E') {
                        dp[i][j] = 1;
                    }
                    if (j < m - 1) {
                        dp[i][j] += dp[i][j + 1];
                    }
                }
                res[i][j] += dp[i][j];
            }
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    result = Math.max(result, res[i][j]);
                }
            }
        }
        
        return result == Integer.MIN_VALUE ? 0 : result;
    }
}