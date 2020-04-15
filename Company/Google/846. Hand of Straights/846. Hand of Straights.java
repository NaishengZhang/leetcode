class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        
        if (hand.length % W != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); // Card, Frequency
        
        for (int x : hand) {
            map.put(x, map.getOrDefault(x, 0) + 1); // O(NLogN)
        }
        
        while (map.size() > 0) {
            Integer key = map.firstKey();
            for (int i = key; i < key + W; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                if (map.get(i) == 1) {
                    map.remove(i);
                } else {
                     map.put(i, map.get(i) - 1);
                }
            }
        }
        
        return true;
    }
}

// Time: O(MlogM + MW) M is the number of different cards;
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        
        if (hand.length % W != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); // Card, Frequency
        
        for (int x : hand) {
            map.put(x, map.getOrDefault(x, 0) + 1); // O(NLogN)
        }
        
        for (int x : map.keySet()) {
            if (map.get(x) > 0) {
                for (int i = W - 1; i >= 0; --i) {
                    if (map.getOrDefault(x + i, 0) < map.get(x)) {
                        return false;
                    }
                    map.put(x + i, map.get(x + i) - map.get(x));
                }
            }
        }
        return true;
    }
}

// Follow Up
// We just got lucky AC solution. Because W <= 10000.
// What if W is huge, should we cut off card on by one?











