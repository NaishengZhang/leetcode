// Time: O(n + m)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //Mono stack
        Deque<Integer> stack = new ArrayDeque<>(); // Monotonic stack decrease.
        Map<Integer, Integer> map = new HashMap<>(); // <nums1, greater>
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }
        int n = nums1.length;
        int m = nums2.length;
        for (int i = 0; i < m; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i);
        }
        
        for (int i = 0; i < n; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return nums1;
    }
}