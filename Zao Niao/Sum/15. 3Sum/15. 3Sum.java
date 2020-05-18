/*
Brute force Time: O(n^3). 固定第一个数x后，再从后边的数组中找two sum == 0 - x;

Two pointer: Time O(nlogn + n^2) Space Complexity: from O(logn) to O(n) depending on the implementation of the sorting algorithm. 

*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { //第一个数去重
                continue;
            }
            
            int target = 0 - nums[i];
            int l = i + 1, r = nums.length - 1;
            
            while (l < r) {
                int sum = nums[l] + nums[r];
                
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < nums.length && nums[l] == nums[l - 1]) { // 去重
                        l++;
                    } 
                    while (r >= 0 && nums[r] == nums[r + 1]) { // 去重
                        r--;
                    }
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
/*

HashSet time: O(n^2) space O(n^2) We may need to store up to O(n^2) elements in a hash set for deduplication.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Set<String> found = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -nums[i] - nums[j];
                if (seen.contains(complement)) {
                    int min = Math.min(nums[i], Math.min(nums[j], complement));
                    int max = Math.max(nums[i], Math.max(nums[j], complement));
                    String s = String.valueOf(min) + String.valueOf(max);
                    if (found.add(s)) {
                        res.add(Arrays.asList(complement, nums[i], nums[j]));
                    }
                }
                seen.add(nums[j]);
            }
        }
        
        return res;
    }
}