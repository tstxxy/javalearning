题目和答案均来源互联网。
- - -
Java跨平台的特点是怎么体现的？
* 源程序.java 经过编译以后生成 字节码.class。字节码.class 被JVM虚拟机执行。
* JVM虚拟机是与平台相关的，不同的平台需要不同的jvm虚拟机。
- - -
编译语言和解释语言的区别？
* 编译语言是直接将整个程序进行编译成为机器语言后执行。
* 解释语言是一句一句的解释然后执行。
- - -
Java类中的静态变量是什么时候初始化的？
* 一个类要被时候需要经过装载、连接和初始化这三个过程。
* 在装载阶段，类装在器把编译形成的Class文件载入内存，创建类相关的Class对象，这个Class对象封装了我们要使用的类的类型信息。
* 连接又可以分为三个子步骤：验证、准备和解析。
  * 验证就是要确保Java类型数据格式的正确性，并适用于JVM使用。
  * 准备阶段，JVM为静态变量分配内存空间，并设置默认值，注意，这里是默认值，比如整型的变量会被赋予默认值0。在这个阶段，JVM可能还会为一些数
  据结构分配内存，目的是提高运行程序的性能，比如方法表。
  * 解析过程就是在类型的常量池中寻找类、接口、字段和方法的符号引用，把这些符号引用替换成直接引用。这个阶段可以被推迟到初始化之后，当程序运
  行的过程中真正使用某个符号引用的时候再去解析它。
* 类会在首次被_主动使用_时被初始化，为类（静态）变量赋予正确的初始值。在Java代码中，一个正确的初始化是通过类变量初始化语句或者静态初始化给
出的。而我们这里所说的主动使用包括：
  * 创建类的实例
  * 调用类的静态方法
  * 使用类的非常量静态字段
  * 调用Java API中的某些反射方法
  * 初始化某个类的子类
  * 含有main()方法的类启动时
* 初始化一个类包括两个步骤：
  * 如果类存在直接父类且直接夫类还没有被初始化，则先初始化其直接夫类。
  * 如果类存在一个初始化方法，则执行此方法。
- - -