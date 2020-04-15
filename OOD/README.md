# OOD

### 5C 解题法

**Clarify**

通过和面试官交流，去除题目中的歧义，确定答题范围

1. What
2. How
3. Who
4. When

**Core Object**

确定题目所涉及的类，以及类之间的映射关系

**Cases**

确定题目中所需要实现的场景和功能

**Classes**

通过类图的方式，具体填充题目中设计的类

**Correctness**

检查自己的设计，是否满足关键点

1. validate use cases: 检查是否支持所有的use case
2. Follow good practice
3. S.O.L.I.D
4. Design pattern



## 1. 实物类

### Vending machine

 	1. Clarify

Output: item

Input: coin

1.1\. What:

(1)**Vending machine** -> manufacture, weight, color

(2) **Item**: 

what items does this vending machine sell?

--Each item matches a class

What to do when an item sold out?

--might need to support refill use case

(3) **Payment** --> strategy design pattern

What are the supported payment methods?

Coin/paper money: 知道当前收了多少钱，找零

Credit Card: 直接当前item的价格

1.2 How 

how to select item to purchase -> 输入一个input代表一种item（eg. "A1" -> coke）

1.3 Who and When: N/A



2. Core Object

   Coin ---- vending machine --- item ---- sprint, coke,...

   vending machine包含List<coin> coin and List<Item> items

3. Use cases

   - Select item

     vm takes an external input, shows the price of that item. 

   - insert coin

   - execute transaction

     get teh current seleted item

     compare the item price and inserted coints

     if not enough money, throw an exception

     Else return the item purchased

     refund if any

   - Cancel transaction

     return the current coins that has been inserted

   - Refill items

     refill items on top of current stock







### Alarm Clock



Use case:

1.) user can see the current time on the clock 
2.) user can set/update the alarm (time, sound, ipod etc) for the clock 
3.) clock needs to sound the alarm at the right time 
4.) user can snooze/end the alarm. 



