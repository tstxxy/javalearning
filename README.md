# 题目和答案均来源于互联网。
### Java重写与重载的区别
* 重写
  * 重写是子类对父类的允许访问的方法的实现过程进行重新便携，返回值和形参都不能改变。即外壳不变，核心重写！
  * 重写的好处在于子类可以根据需要，定义特定于自己的行为。也就是说子类能够根据需要实现父类的方法。
  * 重写方法不能抛出新的检查异常或者比重写方法申明更加宽泛的异常，例如：父类的一个方法申明了一个检查异常IOEceptin，但是在重写这个方法的时候不能抛出Exception异常，因为Exception是IOException的父类，只能抛出IOException的子类异常。
  * 方法的重写规则：
    * 参数列表必须完全与被重写方法的相同。
    * 返回类型必须完全与被重写方法的返回类型相同。
    * 访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为public，那么在子类中重写该方法就不能声明为protected。
    * 父类的成员方法只能被它的子类重写。
    * 声明为final的方法不能被重写。
    * 声明为static的方法不能被重写，但是能够被再次声明。
    * 子类和父类在同一个包中，那么子类可以重写父类所有方法，出了声明为private和final的方法。
    * 子类和父类不在同一个包中，那么子类只能够重写父亲的声明为public和protected的非final方法。
    * 重写的方法能够抛出任何非强制异常，无论被重写的方法是否抛出异常。但是，重写的方法不能抛出新的强制性异常，或者比被重写方法声明更广泛的强制性异常。
    * 构造方法不能被重写。
    * 如果不能继承这个方法，则不能重写这个方法。
* 重载
  * 重载是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。
  * 每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。
  * 重载规则：
    * 被重载的方法必须改变列表参数（参数个数或顺序不一样）。
    * 被重载的方法可以改变返回类型。
    * 被重载的方法可以改变访问修饰符。
    * 被重载的方法可以声明新的或更广的异常检查。
    * 方法能够在统一额类中或者子类中被重载。
    * 无法以返回值类型作为重载函数的区分标准。
* 区别：

    | 区别点        | 重载方法        | 重写方法  |
    | -------------|:-------------:|: -----:|
    | 参数列表      | 必须修改 | 一定不能修改 |
    | 返回类型      | 可以修改      |   一定不能修改 |
    | 异常          | 可以修改      |    可以减少或删除，一定不能抛出新的异常或者更广的异常 |
    | 访问          | 可以修改      |    一定不能做更严格的限制（可以降低限制） |
- - -
### 什么是加载器？
* 负责读取Java字节代码，并转换成java.lang.class类的一个实例。
- - -
### 类加载器与类的"相同"判断
* 类加载器出了用于加载类外，还可用于确定类在Java虚拟机中的唯一性。即便是同样的字节代码，被不同的类加载器加载后所得到的类，也是不同的。通俗地讲，要判断两个类是否"相同"，前提是这两个类必须被同一个类加载器加载，否则这两个类不"相同"。这里指的"相同"，包括类的Class对象equals()方法、isAssignableFrom()方法、isInstance()方法、instanceof关键字等判断出来的结果。
- - -
### 类加载器种类
* 启动类加载器，Bootstrap ClassLoader，加载JAVA_Home\lib，或者被-Xbootclasspath参数限定的类。
* 扩展类加载器，Extension ClassLoader，加载\lib\ext，或者被java.ext.dirs系统变量指定的类。
* 应用程序类加载器，Application ClassLoader，加载ClassPath中的类库。
* 自定义类加载器，通过继承ClassLoader实现，一般是加载我们的自定义类。
- - -
### 双亲委派模型
* 类加载Java类如同其他的Java类一样，也是要由类加载器来加载；出了启动类加载器，每个类都有其父类加载器（父子关系由组合不是继承来实现）
* 所谓双亲委派是指每次收到类加载请求时，先将请求委派给父类加载器完成（所有加载请求最终会委派到顶层的Bootstrap ClassLoader加载器中），如果父类无法完成这个加载（该加载器的搜索范围中没有找到对应的类），子类尝试自己加载。
* 双亲委派好处
  * 避免同一个类被多次加载。
  * 每个加载器只能加载自己范围内的类。
