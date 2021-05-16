# 命令
java -XX:+PrintGCDetails -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis

## GC日志
正在执行...
[GC (Allocation Failure) [ParNew: 136320K->17023K(153344K), 0.0179041 secs] 136320K->40160K(648576K), 0.0179602 secs] [Times: user=0.01 sys=0.02, real=0.02 secs]
[GC (Allocation Failure) [ParNew: 153343K->17024K(153344K), 0.0424935 secs] 176480K->81733K(648576K), 0.0425426 secs] [Times: user=0.01 sys=0.03, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0285471 secs] 218053K->123385K(648576K), 0.0286079 secs] [Times: user=0.04 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0255606 secs] 259705K->164181K(648576K), 0.0256135 secs] [Times: user=0.04 sys=0.01, real=0.02 secs]
[GC (Allocation Failure) [ParNew: 153035K->17024K(153344K), 0.0321917 secs] 300193K->212231K(648576K), 0.0322646 secs] [Times: user=0.05 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 152622K->17024K(153344K), 0.0280436 secs] 347830K->254784K(648576K), 0.0281026 secs] [Times: user=0.04 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 153112K->17024K(153344K), 0.0336414 secs] 390873K->304827K(648576K), 0.0336957 secs] [Times: user=0.05 sys=0.02, real=0.04 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 287803K(495232K)] 325261K(648576K), 0.0003811 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.010/0.010 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0330162 secs] 441147K->346907K(648576K), 0.0330702 secs] [Times: user=0.03 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0327880 secs] 483227K->395306K(648576K), 0.0328422 secs] [Times: user=0.05 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0368686 secs] 531626K->439906K(648576K), 0.0369291 secs] [Times: user=0.05 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0334638 secs] 576226K->487529K(648576K), 0.0335183 secs] [Times: user=0.04 sys=0.02, real=0.03 secs]
[CMS-concurrent-abortable-preclean: 0.009/0.221 secs] [Times: user=0.25 sys=0.08, real=0.22 secs]
[GC (CMS Final Remark) [YG occupancy: 32467 K (153344 K)][Rescan (parallel) , 0.0011101 secs][weak refs processing, 0.0000100 secs][class unloading, 0.0002532 secs][scrub symbol table, 0.0007502 secs][scrub string table, 0.0001643 secs][1 CMS-remark: 470505K(495232K)] 502972K(648576K), 0.0024423 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.007/0.007 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0196498 secs] 539860K->448718K(648576K), 0.0196970 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 431694K(495232K)] 452677K(648576K), 0.0003317 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 153128K->17024K(153344K), 0.0164455 secs] 584822K->494451K(648576K), 0.0165055 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
[CMS-concurrent-abortable-preclean: 0.002/0.035 secs] [Times: user=0.04 sys=0.01, real=0.04 secs]
[GC (CMS Final Remark) [YG occupancy: 45603 K (153344 K)][Rescan (parallel) , 0.0007420 secs][weak refs processing, 0.0000889 secs][class unloading, 0.0002457 secs][scrub symbol table, 0.0004984 secs][scrub string table, 0.0002043 secs][1 CMS-remark: 477427K(495232K)] 523031K(648576K), 0.0019052 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 153154K->17024K(153344K), 0.0126671 secs] 487211K->399027K(648576K), 0.0127221 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 382003K(495232K)] 402127K(648576K), 0.0002149 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0116560 secs] 535347K->441493K(648576K), 0.0119489 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 153344K->17024K(153344K), 0.0120446 secs] 577813K->487308K(648576K), 0.0120938 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 153344K->153344K(153344K), 0.0000283 secs][CMS[CMS-concurrent-abortable-preclean: 0.006/0.091 secs] [Times: user=0.10 sys=0.01, real=0.09 secs]
 (concurrent mode failure): 470284K->341519K(495232K), 0.0651164 secs] 623628K->341519K(648576K), [Metaspace: 2828K->2828K(1056768K)], 0.0652431 secs] [Times: user=0.07 sys=0.00, real=0.07 secs]
执行结束!共生成对象次数:9033
Heap
 par new generation   total 153344K, used 77219K [0x00000000d7600000, 0x00000000e1c60000, 0x00000000e1c60000)
  eden space 136320K,  56% used [0x00000000d7600000, 0x00000000dc168dc0, 0x00000000dfb20000)
  from space 17024K,   0% used [0x00000000dfb20000, 0x00000000dfb20000, 0x00000000e0bc0000)
  to   space 17024K,   0% used [0x00000000e0bc0000, 0x00000000e0bc0000, 0x00000000e1c60000)
 concurrent mark-sweep generation total 495232K, used 341519K [0x00000000e1c60000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 2835K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 300K, capacity 386K, committed 512K, reserved 1048576K

  ## 总结
  1. 在设置最大最小堆内存为1G时，执行了7YoungGc,3次FULLGC
  2. [GC (Allocation Failure) [ParNew: 136320K->17023K(153344K), 0.0179041 secs] 136320K->40160K(648576K), 0.0179602 secs] [Times: user=0.01 sys=0.02, real=0.02 secs] ... 此次GC为YoungGc,年轻代回收前为:136241K,回收后:17023K,136241K,所以此次有70942K的空间晋升到了老年代。用时共30ms。
  3. CMS Initial Mark(CMS初始化标记),CMS-concurrent-mark-start(并发标记),CMS-concurrent-preclean-start(并发预清理),
  Final Remark(最终标记),Concurrent Sweep(并发清楚),
  Concurrent Reset(并发重置)