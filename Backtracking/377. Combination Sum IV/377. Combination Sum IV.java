//TLE
class Solution {
    private int res = 0;
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        dfs(nums, target);
        return res;
    }
    
    private void dfs(int[] nums, int target) {
        //出口 exit
        if (target == 0) {
            res++;
            return;
        }
        
        if (target < 0) {
            return;
        }

        //选择 selection
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] < 0) {
                continue;
            }
            dfs(nums, target - nums[i]);
        }
    }
}