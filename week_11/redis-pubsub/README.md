# 基于Redis的PubSub实现订单异步处理
***
## 作业思路
&ensp;&ensp;&ensp;&ensp;使用jedis的相关函数实现,简单创建两个类，一个用于处理订单，一个发布订单


### 订单订阅处理
&ensp;&ensp;&ensp;&ensp;生成一个订单订阅处理类：SubscribeOrder，使用多线程进行启动，不然会阻塞，当收到消息为空时结束