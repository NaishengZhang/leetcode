// 先构建trie，从board每一个点开始dfs，在dfs的中间要判断当前在的点是否在trie中，然后在dfs的过程中发现word ！= null，就加入res中

class Solution {
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // up, left, right, down
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, words, res, trie.root, i, j);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board, String[] words, List<String> res, Node node, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }
        char c = board[x][y];
        if (c == '*' || node.child[c - 'a'] == null) {
            return;
        }
        
        node = node.child[c - 'a'];
        
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[x][y] = '*';
        for (int[] d : direction) {
            dfs(board, words, res, node, x + d[0], y + d[1]);
        }
        board[x][y] = c;
    }
    
    
    class Node {
        private String word;
        private Node[] child;
        public Node() {
            child = new Node[26];
        }
    }

    class Trie {
        private Node root;
        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                if (cur.child[c - 'a'] == null) {
                    cur.child[c - 'a'] = new Node();
                }
                cur = cur.child[c - 'a'];
            }
            cur.word = word;
        }
    }
}

