class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int r = 0;
        int[] sMap = new int[256];
        int[] pMap = new int[256];
        for (char c : p.toCharArray()) {
            pMap[c]++;
        }
        
        while (r < s.length()) {
            char c = s.charAt(r);
            sMap[c]++;
            if (r > p.length() - 1) {
                sMap[s.charAt(r - p.length())]--;    
            }
            if (Arrays.equals(sMap, pMap)) {
                res.add(r - p.length() + 1);
            }
            r++;
        }
        return res;
    }
}