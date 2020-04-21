// bfs
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '*';
                    queue.offer(new int[]{i, j});
                    bfs(grid, queue);
                }
            }
        }
        return count;
    }
    
    private void bfs(char[][] grid, Queue<int[]> queue) {
        int[][] dir = {{0,1}, {0,-1}, {1,0},{-1,0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dir) {
                    int next0 = cur[0] + d[0];
                    int next1 = cur[1] + d[1];
                    if (next0 < 0 || next1 < 0 || next0 >= grid.length 
                        || next1 >= grid[0].length || grid[next0][next1] != '1') {
                        continue;
                    }
                    queue.offer(new int[]{next0, next1});
                    grid[next0][next1] = '*';
                }
            }
        }
    }
    
}

//dfs
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int res = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res += grid[i][j] - '0';
                dfs(grid, i, j, n, m);
            }
        }
        return res;
    }
    
    private void dfs(char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j, n, m);
        dfs(grid, i - 1, j, n, m);
        dfs(grid, i, j - 1, n, m);
        dfs(grid, i, j + 1, n, m);
    }
}