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

- **记忆化搜索（DP）**

  









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





### [[二分/排序/搜索\]](https://www.1point3acres.com/bbs/forum.php?mod=forumdisplay&fid=84&filter=typeid&typeid=199) **总结几个BFS 的pattern**

https://www.1point3acres.com/bbs/thread-591930-1-1.html

###**Backtracking**

https://www.1point3acres.com/bbs/thread-589136-1-1.html



https://www.1point3acres.com/bbs/thread-441415-1-1.html



TreeMap

https://www.1point3acres.com/bbs/thread-586249-1-1.html

