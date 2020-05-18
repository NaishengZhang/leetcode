/*
首先可以再 1.two sum的基础上改一下 index base 0 -> 1, 
but both existing solutions do not make use of the property where the input array is sorted.
*/

/*
Binary search get benifit from sorted array. Reduce loop up time from O(n) to O(logn)
Time: O(nlogn) Space: O(1)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            int complement = target - nums[i];
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;

                if (nums[mid] == complement) {
                    return new int[]{i + 1, mid + 1};
                } else if (nums[mid] > complement) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            if (nums[l] == complement) {
                return new int[]{i + 1, l + 1};
            } else if (nums[r] == complement) {
                return new int[]{i + 1, r + 1};
            }
        }
        return new int[0];
    }
}
/*
Two Pointer
Time O(n), Space O(1)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // null check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int l = 0, r = nums.length - 1;
        int sum = 0;
        while (l < r) {
            sum = nums[l] + nums[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum > target) {
                r--;
            } else {
                l++; 
            }
        }
        return new int[0];
    }
}