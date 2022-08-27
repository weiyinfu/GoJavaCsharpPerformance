比较编程语言的性能，参赛的语言：
* Go
* Java
* C#

# 结论
* `Java>Go>C#`
* Golang与Java性能相当，但是内存占用会小许多。  

# 比较说明
* 只比较时间，不考虑空间
* 时间单位是毫秒
* 测试环境是Mac
* 计算时间的时候都是在程序内部计算时间，不考虑程序编译、启动的时间

|Demo|Java|C#|Go|
|---|---|---|---|
|一道算法题打表  | 2976  | 7848   |  |
|1000维矩阵乘法  | 911  |  8318  | 1850 |
|斐波那契求值  | 2452  |  7985  | 3846 |

# 参考资料
* https://zhuanlan.zhihu.com/p/50160756
* https://github.com/frol/completely-unscientific-benchmarks