//hashtable, templet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        int[] hash = new int[256];
        
        int head = 0, res = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (hash[s.charAt(i)] < 2) {
                hash[s.charAt(i)]++;
            }
            while (hash[s.charAt(i)] > 1) {
                hash[s.charAt(head)]--;
                head++;
            }
            res = Math.max(res, i - head + 1);
        }
        return res;
    }
}

// HashSet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int max = 0, i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return max;
    }
}

// HashMap
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, head = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                head = Math.max(map.get(s.charAt(i)) + 1, head);
            } 
            max = Math.max(max, i - head + 1);
            map.put(s.charAt(i), i);
        }
        return max;
    }
}