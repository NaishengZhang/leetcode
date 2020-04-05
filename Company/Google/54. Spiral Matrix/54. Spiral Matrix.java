// O(n) O(n)

//         top-  -  -
//     left  ｜6, 7,｜ right
//         bottom    -------

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // corner case: null, 0,0
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        } 
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, top = 0, bottom = m - 1, right = n - 1;
        
        // break condition: top = bottom || left = right;
        while (true) {
            // left -> right
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            if (left > right || top > bottom) {
                break;
            }
            
            //top -> bottom
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (left > right || top > bottom) {
                break;
            }
            //right -> left
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            if (left > right || top > bottom) {
                break;
            }
            //bottom -> top
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right || top > bottom) {
                break;
            }
            
        }
        
        return res;
    }
}

// time: O(n*m) Memory: O(n*m)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // corner case: null, 0,0
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        } 
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int d = 0;
        int[] dy = {1,0,-1,0};
        int[] dx = {0,1,0,-1};
        int x = 0;
        int y = 0;
        for (int k = 0; k < m*n; k++) {
            res.add(matrix[x][y]);
            visited[x][y] = true;
            int i = x + dx[d], j = y + dy[d];
            
            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == true) {
                d = (d + 1) % 4;
                i = x + dx[d];
                j = y + dy[d];
            }
            x = i;
            y = j;
        }
        return res;
    }
}
