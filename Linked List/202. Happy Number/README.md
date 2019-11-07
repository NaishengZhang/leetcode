

### 202. Happy Number



Two Method:

1. HashSet

   将所有计算的结果存入HashSet中，每次计算出结果后检查是否已经存在于Set中，如果存在，则证明已经开始循环，也就不是happynumber。如果出现等于1的情况，则是happynumber。

   ```java
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
   ```

   

2. 

3. Fast Slow Pointer

   使用`getNext()`方法使得假装是个List，如果`Fast == Slow`出现，则证明有环，也就是数字开始重复。

   ![1](1.png)

   Fast slow pointer⚠️：

   while循环两种退出条件：

   如果一开始slow和fast都指向head，那么就不能用`while (slow != fast) `作为条件，因为一开始是相等的。需要`while(true)`中间判断相等时break。

   ```java
   int slow = n, fast = n; 
   while (true) {
               slow = getNext(slow);
               fast = getNext(getNext(fast));
               if (fast == slow) break;
           }
   ```

   另外一种解决办法就是，slow指向head，fast指向head.next，判出条件`while (slow != fast) `

   ```java
   int slow = n, fast = getNext(n);
   while (slow != fast) {
               slow = getNext(slow);
               fast = getNext(getNext(fast));
           }
   ```

   