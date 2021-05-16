# 运行命令
java -XX:+PrintGCDetails -Xmx1g -Xms1g GCLogAnalysis

## GC日志
[GC (Allocation Failure) [PSYoungGen: 262144K->43515K(305664K)] 262144K->80525K(1005056K), 0.0307133 secs] [Times: user=0.01 sys=0.03, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 305659K->43519K(305664K)] 342669K->150204K(1005056K), 0.0443137 secs] [Times: user=0.03 sys=0.05, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 305663K->43516K(305664K)] 412348K->223267K(1005056K), 0.0332192 secs] [Times: user=0.03 sys=0.04, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 305660K->43515K(305664K)] 485411K->293172K(1005056K), 0.0326596 secs] [Times: user=0.02 sys=0.04, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 305659K->43511K(305664K)] 555316K->370240K(1005056K), 0.0369508 secs] [Times: user=0.02 sys=0.05, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 305655K->43501K(160256K)] 632384K->448099K(859648K), 0.0540200 secs] [Times: user=0.02 sys=0.04, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 160237K->73088K(232960K)] 564835K->485156K(932352K), 0.0173656 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 189824K->97125K(232960K)] 601892K->520623K(932352K), 0.0221490 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 213861K->107897K(232960K)] 637359K->547163K(932352K), 0.0242756 secs] [Times: user=0.04 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [PSYoungGen: 224633K->78629K(232960K)] 663899K->578446K(932352K), 0.0440962 secs] [Times: user=0.04 sys=0.03, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 195365K->39910K(232960K)] 695182K->609292K(932352K), 0.0392782 secs] [Times: user=0.02 sys=0.04, real=0.04 secs]
[GC (Allocation Failure) [PSYoungGen: 156646K->40530K(232960K)] 726028K->643120K(932352K), 0.0271982 secs] [Times: user=0.02 sys=0.02, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 40530K->0K(232960K)] [ParOldGen: 602589K->335161K(699392K)] 643120K->335161K(932352K), [Metaspace: 2828K->2828K(1056768K)], 0.0794416 secs] [Times: user=0.10 sys=0.04, real=0.08 secs]
执行结束!共生成对象次数:8679
Heap
 PSYoungGen      total 232960K, used 4743K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 116736K, 4% used [0x00000000eab00000,0x00000000eafa1e50,0x00000000f1d00000)
  from space 116224K, 0% used [0x00000000f8e80000,0x00000000f8e80000,0x0000000100000000)
  to   space 116224K, 0% used [0x00000000f1d00000,0x00000000f1d00000,0x00000000f8e80000)
 ParOldGen       total 699392K, used 335161K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 47% used [0x00000000c0000000,0x00000000d474e470,0x00000000eab00000)
 Metaspace       used 2835K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 300K, capacity 386K, committed 512K, reserved 1048576K

## 总结
1. 一共12次YoungGc,一次FULL GC

2. [Full GC (Ergonomics) [PSYoungGen: 40530K->0K(232960K)] [ParOldGen: 602589K->335161K(699392K)] 643120K->335161K(932352K), [Metaspace: 2828K->2828K(1056768K)], 0.0794416 secs] [Times: user=0.10 sys=0.04, real=0.08 secs]
年轻代内存直接清空,老年代内存从 602589K 回收至 335161K 老年代一共回收267428K Metaspace内存无变化。总共用时79ms