/*
Backtracking
we would walk through the 2D grid. At each step, index start from 0, and dfs to find if there is a match sequence.
n the worst case, the total number of invocation would be the number of nodes in a full 4-nary tree.
Time: O(n * 4^l) n is number of cells in the grid, l is the length of word.
Spcae: O(l) => stack.
Two different way to mark the path: visited[i][j] = true or board[i][j] = '*'
*/
class Solution {
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // up, left, right, down
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               if (dfs(board, word, 0, i, j)) {
                   return true;
               }
            }
        }
        return false;
        
    }
    
    private boolean dfs(char[][] board, String word, int index, int x, int y) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != word.charAt(index)) {
            return false;
        }
        boolean found = false;
        char temp = board[x][y];
        board[x][y] = '*';
        for (int[] dir : direction) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            found = found || dfs(board, word, index + 1, nextX, nextY);
            if (found) {
                break;
            }
        }
        board[x][y] = temp;
        return found;
    }
}

class Solution {
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // up, left, right, down
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               if (dfs(board, word, 0, i, j, visited)) {
                   return true;
               }
            }
        }
        return false;
        
    }
    
    private boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited ) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        boolean found = false;
        visited[x][y] = true;
        for (int[] dir : direction) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            found = found || dfs(board, word, index + 1, nextX, nextY, visited);
            if (found) {
                break;
            }
        }
        visited[x][y] = false;
        return found;
    }
}