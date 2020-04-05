class Logger {
    HashMap<String, Integer> map;
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        //option 1:
//         if (!map.containsKey(message)) {
//             map.put(message, timestamp);
//             return true;            
//         } else if (timestamp - map.get(message) >= 10){
//             map.put(message, timestamp);
//             return true; 
//         }
        
//         return false;
        
        //option 2:
        if (!map.containsKey(message) || timestamp - map.getOrDefault(message, 0) >= 10) {
            map.put(message, timestamp);
            return true;
        } else {
            return false;
        }
        
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */