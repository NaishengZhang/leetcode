### DP

算法思路：

1. 确定状态

   研究最优策略的最后一步，化为子问题

2. 转移方程

   根据子问题定义直接得到

3. 初始条件和边界情况

   细心、考虑周全

   初始条件通常为f[0]，边界情况，如果index为负数怎么办，index超过数组length

4. 计算顺序

   利用之前的计算结果，通常一维从小到大；二维从上到下，从左到右；