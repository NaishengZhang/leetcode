// Union Find time: O(N)
class Solution {
    public static final int MAX = 1000;
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            throw new AssertionError();
        }
        
        UF uf = new UF(MAX + 1);
        
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[2];
    }
    
    class UF {
        private int[] parent;
        private int[] rank; // 存放root的秩
        
        public UF(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }
        
        public int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }
        
        public boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) {
                return false;
            }
            if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else {
                parent[yr] = xr;
                rank[xr]++;
            }
            return true;
        }
        
    }
}