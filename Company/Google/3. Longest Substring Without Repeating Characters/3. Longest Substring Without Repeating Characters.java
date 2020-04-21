// sliding window, time:O(2n) In the worst case each character will be visited twice by ii and jj.
// space: O(min(n, m))  The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet m.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int i = 0, j = 0, res = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                res = Math.max(j - i + 1, res);
                j++;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return res;
        
    }
}

// 优化hashmap time: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, j = 0, res = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        
        while (i < n && j < n) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i); // 这里必须是要判断一下
                // i = map.get(s.charAt(j));
            }
            res = Math.max(j - i + 1, res);
            map.put(s.charAt(j), j + 1);
            j++;
        }
        return res;
    }
}