- - -
### 什么是RESTful
* RESTful 是目前最流行的 API 设计规范，用于 Web 数据接口的设计。
* URL设计
  * RESTful的核心思想就是，客户端发出的数据操作指令都是"动词 + 宾语"的结构。比如，GET /articles这个命令，GET是动词， /articles是宾语。
  * 动词通常是五种HTTP方法（根据HTTP规范，动词一律大写）：
    * GET：读取（Read）
    * POST：新建（Create）
    * PUT：更新（Update）
    * PATCH：更新（Update），通常是部分更新
    * DELETE：删除（Delete）
  * 动词的覆盖
    * 有些动词只能是有GET和POST这两种方法。服务器必须接收POST模拟其他三个方法（POST，PATCH，DELETE）。这时，客户端发出的HTTP请求，要加上X-HTTP-Method-Override属性，告诉服务器应该使用哪一个动词，覆盖POST方法。
  * 宾语必须是名词
    * 宾语就是API的URL，是HTTP动词作用的对象。它应该是名词，不能是动词。比如，/articles这个URL就是正确的，/getAllCars这个URL是错误的。
  * 复数URL
    * 为了统一起见，建议都是用复数URL，比如GET /articles/2要好于GET /article/2
  * 避免多级URL
    * 常见的情况是，资源需要多级分类，因此很容易写出多级的URL，比如获取某个作者的某一类文章。GET /authors/12/categories/2。这种URL不利于扩展，语义也不明确，往往要想一会，才能明白意义。更好的做法是除了第一级，其他级别都用查询字符串表达。
    * 例如：GET /authors/12?categories=2就比上述的URL好。GET /articles?published=true比GET /articles/published好。
* 状态码
  * 客户端的每一次请求，服务器都必须给出回应。回应包括HTTP状态码和数据两部分。
  * HTTP状态码是一个三位数，分为五个类别。
    * 1xx：相关信息（未定义，不做了解）
    * 2xx：操作成功
    * 3xx：重定向
    * 4xx：客户端错误
    * 5xx：服务器错误
  * 2xx状态码
    * 200状态码表示操作成功，但是不同的方法可以返回更精确的状态码。
      * GET：200 OK
      * POST：201 Created（表示生成了新的资源；delete返回204状态码，表示资源已经不存在。）
      * PUT：200 OK
      * PATCH：200 OK
      * DELETE：204 No Content
      * 202 Accepted表示服务器已经收到了请求，但还未进行处理，会在未来再处理，通常用于异步操作。
  * 3xx状态码
    * API用不到301状态码（永久重定向）和302状态码（暂时重定向，307也是这个含义），因为它们可以由应用级别返回，浏览器会直接跳转，API级别可以不用考虑这两种情况。
    * API用到的3xx状态码，主要是303 See Other，表示参考另一URL。它与302，307的含义一样，也是"暂时重定向"，区别在于302，307用于GET请求，而303用于POST、PUT、DELETE和DELETE请求。收到303以后，浏览器不会自动跳转，而会让用户自己决定下一步怎么办。
  * 4xx状态码
    * 4xx状态码表示客户端错误，主要有下面几种：
      400 Bad Request：服务器不理解客户端的请求，未做任何任何处理。
      401 Unauthorized：用户未提供身份验证凭据、或者没有通过身份验证。
      403 Forbidden：用户通过了身份验证、但是不具有访问资源所需的权限。
      404 Not Found：所请求的资源不存在、或不可用。
      405 Method Not Allowed：用户已经通过身份验证，但是所用的HTTP方法不在他的权限之内。
      410 Gone：所请求的资源疫情这个地址转移，不再可用。
      415 Unsupported Media Type：客户端要求的返回格式不支持。比如，API只能返回JSON格式，但是客户端要求返回XML格式。
      422 Unprocessable Entity：客户端上传的附件无法处理，导致请求失败。
      429 Too Many Requests：客户端的请求次数超过限额。
    * 5xx状态码
      * 5xx状态码表示服务端错误。一般来说，API不会向用户透露服务器的详细信息，所以只要两个状态码够了。
      * 500 Internal Server Error：客户端请求有效，服务器处理时发生了意外。
      * 503 Service Unavaliable：服务器无法处理请求，一般用于网站维护状态。
* 服务器回应
  * 不要返回纯文本
    * API返回的数据格式，不应该是村文本，而应该是一个JSON对象，因为这样才能返回标准的结构化数据。所以，服务器回应的HTTP头的Content-Type属性要设为application/json。
    * 客户端请求时，也要明确告诉服务器，可以接受JSON格式，即请求的HTTP头的ACCEPT属性也要设为application/json。
  * 发生错误时，不要返回200状态码
    * 有一种不恰当的做法是，即使发生错误，也返回200状态码，把错误信息放在数据体里面。这种做法实际上取消了状态码，完全是不可取的。正确的做法是，状态码反映发生的错误，具体的错误信息放在数据体里面返回。
  * 提供链接
    * API的使用者未必知道，URL是怎么设计的。一个解决方法是，在会用，给出相关链接，便于下一步操作。这样的话，用户只要记住一个URL，就可以发现其他的URL。这种方法叫HATEOAS。
    * 举例来说Github的API都在api.github.com这个域名。访问它，就可以得到其他URL。
