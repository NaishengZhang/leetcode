class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[2][m];
        int old = 1, now = 0;
        for (int i = 0 ; i < n; i++) {
            old = now;
            now = 1 - old;
            for (int j = 0; j < m; j++) {
                dp[now][j] = Integer.MAX_VALUE;
                if (i == 0 && j == 0) {
                    dp[now][j] = grid[i][j];
                }
                if (i > 0) {
                    dp[now][j] = Math.min(dp[old][j] + grid[i][j], dp[now][j]);
                }
                if (j > 0) {
                    dp[now][j] = Math.min(dp[now][j - 1] + grid[i][j], dp[now][j]);
                }
            }
        }
        return dp[now][m - 1];
    }
}