# springCloudAlibaba-MyExample
# springCloudAlibaba简单搭建例子
# 项目介绍
# 搭建了SpringCloudAlibaba的基本框架（为了方便没有搭建聚合工程，都是单独的项目）
# 1.部署搭建Nacos服务，可根据官方文档进行搭建https://nacos.io/zh-cn/docs/quick-start.html
# 2.引入OpenFeign，使用注解进行服务调用，参考官方文档https://spring.io/projects/spring-cloud-openfeign#overview
# 3.引入Sentinel，作为流量控制组件，可以通过sentinel客户端界面配置流控熔断等参数，可以通过Sentinel Starter内置类FlowRuleManager.loadRules()用来使用代码设置（例子在ArticleController的initFlowRule方法）,可以通过Ncaos持久化配置
# 参考https://sentinelguard.io/zh-cn/docs/parameter-flow-control.html
# 4.引入gateway网关，通过yml配置路由转发
# 5.使用Nacos作为配置中心，引入spring-cloud-starter-alibaba-nacos-config依赖，在resource目录新建bootstrap.yml配置Nacos客户端上配置的相关信息如配置共享属性等，就可以扔掉application.yml了
# 6.本项目没有搭建前端项目交互，都是通过postman进行接口测试，或者使用IDEA的RestfulTool插件进行测试
# 7.拉取项目启动时需在配置文件中数据库和Nacos地址
