# Himly TCC Dubbo 程序示例
***
## 工程运行说明
- 1.运行底层基础服务（provider）：account工程下的 AccountServiceApplication
- 2.运行业务层测试：transaction工程下的 TransactionServiceApplication

### 相关思路和原理

&ensp;&ensp;&ensp;&ensp;下面简单说下思路：

&ensp;&ensp;&ensp;&ensp;根据之前缩写的TCC Demo程序的思路，使用 TCC 就是为了将 confirm和cancel的操作委托给第三方，在程序本身程序try后，由TCC第三方来推动进行下面的阶段

&ensp;&ensp;&ensp;&ensp;结合Dubbo的话，根据RPC的思路，为了简化客户端（provider）的使用，具体的函数调用都放到基础服务端

&ensp;&ensp;&ensp;&ensp;阶段一（try阶段）都是程序本身在使用RPC的方式进行调用执行

&ensp;&ensp;&ensp;&ensp;后面的confirm和cancel由TCC框架来进入RPC调用执行（在himly中全局的那个切面中）

&ensp;&ensp;&ensp;&ensp;从日志运行中也可以看出，具体的调用日志全打印在基础工程（Account）中