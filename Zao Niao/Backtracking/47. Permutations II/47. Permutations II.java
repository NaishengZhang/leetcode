class Solution {
    private List<List<Integer>> res;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>());
        
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> subset) {
        if (subset.size() == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) continue; // !visited[i - 1]意思是两个相同的数是可以出现在当前的res中
            subset.add(nums[i]);
            visited[i] = true;
            dfs(nums, subset);
            subset.remove(subset.size() - 1);
            visited[i] = false;
        }
    }
}