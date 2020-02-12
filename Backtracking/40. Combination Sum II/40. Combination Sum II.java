class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(candidates, target, 0, res, subset);
        return res;
    }
    
    private void dfs(int[] candidates, int target, int index, List<List<Integer>> res, List<Integer> subset) {
        
        //出口
        if(target == 0) {
            res.add(new ArrayList<>(subset));
            return;
        }
        // if (target < 0) {
        //     return;
        // }
        //选择
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            }
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            subset.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, res, subset);
            subset.remove(subset.size() - 1);
            
        }
    }
}