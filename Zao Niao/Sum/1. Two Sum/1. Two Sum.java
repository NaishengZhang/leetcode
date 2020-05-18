/*
brute force, Time O(n^2) Space O(1). 
For each element, we try to find its complement by looping through the rest of array which takes O(n) time.
ex:
[2 7 11 15] target: 9
2 + 7/11/15 ?= 9 
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // brute force
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}

/*
We reduce the look up time from O(n) to O(1),
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // HashMap
        Map<Integer, Integer> map = new HashMap<>(); // <num, index>
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}