// dfs 
class Solution {
    public int findCircleNum(int[][] M) {
        
        int n = M.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, M, visited);
                count++;
            }
        }
        return count;
    }
    
    private void dfs(int i, int[][] M, boolean[] visited) {
        
        for (int j = 0; j < M.length; j++) {
            if(!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                dfs(j, M, visited);
            }
        }
    }
}

// time: O(n^2) space: O(n) 有path compress 没有union by rank
class Solution {
    private int[] parent;
    public int findCircleNum(int[][] m) {
        int n = m.length; // n students
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (m[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = find(i); // 关键点，因为union完之后，不是每个值都直接指向root，需要做一次find更新所有值
            set.add(parent[i]);
        }
        return set.size();
    }
    private void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            parent[fx] = fy;
        }
    }
    private int find(int id) {
        if (parent[id] != id) {
            parent[id] = find(parent[id]);
        }
        return parent[id];
    }
}

//time： O(n^2) space: O(n) path compress+union by rank
class Solution {
    private int[] parent;
    private int[] rank;
    public int findCircleNum(int[][] m) {
        int n = m.length; // n students
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        rank = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (m[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = find(i); // 关键点，因为union完之后，不是每个值都直接指向root，需要做一次find更新所有值
            set.add(parent[i]);
        }
        return set.size();
    }
    private void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (rank[fx] > rank[fy]) {
                parent[fy] = parent[fx];
            } else if (rank[fx] < rank[fy]) {
                parent[fx] = parent[fy];
            } else {
                parent[fx] = fy;
                rank[fy]++;
            }
        }
    }
    // //没有路径压缩
    // private int find(int id) {
    //     while (parent[id] != id) {
    //         id = parent[id];
    //     }
    //     return id;
    // }
    private int find(int id) { //路径压缩
        if (parent[id] != id) {
            parent[id] = find(parent[id]);
        }
        return parent[id];
    }
}