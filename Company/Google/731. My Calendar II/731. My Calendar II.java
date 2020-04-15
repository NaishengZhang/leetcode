//https://www.youtube.com/watch?v=rRMdxFA-8G4&t=275s
// Time: 1. for loop中遍历values：O(n) 2. put: O(logn) => 整个for loop是 O(n + logn) => O(n)
// n个events => O(n^2)
class MyCalendarTwo {
    TreeMap<Integer, Integer> calendar; // Time, Frequency
    public MyCalendarTwo() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        
        int active = 0;
        for (int x : calendar.values()) {
            active += x;
            if (active > 2) {
                calendar.put(start, calendar.getOrDefault(start, 0) - 1);
                calendar.put(end, calendar.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */