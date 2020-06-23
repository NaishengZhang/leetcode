//recursion top-down TLE
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {   
        return helper(0, 0, triangle);
    }
    private int helper(int level, int i, List<List<Integer>> triangle) {
        if (level == triangle.size() -1) {
            return triangle.get(level).get(i);
        }
        int left = helper(level + 1, i, triangle);
        int right = helper(level + 1, i + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(i);
    }
}

// dfs+memo top-down
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        Integer[][] memo = new Integer[n][n];
        
        return helper(0, 0, triangle, memo);
    }
    private int helper(int level, int i, List<List<Integer>> triangle, Integer[][] memo) {
        if (level == triangle.size() -1) {
            return triangle.get(level).get(i);
        }
        if (memo[level][i] == null) { // 剪枝的一种
            int left = helper(level + 1, i, triangle, memo);
            int right = helper(level + 1, i + 1, triangle, memo);
            memo[level][i] = Math.min(left, right) + triangle.get(level).get(i);
        }
        return memo[level][i];
    }
}

// dp top-down
// triangle[i][j] 一定经过triangle[i-1][j]和triangle[i-1][j-1]，（判断特殊条件）
// Time: O(n^2) n 为三角形的总行数
// space: O(n^2) 
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i-1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j - 1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i : dp[n - 1]) {
            res = Math.min(res, i);
        }
        return res;
    }
}

/*
Space: O(n)
利用dp[j]表示第x行第j列最小路径和 j > level的不会被用到。
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        
        int n = triangle.size();
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}

// dp bottom-up
// Time: O(n^2) n 为三角形的总行数
// Space: O(n^2) 
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}

/*
Space: O(n)
对于第i行的推导，只需要用到第i-1行的dp[i - 1][j]和dp[i - 1][j - 1]元素，可以使用两个变量暂存，因为更新新的一层时，会把需要用的之前的信息替换掉。
利用dp[j]表示第x行第j列最小路径和 
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0]= triangle.get(0).get(0);
        int cur = 0, pre = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                cur = dp[j];
                if (j == 0) {
                    dp[j] = cur + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[j] = pre + triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(cur, pre) + triangle.get(i).get(j);
                }
                pre = cur;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i : dp) {
            res = Math.min(res, i);
        }
        return res;
    }
}
