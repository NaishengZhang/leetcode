// https://www.acwing.com/problem/content/description/167/
import java.util.*;

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
