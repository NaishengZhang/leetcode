// O(mnk) k is the number of gates, 每个gate都做一次完整的bfs，
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j);
                }
            }
        }
    }
    
    private void dfs(int[][] rooms, int i, int j) { // gate => i,j
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        boolean[][] visited = new boolean[m][n];
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                for (int[] d : dir) {
                    int nextX = cur[0] + d[0];
                    int nextY = cur[1] + d[1];
                    if (nextX < 0 || nextY < 0 || nextX >=m || nextY >= n 
                        || visited[nextX][nextY] || rooms[nextX][nextY] == -1 || rooms[nextX][nextY] < step) continue;
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                    rooms[nextX][nextY] = step;
                }
            }
        }
    }
    
}

//O(mn) initiate BFS from all gates at the same time. Bi-direction BFS
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        dfs(rooms);
    }
    
    private void dfs(int[][] rooms) { // gate => i,j
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        // boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {//gates
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                for (int[] d : dir) {
                    int nextX = cur[0] + d[0];
                    int nextY = cur[1] + d[1];
                    if (nextX < 0 || nextY < 0 || nextX >=m || nextY >= n 
                        || rooms[nextX][nextY] != Integer.MAX_VALUE) continue;
                    rooms[nextX][nextY] = step;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
    
}
