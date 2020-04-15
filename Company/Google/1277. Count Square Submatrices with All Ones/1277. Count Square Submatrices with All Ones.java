class Solution {
    // same as 221 maximal square =>求matrix中最大的正方形。
    // 以i,j为右下角顶点的正方形 就是 其最大正方形的边长
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = matrix[i][j];
                if (i != 0 && j != 0 && dp[i][j] != 0) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                res += dp[i][j];
            }
        }
        return res;
    }
}

