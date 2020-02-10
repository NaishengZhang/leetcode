class Solution {
    private Set<Integer> col = new HashSet<>();
    private Set<Integer> sum = new HashSet<>();
    private Set<Integer> diff = new HashSet<>();
    private List<List<String>> res = new ArrayList<>();
    private int n = 0;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(0, col, sum, diff, board);
        return res;
    }
    
    private void dfs(int level, Set<Integer> col, Set<Integer> sum, Set<Integer> diff, char[][] board) {
        if (level == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(String.valueOf(board[i]));
            }
            res.add(temp);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (col.contains(i) || sum.contains(i + level) || diff.contains(level - i)){
                continue;
            }
            col.add(i);
            sum.add(level + i);
            diff.add(level - i);
            board[level][i] = 'Q';
            
            dfs(level + 1, col, sum, diff, board);
            
            col.remove(i);
            sum.remove(level + i);
            diff.remove(level - i);
            board[level][i] = '.';
        }
    }
}