// Time: O(n^3) 用dp先把（0，0）到任意（i，j）的合求出来。 然后再遍历整个matrix的点，在以这个点为右上角，开始遍历能够组成正方形的size
// sum是一个m+1 * n + 1的矩阵，多出来第一行和第一列 方便计算边界情况，index不会超出边界。
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] sum = new int[n+1][m+1];
        
        for(int i = 1; i <= n; i++) { // O(n^2)
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] 
                    - sum[i - 1][j - 1] + matrix[i - 1][j - 1] - '0'; 
            }
        }
        
        int res = 0;
        for (int i = 1; i <= n; i++) { //O(n^3)
            for (int j = 1; j <= m; j++) {
                for (int k = Math.min(n - i + 1, m - j + 1); k > 0; k--){
                    int num = sum[i + k - 1][j + k - 1] - sum[i + k - 1][j - 1] 
                        - sum[i - 1][j + k - 1] + sum[i - 1][j - 1];
                    if (num == k * k) {
                        res = Math.max(res, num);
                        break; //因为是从大都小，只需要记录第一个满足的情况
                    }
                }
            }
        }
        return res;
    }   
}

// Time: O(nm) Memory: O(nm)
// dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])+1
//以(i,j)为右下角的正方形能有多大，取决于：以(i-1,j)为右下角的最大正方形，以(i,j-1)为右下角的最大正方形，以(i-1,j-1)为右下角的最大正方形，这三者最小的那一个。
class Solution {
    public int maximalSquare(char[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = matrix[i][j] - '0';
                if (dp[i][j] == 0) {
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                }
                res = Math.max(res, dp[i][j] * dp[i][j]);
            }
        }
        return res;
    }
}