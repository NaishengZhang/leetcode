class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (k <= 0) {
            return res;
        }
        
        dfs(k, n, 1, res, subset);
        
        return res;
    }
    
    private void dfs(int k, int target, int index, List<List<Integer>> res, List<Integer> subset) {
        //出口
        if (target == 0 && subset.size() == k) {
            res.add(new ArrayList<>(subset));
            return;
        }
        
        if (subset.size() > k) {
            return;
        }
        //选择
        for (int i = index; i < 10; i++) {
            
            if(target - i < 0) {
                continue;
            }
            
            subset.add(i);
            dfs(k, target - i, i + 1, res, subset);
            subset.remove(subset.size() - 1);
        }
    }
}