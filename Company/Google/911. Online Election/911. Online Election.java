/*
Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.

Note: [[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]] 中[0,1,1,0,0,1,0]是投票给了0 或者1号candidate；
[0,5,10,15,20,25,30]是时间点
所以，time 12 也就是有三次投票 对应 [0,1,1] 
*/

// Use HashMap + binarySearch. Time: O(nlogn)
class TopVotedCandidate {// Time: O(1)
    private Map<Integer, Integer> map = new HashMap<>();
    private int[] times;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int[] count = new int[persons.length];
        int lead = -1;
        for (int i = 0; i < times.length; i++) {
            count[persons[i]]++;
            if (map.isEmpty() || count[lead] <= count[persons[i]]) {
                lead = persons[i];
            }
            map.put(times[i], lead);
        }
    }
    
    public int q(int t) {// Time: O(nlogn)
        int index = Arrays.binarySearch(times, t);
        int time = times[index >= 0 ? index : -index - 2];
        return map.get(time);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */


// use TreeMap.  map.floorEntry(t).getValue();
// For operations like add, remove, containsKey, time complexity is O(log n where n is number of elements present in TreeMap.
// TreeMap always keeps the elements in a sorted(increasing) order, while the elements in a HashMap have no order. TreeMap also provides some cool methods for first, last, floor and ceiling of keys.

class TopVotedCandidate {
    private TreeMap<Integer, Integer> map = new TreeMap<>();
    public TopVotedCandidate(int[] persons, int[] times) {
        int[] count = new int[persons.length];
        int lead = -1;
        for (int i = 0; i < times.length; i++) {
            count[persons[i]]++;
            if (i == 0 || count[persons[i]] >= count[lead]) {
                lead = persons[i];
            } 
            map.put(times[i], lead);
            
        }
    }
    
    public int q(int t) {
        return map.floorEntry(t).getValue();
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */









// suppose topK should be called multiple time
// support there is only one candidate voted at one timestamp(this is possible if the timestamp is second based)
// use multimap to record the frequency of candidates: data, since candidates may have same number of votes
// use unordered_map to record the position of one candidate in the data: data_map
// store all the votes as timestamp, candidate pairs;
// record cur_timestamp, for each call of topK, check the timestamp with last cur_timestamp
// timestamp < cur_timestamp: put votes in this range to the multimap
// timestamp > cur_timestamp: remove votes in this range to the multimap
// get k elements from the multimap

class SortCandidates {
private:
        unsigned cur_timestamp;
        std::multimap<unsigned, std::string, std::less<unsigned> > data;
        typedef std::multimap<unsigned, std::string, std::less<unsigned> >::iterator Iter;
        std::unordered_map<std::string, Iter> data_map;
        std::map<unsigned, std::string> votes;

        void init(const std::vector<std::pair<std::string, unsigned> >& input_votes) {
                // initialize
                for (auto v : input_votes) {
                        votes.emplace(v.second, v.first);
                        insert(v.first);
                }
                cur_timestamp = votes.rbegin()->first;
        }

        void insert(std::string& v) {
                // insert one vote to the data
                auto pos = data_map.find(v);
                if (pos == data_map.end()) {
                        data_map[v] = data.emplace(1, v);
                }
                else {
                        unsigned count = pos->second->first + 1;
                        data.erase(pos->second);
                        data_map[v] = data.emplace(count, v);
                }
        }
        void remove(unsigned timestamp) {
                // remove one vote from data
                auto pos = votes.find(timestamp);
                if (pos == votes.end()) {
                        return;
                }
                std::string& cand = pos->second;
                auto iter = data_map.find(cand)->second;
                if (1 == iter->first) {
                        data.erase(iter);
                }
                else {
                        size_t count = iter->first - 1;
                        data.erase(iter);
                        data_map[cand] = data.emplace(count, cand);
                }
        }
public:
        SortCandidates(const std::vector<std::pair<std::string, unsigned> >& input_votes) {
                init(input_votes);
        }
        std::vector<std::string> topK(unsigned k, unsigned timestamp) {
                std::vector<std::string> result;
                if (0 == k) {
                        return result;
                }
                result.reserve(k);
                if (timestamp > cur_timestamp) {
                        while (cur_timestamp < timestamp) {
                                ++cur_timestamp;
                                auto pos = votes.find(cur_timestamp);
                                if (pos != votes.end()) {
                                        insert(pos->second);
                                }
                        }
                }
                else if (timestamp < cur_timestamp) {
                        do {
                                remove(cur_timestamp);
                                --cur_timestamp;
                        } while (cur_timestamp > timestamp);
                }
                auto iter = data.begin();
                while (iter != data.end() && k) {
                        result.push_back(iter->second);
                        ++iter;
                        --k;
                }
                return result;
        }
};