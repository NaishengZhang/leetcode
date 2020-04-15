// Time o(nklogk), because sort each string in o(klogk) time;
// Memory o(nk)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String s1 = String.valueOf(c);
            if (!map.containsKey(s1)) {
                map.put(s1, new ArrayList<>());
            }
            map.get(s1).add(s);
        }
        
        return new ArrayList<>(map.values());
        
    }
}

// Time O(nk) n is the length of strs, k is the maximum length of a string in strs
// memory O(nk)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int x : count) {
                sb.append("#");
                sb.append(x);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}