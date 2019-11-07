### 202. Happy Number



Two Method:

1. HashSet

   `sda`

2. Fast Slow Pointer

   使用`getNext()`方法使得假装是个List，如果`Fast == Slow`出现，则证明有环，也就是数字开始重复。

   ![1](1.png)

   