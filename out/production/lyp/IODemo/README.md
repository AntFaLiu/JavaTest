# java IO

##  I/O VS NIO
I/O               NIO
面向流	        面向缓冲
阻塞 IO	        非阻塞 IO
无	             选择器
NIO 是基于块 (Block) 的，它以块为基本单位处理数据。在 NIO 中，最为重要的两个组件是缓冲 Buffer 和通道 Channel。缓冲是一块连续的内存块，
是 NIO 读写数据的中转地。通道标识缓冲数据的源头或者目的地，它用于向缓冲读取或者写入数据，是访问缓冲的接口。Channel 是一个双向通道，即可读，也可写。
Stream 是单向的。应用程序不能直接对 Channel 进行读写操作，而必须通过 Buffer 来进行，即 Channel 是通过 Buffer 来读写数据的。

使用 Buffer 读写数据一般遵循以下四个步骤：

写入数据到 Buffer；
调用 flip() 方法；
从 Buffer 中读取数据；
调用 clear() 方法或者 compact() 方法。