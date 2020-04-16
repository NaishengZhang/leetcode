// Time: O(NM) Running leetcode84 on each row takes M (length of each row) time. This is done N times
// space: O(M)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            area = Math.max(largestRectangleArea(heights), area);
        }
        return area;
        
    }
    
    private int largestRectangleArea(int[] heights) {
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