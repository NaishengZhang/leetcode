

### DFS

#### DFS剪枝技巧

* **优化搜索顺序**

  - 不同搜索顺序会产生不同的搜索树形态，选择产生分支较少的搜索顺序（也就是最容易达到递归边界的顺序）。

  - 总体来看，不管怎么样变化搜索顺序，在理论最坏情况下都会遍历完所有情况，这样看起来好像优化搜索顺序就没用了。但是，关键点在于搜索问题一般很难达到理论最坏情况，尽量先搜索出来一个可行解，在之后的搜索中，就可以利用这个可行解来做最优性剪枝。

* **排除等效冗余**

  在搜索过程中，如果能判断从搜索树当前节点上沿着几条不同分支到达的子树是相同的，那么只需对其中一条分支执行搜索。

* **可行性剪枝**

  也叫做上下界剪枝，指在搜索过程中，及时对当前状态进行检查，若发现分支已无法到达递归边界，就进行回溯。

* **最优性剪枝**

  若当前花费的代价已超过当前搜索到的最优解，无论再优秀的策略到达递归边界，都不可能更新答案，那么就可以停止对当前分支的搜索，进行回溯。

- **记忆化搜索 DFS + memo（DP）**

  

**Example：**

https://www.acwing.com/problem/content/description/167/

主要思想是先对猫的重量排序，在从最重的猫开始往车里放。

最开始状态是 0号猫 0个车，下一层把0号猫，装到1号车里。接着下一层是考虑1号猫装到1号车或者再重新开一辆车。

```java
import java.util.*;
// dfs要怎么考虑？
// 1. dfs 的顺序  （1~ 一般是从1 or 0两种情况，即选或不选）
// 2. dfs 的递归状态，即参数
// 3. 剪枝，一个dfs常用的原则是“有限考虑决策少的元素”->"使根部的分支较少，子树变少，搜索空间大幅减少" 
// （3~ 本题中优先考虑比较重的猫，因为选择比较少，很可能要新开车）
public class Main{

    private static int answer;
    private static int[] cars = new int[18];
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        answer = n;
        List<Integer> cats = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {
            cats.add(scanner.nextInt());
        }
        System.out.println(solve(cats, w, n));
        
    }
    
    static int solve(List<Integer> cats, int w, int n){
        //优化搜索顺序:先从最重的往车里放可以减少之后的分支。
        cats.sort((a,b)->(b-a));
        dfs(0, 0, cats, w);
        return answer;
    }
    
    static void dfs(int u, int v, List<Integer> cats, int max){ // u是第几个猫，v是有几辆车
        if (v > answer) return;
        if (u == cats.size()){
            answer = v;
            return;
        }
        int w = cats.get(u);
        for (int i = 0 ; i < v ;i++){
            if (cars[i] + w > max) continue;

            cars[i] += w;
            dfs(u+1, v, cats, max);
            cars[i] -= w;
        }
        cars[v] += w;
        dfs(u+1, v+1, cats, max);
        cars[v] -= w;
    }
}

```

### DP

#### Fibonacci Sequence.

```java
   public int fib(int n) {
        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }
```

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfwdd681ehj31ty0to1e8.jpg)

存在很多重复计算

用memo存储计算过的答案

- dfs+memo 记忆化搜索 自顶向下

```java
    private int fib(int n, int[] memo){ // Arrays.fill(memo, -1);
        if(n == 0)
            return 0;

        if(n == 1)
            return 1;

        if(memo[n] == -1) // 如果之前访问过，那么就直接用memo(n)的值。
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);

        return memo[n];
    }
```

- DP 自下而上，先从0 和 1开始往上层计算。

```java
    public int fib(int n){
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2 ; i <= n ; i ++)
            memo[i] = memo[i - 1] + memo[i - 2];

        return memo[n];
    }
```



![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfwdqsmdblj31990u04ap.jpg)

#### Climb Stairs

https://leetcode.com/problems/climbing-stairs/

- Recursion: TLE

```java
class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n-2);
    }
}
```

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfwe4llpj3j318q0o0wra.jpg)

- DFS + memo: 自顶向下

```java
class Solution {
    
    public int climbStairs(int n) {
        
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return climbStairs(n, memo);
        
    }
    
    private int climbStairs(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        
        if (memo[n] == -1) {
            memo[n] = climbStairs(n - 1, memo) + climbStairs(n-2, memo);
        }
        
        return memo[n];
    }
}
```

- DP 自下而上

```java
class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }   
        return memo[n];
    }
}
```



