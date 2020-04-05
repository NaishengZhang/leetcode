class Solution {
    public int[][] generateMatrix(int n) {
        
        int left = 0, top = 0, right = n - 1, bottom = n - 1;
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int k = 1;
        while (k <= n * n) {
            // left -> right
            for (int i = left; i <= right; i++) {
                res[top][i] = k;
                k++;
            }
            top++;
            
            for (int i = top; i <= bottom; i++) {
                res[i][right] = k;
                k++;
            }
            right--;
            
            for (int i = right; i >= left; i--) {
                res[bottom][i] = k;
                k++;
            }
            bottom--;
            
            for (int i = bottom; i >= top; i--) {
                res[i][left] = k;
                k++;
            }
            left++;

        }
        
        return res;
    }
}