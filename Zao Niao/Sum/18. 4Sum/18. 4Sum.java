class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 3; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length - 2; i++) {
                if (i > i + 1 && nums[i] == nums[i - 1]) { //第2个数去重
                    continue;
                }

                int l = i + 1, r = nums.length - 1;

                while (l < r) {
                    int sum = nums[l] + nums[r] + nums[i] + nums[k];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[k], nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) { // 去重
                            l++;
                        } 
                        while (l < r && nums[r] == nums[r - 1]) { // 去重
                            r--;
                        }
                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }            
        }

        return res;
    }
}