#### 120. Triangle

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfx1tv3l05j30bs09cmxn.jpg)

- **Recursion top-down TLE O(2^n)**

  对于2来说，计算数字之和最小就是到3 和 4的数字之和最小。但是求3 和 4路径之和时，会重复计算5路径之和。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {   
        return helper(0, 0, triangle);
    }
    private int helper(int level, int i, List<List<Integer>> triangle) {
        if (level == triangle.size() -1) {
            return triangle.get(level).get(i);
        }
        int left = helper(level + 1, i, triangle);
        int right = helper(level + 1, i + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(i);
    }
}
```

- **DFS + Memo**

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        Integer[][] memo = new Integer[n][n];
        return helper(0, 0, triangle, memo);
    }
    private int helper(int level, int i, List<List<Integer>> triangle, Integer[][] memo) {
        if (level == triangle.size() -1) {
            return triangle.get(level).get(i);
        }
        if (memo[level][i] == null) {
            int left = helper(level + 1, i, triangle, memo);
            int right = helper(level + 1, i + 1, triangle, memo);
            memo[level][i] = Math.min(left, right) + triangle.get(level).get(i);
        }
        return memo[level][i];
    }
}
```



#### 343. Integer Break

- brute force, backtracking.

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfx9c1r46nj31h80r0k5r.jpg)

```java
class Solution {
    public int integerBreak(int n) {
        if (n == 1) {
            return 1;
        }
        int res = -1;
        for (int i = 1; i < n; i++) {
            res = Math.max(Math.max(i * integerBreak(n - i), i * (n - i)), res);
        }
        return res;
    }
}
```



可以先想清楚一个题的递归结构后，发现这个结构有重叠子问题 以及 最优子结构，就可以用DP或者记忆化搜索解题。最优子结构就是，通过求子问题的最优解，可以获得原问题的最优解。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfx9gnn8aij31bs0os49o.jpg)

- 记忆化搜索 top-down

```java
class Solution {
    public int integerBreak(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return integerBreak(n, memo);
    }
    public int integerBreak(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = -1;
        for (int i = 1; i < n; i++) {
            res = Math.max(Math.max(i * integerBreak(n - i, memo), i * (n - i)), res);
        }
        memo[n] = res;
        return res;
    }
}
```

- DP bottom-up time:O(n^2)

```java
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(j * dp[i-j], Math.max(dp[i], j * (i - j)));
            }
        }
        return dp[n];
    }
}
```

#### 198. House Robber

- Brute Force 

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxde28uotj31c80ha498.jpg)

```java
class Solution {
    public int rob(int[] nums) {
        return rob(nums, 0);
    }
    
    //考虑抢劫[index, nums.length - 1]的房子所有价值
    private int rob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        int res = 0;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + rob(nums, i + 2));
        }
        return res;
    }
}
```

- 记忆化搜索

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxdgwvgeoj31do0jyqe0.jpg) 



#### 0-1背包

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxg6bsnxtj31fi0meakd.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxg5fatv5j31g80sqqby.jpg)

- Brute force

```java
  // Solution1, Brute force, time O(n^2), f(index, c) 由index和c组成的对 可能有重复计算。
    private static int knapsack1(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) {//没有物品或者背包没有空间
            return 0;
        }
        int res = knapsack1(w, v, index - 1, c);
        if (c >= w[index]) {
            res = Math.max(res, v[index] + knapsack1(w, v, index - 1, c - w[index]));
        }
        return res;
    }
```

- 记忆化搜索 

```java
    // Solution2,记忆化搜索 int[][] memo = new int[N + 1][C + 1];
    private static int knapsack2(int[] w, int[] v, int index, int c, int[][] memo) {
        if (index < 0 || c <= 0) {//没有物品或者背包没有空间
            return 0;
        }
        if (memo[index][c] == -1) {
            memo[index][c] = knapsack2(w, v, index - 1, c, memo);
            if (c >= w[index]) {
                memo[index][c] = Math.max(memo[index][c],
                        v[index] + knapsack2(w, v, index - 1, c - w[index], memo));
            }
        }
        return memo[index][c];
    }
```

- DP

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxi1k1s6rj31gq0u0h1f.jpg)

```java
    // Solution3, DP, Time and Space O(n * c)
    private static int knapsack3(int[] w, int[] v, int index, int c, int[][] dp) {

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == 0) {
                    dp[i][j] = j >= w[i] ? v[i] : 0;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= w[i]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                    }
                }
            }
        }
        int n = dp.length;
        return dp[n - 1][c];
    }
```

- Optimized DP O(2C)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfxirlwfi0j31de0pwk13.jpg)

