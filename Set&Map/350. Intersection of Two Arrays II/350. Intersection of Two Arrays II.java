/*
Time O(n + m)
Space O(n or m)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // num, frequence
        
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) -  1);
            }
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}

/*

sort nlog(n)
exclude the sorting process.
Time for the linear scan: O(min(n, m)).   + sort => total: O(nlogn + m longm)
Space: O(1)
if the input is sorted, time is O(n)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0;
        int j = 0;
        List<Integer> res = new ArrayList<>();
        int n = nums1.length;
        int m = nums2.length;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return res.stream().mapToInt(k->k).toArray();
    }
}