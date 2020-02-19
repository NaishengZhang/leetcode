class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(subset, visited, res, nums);
        return res;
    }
    
    private void dfs(List<Integer> subset, Set<Integer> visited, List<List<Integer>> res, int[] nums) {
        //出口
        if (subset.size() == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        for (int i = 0; i< nums.length; i++) {
            if (visited.contains(i)) { //记录位置
                continue;
            }
            if (i > 0 && nums[i] == nums [i - 1] && !visited.contains(i - 1)) { //important condition: visited.contains(i - 1)
                continue;
            }
            subset.add(nums[i]);
            visited.add(i);
            dfs(subset, visited, res, nums);
            subset.remove(subset.size() - 1);
            visited.remove(i);
        }
    }
    
    
    
}