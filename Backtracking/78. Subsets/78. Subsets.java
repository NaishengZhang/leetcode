class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> subset, int index) {
        res.add(new ArrayList<>(subset));
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, res, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
        
        //出口
    }
}