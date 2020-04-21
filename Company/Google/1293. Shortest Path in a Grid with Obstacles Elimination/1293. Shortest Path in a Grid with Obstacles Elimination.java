// bfs 求迷宫最短路，without Obstacles Elimination
class Solution {
    public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        Queue<int[]> queue = new LinkedList<>(); // int[] 坐标
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        if (grid[0][0] == 0) {
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;
        } else {
            return -1;
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dir) {
                    int next0 = cur[0] + d[0];
                    int next1 = cur[1] + d[1];
                    if (next0 == n - 1 && next1 == m - 1) {
                        return res + 1;
                    }
                    if (next0 < 0 || next1 < 0 || next0 >= n || next1 >= m 
                        || grid[next0][next1] == 1 || visited[next0][next1]) {
                        continue;
                    }

                    queue.offer(new int[]{next0, next1});
                    visited[next0][next1] = true;
                }
            }
            res++;
        }
        return -1;
    }
}

// with Obstacles Elimination
// visited由二维的变成三维，queue中记录的tuple也变为三个数，增加了目前消除了几个obstacles
// 此问题主要在于判断是否要剪去queue要加入的点，也就是说如果这个点 已经先被访问过 并且 经过obstacles数量相同，那么就将这个点之后的路径都剪去
// 因为经过相同数量的obstacle，然后在这个点之后的路径选择都是相同的，但是后被访问到就说明要加入的点以及不可能是最短路径了

// 对比 without Obstacles Elimination，只要visited[x][y] == true就 continue，也就是queue不加这个点，
// 因为这种情况下，不同路径，经过的相同点之后的选择是一样的，而with情况不一样（k不同），那么就可以肯定如果再访问之前访问过的点，就一定不是最短路径，那么剪去这种情况

// TimeL O(mnk)
class Solution {
    public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        Queue<int[]> queue = new LinkedList<>(); // int[] 坐标 + k
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] visited = new boolean[n][m][k + 1];
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        queue.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        int res = 0;
        
        if (n == 1 && m == 1) {
            return 0;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dir) {
                    int next0 = cur[0] + d[0];
                    int next1 = cur[1] + d[1];
                    int next2 = cur[2]; 
                    if (next0 == n - 1 && next1 == m - 1) {
                        return res + 1;
                    }
                    if (next0 < 0 || next1 < 0 || next0 >= n || next1 >= m) {
                        continue;
                    }
                    
                    if (grid[next0][next1] == 1) {//处理障碍物
                        if (cur[2] < k) {//还有可以消除的障碍物
                            next2++;
                        } else {
                            continue;
                        }
                    }
                    if (!visited[next0][next1][next2]) {
                        queue.offer(new int[]{next0, next1, next2});
                        visited[next0][next1][next2] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
}

//将上边这种方法的tuple包装成一个point类
class Solution {
    public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        Queue<Point> queue = new LinkedList<>(); // int[] 坐标 + k
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] visited = new boolean[n][m][k + 1];
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        queue.offer(new Point(0, 0, 0));
        visited[0][0][0] = true;
        int res = 0;
        
        if (n == 1 && m == 1) {
            return 0;
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                for (int[] d : dir) {
                    int next0 = cur.x + d[0];
                    int next1 = cur.y + d[1];
                    int next2 = cur.z; 
                    if (next0 == n - 1 && next1 == m - 1) {
                        return res + 1;
                    }
                    if (next0 < 0 || next1 < 0 || next0 >= n || next1 >= m) {
                        continue;
                    }
                    
                    if (grid[next0][next1] == 1) {//处理障碍物
                        if (cur.z < k) {//还有可以消除的障碍物
                            next2++;
                        } else {
                            continue;
                        }
                    }
                    if (!visited[next0][next1][next2]) {
                        queue.offer(new Point(next0, next1, next2));
                        visited[next0][next1][next2] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
    
    class Point{
        int x;
        int y;
        int z;
        
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
    }
}