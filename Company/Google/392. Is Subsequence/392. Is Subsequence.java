// Two pointer time: O(n + m)
class Solution {
    public boolean isSubsequence(String s, String t) {
        
        int i = 0, j = 0;
        int n = s.length();
        int m = t.length();

        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }  
            j++;
        }
        return i == n;
    }
}

// follow up: use hashmap 存储 t中每个字符的index
class Solution {
    public boolean isSubsequence(String s, String t) {
        // follow up
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                map.get(c).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(c, list);
            }
        }
        
        int pre = -1;
        for (int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            int insertion = Collections.binarySearch(map.get(c), pre);
            if (insertion < 0) {
               insertion = -insertion - 1;
            }
            if (insertion == map.get(c).size()) {
                return false;
            }
            pre = map.get(c).get(insertion) + 1;
        }
        return true;
    }
}
