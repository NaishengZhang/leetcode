class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }
        
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (checkOverlap(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkOverlap(int[] arr1, int[] arr2) {
        // option1:
        //return Math.min(arr1[1], arr2[1]) > Math.max(arr1[0], arr2[0]);
        
        // option2: arr1的开始时间，在arr2之间，或者，arr2的开始时间在arr1之间
        return (arr1[0] >= arr2[0] && arr1[0] < arr2[1]) || (arr2[0] >= arr1[0] && arr2[0] < arr1[1]);
    }
}

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        
        for (int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}

//把所有的开始时间和结束时间分别sort，默认第一个开始时间以及开始，第二个开始时间如果小于第一个结束时间，那么overlap。
//如果所有的intervals都不overlap，那么sort之后的两个数组，start，end，一一对应（也就是start[i]和end[i]是原来intervals[i]）
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        for (int i = 1; i < n; i++) {
            if (start[i] < end[i - 1]) {
                return false;
            }
        }
        return true;
    }
}

