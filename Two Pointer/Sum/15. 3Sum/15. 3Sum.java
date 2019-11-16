class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
       List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = 0 - nums[i];
            twoSum(nums, i, remain, ans);
        }
        return ans;
    }
    
    private void twoSum(int[] nums, int i, int target, List<List<Integer>> ans) {
        
        int lo = i + 1, hi = nums.length - 1;

        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum == target) {
                ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                lo++;
                hi--;
                while (lo < hi && nums[lo] == nums[lo - 1]) {
                    lo++;
                }
                while (lo < hi && nums[hi] == nums[hi + 1]) {
                    hi--;
                }
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
    }
    
    
    
}