class Solution {
    // https://www.youtube.com/watch?v=eG27FBcmy1k
    // duplicates会导致 左半段的start == 右半段的end，那么如果nums[mid]= nums[start]的话,就不能判断mid在左还是右了。
    // 通过start++来破坏nums[mid]= nums[start]， 如果mid在左边，那么mid变大 或者 如果mid在右边 start变大
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[left]) { // mid在左侧 有序
                if (target <= nums[mid] && target >= nums[left]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (nums[mid] < nums[left]) { // mid在右侧 有序
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                left++;
            }
            
        }
        if (nums[left] == target) {
            return true;
        }
        if (nums[right] == target) {
            return true;
        }
        
        return false;
    }
}