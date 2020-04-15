class Solution {
    public boolean confusingNumber(int N) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        
        int num = 0;
        int temp = N;
        while (temp > 0) {
            int cur = temp % 10;
            if (!map.containsKey(cur)) {
                return false;
            }
            num = num * 10 + map.get(cur);
            temp /= 10;
        }
        
        return num != N;
    }
}