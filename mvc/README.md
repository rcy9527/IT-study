 自己实现mvc的基本步骤

1.  从web.xml的 <servlet>里面获取需要进行扫描包的路径，

2. 从扫描的路径里获取被@Controller 和@Service标记的类，把全限定类名放入一个List 里

3. 这些类通过List反射实例化然后放入​①HashMap（instanceMaps）里，把注解的value或者类名作为key,对象引用作为value

4. 在这些被注解标记的类里扫描，获得该类里的所有类成员变量，包括私有变量

5. 查看这些变量是否有@Autowired标记的类成员变量， 有的话根据类型，从①里找到相应的实例（被@Service标记），然后注入这个类成员变量

6. 在①里面找到@Controller标记的类 ，获得@RequestMapping 标记的方法，

   以@RequestMapping的url 为key   以被@RequestMapping标记的方法method为value,存入一个②HashMap(handlerMaps)，

   以@RequestMapping的url 为key   以被 写入一个 ③HashMap(controllerMaps)，

   



当一个请求发送过来时：

1. 获取请求的url ,根据url  在②HashMap(handlerMaps)，获取执行的方法，如果没有返回404.

2. 根据handlerMaps 的value  获取执行方法的参数列表（JDK1.8新特性）。

    根据形参类型，先将请求里的Request和Response的实参放入参数列表，

    根据形参变量名称获取实参，如果形参被@RequestParam标记，就以注解的value获取实参

    然后将实参类型转换为形参类型，通过反射方法method.invoke(),通过controllerMaps 以url为key获取要执行的类， 将上面的参数列表入参执行方法。
