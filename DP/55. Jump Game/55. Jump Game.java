class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean[] dp = new boolean[nums.length] ;
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                }
            }
        }
        return dp[nums.length - 1];
    }
}

/*	backtracking recover
    private void dfs(int level, int n) {
        退出条件  
        for 选择 in 选择列表：
          	做选择
          	dfs
          	撤销选择
        } 
    }*/
class Solution {
    public boolean canJump(int[] nums) {
        return dfs(0, nums);
    }
    
    private boolean dfs(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for(int i = position + 1; i <= furthestJump; i++) {
            if (dfs(i, nums)) {
                return true;
            }
        }
        return false;
        
    }
}
