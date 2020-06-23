/*
iteratively insert
*/
class Trie {
    class Node {
        private boolean isWord;
        Node[] child;

        public Node() {
            isWord = false;
            child = new Node[26];
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) { // iteration
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new Node();
            }
            cur = cur.child[c - 'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.child[c - 'a'];
            if (cur == null) {
                return false;
            }
        }
        return cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.child[c - 'a'];
            if (cur == null) {
                return false;
            }
        }
        return true;
    }
}

/*
recursively insert
*/
class Trie {

    private class Node {
        boolean isWord;
        Node next[];
        public Node() {
            isWord = false;
            next = new Node[26];
        }
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(word, root, 0);
    }
    
    private void insert(String word, Node cur, int index) { // recursion
        if (index == word.length()) {
            cur.isWord = true;
            return;
        }
        char c = word.charAt(index);
        if (cur.next[c - 'a'] == null) {
            cur.next[c - 'a'] = new Node();
        }
        cur = cur.next[c - 'a'];
        insert(word, cur, index + 1);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next[c -'a'] == null) {
                return false;
            }
            cur = cur.next[c -'a'];
        }
        return cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next[c -'a'] == null) {
                return false;
            }
            cur = cur.next[c -'a'];
        }
        return true;
    }
}
