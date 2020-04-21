// time O(k *logmn) logmn是树的高度 where k is the length of the positions
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> res = new ArrayList<>();
        int[] roots = new int[m * n];
        int count = 0;
        Arrays.fill(roots, -1);
        int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for (int[] position : positions) {
            int cur = position[0] * n + position[1];
            if (roots[cur] != -1) { //这个点已经访问过了
                res.add(count);
                continue;
            }
            roots[cur] = cur;
            count++;
            for (int[] d : dir) {
                int x = position[0] + d[0];
                int y = position[1] + d[1];
                int next = x * n + y;
                if (x < 0 || y < 0 || x >= m || y >= n || roots[next] == -1) { //roots[next] == -1说明这个点目前是water
                    continue;
                }
                int root1 = find(roots, cur), root2 = find(roots, next);
                if (root1 != root2) {
                    roots[root2] = root1; // 相当于union
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    private int find (int[] roots, int id) {
        return id == roots[id] ? id : find(roots, roots[id]);
    }
}