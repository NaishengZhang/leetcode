## Tail 删数类

https://www.youtube.com/watch?v=LInZvnsQGmA&list=PLvHc59peqCbP5CTr-Wm4SqJD6E4mV3eAL&index=4

前向类问题，两个指针都在头。

Tail有两种模版：

1. remove

2. move



```java
int tail = 0
for(int i = 0; i < nums.length; i++){
  if(nums[i]满足新数组) {
    nums[tail] = nums[i] // remove
    //or
    //swap(nums,tail,i); // move
    tail++
  }
}
```

