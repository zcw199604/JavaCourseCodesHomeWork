# 命令
java -XX:+PrintGCDetails -Xmx512m -Xms512m -XX:+UseSerialGC GCLogAnalysis

## GC日志
正在执行...
[GC (Allocation Failure) [DefNew: 139776K->17471K(157248K), 0.0313076 secs] 139776K->44171K(506816K), 0.0315431 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157247K->17468K(157248K), 0.0387140 secs] 183947K->84462K(506816K), 0.0388763 secs] [Times: user=0.03 sys=0.01, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 157244K->17471K(157248K), 0.0337332 secs] 224238K->130196K(506816K), 0.0339268 secs] [Times: user=0.01 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 157247K->17467K(157248K), 0.0325112 secs] 269972K->174613K(506816K), 0.0327163 secs] [Times: user=0.01 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 157243K->17471K(157248K), 0.0315419 secs] 314389K->218791K(506816K), 0.0315754 secs] [Times: user=0.00 sys=0.03, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157213K->17470K(157248K), 0.0324984 secs] 358532K->264460K(506816K), 0.0328241 secs] [Times: user=0.01 sys=0.03, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157030K->17472K(157248K), 0.0312434 secs] 404020K->306924K(506816K), 0.0314325 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157248K->17471K(157248K), 0.0297998 secs] 446700K->344857K(506816K), 0.0300443 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0001407 secs][Tenured: 327385K->272882K(349568K), 0.0458776 secs] 484633K->272882K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0463743 secs] [Times: user=0.04 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139547K->17472K(157248K), 0.0084929 secs] 412429K->320195K(506816K), 0.0086710 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [DefNew: 157248K->157248K(157248K), 0.0002119 secs][Tenured: 302723K->304594K(349568K), 0.0479845 secs] 459971K->304594K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0485353 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 139624K->139624K(157248K), 0.0001809 secs][Tenured: 304594K->316086K(349568K), 0.0527439 secs] 444218K->316086K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0532317 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139622K->139622K(157248K), 0.0000954 secs][Tenured: 316086K->308813K(349568K), 0.0526719 secs] 455709K->308813K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0531810 secs] [Times: user=0.04 sys=0.01, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139776K->17471K(157248K), 0.0121931 secs] 448589K->353619K(506816K), 0.0123935 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0004562 secs][Tenured: 336147K->337073K(349568K), 0.0478967 secs] 493395K->337073K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0487774 secs] [Times: user=0.04 sys=0.00, real=0.05 secs]
执行结束!共生成对象次数:7963
Heap
 def new generation   total 157248K, used 6218K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,   4% used [0x00000000e0000000, 0x00000000e0612898, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
  to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
 tenured generation   total 349568K, used 337073K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 349568K,  96% used [0x00000000eaaa0000, 0x00000000ff3cc568, 0x00000000ff3cc600, 0x0000000100000000)
 Metaspace       used 2835K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 300K, capacity 386K, committed 512K, reserved 1048576K

## 总结
1. 在设置最大最小堆内存为512M时，执行了10YoungGc,5次FULLGC
2. [GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0001407 secs][Tenured: 327385K->272882K(349568K), 0.0458776 secs] 484633K->272882K(506816K), [Metaspace: 2828K->2828K(1056768K)], 0.0463743 secs] [Times: user=0.04 sys=0.00, real=0.05 secs] ...  这次垃圾回收没有回收年轻代空间，可能是年轻代需要晋升老年代，但是老年代此时空间不足，所以需要回收老年代空间。老年代总大小为：349568K，回收前已使用327385K，回收后使用空间为272882K，此次老年代回收空间为54503K,用时50ms。
3. Heap解读： 年轻代总大小:157248K，已经使用:6218K。老年代总大小:349568K,已使用:337073K，使用率96%，所以运行该程序时，堆内存分配过小，需要提升Xmx大小。



# 命令
java -XX:+PrintGCDetails -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis

## GC日志
正在执行...
[GC (Allocation Failure) [DefNew: 279616K->34943K(314560K), 0.0669473 secs] 279616K->85282K(1013632K), 0.0669892 secs] [Times: user=0.01 sys=0.05, real=0.07 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0767823 secs] 364898K->163337K(1013632K), 0.0768243 secs] [Times: user=0.02 sys=0.06, real=0.08 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0562123 secs] 442953K->231276K(1013632K), 0.0562491 secs] [Times: user=0.02 sys=0.03, real=0.06 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0611474 secs] 510892K->307709K(1013632K), 0.0611870 secs] [Times: user=0.04 sys=0.02, real=0.06 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0596359 secs] 587325K->387719K(1013632K), 0.0597582 secs] [Times: user=0.02 sys=0.03, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0559440 secs] 667335K->460799K(1013632K), 0.0559817 secs] [Times: user=0.03 sys=0.03, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0553976 secs] 740415K->534879K(1013632K), 0.0554423 secs] [Times: user=0.02 sys=0.03, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 314559K->34943K(314560K), 0.0613172 secs] 814495K->613458K(1013632K), 0.0613621 secs] [Times: user=0.02 sys=0.03, real=0.06 secs]
执行结束!共生成对象次数:8478
Heap
 def new generation   total 314560K, used 46199K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,   4% used [0x00000000c0000000, 0x00000000c0afded8, 0x00000000d1110000)
  from space 34944K,  99% used [0x00000000d1110000, 0x00000000d332fff8, 0x00000000d3330000)
  to   space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
 tenured generation   total 699072K, used 578514K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
   the space 699072K,  82% used [0x00000000d5550000, 0x00000000f8a44a90, 0x00000000f8a44c00, 0x0000000100000000)
 Metaspace       used 2835K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 300K, capacity 386K, committed 512K, reserved 1048576K

## 总结
1. 在设置最大最小堆内存为1G时，执行了8YoungGc,0次FULLGC
2. [GC (Allocation Failure) [DefNew: 279616K->34943K(314560K), 0.0669473 secs] 279616K->85282K(1013632K), 0.0669892 secs] [Times: user=0.01 sys=0.05, real=0.07 secs] 此次GC为YoungGc,年轻代回收前为:279616K,回收后:34943K,而堆的总使用内存为279616K,所以此次有159391K的空间晋升到了老年代。用时共66ms。