- - -
### Java跨平台的特点是怎么体现的？
* 源程序.java 经过编译以后生成 字节码.class。字节码.class 被JVM虚拟机执行。
* JVM虚拟机是与平台相关的，不同的平台需要不同的jvm虚拟机。
- - -
### 编译语言和解释语言的区别？
* 编译语言是直接将整个程序进行编译成为机器语言后执行。
* 解释语言是一句一句的解释然后执行。
- - -
### Java类中的静态变量是什么时候初始化的？
* 一个类要被加载时候需要经过装载、连接和初始化这三个过程。
* 在装载阶段，类装在器把编译形成的Class文件载入内存，创建类相关的Class对象，这个Class对象封装了我们要使用的类的类型信息。
* 链接又可以分为三个子步骤：验证、准备和解析。
  * 验证就是要确保Java类型数据格式的正确性，并适用于JVM使用。包括四个过程：
    * 文件格式验证
    * 元数据验证
    * 字节码验证
    * 符号引用验证
  * 准备阶段，JVM为静态变量分配内存空间，并设置默认值，注意，这里是默认值，比如整型的变量会被赋予默认值0。在这个阶段，JVM可能还会为一些数据结构分配内存，目的是提高运行程序的性能，比如方法表。
  * 解析过程就是在类型的常量池中寻找类、接口、字段和方法的符号引用，把这些符号引用替换成直接引用。这个阶段可以被推迟到初始化之后，当程序运行的过程中真正使用某个符号引用的时候再去解析它。
* 类会在首次被_主动使用_时被初始化，为类（静态）变量赋予正确的初始值。在Java代码中，一个正确的初始化是通过类变量初始化语句或者静态初始化给出的。而我们这里所说的主动使用包括：
  * 创建类的实例
  * 调用类的静态方法
  * 使用类的非常量静态字段
  * 调用Java API中的某些反射方法
  * 初始化某个类的子类
  * 含有main()方法的类启动时
* 初始化一个类包括两个步骤：
  * 如果类存在直接父类且直接父类还没有被初始化，则先初始化其直接父类。
  * 如果类存在一个初始化方法，则执行此方法。
- - -
### Java异常体系简析
* 《Jva编程思想》对异常的描述：
  * 除非你能解决（或必须要处理）这个异常，否则不要捕获它，如果打算记录错误消息，那么别忘了把它再抛出去。
  * 异常即代表一种错误，又可以代表一个消息。
* 为什么会有异常？
  * 如果我们有一个函数是用来作除法的。除法操作是不能够除以零的。如果函数传入的分母是零，这时异常出现了。直接让程序挂掉了是不行，但函数自己并不能自己处理这种突发情况，所以得想办法把这种情况告诉用户，让用户自己来作决定，也就是说程序要把遇到的这种异常情况包装一下发送出去，由用户来做决定如何处理。
  * 异常表示着一种信息。例如EOFException
* Java异常的分类
  * Throwable类是所有异常的父类。Error类和Exception类继承于Throwable类。
  * Error类表示很严重的问题发生了，可以捕获但是不要捕获，因为捕获了并不能解决问题。这个不是由程序产出的，是底层出现了问题。
  * Exception类分为RuntimeException类和其他类
* 异常的处理的理解
   * 重复尝试解决偶发的问题。
     * 例如：当我我们于服务器建立了Socket连接，当长时间不用被服务器主动断开了连接，或者网络都懂导致的断开），那么我们捕获这个异常，然后重新建立一个连接来发送。这是最基本的解决方法，或者设置一个重复次数，当出现异常的时候重复发送指定的次数。
     * 上述的例子里面，这个连接异常我们没有真正的解决它，而是通过又新建一个连接来处理，我们的解决的不是异常问题，而是发送数据出现了问题，我们解决的是发送数据没有成功这个问题。
   * 不想让用户看到错误堆栈
     * 例如用户发送了请求，我们无法处理并导致了异常。如果不捕获异常，那么用户将会看到一个500页面，附带着堆栈信息，这种是不友好的表现方式。实际上我们会有一个最大的catch抱住一个个方法，当出现异常的情况跳转到错误页面。
     * 实际上我们并没有解决异常，我们只是继绝了异常导致的问题，异常本身还在那，真正的解决方法就是程序员解决bug然后重新上线。
   * 我们应该怎么做
     * 就像简单的除零异常，以及字符串转数字异常，本身异常堆栈就会提供基本的信息，但是我如果在一个用户交互的环境下，加入我们想要知道是哪个用户的输入到导致了异常的产生，这个时候系统产生的异常堆栈信息就不能满足我们的要求。这个时候我们就要主动捕获异常个然后打印出我们想要的信息。
* 异常的处理
   * 对认为一定不会出现的异常，如果出现了异常那我们直接抛出Error类，表示应该立即停止程序并重启解决问题。
   * 对假定不应该出现的异常，抛出运行时异常。（抛出异常好像不需要写异常情况的return）
   * 对假定一定出现异常的情况。这种代码你也好意思写，你要脸吗？
* 异常的一些特殊情况
   * 防止异常丢失，当我们在finally的情况中写了return时，异常就会丢失。
   * 线程中ThreadDeath异常，这个异常出现时会终结线程。如果捕获了这个异常必须抛出，不然会导致本该死亡的线程继续运行下去。
* try catch 对程序性能的影响
  * 会阻止Java对try块的代码的一些优化，例如重排序。
  * 当异常真正发生的时候会去查找catch中有没有对应的异常，有的话处理返回，没有的话拷贝异常的引用返回。
- - -
