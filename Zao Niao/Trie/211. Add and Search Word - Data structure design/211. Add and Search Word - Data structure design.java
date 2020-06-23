class WordDictionary {

    private class Node {
        
        private boolean isWord;
        private Node[] next;
        
        public Node() {
            isWord = false;
            next = new Node[26];
        }
        
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(word, root, 0);
    }
    
    private void addWord(String word, Node cur, int index) {
        if (index == word.length()) {
            cur.isWord = true;
            return;
        }
        char c = word.charAt(index);
        if (cur.next[c - 'a'] == null) {
            cur.next[c - 'a'] = new Node();
        }
        cur = cur.next[c - 'a'];
        addWord(word, cur, index + 1);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root, 0);
    }
    
    private boolean search(String word, Node cur, int index) {
        if (index == word.length()) {
            return cur.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
            return search(word, cur, index + 1);
        } else {
            for (Node node : cur.next) {
                if (node != null && search(word, node, index + 1)) {
                   return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */