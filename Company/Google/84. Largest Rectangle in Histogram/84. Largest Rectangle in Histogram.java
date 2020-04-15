//best https://zhuanlan.zhihu.com/p/26465701
//https://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
//https://www.cnblogs.com/grandyang/p/4322653.html

// brute force O(n^2)
class Solution {
    public int largestRectangleArea(int[] heights) {
        
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int length = heights.length;
        int res = 0;
        for (int i = 0; i < length; i++) {
            int min = heights[i];
            for (int j = i; j >= 0; j--) {
                min = Math.min(min, heights[j]);
                res = Math.max(res, min * (i - j + 1));
            }
        }
        return res;
    }
}

// O(n)优化brute force：当heights[i]大于heights[i + 1]时再往回计算最大面积，因为如果i高度小于i+1高度，那么面积最大值一定在i+1之后
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int length = heights.length;
        int res = 0;
        for (int i = 0; i < length; i++) {
            int min = heights[i];
            if (i == length - 1 || heights[i] > heights[i + 1]) {
                for (int j = i; j >= 0; j--) {
                    min = Math.min(min, heights[j]);
                    res = Math.max(res, min * (i - j + 1));
                }
            }
        }
        return res;
    }
}

// 对于这个做法的关键就是， 找到任意一个column顶 最左边的边界 和最右边的边界，这时候用到递增monotonic stack的性质
// 一个数入栈说明他比栈内所有数都大，也就是说这个数底下的值是左边界，而一个数出栈说明 新的数是next greater，也就是找到了右边界
// 这时 以这一个column为顶的最大矩形也就找到了。一个技巧是在array最后加一个0，那么就可以使栈中的数都出栈。
// Time: O(n) n numbers are pushed and popped => 2n; Memory: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int[] h = new int[n + 1];
        h = Arrays.copyOf(heights, n + 1);
        int area = 0;
        for (int i = 0; i <= n; i++) {
            while(!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int height = h[stack.pop()];
                area = Math.max(area, height * (stack.isEmpty() ? i : (i - stack.peek() - 1)));
            }
            stack.push(i);
        }
        return area;
    }
}