```java
    // Solution4, Optimize DP
    private static int knapsack4(int[] w, int[] v, int index, int C, int[][] memo) {
        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        for (int j = 0; j <= C; j++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);
        for (int i = 1; i < n; i++)
            for (int j = 0; j <= C; j++) {
                memo[i % 2][j] = memo[(i - 1) % 2][j];
                if (j >= w[i])
                    memo[i % 2][j] = Math.max(memo[i % 2][j], v[i] + memo[(i - 1) % 2][j - w[i]]);
            }

        return memo[(n - 1) % 2][C];
    }
```

比较算法运行完后的dp memo，记忆化搜索只改变了需要用到的位置，dp把每一行每一列的都计算了一遍

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfya2ovl8lj30fs0s6die.jpg)

- Optimize DP. Space O(C)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfy05db6jjj31eu0negrb.jpg)

第i行元素只依赖于i-1行元素，对于dpij只依赖于dpi-1 j和dpi-1 j-w[i]两个元素。

所以只需要一行数组，每次从后往前计算`memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);`因为是从后往前计算，所以更新后的数据不会在被这一行用到。

```java
    // Solution5, Optimize DP. Space O(C)
    private static int knapsack5(int[] w, int[] v, int index, int C, int[] memo) {
        int n = w.length;
        if (n == 0 || C == 0)
            return 0;
        for (int j = 0; j <= C; j++)
            memo[j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++)
            for (int j = C; j >= w[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);

            }
        return memo[C];
    }
```

#### 416. Partition Equal Subset Sum (01背包模型)

- brute force

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return canPartition(nums, nums.length - 1, sum);
    }
    // 使用[0... index]是否可以完全填充一个容量为sum的背包
    private boolean canPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        if (index < 0 || sum < 0) {
            return false;
        }
        return canPartition(nums, index - 1, sum) || canPartition(nums, index - 1, sum - nums[index]);
    }
}
```

以index和sum为数据对 有重复计算

- 记忆化搜索

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int[][] memo = new int[nums.length][sum / 2 + 1]; //memo[i][c] 表示 [0...i] 这些元素 是否可以完全填充一个容量为c的背包
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return canPartition(nums, nums.length - 1, sum / 2, memo);
    }
    
    // 使用[0... index]是否可以完全填充一个容量为sum的背包
    private boolean canPartition(int[] nums, int index, int sum, int[][] memo) {
        if (sum == 0) {
            return true;
        }
        if (index < 0 || sum < 0) {
            return false;
        }
        if (memo[index][sum] == -1) {
            if (canPartition(nums, index - 1, sum, memo) || canPartition(nums, index - 1, sum - nums[index], memo)) {
                memo[index][sum] = 1;
            } else {
                memo[index][sum] = 0;
            }
        }
        return memo[index][sum] == 1;
    }
    
}
```

- DP Space (n * sum/2)

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int c = sum / 2;
        boolean[][] dp = new boolean[n][c + 1];
        for (boolean[] d : dp) {
            Arrays.fill(d, false);
        }
        
        for (int j = 0; j <= c; j++) {
            dp[0][j] = nums[0] == j;
        }
        
        for (int i = 1; i < n; i++) {
          // 注意：这里的for loop不能从后往前遍历，下边优化后可以从后往前遍历。
          // 因为优化后，在j > nums[i]的数组是还保留之前上一行的信息，这里是初始化的false；
            for (int j = 0; j <= c ; j++) {
                dp[i][j] = dp[i - 1][j] || (j >= nums[i] ? dp[i - 1][j - nums[i]] : false);
            }
        }
        return dp[n - 1][c];
    }
}
```



- DP Time(n * sum/2) Space(sum/2)

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        int n = nums.length;
        int c = sum / 2 + 1;
        boolean[] dp = new boolean[c + 1]; 
        Arrays.fill(dp, false);
        
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int j = c; j >= 0; j--) {
                    dp[j] = (nums[0] == j);
                }
            }
            for (int j = c; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[c];
    }
} 
```













### [[二分/排序/搜索\]](https://www.1point3acres.com/bbs/forum.php?mod=forumdisplay&fid=84&filter=typeid&typeid=199) **总结几个BFS 的pattern**

https://www.1point3acres.com/bbs/thread-591930-1-1.html

###**Backtracking**

https://www.1point3acres.com/bbs/thread-589136-1-1.html



https://www.1point3acres.com/bbs/thread-441415-1-1.html



TreeMap

https://www.1point3acres.com/bbs/thread-586249-1-1.html

