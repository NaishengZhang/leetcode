// Original Dijkstra： Time:O(v^2)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        int x = times.length;
        int y = times[0].length;
        //构建邻接矩阵
        int[][] graph = new int[n + 1][n + 1]; //长度是n+1 因为node取值范围是1 to n
        // graph = -1 说明两个node没有相连 注意 fill不行填充二维数组 ❌Arrays.fill(graph, -1); 错误
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = -1;
            }
        }
        
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        
        //构建源点到每个点的距离 k -> i  from 1 to n
        int[] dis = new int[n + 1];
        Arrays.fill(dis, -1); //dis = -1说明源点到这个点没访问过， 或者访问不到
        for (int i = 1; i <= n; i++) { //初始化 源点到源点 到各个点的距离
            dis[i] = graph[k][i];
        }
        dis[k] = 0;
        
        //构建visited,表示该点是否找到最短距离
        boolean[] visited = new boolean[n + 1]; // 确定源点到每个点的最短距离后 true；
        visited[k] = true;
        
        for (int i = 1; i <= n - 1; i++) { // 每循环一次就可以找到一个最短距离 所以是循环n-1次
            int minDis = Integer.MAX_VALUE; //先找源点到当前可选列表中距离最短的点，greedy
            int minIdx = 1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && dis[j] != -1 && dis[j] < minDis) { // dis[j] != -1意思是要找当前已经知道距离的点。
                    minDis = dis[j];
                    minIdx = j;
                }
            }
            
            visited[minIdx] = true; // 源点到minIdx的最短距离已经找到了。
            //从minIdx中榨取信息，也就是要更新一下 从源点经过minIdx到其他点的距离 是否比 源点直接到minIdx短。
            for (int j = 1; j <= n; j++) {
                if (graph[minIdx][j] != -1) { //更新minIdx相邻的点
                    if (dis[j] != -1) {
                        dis[j] = Math.min(dis[j], dis[minIdx] + graph[minIdx][j]);
                    } else {
                        dis[j] = dis[minIdx] + graph[minIdx][j];
                    }
                    
                }
            }
        }
        
        int max = 0;
        
        for (int i = 1; i <= n; i++) { //注意是从1开始
            if (dis[i] == -1) {
                return -1;
            } else {
                max = Math.max(max, dis[i]);
            }
        }
  
        return max;
    }
}


// heap



// dfs
// 复杂度https://leetcode.com/articles/network-delay-time/
class Solution {
    Map<Integer, Integer> dis = new HashMap<>(); // 存放每个端点对应的最短距离
    
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for (int[] time : times) {
            if (!graph.containsKey(time[0])) {
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> (a[1] - b[1])); //按照 node到源点的距离 由小到大排序；
        }
        //初始化distance
        for (int node = 1; node <= N; node++) {
            dis.put(node, Integer.MAX_VALUE);
        }
        
        dfs(graph, K, 0);
        
        int max = 0;
        for (int num : dis.values()) {
            if (num == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, num);
        }
        return max;
    }
    
    private void dfs(Map<Integer, List<int[]>> graph, int node, int distance) { // distance是指传到当前node经过的距离
        if (dis.get(node) <= distance) { //如果发现要dfs的node当前的距离已经大于之前找到的最短距离，剪枝
            return;
        }
        dis.put(node, distance);
        //从当前node查找相邻的其他node
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node)) {
                dfs(graph, info[0], distance + info[1]);
            }
        }
        
    }
}