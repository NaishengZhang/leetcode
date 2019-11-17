class Solution {
    public int maxArea(int[] height) {
        if (height.length == 0 || height == null) {
            return 0;
        }
        int ans = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            int area = (r - l) * Math.min(height[r], height[l]);
            ans = Math.max(area, ans);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}