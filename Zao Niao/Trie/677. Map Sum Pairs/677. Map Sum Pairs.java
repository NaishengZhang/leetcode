/*
用hashmap保存每个key的val，当新的key insert时，先计算一下和之前的差值delta，之后在遍历node时，node.val += delta
*/
class MapSum {

    private class Node {
        private int val;
        private Node[] next;
        public Node() {
            val = 0;
            next = new Node[26];
        }
    }
    HashMap<String, Integer> map;
    private Node root;
    
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap();
        root = new Node();
    }
    
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        Node cur = root;
        cur.val += delta;
        for (char c : key.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node();
            } 
            cur = cur.next[c - 'a'];
            cur.val += delta;
        }
        
    }
    
    public int sum(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.next[c - 'a'];
            if (cur == null) return 0;
        }
        return cur.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */


/*
正常的insert，但是sum时，先找到prefix的最后一个node，然后从这个node开始遍历下边所有的val再加一起。
*/
class MapSum {

    private class Node {
        private int val;
        private Node[] next;
        public Node() {
            val = 0;
            next = new Node[26];
        }
    }
    HashMap<String, Integer> map;
    private Node root;
    
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap();
        root = new Node();
    }
    
    public void insert(String key, int val) {
        Node cur = root;
        for (char c : key.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node();
            } 
            cur = cur.next[c - 'a'];
        } 
        cur.val = val;
    }
    
    public int sum(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.next[c - 'a'];
            if (cur == null) {
                return 0;
            }
        }
        return sum(cur);
    }
    
    private int sum(Node cur) {
        if (cur == null) {
            return 0;
        }
        int res = cur.val;
        for (Node node : cur.next) {
            if (node == null) {
                continue;
            }
            res += sum(node);
        }
        
        return res;
    }
}