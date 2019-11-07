
//HashSet
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            int cur = n;
            while (cur != 0) {
                sum += (cur % 10) * (cur % 10);
                cur /= 10;
            }
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
            
        }
        
        return true;
    }
}

//Fast Slow Pointer
class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while (true) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            if (fast == slow) break;
        }
        return slow == 1;
    }
    
    public int getNext(int n) {
        int sum = 0;
        int cur = n;
        while (cur != 0) {
            sum += (cur % 10) * (cur % 10);
            cur /= 10;
        }
        return sum;
    }
}

class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = getNext(n);
        while (slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1;
    }
    
    public int getNext(int n) {
        int sum = 0;
        int cur = n;
        while (cur != 0) {
            sum += (cur % 10) * (cur % 10);
            cur /= 10;
        }
        return sum;
    }
}


