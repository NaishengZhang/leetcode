//hashsets
class Solution {
    private int res = 0;
    private Set<Integer> col = new HashSet<>();
    private Set<Integer> diff = new HashSet<>();
    private Set<Integer> sum = new HashSet<>();
    public int totalNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        dfs(0,n);
        return res;
    }
    private void dfs(int level, int n) {
        if (level == n) {
            res++;
            return;
        }   
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !diff.contains(level - i) 
                && !sum.contains(level + i)) {
                col.add(i);
                diff.add(level - i);
                sum.add(level + i);
                dfs(level + 1, n); 
                col.remove(i);
                diff.remove(level - i);
                sum.remove(level + i);
            }
        } 
    }
}


//integer array
class Solution {
    private int res = 0;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        boolean[] col = new boolean[n];
        boolean[] s1 = new boolean[2*n];
        boolean[] s2 = new boolean[2*n];
        dfs(0, n, col, s1, s2);
        return res;
    }
    
    private void dfs(int row, int n, boolean[] col, boolean[] s1, boolean[] s2) {
        if (row == n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col[i] == true || s1[row + i] == true || s2[n + row - i] == true) {
                continue;
            }
            col[i] = true;
            s1[row + i] = true;
            s2[n + row - i] = true;
            dfs(row + 1, n, col, s1, s2);
            col[i] = false;
            s1[row + i] = false;
            s2[n + row - i] = false;
        }
        
    }
}


//bit operation
// (1<<j) & s 表示只取s的第j位
// (1<<j) | s 表示把s的第j位置为1