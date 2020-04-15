//time: O(n2) n is the numbers of events booked. memory: O(n)
// n * n 其中一个n是for循环
class MyCalendar {
    List<int[]> calendar;
    public MyCalendar() {
        calendar = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        
        for (int[] x : calendar) {
            if (x[0] < end && x[1] > start) {
                return false;
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */


//如果上边的calendar是有序的话，那么查找(start, end)就变成O(logn) => Binary search

class MyCalendar {
    private TreeMap<Integer, Integer> map; // start, end
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer next = map.ceilingKey(start);
        Integer pre = map.floorKey(start);
        if ((pre == null || start >= map.get(pre)) && (next == null || end <= next)) {
            map.put(start, end);
            return true;
        }
        return false;
    }
}