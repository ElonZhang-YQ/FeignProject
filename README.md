#  SpringBoot3为基础的微服务框架

## 技术使用

- SpringBoot3
  - 使用SpringBoot搭建了一个简单的微服务框架，只有两个模块。不过这两个模块可以完成大部分微服务的功能。
- MySQL
  - 配置在本地虚拟机中的MySQL。
- Redis
  - 配置在本地虚拟机中的Redis，主要有两个功能：
    - 实现缓存
    - 实现分布式ID
- SpringCloudOpenFeign
  - 微服务之间通过OpenFeign进行REST模式的通信
- RabbitMQ
  - 简单的通信功能，用户注册成功，登录成功还有修改成功会进行广播通信。

后续的下个项目，会添加Nginx作为限流功能。然后mysql和redis会添加集群。
