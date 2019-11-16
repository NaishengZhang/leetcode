### 15. 3Sum

先固定一个数，对剩下两个做two sum。

但是要注意去重复：

第一个数去重复：

```java
for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = 0 - nums[i];
            twoSum(nums, i, remain, ans);
        }
```

第二三个数去重复

```java
while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum == target) {
                ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                lo++;
                hi--;
                while (lo < hi && nums[lo] == nums[lo - 1]) {//去重
                    lo++;
                }
                while (lo < hi && nums[hi] == nums[hi + 1]) {//去重
                    hi--;
                }
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
```

Array.asList用法：

ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

