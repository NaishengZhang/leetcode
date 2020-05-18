// Time: O(n^2), 对于任意一个index，找其左右第一个小于其高度的index
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int cur = heights[i];
            while (left - 1 >= 0 && heights[left - 1] >= cur) { // 左边第一个小于cur的index
                left--;
            }
            int right = i;
            while (right + 1 < heights.length && heights[right + 1] >= cur) { //右边第一个小于cur的index
                right++;
            }
            int area = cur * (right - left + 1);
            res = Math.max(res, area);
        }
        return res;      
        
    }
}

/* Mono Stack： https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
两种 corner case
1. 弹栈的时候，栈为空；
2. 遍历完成以后，栈中还有元素；
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int[] h = Arrays.copyOf(heights, n + 1); //2. 遍历完成以后，栈中还有元素；
        int area = 0;
        for (int i = 0; i <= n; i++) {
            while(!stack.isEmpty() && h[stack.peek()] > h[i]) { // 1. 弹栈的时候，栈为空；
                int height = h[stack.pop()];
                area = Math.max(area, height * (stack.isEmpty() ? i : (i - stack.peek() - 1))); // 1. 弹栈的时候，栈为空；
            }
            stack.push(i);
        }
        return area;
    }
}


class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        if (heights == null || heights.length == 0) {
            return res;
        }
        int n = heights.length;
        int[] heights1 = new int[n + 2];
        System.arraycopy(heights, 0, heights1, 1, n);
        
        //首尾都加一个最小值，保证栈不可能为空，并且除首位外，其他元素全被pop
        heights1[0] = 0;
        heights1[n + 1] = 0;
        stack.push(0); //相当于把index = 0先放进stack中
        
        for(int i = 1; i < n + 2; i++) { // 0已经放入stack中了，所以从1开始
            //递增

            while(heights1[stack.peek()] > heights1[i]) {
                int h = heights1[stack.pop()];
                int area = h * (i - stack.peek() - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        
        return res;
    }
}