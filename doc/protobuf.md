## proto buffers

Protocol Buffers - Google's data interchange format

---

### rmi: remote method invocation 

局限：只能在java代码中实现

远程方法调用都会涉及到代码生成（序列化和反序列化）

client中称之为 stub；server中称为skeleton

序列化和反序列化也成为：编码解码

### RPC remote Procedure Call

跨语言

1. 定义一个结构说明文件：描述对象（结构体）、对象成员、接口方法等一系列信息
2. 通过RPC框架所提供的编译器，将接口说明文件编译成具体的语言文件
3. 在客户端和服务器端分别引入RPC编译器所生成的文件，即可像调用本地方法一样调用远程方法

### protobuf

protobuf的测试执行了以下命令，用于生成java实体类

windows:

```shell script
protoc --java_out=.\src\main\java  .\src\main\resources\protobuf\Student.proto
```

macos/linux:

```shell script
protoc --java_out=./src/main/java  ./src/main/resources/protobuf/Student.proto
```