### 198. Permutation Index II

与上一题的不同之处时会有重复的数。那么，只要在发现是重复数的那一位用rank * fact的结果除以重复的次数dup再加入index就可以了。当然，每个重复数的dup都要阶乘，例如有3个2，4个8，dup就是3! * 4! = 144。index是所有previous排列的次数和，返回下一次index+1。

permutation with dup:
n!/(a!*b!*...k!)
n: digit
a, b, ...,k : a个2， b个3，.k个5