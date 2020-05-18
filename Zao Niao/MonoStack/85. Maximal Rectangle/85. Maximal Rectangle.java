// O(mn) 对matrix每行都计算一次84，注意当matrix[i][j] == ‘0’时，就不能累加到之后的高度。
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int area = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return area;
        }
        
        int[] heights = new int[matrix[0].length];
        
        for (char[] m : matrix) {
            for (int i = 0; i < m.length; i++) {
                if (m[i] == '1') {
                    heights[i] += 1;
                } else {
                    heights[i] = 0;
                }
            }
            area = Math.max(area, largestRectangle(heights));
        }
        return area;
    }
    
    private int largestRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;
        int[] heights1 = Arrays.copyOf(heights, n + 1);
        heights = heights1;
        int res = 0;
        for (int i = 0; i < n + 1; i++) {
            //increase
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int area = h * (stack.isEmpty() ? i : (i -stack.peek() - 1));
                res = Math.max(res, area);
            }
            stack.push(i);
        }

        return res;
    }
}