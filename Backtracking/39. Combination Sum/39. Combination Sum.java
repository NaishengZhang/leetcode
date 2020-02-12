class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        dfs(candidates, new ArrayList<Integer>(), res, target, 0);
        return res;
    }
    
    private void dfs(int[] candidates, List<Integer> subset, List<List<Integer>> res, int target, int index) {
        //出口
        if (target == 0) {
            res.add(new ArrayList<>(subset));//深拷贝,因为subset在dfs的过程中会动态的变化，当subset保存了一个可行解时，
                                            //应该用深拷贝保存在res中，否则浅拷贝的话，res只是保存了subset的地址，subset之后remove元素后，res中的元素也会被remove。
            return;
        }
        if (target < 0) {
            return;
        }
        
        for (int i = index; i < candidates.length; i++) { //从index开始，如果从零开始就变成数字相同排列不同的结果。
            //选择列表
            subset.add(candidates[i]);
            dfs(candidates, subset, res, target - candidates[i], i);
            subset.remove(subset.size() - 1);
        }
    }
}